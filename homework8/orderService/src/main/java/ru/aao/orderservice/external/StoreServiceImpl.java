package ru.aao.orderservice.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.aao.orderservice.configuration.ServiceConfiguration;
import ru.aao.orderservice.controller.CustomErrorHandler;
import ru.aao.orderservice.exception.ApplicationException;

@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ServiceConfiguration serviceConfiguration;
    private final RestTemplate restTemplate = new RestTemplate();

    public StoreServiceImpl(@Qualifier("storeConfiguration") ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        restTemplate.setErrorHandler(new CustomErrorHandler());
    }

    @Override
    public boolean reserve(ReserveRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String jsonRequest = mapper.writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
            String url = UriComponentsBuilder.fromHttpUrl(serviceConfiguration.getConfiguration() + "/reserve")
                    .encode()
                    .toUriString();
            val result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    entity,
                    String.class);
            return true;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationException(e);
        }
    }

    @Override
    public boolean unseserve(ReserveRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String jsonRequest = mapper.writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
            String url = UriComponentsBuilder.fromHttpUrl(serviceConfiguration.getConfiguration() + "/unreserve")
                    .encode()
                    .toUriString();
            val result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    entity,
                    String.class);
            return true;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationException(e);
        }
    }
}
