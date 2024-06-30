package ru.aao.visualizationservice.service;


import ru.aao.visualizationservice.model.Location;

import java.util.List;

public interface LocationService {
    Location save(Location location);
    List<Location> findAll();
    Location findById(Long id);
    List<Location> findAllById(Long id);
}
