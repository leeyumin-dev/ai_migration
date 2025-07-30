package backend.domain;

import backend.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "postQueries",
    path = "postQueries"
)
public interface PostQueryRepository
    extends PagingAndSortingRepository<PostQuery, Long> {}
