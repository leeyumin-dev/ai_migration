package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConversionHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Conversion>> {

    @Override
    public EntityModel<Conversion> process(EntityModel<Conversion> model) {
        return model;
    }
}
