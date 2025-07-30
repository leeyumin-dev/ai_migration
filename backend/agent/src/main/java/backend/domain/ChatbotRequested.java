package backend.domain;

import backend.infra.AbstractEvent;
import lombok.Data;
import lombok.ToString;

//<<< DDD / Domain Event
@Data
@ToString
public class ChatbotRequested extends AbstractEvent {

    private Long id;

    public ChatbotRequested(Agent aggregate) {
        super(aggregate);
    }

    public ChatbotRequested() {
        super();
    }
}
//>>> DDD / Domain Event
