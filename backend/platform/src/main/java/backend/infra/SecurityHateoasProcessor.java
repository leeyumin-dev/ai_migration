package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class SecurityHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Security>> {

    @Override
    public EntityModel<Security> process(EntityModel<Security> model) {
        return model;
    }
}
