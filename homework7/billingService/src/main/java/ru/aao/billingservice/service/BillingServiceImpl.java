package ru.aao.billingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aao.billingservice.model.dto.AccountInfo;
import ru.aao.billingservice.model.dto.AccountRequest;
import ru.aao.billingservice.model.dto.TransferInfo;
import ru.aao.billingservice.repository.BillingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

    @Override
    @Transactional
    public int createAccount(AccountRequest request) {
        return billingRepository.createAccount(request);
    }

    @Override
    @Transactional
    public int debitAccount(TransferInfo transferInfo) {
        return billingRepository.debitAccount(transferInfo);
    }

    @Override
    @Transactional
    public int creditAccount(TransferInfo transferInfo) {
        return billingRepository.creditAccount(transferInfo);
    }

    @Override
    @Transactional
    public List<AccountInfo> getAccountInfo(Long clientId) {
        return billingRepository.getAccountByClientId(clientId);
    }
}
