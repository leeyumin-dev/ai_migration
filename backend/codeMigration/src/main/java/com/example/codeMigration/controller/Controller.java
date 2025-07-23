package com.example.codeMigration.controller;

import com.example.codeMigration.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.codeMigration.service.KafkaProducerService;

@RequiredArgsConstructor
@RestController
public class Controller {
    private final KafkaProducerService producerService;
    String topicName = "api-topic";

    @PostMapping("/test")
    public ResponseEntity<?> sendMessage(@RequestBody RequestDto requestDto) {
        producerService.sendMessage("test_topic", requestDto);
        System.out.println("send Message");
        return ResponseEntity.ok("메시지 전송 완료");
    }

    @PostMapping("/translate")
    public ResponseEntity<?> codeTranslate(@RequestBody RequestDto requestDto) {
        producerService.sendMessage(topicName, requestDto);
        System.out.println("변환 요청");
        return ResponseEntity.ok("변환 요청 완료");
    }

    @PostMapping("/security")
    public ResponseEntity<?> documentSecurity(@RequestBody RequestDto requestDto) {
        producerService.sendMessage(topicName, requestDto);
        System.out.println("send Message");
        return ResponseEntity.ok("메시지 전송 완료");
    }

//    @PostMapping("/chatbot")
//    public ResponseEntity<?> chatBot() {
//        producerService.sendMessage(topicName, new RequestDto(1L));
//        System.out.println("send Message");
//        return ResponseEntity.ok("메시지 전송 완료");
//    }
}
