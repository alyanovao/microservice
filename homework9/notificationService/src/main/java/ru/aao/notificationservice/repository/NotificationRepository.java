package ru.aao.notificationservice.repository;

import ru.aao.notificationservice.model.dto.ClientMessageResponse;
import ru.aao.notificationservice.model.dto.NotificationRequest;

import java.util.List;

public interface NotificationRepository {
    int saveMessage(NotificationRequest request);
    List<ClientMessageResponse> getMessages(Long clientId);
}
