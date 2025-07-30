package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AgentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Agent>> {

    @Override
    public EntityModel<Agent> process(EntityModel<Agent> model) {
        return model;
    }
}
