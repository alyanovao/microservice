package ru.aao.orderservice.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import ru.aao.orderservice.exception.ApplicationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = IOUtils.toString(response.getBody(), StandardCharsets.UTF_8);
        throw new ApplicationException(body);
    }
}
