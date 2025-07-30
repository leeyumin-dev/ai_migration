package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ConversionResSaved extends AbstractEvent {

    private Long id;

    public ConversionResSaved() {
        super();
    }
}
//>>> DDD / Domain Event
