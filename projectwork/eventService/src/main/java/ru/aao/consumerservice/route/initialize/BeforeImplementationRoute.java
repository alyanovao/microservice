package ru.aao.consumerservice.route.initialize;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.consumerservice.common.Constant;
import ru.aao.consumerservice.common.exception.ApplicationException;
import ru.aao.consumerservice.common.types.log.DIRECTION;
import ru.aao.consumerservice.processor.PrepareRequest;
import ru.aao.consumerservice.processor.WireTapProcessor;
import ru.aao.consumerservice.processor.logging.LogIdGenerator;

@Component
public class BeforeImplementationRoute extends RouteBuilder {

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:beforeImplementationRoute")
            .routeId("beforeImplementationRouteId")
                .setProperty(Constant.NAMEPROJECT, constant(Constant.NAMEPROJECT))
                .setProperty(Constant.LOG_COMPONENT, constant(Constant.NAMEPROJECT + "REST"))
                .setProperty(Constant.LOG_OPERATION, header(Exchange.HTTP_URI))
                .setProperty(Constant.LOG_STEP, constant("Request"))

                .process(new LogIdGenerator())
                .process(new PrepareRequest())

                .log(LoggingLevel.INFO, Constant.NAMEPROJECT + "REST :: operation - ${exchangeProperty." + Constant.LOG_OPERATION + "}"
                        + " :: TRACE_ID - ${exchangeProperty." + Constant.LOG_ID + "}")

                .wireTap("seda:logging")
                .onPrepare(new WireTapProcessor(
                        simple("${exchangeProperty." + Constant.LOG_COMPONENT + "}"),
                        exchangeProperty(Constant.LOG_OPERATION),
                        DIRECTION.Inbound,
                        exchangeProperty(Constant.LOG_STEP)
                        )
                )
                .copy(true)
                .end()

                .process(exchange -> {
                    Object obj = exchange.getIn().getBody();
                    if (obj instanceof Exception exception) {
                        throw new ApplicationException("Rest route", exception);
                    }
                });
    }
}
