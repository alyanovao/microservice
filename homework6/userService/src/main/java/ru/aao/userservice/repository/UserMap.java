package ru.aao.userservice.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.aao.userservice.model.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserMap implements RowMapper<UserDto> {

    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String login = rs.getString("login");
        int age = rs.getInt("age");
        String firstName = Objects.nonNull(rs.getObject("firstname")) ? rs.getString("firstname") : null;
        String lastName = Objects.nonNull(rs.getObject("lastname")) ? rs.getString("lastname") : null;
        String email = Objects.nonNull(rs.getObject("email")) ? rs.getString("email") : null;
        return new UserDto(id, login, age, firstName, lastName, email);
    }
}
