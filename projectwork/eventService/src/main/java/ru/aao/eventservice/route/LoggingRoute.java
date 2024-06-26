package ru.aao.eventservice.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.aao.eventservice.common.Constant;
import ru.aao.eventservice.processor.logging.LogConverter;

@Component
@RequiredArgsConstructor
public class LoggingRoute extends RouteBuilder {

    private final ObjectMapper mapper;

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("seda:logging?concurrentConsumers={{logging.cc:3}}&waitForTaskToComplete=Never")
        	.routeId("loggingId")
        	.setExchangePattern(ExchangePattern.InOnly)
        	.process(new LogConverter(mapper))
        	.log(LoggingLevel.INFO, Constant.NAMEPROJECT,"${body}")
        .end();
        
    }
}

