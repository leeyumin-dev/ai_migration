from analyzer.ast_parser import ASTAnalyzer
from analyzer.structure_mapper import StructureMapper
from analyzer.prompt_builder import PromptBuilder
import faiss, json, numpy as np, pandas as pd
from openai import OpenAI
import tiktoken
from analyzer.extract_code_block import extract_code_block
from analyzer.framework_detector import FrameworkDetector
from analyzer.dependency_filter import DependencyFilter
from analyzer.external_usage_detector import ExternalUsageDetector
from analyzer.comment_analyzer import CommentAnalyzer
from analyzer.directory_analyzer import DirectoryStructureAnalyzer

MAX_TOKENS = 8192
EMBED_MODEL = "text-embedding-3-small"
TOP_K = 4

def run_pipeline_with_rag(python_code: str, api_key: str):
    tokenizer = tiktoken.encoding_for_model(EMBED_MODEL)
    def truncate(text):
        tokens = tokenizer.encode(text)
        return tokenizer.decode(tokens[:MAX_TOKENS]) if len(tokens) > MAX_TOKENS else text

    index = faiss.read_index("data/egov_code_index.faiss")
    metadata_df = pd.read_pickle("data/egov_metadata.pkl")
    with open("data/egov_code_segments.jsonl", "r", encoding="utf-8") as f:
        code_dict = {json.loads(line)["path"]: json.loads(line)["code"] for line in f}

    analyzer = ASTAnalyzer(python_code)
    functions = analyzer.extract_functions()
    mapper = StructureMapper()
    prompter = PromptBuilder()
    client = OpenAI(api_key=api_key)

    for func in functions:
        func["role"] = mapper.infer_role(func)
        prompt = prompter.build_prompt(func)

        path_meta = func.get("path") or "src/main/java/egovframework/com/cop/bbs/web/EgovBBSController.java"
        dir_info = DirectoryStructureAnalyzer(path_meta).analyze()


        query_emb = client.embeddings.create(input=func["body"], model=EMBED_MODEL).data[0].embedding
        D, I = index.search(np.array([query_emb], dtype="float32"), k=TOP_K)
        examples = [f"{metadata_df.iloc[i]['title']}:\n{code_dict[metadata_df.iloc[i]['path']]}" for i in I[0]]
        fewshot_block = "\n\n".join(examples)
        full_prompt = f"{prompt}\n\n다음은 참고할 Java 코드 예시입니다:\n\n{fewshot_block}\n\n변환된 Java 코드를 보여줘."

        framework_info = FrameworkDetector(func["body"]).detect()
        external_apis = ExternalUsageDetector(func["body"]).detect()
        comments = CommentAnalyzer(func["body"]).detect()

        res = client.chat.completions.create(
            model="gpt-4",
            messages=[{"role": "user", "content": full_prompt}],
            temperature=0.2
        )
        output = res.choices[0].message.content
        code_only = extract_code_block(output, language="java")
        print(f"🔹 Function: {func['name']} ({func['role']})")
        print(f"Detected imports  : {framework_info}")
        print(f"External systems  : {external_apis}")
        print(f"Comments          : {[c['comment'] for c in comments if c['function'] == func['name']]}")
        print(f"Domain            : {dir_info['domain']}")
        print(f"Feature           : {dir_info['feature']}")
        print(f"Layer             : {dir_info['layer']}")
        print(f"Type              : {dir_info['type']}")
        print("Java 변환 결과:")
        print(code_only)

