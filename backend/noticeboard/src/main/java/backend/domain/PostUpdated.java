package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostUpdated extends AbstractEvent {

    private Long id;

    public PostUpdated(Post aggregate) {
        super(aggregate);
    }

    public PostUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
