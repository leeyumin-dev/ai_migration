package backend.domain;

import backend.domain.*;
import backend.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ConversionResSaved extends AbstractEvent {

    private Long id;
}
