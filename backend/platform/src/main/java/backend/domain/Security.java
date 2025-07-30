package backend.domain;

import backend.PlatformApplication;
import backend.domain.SecurityReportDownloaded;
import backend.domain.SecurityReportSaved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Security_table")
@Data
//<<< DDD / Aggregate Root
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        SecurityReportDownloaded securityReportDownloaded = new SecurityReportDownloaded(
            this
        );
        securityReportDownloaded.publishAfterCommit();

        SecurityReportSaved securityReportSaved = new SecurityReportSaved(this);
        securityReportSaved.publishAfterCommit();
    }

    public static SecurityRepository repository() {
        SecurityRepository securityRepository = PlatformApplication.applicationContext.getBean(
            SecurityRepository.class
        );
        return securityRepository;
    }

    //<<< Clean Arch / Port Method
    public static void saveSecurityReportPolicy(
        SecurityResSaved securityResSaved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Security security = new Security();
        repository().save(security);

        SecurityReportSaved securityReportSaved = new SecurityReportSaved(security);
        securityReportSaved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(securityResSaved.get???()).ifPresent(security->{
            
            security // do something
            repository().save(security);

            SecurityReportSaved securityReportSaved = new SecurityReportSaved(security);
            securityReportSaved.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
