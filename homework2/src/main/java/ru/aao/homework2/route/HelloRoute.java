package ru.aao.homework2.route;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.homework2.service.CustomService;

@Component
@RequiredArgsConstructor
public class HelloRoute extends RouteBuilder {

    private final CustomService service;

    @Override
    public void configure() {
        errorHandler(noErrorHandler());
        from("direct:helloRoute")
            .routeId("HelloRouteId")
            .log("route hello")
            .bean(service, "getHello")
        .end();
    }
}
