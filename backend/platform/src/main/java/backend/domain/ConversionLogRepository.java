package backend.domain;

import backend.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "conversionLogs",
    path = "conversionLogs"
)
public interface ConversionLogRepository
    extends PagingAndSortingRepository<ConversionLog, Long> {}
