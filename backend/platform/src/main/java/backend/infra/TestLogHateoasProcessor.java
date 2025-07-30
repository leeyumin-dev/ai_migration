package backend.infra;

import backend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class TestLogHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<TestLog>> {

    @Override
    public EntityModel<TestLog> process(EntityModel<TestLog> model) {
        return model;
    }
}
