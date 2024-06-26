package ru.aao.eventservice.processor.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import ru.aao.eventservice.common.Constant;
import ru.aao.eventservice.common.exception.ApplicationException;
import ru.aao.eventservice.common.types.log.DIRECTION;
import ru.aao.eventservice.common.types.log.LogRecord;
import ru.aao.eventservice.common.util.MessageUtils;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@Component
@RequiredArgsConstructor
public class LogConverter implements Processor {

	private final ObjectMapper mapper;

	@Override
	public void process(Exchange exchange) {
		LogRecord logRecord = new LogRecord();
		logRecord.setSystem(exchange.getIn().getHeader(Constant.NAMEPROJECT, String.class));
		logRecord.setTraceId(exchange.getProperty(Constant.LOG_ID, String.class));
		logRecord.setSpanId(exchange.getProperty(Constant.CORRELATION_ID, String.class));
		logRecord.setTimestamp(DateTime.now());

		logRecord.setComponent(exchange.getIn().getHeader(Constant.LOG_COMPONENT, String.class));
		logRecord.setOperation(exchange.getIn().getHeader(Constant.LOG_OPERATION, String.class));
		logRecord.setDirection(exchange.getIn().getHeader(Constant.LOG_DIRECTION, DIRECTION.class));
		logRecord.setStep(exchange.getIn().getHeader(Constant.LOG_STEP, String.class));

		try {
			logRecord.setHost(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e1) {
			logRecord.setHost("unknown");
		}

		Object redelivery = exchange.getIn().getHeader(Constant.LOG_REDELIVERY) == null ? null
				: exchange.getIn().getHeader(Constant.LOG_REDELIVERY, String.class);
		Object body = exchange.getMessage().getBody() == null ? exchange.getIn().getBody() : exchange.getMessage().getBody();

		Object obj = redelivery == null ? body : redelivery;
		if (obj instanceof InputStream inputStream) {
			logRecord.setData(MessageUtils.getStringFromInputStream(inputStream));
		} else if (obj instanceof String) {
			logRecord.setData(obj);
		} else {
            try {
                String data = mapper.writeValueAsString(obj);
				logRecord.setData(data);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
				throw new ApplicationException(e);
            }
		}

		String bodyMessage;
		try {
            bodyMessage = mapper.writeValueAsString(logRecord);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
			throw new ApplicationException(e);
        }

        exchange.getMessage().setBody(bodyMessage);
		exchange.setProperty("record", logRecord);
	}

}
