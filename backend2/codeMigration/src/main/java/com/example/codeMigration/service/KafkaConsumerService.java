package com.example.codeMigration.service;

import com.example.codeMigration.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    @KafkaListener(topics = "response-topic", groupId = "group1")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}