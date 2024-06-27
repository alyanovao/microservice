package ru.aao.visualizationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.aao.visualizationservice.model.Location;

import java.util.List;

public interface LocationRepository extends MongoRepository<Location, Long> {
    @Query("{clientId: ?0}")
    List<Location> findAllByClientId(Long clientId);
}
