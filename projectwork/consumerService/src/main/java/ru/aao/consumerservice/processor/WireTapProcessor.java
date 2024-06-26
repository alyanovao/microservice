package ru.aao.consumerservice.processor;

import lombok.AllArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ValueBuilder;
import ru.aao.consumerservice.common.Constant;
import ru.aao.consumerservice.common.types.DIRECTION;

@AllArgsConstructor
public class WireTapProcessor implements Processor {

    private ValueBuilder component;
    private ValueBuilder operation;
    private DIRECTION direction;
    private ValueBuilder step;

    @Override
    public void process(Exchange exchange) {
        exchange.getIn().setHeader(Constant.LOG_COMPONENT, component.convertToString());
        exchange.getIn().setHeader(Constant.LOG_OPERATION, operation.convertToString());
        exchange.getIn().setHeader(Constant.LOG_DIRECTION, direction);
        exchange.getIn().setHeader(Constant.LOG_STEP, step.convertToString());
    }
}
