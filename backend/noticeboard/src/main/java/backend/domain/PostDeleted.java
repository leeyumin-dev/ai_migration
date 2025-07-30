package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PostDeleted extends AbstractEvent {

    private Long id;

    public PostDeleted(Post aggregate) {
        super(aggregate);
    }

    public PostDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
