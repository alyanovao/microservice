package ru.aao.consumerservice.route.initialize;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.consumerservice.common.Constant;
import ru.aao.consumerservice.common.types.DIRECTION;
import ru.aao.consumerservice.processor.WireTapProcessor;

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
