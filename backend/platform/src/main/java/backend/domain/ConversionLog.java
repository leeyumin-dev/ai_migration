package backend.domain;

import backend.PlatformApplication;
import backend.domain.ConversionLogFetched;
import backend.domain.ConversionLogSaved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ConversionLog_table")
@Data
//<<< DDD / Aggregate Root
public class ConversionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        ConversionLogFetched conversionLogFetched = new ConversionLogFetched(
            this
        );
        conversionLogFetched.publishAfterCommit();

        ConversionLogSaved conversionLogSaved = new ConversionLogSaved(this);
        conversionLogSaved.publishAfterCommit();
    }

    public static ConversionLogRepository repository() {
        ConversionLogRepository conversionLogRepository = PlatformApplication.applicationContext.getBean(
            ConversionLogRepository.class
        );
        return conversionLogRepository;
    }

    //<<< Clean Arch / Port Method
    public static void saveConversionLogPolicy(
        ConversionResSaved conversionResSaved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ConversionLog conversionLog = new ConversionLog();
        repository().save(conversionLog);

        ConversionLogSaved conversionLogSaved = new ConversionLogSaved(conversionLog);
        conversionLogSaved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(conversionResSaved.get???()).ifPresent(conversionLog->{
            
            conversionLog // do something
            repository().save(conversionLog);

            ConversionLogSaved conversionLogSaved = new ConversionLogSaved(conversionLog);
            conversionLogSaved.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
