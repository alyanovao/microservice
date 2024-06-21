package ru.aao.billingservice.model.dto;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMap implements RowMapper<AccountInfo> {

    @Override
    public AccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(1);
        String accountToken = rs.getString(2);
        BigDecimal amount = rs.getBigDecimal(3);
        return new AccountInfo(id, accountToken, amount);
    }
}
