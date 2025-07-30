package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostRegistered extends AbstractEvent {

    private Long id;

    public PostRegistered(Post aggregate) {
        super(aggregate);
    }

    public PostRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
