package ru.aao.eventservice.route.initialize;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.eventservice.common.Constant;
import ru.aao.eventservice.common.types.log.DIRECTION;
import ru.aao.eventservice.processor.WireTapProcessor;

@Component
public class AfterImplementationRoute extends RouteBuilder {

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:afterImplementationRoute")
            .routeId("afterImplementationRouteId")
                .setProperty(Constant.LOG_STEP, constant("Response"))

                .wireTap("seda:logging")
                    .onPrepare(new WireTapProcessor(
                            exchangeProperty(Constant.LOG_COMPONENT),
                            exchangeProperty(Constant.LOG_OPERATION),
                            DIRECTION.Outbound,
                            exchangeProperty(Constant.LOG_STEP)
                        )
                    )
                .end()

                .removeHeader("*");
    }
}
