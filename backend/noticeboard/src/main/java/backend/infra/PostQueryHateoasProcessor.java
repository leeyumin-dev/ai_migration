package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostQueryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PostQuery>> {

    @Override
    public EntityModel<PostQuery> process(EntityModel<PostQuery> model) {
        return model;
    }
}
