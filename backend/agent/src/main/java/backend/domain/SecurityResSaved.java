package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SecurityResSaved extends AbstractEvent {

    private Long id;

    public SecurityResSaved() {
        super();
    }
}
//>>> DDD / Domain Event
