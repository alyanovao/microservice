package ru.aao.consumerservice.route;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.consumerservice.domain.Location;

@Slf4j
@Component
public class ConsumerRoute extends RouteBuilder {

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
            .to("direct:afterImplementationRoute")
        .end();
    }
}
