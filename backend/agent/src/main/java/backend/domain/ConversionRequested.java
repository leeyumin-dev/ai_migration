package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ConversionRequested extends AbstractEvent {

    private Long userId;
    private String filePath;
    private String conversionType;
    private String inputeGovFrameVer;
    private String outputeGovFrameVer;
    private Boolean isTestCode;

    public ConversionRequested(Agent aggregate) {
        super(aggregate);
    }

    public ConversionRequested() {
        super();
    }
}
//>>> DDD / Domain Event
