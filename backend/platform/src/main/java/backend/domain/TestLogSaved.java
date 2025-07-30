package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TestLogSaved extends AbstractEvent {

    private Long id;

    public TestLogSaved(TestLog aggregate) {
        super(aggregate);
    }

    public TestLogSaved() {
        super();
    }
}
//>>> DDD / Domain Event
