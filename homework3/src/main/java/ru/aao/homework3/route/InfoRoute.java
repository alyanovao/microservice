package ru.aao.homework3.route;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.homework3.service.CustomService;

@Component
@RequiredArgsConstructor
public class InfoRoute extends RouteBuilder {

    private final CustomService service;

    @Override
    public void configure() {
        errorHandler(noErrorHandler());
        from("direct:infoRoute")
            .routeId("InfoRouteId")
            .log("route info")
            .bean(service, "getInfo")
        .end();
    }
}
