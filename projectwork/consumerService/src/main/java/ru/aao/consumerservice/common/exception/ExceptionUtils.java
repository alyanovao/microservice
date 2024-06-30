package ru.aao.consumerservice.common.exception;

import org.apache.camel.Exchange;

import java.util.Objects;

public class ExceptionUtils {
    public static Exception exceptionExtractor(Exchange exchange) {
        Exception e = null;
        if (Objects.nonNull(exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class))) {
            e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        } else if (Objects.nonNull(exchange.getIn().getBody(Exception.class))) {
            e = exchange.getIn().getBody(Exception.class);
        } else if (Objects.nonNull(exchange.getException())) {
            e = exchange.getException();
        } else {
            String stringException = exchange.getIn().getHeader(Exchange.EXCEPTION_CAUGHT, String.class);
            if (Objects.nonNull(stringException)) {
                e = new Exception();
            }
        }
        return e;
    }
}
