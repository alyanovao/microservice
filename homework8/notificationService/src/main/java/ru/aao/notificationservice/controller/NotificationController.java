package ru.aao.notificationservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aao.notificationservice.model.dto.ClientMessageResponse;
import ru.aao.notificationservice.model.dto.NotificationRequest;
import ru.aao.notificationservice.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/sendmessage")
    public ResponseEntity<String> sendMessage(@RequestBody NotificationRequest request) {
        notificationService.saveMessage(request);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getClientMessages")
    public ResponseEntity<List<ClientMessageResponse>> getClientMessage(@RequestParam Long clientId) {
        val messages = notificationService.getClientMessage(clientId);
        return ResponseEntity.status(200).body(messages);
    }
}
