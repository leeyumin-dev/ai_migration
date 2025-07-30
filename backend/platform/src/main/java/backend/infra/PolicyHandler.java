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
    ConversionRepository conversionRepository;

    @Autowired
    TestLogRepository testLogRepository;

    @Autowired
    ConversionLogRepository conversionLogRepository;

    @Autowired
    SecurityRepository securityRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConversionResSaved'"
    )
    public void wheneverConversionResSaved_SaveConversionFilePolicy(
        @Payload ConversionResSaved conversionResSaved
    ) {
        ConversionResSaved event = conversionResSaved;
        System.out.println(
            "\n\n##### listener SaveConversionFilePolicy : " +
            conversionResSaved +
            "\n\n"
        );

        // Sample Logic //
        Conversion.saveConversionFilePolicy(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConversionResSaved'"
    )
    public void wheneverConversionResSaved_SaveConversionLogPolicy(
        @Payload ConversionResSaved conversionResSaved
    ) {
        ConversionResSaved event = conversionResSaved;
        System.out.println(
            "\n\n##### listener SaveConversionLogPolicy : " +
            conversionResSaved +
            "\n\n"
        );

        // Sample Logic //
        ConversionLog.saveConversionLogPolicy(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConversionResSaved'"
    )
    public void wheneverConversionResSaved_SaveTestLogPolicy(
        @Payload ConversionResSaved conversionResSaved
    ) {
        ConversionResSaved event = conversionResSaved;
        System.out.println(
            "\n\n##### listener SaveTestLogPolicy : " +
            conversionResSaved +
            "\n\n"
        );

        // Sample Logic //
        TestLog.saveTestLogPolicy(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SecurityResSaved'"
    )
    public void wheneverSecurityResSaved_SaveSecurityReportPolicy(
        @Payload SecurityResSaved securityResSaved
    ) {
        SecurityResSaved event = securityResSaved;
        System.out.println(
            "\n\n##### listener SaveSecurityReportPolicy : " +
            securityResSaved +
            "\n\n"
        );

        // Sample Logic //
        Security.saveSecurityReportPolicy(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
