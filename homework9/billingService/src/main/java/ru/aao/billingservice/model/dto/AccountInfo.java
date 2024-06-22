package ru.aao.billingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
    private Long id;
    private Long clientId;
    private String accountToken;
    private BigDecimal amount;
}
