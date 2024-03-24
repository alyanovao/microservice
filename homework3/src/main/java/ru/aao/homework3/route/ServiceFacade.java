package ru.aao.homework3.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.homework3.dto.ServiceResponse;

@Component
public class ServiceFacade extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
            .component("netty-http")
            .enableCORS(true);

        rest("").description("camel netty rest service")
            .id("api-route")

            .get("/health")
                .routeId("restApiInfoRoute")
                .description("get method")
                .type(String.class)
                .outType(ServiceResponse.class)
                .to("direct:infoRoute");
    }
}
