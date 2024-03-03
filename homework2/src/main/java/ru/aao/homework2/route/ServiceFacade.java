package ru.aao.homework2.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.homework2.dto.ServiceResponse;

@Component
public class ServiceFacade extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
            .component("netty-http")
            .enableCORS(true);

        rest("").description("camel netty rest service")
            .id("api-route")

            .get("/hello")
                .description("get method")
                .type(String.class)
                .outType(ServiceResponse.class)
                .to("direct:helloRoute");
    }
}
