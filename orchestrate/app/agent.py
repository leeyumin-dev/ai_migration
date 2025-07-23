import openai
from langchain.chat_models import ChatOpenAI
from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
from langgraph.graph import StateGraph, START, END
from typing import TypedDict
import requests

class State(TypedDict):
    message: str

def call_agent(request):
    '''
    request: {"id":1,"agentName":"TRANSLATOR"}
    에이전트 호출 -> 비동기 통신을 위해 kafka 사용으로 바꿀 예정
    '''
    # if request['agentName'] == 'TRANSLATOR':
    #     print('보냄')
    #     url = 'http://translator-agent:8001/invoke'
    #     # url = 'http://translator-agent.default.svc.cluster.local/invoke'
    #     response = requests.post(url, json={})
    #     print(response)
    # elif request['agentName'] == 'AUDITOR':
    #     url = 'http://auditor-agent.default.svc.cluster.local/invoke'
    #     response = requests.post(url, json={})









######################################################################################
def test_node(state: State):
    print(state)
    return {'message': '요청 완료'}

# 그래프 생성
def build_agent():
    builder = StateGraph(State)

    builder.add_node("orchestrator", test_node)

    builder.add_edge(START, 'orchestrator')
    builder.add_edge('orchestrator', END)

    graph = builder.compile()
    return graph

if __name__ == '__main__':
    graph = build_agent()
    graph.invoke({'message': 'test'})