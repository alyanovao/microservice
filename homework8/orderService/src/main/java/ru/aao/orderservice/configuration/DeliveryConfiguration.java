package ru.aao.orderservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external.delivery")
public class DeliveryConfiguration extends ServiceConfiguration {
}
