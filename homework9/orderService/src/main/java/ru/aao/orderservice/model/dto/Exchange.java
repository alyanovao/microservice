package ru.aao.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor@AllArgsConstructor
public class Exchange {
    private int status;
    private String message;

    public Exchange(int status) {
        this.status = status;
    }
}
