package ru.aao.eventservice.route;

import lombok.RequiredArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.eventservice.common.Constant;
import ru.aao.eventservice.service.TechnicalService;

/**
 * @author Alyanov Artem 14.12.2022
 */
@Component
@RequiredArgsConstructor
public class GetVersionRoute extends RouteBuilder {

    private final TechnicalService service;

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:getVersionRoute")
            .routeId("getVersionRouteId")
                .to("direct:beforeImplementationRoute")
                .log(LoggingLevel.INFO, "${header." + Constant.CORRELATION_ID + "}")
                .bean(service, "getVersion()")
                .to("direct:afterImplementationRoute")
        .end();

    }
}
