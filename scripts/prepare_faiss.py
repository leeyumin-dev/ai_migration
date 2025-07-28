import requests, json, faiss, numpy as np, pandas as pd, tiktoken
from tqdm import tqdm
from openai import OpenAI

OWNER = "eGovFramework"
REPO = "egovframe-common-components"
BRANCH = "main"
TARGET_DIR = "src/main/java"
MAX_TOKENS = 8192
EMBED_MODEL = "text-embedding-3-small"
client = OpenAI(api_key="")  # 본인 키

def classify_path_info(path):
    segments = path.split('/')
    domain = segments[5] if len(segments) > 5 else "unknown"
    feature = segments[6] if len(segments) > 6 and not segments[6].endswith(".java") else "common"
    if "controller" in path.lower() or "web" in path.lower():
        component = "Controller"
    elif "serviceimpl" in path.lower() or "impl" in path.lower():
        component = "ServiceImpl"
    elif "service" in path.lower():
        component = "Service"
    elif "dao" in path.lower():
        component = "DAO"
    elif "vo" in path.lower():
        component = "VO"
    elif "mapper" in path.lower():
        component = "Mapper"
    elif "handler" in path.lower() or "hndlr" in path.lower():
        component = "Handler"
    else:
        component = "Other"
    return domain, feature, component

def fetch_file_list():
    url = f"https://api.github.com/repos/{OWNER}/{REPO}/git/trees/{BRANCH}?recursive=1"
    res = requests.get(url)
    res.raise_for_status()
    data = res.json()
    return [f for f in data["tree"] if f["path"].startswith(TARGET_DIR) and f["path"].endswith(".java")]

def save_java_files():
    java_files = fetch_file_list()
    raw_base = f"https://raw.githubusercontent.com/{OWNER}/{REPO}/{BRANCH}"
    with open("data/egov_code_segments.jsonl", "w", encoding="utf-8") as out_f:
        for f in java_files:
            rel_path = f["path"]
            filename = rel_path.split("/")[-1]
            raw_url = f"{raw_base}/{rel_path}"
            domain, feature, component = classify_path_info(rel_path)
            try:
                code = requests.get(raw_url).text.strip()
                entry = {
                    "title": filename.replace(".java", ""),
                    "path": rel_path,
                    "domain": domain,
                    "feature": feature,
                    "type": component,
                    "code": code
                }
                out_f.write(json.dumps(entry, ensure_ascii=False) + "\n")
            except Exception as e:
                print(f"⚠️ Error loading {raw_url}: {e}")

def build_index():
    tokenizer = tiktoken.encoding_for_model(EMBED_MODEL)
    documents, metadatas = [], []
    with open("data/egov_code_segments.jsonl", "r", encoding="utf-8") as f:
        for line in f:
            entry = json.loads(line)
            text = f"{entry['title']}: {entry['code']}"
            tokens = tokenizer.encode(text)
            if len(tokens) > MAX_TOKENS:
                text = tokenizer.decode(tokens[:MAX_TOKENS])
            documents.append(text)
            metadatas.append({
                "title": entry["title"],
                "path": entry["path"],
                "type": entry["type"],
                "domain": entry["domain"],
                "feature": entry.get("feature", "unknown")
            })

    embeddings = []
    for doc in tqdm(documents, desc="🔄 Generating embeddings"):
        response = client.embeddings.create(input=doc, model=EMBED_MODEL)
        embeddings.append(response.data[0].embedding)

    index = faiss.IndexFlatL2(len(embeddings[0]))
    index.add(np.array(embeddings).astype("float32"))
    faiss.write_index(index, "data/egov_code_index.faiss")

    pd.DataFrame(metadatas).to_pickle("data/egov_metadata.pkl")

if __name__ == "__main__":
    save_java_files()
    build_index()
    print("✅ FAISS 인덱스 생성 완료!")
