package ru.aao.orderservice.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "external.store")
public class StoreConfiguration extends ServiceConfiguration {
}
