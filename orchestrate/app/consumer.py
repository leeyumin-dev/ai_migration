from dotenv import load_dotenv
import os
from confluent_kafka import Consumer, KafkaException
import json
from log import Logger
from agent import call_agent, build_agent
load_dotenv()

class MessageConsumer:
    def __init__(self):
        self.logger = Logger(name='consumer').logger
        self.broker = os.environ.get('KAFKA_SERVER')
        self.topic = os.environ.get('CONS_TOPIC')
        self.group_id = os.environ.get('GROUP_ID')
        self.auto_offset_reset = os.environ.get('AUTO_OFFSET_RESET')
        self.consumer = Consumer({
                                    'bootstrap.servers': self.broker,
                                    'group.id': self.group_id, # consumer의 id
                                    'auto.offset.reset': self.auto_offset_reset  # 처음 실행시 가장 마지막 offset부터
                                })
        self.consumer.subscribe([t for t in self.topic.split(',')])

    def consume(self):
        try:
            print(f"start consume: {self.topic}")
            while True:
                message = self.consumer.poll(1.0)
                if message is None:
                    continue
                if message.error():
                    print(f"Kafka error: {message.error()}")
                else:
                    print("Headers:")
                    if message.headers():
                        for header in message.headers():
                            key, value = header
                            print(f"  {key}: {value.decode('utf-8') if value else None}")
                            
                    self.handle_message(message)
        except KafkaException as e:
            self.logger.error(e)
        finally:
            self.consumer.close()

    def handle_message(self, message):
        try:
            request = json.loads(message.value().decode('utf-8'))

            self.logger.info(f"{message.topic()} | key: {message.key()} | value: {request}")

            if message.topic() == 'backend':
                call_agent(request)

        except Exception as e:
            self.logger.exception(e)

if __name__ == '__main__':
    consumer = MessageConsumer()
    consumer.consume()