package backend.domain;

import backend.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "securities",
    path = "securities"
)
public interface SecurityRepository
    extends PagingAndSortingRepository<Security, Long> {}
