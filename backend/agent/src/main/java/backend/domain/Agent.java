package backend.domain;

import backend.AgentApplication;
import backend.domain.ConversionRequested;
import backend.domain.SecurityRequested;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Agent_table")
@Data
//<<< DDD / Aggregate Root
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String filePath;

    private String inputeGovFrameVer;

    private String outputeGovFrameVer;

    private Boolean isTestCode;

    private String conversionType;

    private String query;

//    @PostPersist
//    public void onPostPersist() {
//        ConversionRequested conversionRequested = new ConversionRequested(this);
//        conversionRequested.publishAfterCommit();
//
//        SecurityRequested securityRequested = new SecurityRequested(this);
//        securityRequested.publishAfterCommit();
//    }

    public static AgentRepository repository() {
        AgentRepository agentRepository = AgentApplication.applicationContext.getBean(
            AgentRepository.class
        );
        return agentRepository;
    }

    public void conversionEvent() {
        ConversionRequested conversionRequested = new ConversionRequested(this);
        conversionRequested.publishAfterCommit();
    }

    public void securityEvent() {
        SecurityRequested securityRequested = new SecurityRequested(this);
        securityRequested.publishAfterCommit();
    }

    public void chatbotEvent() {
        ChatbotRequested chatbotRequested = new ChatbotRequested(this);
        chatbotRequested.publishAfterCommit();
    }
}
//>>> DDD / Aggregate Root
