package ru.aao.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.aao.orderservice.configuration.ServiceConfiguration;
import ru.aao.orderservice.controller.CustomErrorHandler;
import ru.aao.orderservice.model.dto.Exchange;
import ru.aao.orderservice.model.dto.Notification;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ServiceConfiguration serviceConfiguration;
    private final RestTemplate restTemplate = new RestTemplate();

    public NotificationServiceImpl(@Qualifier("notificationConfiguration") ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        restTemplate.setErrorHandler(new CustomErrorHandler());
    }

    @Override
    public Exchange sendMessage(Notification notification) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String jsonRequest = mapper.writeValueAsString(notification);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
            String url = UriComponentsBuilder.fromHttpUrl(serviceConfiguration.getConfiguration() + "/sendmessage")
                    .encode()
                    .toUriString();
            val result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    entity,
                    String.class);
            int statusCode = result.getStatusCodeValue() == 204 ? 0 : result.getStatusCodeValue();
            return new Exchange(statusCode);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
