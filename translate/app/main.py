# import openai
# from langchain.chat_models import ChatOpenAI
# from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
# from langgraph.graph import StateGraph, START, END
# from typing import TypedDict
# from fastapi import FastAPI, Request
# import json
# from confluent_kafka import Producer, Consumer
# from consumer import MessageConsumer
#
# if __name__ == '__main__':
#     consumer = MessageConsumer()
#     consumer.consume()
#

# app = FastAPI()
# agent = build_agent()

# '''
# kafka로 바꿀 예정
# '''
# @app.post("/invoke")
# async def invoke():
#     # 실제 구현 시 invoke에 넣을 state를 api로 받아와야됨
#     result = agent.invoke({'message': 'test'})  # LangGraph 기반 agent의 invoke
#     return result


# translate/app/main.py

from dotenv import load_dotenv
import os
from .transformer import run_pipeline_with_rag

load_dotenv()  # .env 로부터 API 키 불러오기

if __name__ == "__main__":
    with open("data/example_code.py", "r", encoding="utf-8") as f:
        python_code = f.read()

    run_pipeline_with_rag(python_code, os.getenv("OPENAI_API_KEY"))