package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SecurityReportDownloaded extends AbstractEvent {

    private Long id;

    public SecurityReportDownloaded(Security aggregate) {
        super(aggregate);
    }

    public SecurityReportDownloaded() {
        super();
    }
}
//>>> DDD / Domain Event
