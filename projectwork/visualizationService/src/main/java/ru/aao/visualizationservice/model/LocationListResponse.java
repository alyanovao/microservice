package ru.aao.visualizationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationListResponse {
    private long deviceId;
    private String deviceName;
    List<LocationYandex> locations;
}
