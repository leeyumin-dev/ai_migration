package com.example.codeMigration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long id; // user의 고유 번호
    private AgentName agentName;

    public enum AgentName { // 실행할 에이전트
        TRANSLATOR,
        AUDITOR,
        CHATBOT,
        TEST // 통신 테스트용 -> 삭제예정
    }
}