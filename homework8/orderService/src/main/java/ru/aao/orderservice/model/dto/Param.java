package ru.aao.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Param {
    private Long id;
    private Long clientId;
    private String name;
    private int count;
}
