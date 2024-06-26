package ru.aao.orderservice.external;

import ru.aao.orderservice.model.dto.Exchange;
import ru.aao.orderservice.model.dto.Notification;

public interface NotificationService {
    Exchange sendMessage(Notification notification);
}
