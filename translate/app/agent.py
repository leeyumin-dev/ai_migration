import openai
from langchain.chat_models import ChatOpenAI
from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
from langgraph.graph import StateGraph, START, END
from typing import TypedDict

class State(TypedDict):
    message: str

def test_node(state: State):
    print(state)
    return {'message': '변환 완료'}

# 그래프 생성
def build_agent():
    builder = StateGraph(State)

    builder.add_node("translator", test_node)

    builder.add_edge(START, 'translator')
    builder.add_edge('translator', END)

    graph = builder.compile()
    return graph

if __name__ == '__main__':
    graph = build_agent()
    graph.invoke({'message': 'test'})