package ru.aao.consumerservice.service;

import ru.aao.consumerservice.domain.Location;

import java.util.List;

public interface LocationService {
    Location save(Location location);
    List<Location> findAll();
}
