package ru.aao.consumerservice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.ResponseEntity;
import ru.aao.consumerservice.common.exception.ExceptionUtils;
import ru.aao.consumerservice.common.exception.error.ERROR_DETAIL;
import ru.aao.consumerservice.common.exception.error.Error;

public class RestFaultResponseBuilder implements Processor {

    @Override
    public void process(Exchange exchange) {
        int status = 400;

        Exception exception = ExceptionUtils.exceptionExtractor(exchange);
        Error error;
        if (exception != null) {
            error = new Error();
            error.setCode(status);
            error.setMessage(exception.getMessage());
        } else {
            error = new Error(ERROR_DETAIL.Inner_error, "");
        }
        ResponseEntity<Error> response = ResponseEntity.status(status).body(error);
        exchange.getIn().setBody(response);
    }
}
