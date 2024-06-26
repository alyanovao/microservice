package ru.aao.eventservice.processor.logging;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.aao.eventservice.common.Constant;

import java.util.UUID;


public class LogIdGenerator implements Processor {
    @Override
    public void process(Exchange exchange) {
        String id = exchange.getIn().getHeader(Constant.LOG_ID, String.class);
        String spanId = exchange.getProperty(Constant.CORRELATION_ID, String.class);
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
            exchange.setProperty(Constant.LOG_ID, id);
            exchange.setProperty(Constant.CORRELATION_ID, id);
            
            exchange.getIn().setHeader(Constant.LOG_ID, id);

        } else if (spanId == null || spanId.isEmpty()){
        	exchange.setProperty(Constant.LOG_ID, id);
        	spanId = UUID.randomUUID().toString();
        	exchange.setProperty(Constant.CORRELATION_ID, spanId);
        	exchange.getIn().setHeader(Constant.CORRELATION_ID, spanId);
        }
        
    }
}
