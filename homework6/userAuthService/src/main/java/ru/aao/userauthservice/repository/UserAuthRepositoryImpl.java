package ru.aao.userauthservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.aao.userauthservice.model.dto.UserDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<UserDto> findByLoginPassword(String login, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
        UserDto user = jdbcTemplate.queryForObject("select id, login, age, firstname, lastname, email from users where login = :login and password = :password",
                params, new UserMap());
        return Optional.ofNullable(user);
    }

    @Override
    public UserDto save(UserDto user) {
        val keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("age", user.getAge());
        params.addValue("firstname", user.getFirstName());
        params.addValue("lastname", user.getLastName());
        params.addValue("email", user.getEmail());
        jdbcTemplate.update("insert into users(login, password, age, firstname, lastname, email) values (:login, :password, :age, :firstname, :lastname, :email)"
                , params, keyHolder, new String[]{"id"});
        Integer id = keyHolder.getKeyAs(Integer.class);
        return UserDto.builder().id(Long.valueOf(id))
                .login(user.getLogin())
                .age(user.getAge())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
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
