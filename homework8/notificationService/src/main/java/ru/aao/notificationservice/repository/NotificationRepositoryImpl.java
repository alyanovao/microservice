package ru.aao.notificationservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.aao.notificationservice.model.dto.ClientMessageResponse;
import ru.aao.notificationservice.model.dto.NotificationRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int saveMessage(NotificationRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", request.getClientId());
        params.addValue("email", request.getEmail());
        params.addValue("message", request.getMessage());
        return namedParameterJdbcTemplate.update("insert into notifications(clientId, email, message) values (:clientId, :email, :message)",
                params);
    }

    @Override
    public List<ClientMessageResponse> getMessages(Long userId) {
        Map<String, Object> params = Collections.singletonMap("clientId", userId);
        val s = namedParameterJdbcTemplate.query("select id, email, message from notifications where clientId = :clientId",
                params, (rs, rowNum) -> {
                    long id = rs.getLong("id");
                    String email = rs.getString("email");
                    String message = rs.getString("message");
                    return new ClientMessageResponse(id, email, message);
                });
        return s;
    }
}
