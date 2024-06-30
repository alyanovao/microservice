package ru.aao.visualizationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aao.visualizationservice.model.Location;
import ru.aao.visualizationservice.model.LocationYandex;
import ru.aao.visualizationservice.model.LocationListResponse;
import ru.aao.visualizationservice.model.LocationResponse;
import ru.aao.visualizationservice.service.LocationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visualization")
public class QueryController
{
    private final LocationService locationService;

    @GetMapping("/getLocationByDeviceId")
    public LocationResponse getLocationByDeviceId(@RequestParam Long deviceId) {
        Location location = locationService.findById(deviceId);
        return new LocationResponse(deviceId, "TestDevice", location.getLatitude(), location.getLongitude());
    }

    @GetMapping("/getLocations")
    public LocationListResponse getLocations(@RequestParam Long deviceId) {
        val res = locationService.findAllById(deviceId);
        val locations = res.stream().map(location -> new LocationYandex(location.getLatitude(), location.getLongitude())).toList();
        return new LocationListResponse(deviceId, "TestDevice", locations);
    }
}
