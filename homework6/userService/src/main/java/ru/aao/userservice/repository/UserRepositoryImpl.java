package ru.aao.userservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.aao.userservice.model.dao.User;
import ru.aao.userservice.model.dto.UserDto;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<UserDto> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        val s = jdbcTemplate.queryForObject("select id, login, age, firstname, lastname, email from users where id = :id",
                params, new UserMap());
        return Optional.ofNullable(s);
    }

    @Override
    public User save(long id, String login) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("login", login);
        jdbcTemplate.update("insert into users(id, login) values (:id, :login)", params);
        return new User(id, login, 30);
    }

    @Override
    public void update(UserDto user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("firstName", user.getFirstName());
        params.addValue("lastName", user.getLastName());
        params.addValue("email", user.getEmail());
        params.addValue("age", user.getAge());
        jdbcTemplate.update("update users set firstName = :firstName, lastName = :lastName,  age = :age, email = :email where id = :id", params);
    }
}
