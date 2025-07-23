import openai
from langchain.chat_models import ChatOpenAI
from langchain_core.messages import SystemMessage, AIMessage, HumanMessage
from langgraph.graph import StateGraph, START, END
from typing import TypedDict
from fastapi import FastAPI, Request
import json
from confluent_kafka import Producer, Consumer
from consumer import MessageConsumer

if __name__ == '__main__':
    consumer = MessageConsumer()
    consumer.consume()
