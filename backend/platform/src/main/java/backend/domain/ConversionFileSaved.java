package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ConversionFileSaved extends AbstractEvent {

    private Long id;

    public ConversionFileSaved(Conversion aggregate) {
        super(aggregate);
    }

    public ConversionFileSaved() {
        super();
    }
}
//>>> DDD / Domain Event
