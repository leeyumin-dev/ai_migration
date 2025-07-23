import openai
from langchain.chat_models import ChatOpenAI
from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
from langgraph.graph import StateGraph, START, END
from typing import TypedDict
from fastapi import FastAPI, Request
from agent import build_agent

app = FastAPI()
agent = build_agent()

'''
kafka로 바꿀 예정
'''
@app.post("/invoke")
async def invoke():
    # 실제 구현 시 invoke에 넣을 state를 api로 받아와야됨
    result = agent.invoke({'message': 'test'})  # LangGraph 기반 agent의 invoke
    return result