package ru.aao.visualizationservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    private String id;

    private Long clientId;

    private Long timestamp;
    //широта
    private Double latitude;
    //долгота
    private Double longitude;

    private Double speed;
    //азимут
    private Double bearing;
    //высота
    private Double altitude;
    //точность
    private Double accuracy;

    private Double batteryLevel;

    private String userAgent;
}
