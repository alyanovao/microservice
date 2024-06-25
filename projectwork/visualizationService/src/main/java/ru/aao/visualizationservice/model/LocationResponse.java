package ru.aao.visualizationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {
    private long deviceId;
    private String deviceName;
    private double latitude;
    private double longitude;
}