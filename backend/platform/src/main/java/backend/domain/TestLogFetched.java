package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TestLogFetched extends AbstractEvent {

    private Long id;

    public TestLogFetched(TestLog aggregate) {
        super(aggregate);
    }

    public TestLogFetched() {
        super();
    }
}
//>>> DDD / Domain Event
