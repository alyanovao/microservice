package ru.aao.consumerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aao.consumerservice.domain.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
}
