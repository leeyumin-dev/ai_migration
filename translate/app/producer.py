from dotenv import load_dotenv
import os
from confluent_kafka import Producer, KafkaException
import json
from log import Logger

load_dotenv()

class MessageProducer:
    def __init__(self):
        self.logger = Logger(name='producer').logger
        self.broker = os.environ.get('KAFKA_SERVER')
        self.producer = Producer({
                                    'bootstrap.servers': self.broker,
                                })
    
    def delivery_callback(self, err, msg):
        if err:
            self.logger.error('ERROR: Message failed delivery: {}'.format(err))
        else:
            self.logger.info(f"Produced event to {msg.topic()} | key: {msg.key()} | value: {json.loads(msg.value().decode('utf-8'))}")
            
    def send_message(self, topic, message: dict):
        message = json.dumps(message).encode('utf-8')
        self.producer.produce(topic, value=message, callback=self.delivery_callback)
        self.producer.flush()

if __name__ == '__main__':
    producer = MessageProducer()
    producer.send_message("test")
