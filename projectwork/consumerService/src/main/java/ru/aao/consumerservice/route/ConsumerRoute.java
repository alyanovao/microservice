package ru.aao.consumerservice.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import ru.aao.consumerservice.domain.Location;
import ru.aao.consumerservice.service.LocationService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerRoute extends RouteBuilder {

    private final LocationService locationService;

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("kafka:{{kafka.consumer.topic}}?brokers={{camel.component.kafka.brokers}}&" +
                "groupId=1&" +
                "autoCommitEnable=true" + "&" +
                "allowManualCommit=true" + "&" +
                "autoOffsetReset=earliest")
            .routeId("consumerRouteId")
            .log("from kafka: ${body}")
            .unmarshal().json(Location.class)
            .bean(locationService, "save(${body})")
            .bean(locationService, "findAll()")
            .process(exchange -> {
                val message = exchange.getIn();
            })
            .to("direct:afterImplementationRoute")
        .end();
    }
}
