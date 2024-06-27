package ru.aao.visualizationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aao.visualizationservice.exception.ApplicationException;
import ru.aao.visualizationservice.model.Location;
import ru.aao.visualizationservice.repository.LocationRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long id) {
        var res = locationRepository.findAllByClientId(id);
        return res.stream().max(Comparator.comparingLong(Location::getClientId)).orElseThrow(() -> new ApplicationException("Данные не найдены"));
    }

    @Override
    public List<Location> findAllById(Long id) {
        return locationRepository.findAllByClientId(id);
    }
}
