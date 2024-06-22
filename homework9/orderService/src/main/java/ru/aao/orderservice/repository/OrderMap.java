package ru.aao.orderservice.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.aao.orderservice.model.dto.Param;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMap implements RowMapper<Param> {

    @Override
    public Param mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long clientId = rs.getLong("clientId");
        String name = rs.getString("name");
        int amount = rs.getInt("count");
        return new Param(id, clientId, name, amount);
    }
}
