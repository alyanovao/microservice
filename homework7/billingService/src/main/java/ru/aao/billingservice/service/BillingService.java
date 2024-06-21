package ru.aao.billingservice.service;

import ru.aao.billingservice.model.dto.AccountInfo;
import ru.aao.billingservice.model.dto.AccountRequest;
import ru.aao.billingservice.model.dto.TransferInfo;

import java.util.List;

public interface BillingService {
    int createAccount(AccountRequest request);
    int debitAccount(TransferInfo transferInfo);
    int creditAccount(TransferInfo transferInfo);

    List<AccountInfo> getAccountInfo(Long clientId);
}
