package ru.aao.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Long id;
    private String name;
    private BigDecimal cost;
}
