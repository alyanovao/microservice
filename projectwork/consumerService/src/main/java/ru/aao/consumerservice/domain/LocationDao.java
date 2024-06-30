package ru.aao.consumerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDao {

    @Id
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
