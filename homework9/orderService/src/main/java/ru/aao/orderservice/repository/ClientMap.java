package ru.aao.orderservice.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.aao.orderservice.model.dto.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMap implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String login = rs.getString("login");
        String email = rs.getString("email");
        return new Client(id, login, email);
    }
}
