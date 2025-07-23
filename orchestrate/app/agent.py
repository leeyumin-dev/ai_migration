import openai
from langchain.chat_models import ChatOpenAI
from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
from langgraph.graph import StateGraph, START, END
from typing import TypedDict
from producer import MessageProducer
from domain import ToTranslator, ToAuditor
from dataclasses import asdict

producer = MessageProducer()

class State(TypedDict):
    message: str

def call_agent(request):
    '''
    request: {"id":1,"agentName":"TRANSLATOR"}
    '''
    if request['agentName'] == 'TRANSLATOR':
        producer.send_message('translator-topic', asdict(ToTranslator(id=request['id'])))
    elif request['agentName'] == 'AUDITOR':
        producer.send_message('audithor-topic', asdict(ToAuditor(id=request['id'])))








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
    # graph = build_agent()
    # graph.invoke({'message': 'test'})
    call_agent({"id":1,"agentName":"TRANSLATOR"})