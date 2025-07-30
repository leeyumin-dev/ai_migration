package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserLoggedIn extends AbstractEvent {

    private Long id;

    public UserLoggedIn(User aggregate) {
        super(aggregate);
    }

    public UserLoggedIn() {
        super();
    }
}
//>>> DDD / Domain Event
