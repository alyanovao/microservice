package ru.aao.visualizationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aao.visualizationservice.model.Location;
import ru.aao.visualizationservice.model.LocationListResponse;
import ru.aao.visualizationservice.model.LocationResponse;

import java.util.Arrays;

@RestController
@RequestMapping("/api/visualization")
public class QueryController
{
    @GetMapping("/getLocationByDeviceId")
    public LocationResponse getLocationByDeviceId(@RequestParam Long deviceId) {
        return new LocationResponse(1, "TestDevice", 55.76, 37.64);
    }

    @GetMapping("/getLocations")
    public LocationListResponse getLocations(@RequestParam Long deviceId) {
        return new LocationListResponse(1, "TestDevice",
                Arrays.asList(new Location(55.76, 37.64),
                        new Location(55.671, 37.641)));
    }
}
