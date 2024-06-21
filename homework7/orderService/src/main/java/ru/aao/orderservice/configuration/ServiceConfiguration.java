package ru.aao.orderservice.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ServiceConfiguration {

    private String schema;
    private String host;
    private String port;
    private String serviceName;

    public String getConfiguration() {
        return schema + "://" + host + ":" + port + "/" + serviceName;
    }
}
