package ru.aao.notificationservice.service;

import ru.aao.notificationservice.model.dto.ClientMessageResponse;
import ru.aao.notificationservice.model.dto.NotificationRequest;

import java.util.List;

public interface NotificationService {
    int saveMessage(NotificationRequest request);

    List<ClientMessageResponse> getClientMessage(Long clientId);
}
