package backend.infra;

import backend.config.kafka.KafkaProcessor;
import backend.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    TokenRepository tokenRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TokenRequested'"
    )
    public void wheneverTokenRequested_RequestTokenPolicy(
        @Payload TokenRequested tokenRequested
    ) {
        TokenRequested event = tokenRequested;
        System.out.println(
            "\n\n##### listener RequestTokenPolicy : " + tokenRequested + "\n\n"
        );

        // Sample Logic //
        Token.requestTokenPolicy(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
