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
import ru.aao.orderservice.model.dto.Exchange;
import ru.aao.orderservice.model.dto.Message;
import ru.aao.orderservice.model.dto.TransferInfo;
import ru.aao.orderservice.model.dto.UserInfo;

@Slf4j
@Service
public class BillingServiceImpl implements BillingService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ServiceConfiguration serviceConfiguration;
    private final RestTemplate restTemplate = new RestTemplate();

    public BillingServiceImpl(@Qualifier("billingConfiguration") ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        restTemplate.setErrorHandler(new CustomErrorHandler());
    }

    @Override
    public Exchange createAccount(UserInfo request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String jsonRequest = mapper.writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
            String url = UriComponentsBuilder.fromHttpUrl(serviceConfiguration.getConfiguration() + "/createAccount")
                    .encode()
                    .toUriString();
            val result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    entity,
                    String.class);
            val status = result.getStatusCode();
            return new Exchange(status.value(), "");
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationException(e);
        }
    }

    @Override
    public boolean debitAccount(TransferInfo info) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String jsonRequest = null;
        try {
            jsonRequest = mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(e);
        }
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
        String url = UriComponentsBuilder.fromHttpUrl(serviceConfiguration.getConfiguration() + "/debitAccount")
                .encode()
                .toUriString();
        try {
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return true;
        }
        catch (Exception e) {
            try {
                Message message = mapper.readValue(e.getMessage(), Message.class);
                log.error(message.getMessage());
            } catch (JsonProcessingException ex) {
                throw new ApplicationException(ex);
            }
            return false;
        }
    }

    @Override
    public boolean creditAccount(TransferInfo info) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String jsonRequest = null;
        try {
            jsonRequest = mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(e);
        }
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
        String url = UriComponentsBuilder.fromHttpUrl(serviceConfiguration.getConfiguration() + "/creditAccount")
                .encode()
                .toUriString();
        try {
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return true;
        }
        catch (Exception e) {
            try {
                Message message = mapper.readValue(e.getMessage(), Message.class);
                log.error(message.getMessage());
            } catch (JsonProcessingException ex) {
                throw new ApplicationException(ex);
            }
            return false;
        }
    }
}
