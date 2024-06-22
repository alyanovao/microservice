package ru.aao.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aao.notificationservice.model.dto.ClientMessageResponse;
import ru.aao.notificationservice.model.dto.NotificationRequest;
import ru.aao.notificationservice.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public int saveMessage(NotificationRequest request) {
        return notificationRepository.saveMessage(request);
    }

    @Override
    public List<ClientMessageResponse> getClientMessage(Long clientId) {
        return notificationRepository.getMessages(clientId);
    }
}
