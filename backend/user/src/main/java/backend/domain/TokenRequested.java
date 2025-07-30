package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TokenRequested extends AbstractEvent {

    private Long id;

    public TokenRequested(User aggregate) {
        super(aggregate);
    }

    public TokenRequested() {
        super();
    }
}
//>>> DDD / Domain Event
