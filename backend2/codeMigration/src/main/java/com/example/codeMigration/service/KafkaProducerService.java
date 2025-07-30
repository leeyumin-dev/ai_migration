package com.example.codeMigration.service;

import com.example.codeMigration.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, RequestDto> kafkaTemplate;

    public void sendMessage(String topic, RequestDto requestDto) {
        kafkaTemplate.send(topic, requestDto);
    }
}