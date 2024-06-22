package ru.aao.orderservice.external;

import ru.aao.orderservice.model.dto.TransferInfo;
import ru.aao.orderservice.model.dto.Exchange;
import ru.aao.orderservice.model.dto.UserInfo;

public interface BillingService {
    Exchange createAccount(UserInfo request);

    boolean debitAccount(TransferInfo info);
    boolean creditAccount(TransferInfo info);
}
