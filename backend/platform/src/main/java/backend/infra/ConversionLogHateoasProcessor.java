package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConversionLogHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ConversionLog>> {

    @Override
    public EntityModel<ConversionLog> process(
        EntityModel<ConversionLog> model
    ) {
        return model;
    }
}
