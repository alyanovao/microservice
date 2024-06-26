package ru.aao.eventservice.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.aao.eventservice.common.Constant;
import ru.aao.eventservice.common.types.log.DIRECTION;
import ru.aao.eventservice.domain.Location;
import ru.aao.eventservice.processor.RestFaultResponseBuilder;
import ru.aao.eventservice.processor.WireTapProcessor;

@Component
public class ServiceRestFacade extends RouteBuilder {

    @Value("${camel.servlet.mapping.context-path}")
    private String contextPath;

    @Value("${server.address}")
    private String address;

    @Value("${server.port}")
    private String port;

    @Override
    public void configure() {
        errorHandler(defaultErrorHandler());

        onException(Exception.class)
            .handled(true)
            .process(new RestFaultResponseBuilder())
            .wireTap("seda:logging")
                .onPrepare(new WireTapProcessor(
                    exchangeProperty(Constant.LOG_COMPONENT),
                    constant("Exception"),
                    DIRECTION.Inner,
                    exchangeProperty(Constant.LOG_STEP)
                )
            )
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
            .setHeader("Content-Type", simple("${exchangeProperty.ContentType}"))
            .marshal().json(JsonLibrary.Jackson)
        .end();

        restConfiguration()
            .component("servlet")
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .host(address)
            .port(port)
            .contextPath(contextPath.substring(0, contextPath.length() - 2))
            //validation request
            .clientRequestValidation(true)
            // turn on openapi api-doc
            .apiContextPath("/swagger")
            .apiProperty("api.title", "User API")
            .apiProperty("api.version", "1.0.0");

        rest("").description("SmartSearch REST service")
            .id("api-route")
            .consumes("application/json")
            .produces("application/json")

            .get("/getVersion")
                .routeId("getVersion-api")
                .description("getVersion endpoint")
                .outType(String.class)
                .responseMessage().code(200).message("User successfully returned").endResponseMessage()
                .to("direct:getVersionRoute")

            .post("/location")
                .routeId("location-api")
                .description("set location")
                .type(Location.class)
                .outType(String.class)
                .responseMessage().code(200).message("success").endResponseMessage()
                .to("direct:locationRoute");
    }
}
