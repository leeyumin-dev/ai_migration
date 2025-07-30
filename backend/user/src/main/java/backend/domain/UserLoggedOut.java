package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserLoggedOut extends AbstractEvent {

    private Long id;

    public UserLoggedOut(User aggregate) {
        super(aggregate);
    }

    public UserLoggedOut() {
        super();
    }
}
//>>> DDD / Domain Event
