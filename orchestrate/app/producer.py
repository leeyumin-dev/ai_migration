from dotenv import load_dotenv
import os
from confluent_kafka import Producer, KafkaException
import json
from log import Logger
from agent import call_agent

load_dotenv()

class MessageProducer:
    def __init__(self):
        self.logger = Logger(name='producer').logger
        self.broker = os.environ.get('KAFKA_SERVER')
        self.topic = os.environ.get('PROD_TOPIC')
        self.producer = Producer({
                                    'bootstrap.servers': self.broker,
                                })
    
    def delivery_callback(self, err, msg):
        if err:
            self.logger.error('ERROR: Message failed delivery: {}'.format(err))
        else:
            self.logger.info("Produced event to topic {topic}: key = {key:12} value = {value:12}".format(
                topic=msg.topic(), key=msg.key().decode('utf-8'), value=msg.value().decode('utf-8')))
            
    def send_message(self, message):
        self.producer.produce(self.topic, value=message)
        # self.producer.produce(self.topic, value=message, callback=self.delivery_callback)
        self.producer.flush()

if __name__ == '__main__':
    producer = MessageProducer()
    producer.send_message("test")
