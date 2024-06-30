package ru.aao.consumerservice.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import ru.aao.consumerservice.common.Constant;

@Slf4j
@Component
public class LocationRoute extends RouteBuilder {

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:locationRoute")
            .routeId("locationRouteId")
            .to("direct:beforeImplementationRoute")
            .log("start locationRoute :: ${body}")
            .setProperty(Constant.ORIGINAL_BODY).body()
            .marshal().json(JsonLibrary.Jackson)
            .to("kafka:{{kafka.producer.topic}}")
            .setBody(exchangeProperty(Constant.ORIGINAL_BODY))
            .log("send kafka :: ${body}")
            .to("direct:afterImplementationRoute")
        .end();
    }
}
