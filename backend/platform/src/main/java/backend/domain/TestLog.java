package backend.domain;

import backend.PlatformApplication;
import backend.domain.TestLogFetched;
import backend.domain.TestLogSaved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TestLog_table")
@Data
//<<< DDD / Aggregate Root
public class TestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        TestLogFetched testLogFetched = new TestLogFetched(this);
        testLogFetched.publishAfterCommit();

        TestLogSaved testLogSaved = new TestLogSaved(this);
        testLogSaved.publishAfterCommit();
    }

    public static TestLogRepository repository() {
        TestLogRepository testLogRepository = PlatformApplication.applicationContext.getBean(
            TestLogRepository.class
        );
        return testLogRepository;
    }

    //<<< Clean Arch / Port Method
    public static void saveTestLogPolicy(
        ConversionResSaved conversionResSaved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        TestLog testLog = new TestLog();
        repository().save(testLog);

        TestLogSaved testLogSaved = new TestLogSaved(testLog);
        testLogSaved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(conversionResSaved.get???()).ifPresent(testLog->{
            
            testLog // do something
            repository().save(testLog);

            TestLogSaved testLogSaved = new TestLogSaved(testLog);
            testLogSaved.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
