package ru.aao.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Notification {
    private Long clientId;
    private String email;
    private String message;
}
