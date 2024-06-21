package ru.aao.billingservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.aao.billingservice.exception.NoAmountAmountException;
import ru.aao.billingservice.model.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BillingRepositoryImpl implements BillingRepository {

    public static final String CREATE_ACCOUNT = "insert into accounts(clientId, accountToken, amount) values (:clientId, :accountToken, :amount)";
    public static final String DEBIT_ACCOUNT = "update accounts set amount = amount + :amount where clientId = :clientId";
    public static final String GET_ACCOUNT_INFO = "select id, accountToken, amount from accounts where clientId = :clientId";
    public static final String CREDIT_ACCOUNT = "update accounts set amount = amount - :amount where clientId = :clientId";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int createAccount(AccountRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", request.getClientId());
        params.addValue("accountToken", UUID.randomUUID());
        params.addValue("amount", 0);
        return namedParameterJdbcTemplate.update(CREATE_ACCOUNT, params);
    }

    @Override
    public int debitAccount(TransferInfo transferInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", transferInfo.getClientId());
        params.put("amount", transferInfo.getAmount());
        return namedParameterJdbcTemplate.update(DEBIT_ACCOUNT, params);
    }

    @Override
    public int creditAccount(TransferInfo transferInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", transferInfo.getClientId());
        AccountInfo accountInfo = namedParameterJdbcTemplate.queryForObject(GET_ACCOUNT_INFO, params, new AccountMap());
        if (accountInfo.getAmount().compareTo(transferInfo.getAmount()) < 0) {
            throw new NoAmountAmountException("Не достаточно средств");
        }
        params.clear();
        params.put("clientId", transferInfo.getClientId());
        params.put("amount", transferInfo.getAmount());
        return namedParameterJdbcTemplate.update(CREDIT_ACCOUNT, params);
    }

    @Override
    public List<AccountInfo> getAccountByClientId(Long clientId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", clientId);
        return namedParameterJdbcTemplate.query(GET_ACCOUNT_INFO, params, new AccountMap());
    }
}
