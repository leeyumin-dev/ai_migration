package backend.domain;

import backend.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "agents", path = "agents")
public interface AgentRepository
    extends PagingAndSortingRepository<Agent, Long> {}
