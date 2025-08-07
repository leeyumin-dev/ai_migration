from langchain_community.document_loaders import TextLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain_community.vectorstores import FAISS
from langchain_community.embeddings import OpenAIEmbeddings
from dotenv import load_dotenv
import os

#Vector DB 저장 코드(FAISS)

load_dotenv()
OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")

docs_dir = "examples/version/4.x"
documents = []

# ✅ 수집된 자바 파일 로드
for file in os.listdir(docs_dir):
    if file.endswith(".java"):
        path = os.path.join(docs_dir, file)
        loader = TextLoader(path, encoding="utf-8")
        documents.extend(loader.load())

# ✅ 문서 분할 및 임베딩
splitter = RecursiveCharacterTextSplitter(chunk_size=1000, chunk_overlap=100)
split_docs = splitter.split_documents(documents)

embedding = OpenAIEmbeddings(openai_api_key=OPENAI_API_KEY)
vector_db = FAISS.from_documents(split_docs, embedding)
vector_db.save_local("version_vector_store")

print("✅ 벡터 저장 완료: version_vector_store/")
