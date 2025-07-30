package backend.domain;

import backend.PlatformApplication;
import backend.domain.ConversionFileDownloaded;
import backend.domain.ConversionFileSaved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Conversion_table")
@Data
//<<< DDD / Aggregate Root
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        ConversionFileDownloaded conversionFileDownloaded = new ConversionFileDownloaded(
            this
        );
        conversionFileDownloaded.publishAfterCommit();

        ConversionFileSaved conversionFileSaved = new ConversionFileSaved(this);
        conversionFileSaved.publishAfterCommit();
    }

    public static ConversionRepository repository() {
        ConversionRepository conversionRepository = PlatformApplication.applicationContext.getBean(
            ConversionRepository.class
        );
        return conversionRepository;
    }

    //<<< Clean Arch / Port Method
    public static void saveConversionFilePolicy(
        ConversionResSaved conversionResSaved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Conversion conversion = new Conversion();
        repository().save(conversion);

        ConversionFileSaved conversionFileSaved = new ConversionFileSaved(conversion);
        conversionFileSaved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(conversionResSaved.get???()).ifPresent(conversion->{
            
            conversion // do something
            repository().save(conversion);

            ConversionFileSaved conversionFileSaved = new ConversionFileSaved(conversion);
            conversionFileSaved.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
