package ru.aao.notificationservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessageResponse {
    private Long id;
    private String email;
    private String message;
}
