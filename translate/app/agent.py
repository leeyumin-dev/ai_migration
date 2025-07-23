import openai
from langchain.chat_models import ChatOpenAI
from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
from langgraph.graph import StateGraph, START, END
from typing import TypedDict
from producer import MessageProducer

producer = MessageProducer()

class State(TypedDict):
    id: int

def test_node(state: State):
    print('test node:', state)
    return {'message': '변환완료 -> 추후 실제 개발 시 응답 포맷 변경'}

# 그래프 생성
def build_agent():
    builder = StateGraph(State)

    builder.add_node("translator", test_node)

    builder.add_edge(START, 'translator')
    builder.add_edge('translator', END)

    graph = builder.compile()
    return graph

def call_agent(request):
    '''
    state 초기화
    request: orchestrate.domain의 ToTranslator 포맷
    '''
    init_state = State(id=request['id'])
    graph = build_agent()
    graph.invoke(init_state)
    producer.send_message('agent-res-topic', {'result': '변환완료'})

if __name__ == '__main__':
    call_agent({'id': 300})
    # graph = build_agent()
    # graph.invoke({'id': 1000})