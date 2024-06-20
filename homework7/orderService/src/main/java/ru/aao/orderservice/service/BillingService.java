package ru.aao.orderservice.service;

import ru.aao.orderservice.model.dto.TransferInfo;
import ru.aao.orderservice.model.dto.Exchange;
import ru.aao.orderservice.model.dto.UserInfo;

public interface BillingService {
    Exchange createAccount(UserInfo request);

    Exchange creditAccount(TransferInfo info);
}
