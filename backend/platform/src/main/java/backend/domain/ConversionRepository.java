package backend.domain;

import backend.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "conversions",
    path = "conversions"
)
public interface ConversionRepository
    extends PagingAndSortingRepository<Conversion, Long> {}
