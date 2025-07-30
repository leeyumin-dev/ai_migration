package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class TokenHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Token>> {

    @Override
    public EntityModel<Token> process(EntityModel<Token> model) {
        return model;
    }
}
