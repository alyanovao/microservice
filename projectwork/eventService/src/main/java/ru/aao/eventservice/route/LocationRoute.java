package ru.aao.eventservice.route;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.eventservice.domain.Location;

@Slf4j
@Component
public class LocationRoute extends RouteBuilder {

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:locationRoute")
            .routeId("locationRouteId")
            .process(exchange -> {
                val message = exchange.getIn();
                val location = message.getBody(Location.class);
                log.info(location.toString());
            })
            .to("direct:beforeImplementationRoute")
            .log("start locationRoute :: ${body}")
            .to("kafka:{{kafka.producer.topic}}")
            .log("send kafka :: ${body}")
            .to("direct:afterImplementationRoute")
        .end();
    }
}
