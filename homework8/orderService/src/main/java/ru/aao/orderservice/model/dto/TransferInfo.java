package ru.aao.orderservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransferInfo {
    private Long clientId;
    private Long accountId;
    private BigDecimal amount;
}
