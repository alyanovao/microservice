package ru.aao.billingservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.aao.billingservice.exception.NoAmountAmountException;
import ru.aao.billingservice.model.AccountResponse;
import ru.aao.billingservice.model.dto.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BillingRepositoryImpl implements BillingRepository {

    public static final String CREATE_ACCOUNT = "insert into accounts(clientId, accountToken, amount) values (:clientId, :accountToken, :amount)";
    public static final String DEBIT_ACCOUNT = "update accounts set amount = amount + :amount where clientId = :clientId and id = :accountId";
    public static final String GET_ACCOUNT_INFO = "select id, clientId, accountToken, amount from accounts where clientId = :clientId";
    public static final String GET_ACCOUNTS_BY_CLIENT = "select id, clientId, accountToken, amount from accounts where clientId = :clientId and id = :accountId";
    public static final String CREDIT_ACCOUNT = "update accounts set amount = amount - :amount where clientId = :clientId and id = :accountId";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public AccountInfo createAccount(AccountRequest request) {
        val keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", request.getClientId());
        val uuid = UUID.randomUUID();
        params.addValue("accountToken", uuid);
        params.addValue("amount", 0);
        namedParameterJdbcTemplate.update(CREATE_ACCOUNT, params, keyHolder, new String[]{"id"});
        Integer id = keyHolder.getKeyAs(Integer.class);
        return new AccountInfo(Long.valueOf(id), request.getClientId(), uuid.toString(), new BigDecimal(0));
    }

    @Override
    public int debitAccount(TransferInfo transferInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", transferInfo.getAccountId());
        params.put("clientId", transferInfo.getClientId());
        params.put("amount", transferInfo.getAmount());
        return namedParameterJdbcTemplate.update(DEBIT_ACCOUNT, params);
    }

    @Override
    public int creditAccount(TransferInfo transferInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", transferInfo.getAccountId());
        params.put("clientId", transferInfo.getClientId());
        AccountInfo accountInfo = namedParameterJdbcTemplate.queryForObject(GET_ACCOUNTS_BY_CLIENT, params, new AccountMap());
        if (accountInfo.getAmount().compareTo(transferInfo.getAmount()) < 0) {
            throw new NoAmountAmountException("Не достаточно средств");
        }
        params.clear();
        params.put("accountId", transferInfo.getAccountId());
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
