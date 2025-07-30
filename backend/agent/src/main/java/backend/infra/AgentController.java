package backend.infra;

import backend.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/agents")
@Transactional
public class AgentController {

    @Autowired
    AgentRepository agentRepository;

    @PostMapping("/conversion")
    public ResponseEntity<?> conversion(@RequestBody Agent agent) {
        System.out.println("변환 요청");
        agent.conversionEvent();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/security")
    public ResponseEntity<?> security(@RequestBody Agent agent) {
        System.out.println("보안 분석 요청");
        agent.securityEvent();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/chatbot")
    public ResponseEntity<?> chatbot(@RequestBody Agent agent) {
        System.out.println("챗봇 요청");
        agent.chatbotEvent();
        return ResponseEntity.ok().build();
    }
}
//>>> Clean Arch / Inbound Adaptor
