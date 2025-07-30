package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ConversionFileDownloaded extends AbstractEvent {

    private Long id;

    public ConversionFileDownloaded(Conversion aggregate) {
        super(aggregate);
    }

    public ConversionFileDownloaded() {
        super();
    }
}
//>>> DDD / Domain Event
