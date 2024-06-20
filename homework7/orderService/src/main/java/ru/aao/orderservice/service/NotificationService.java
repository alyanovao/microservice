package ru.aao.orderservice.service;

import org.springframework.http.HttpStatusCode;
import ru.aao.orderservice.model.dto.Exchange;
import ru.aao.orderservice.model.dto.Notification;

public interface NotificationService {
    Exchange sendMessage(Notification notification);
}
