package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SecurityReportSaved extends AbstractEvent {

    private Long id;

    public SecurityReportSaved(Security aggregate) {
        super(aggregate);
    }

    public SecurityReportSaved() {
        super();
    }
}
//>>> DDD / Domain Event
