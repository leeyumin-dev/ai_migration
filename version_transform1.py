#구버전 자바를 신버전 자바로 바꾸게 해주는 기능

from dotenv import load_dotenv
import os
from langchain_community.vectorstores import FAISS
from langchain_community.embeddings import OpenAIEmbeddings
from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate

# 환경변수 로드 (.env에서 OPENAI_API_KEY)
load_dotenv()

# 벡터 DB 로드
embedding = OpenAIEmbeddings(openai_api_key=os.getenv("OPENAI_API_KEY"))
vector_db = FAISS.load_local("version_vector_store", embedding, allow_dangerous_deserialization=True)

# 프롬프트 템플릿 불러오기
with open("docs/prompt_version.txt", "r", encoding="utf-8") as f:
    template_str = f.read()

prompt_template = PromptTemplate.from_template(template_str)

# 변환 함수
def convert_to_egov_4x(java_code: str) -> str:
    similar_docs = vector_db.similarity_search(java_code, k=1)
    context = similar_docs[0].page_content if similar_docs else ""

    prompt = prompt_template.format(input_code=java_code, reference=context)
    llm = ChatOpenAI(temperature=0)
    output = llm.predict(prompt)

    return output.strip()

