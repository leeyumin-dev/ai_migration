package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ConversionLogFetched extends AbstractEvent {

    private Long id;

    public ConversionLogFetched(ConversionLog aggregate) {
        super(aggregate);
    }

    public ConversionLogFetched() {
        super();
    }
}
//>>> DDD / Domain Event
