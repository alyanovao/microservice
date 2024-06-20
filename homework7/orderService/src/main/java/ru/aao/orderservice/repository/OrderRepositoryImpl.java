package ru.aao.orderservice.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.aao.orderservice.model.dto.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    public static final String CREATE_USER = "insert into users(login, email) values (:login, :email)";
    public static final String DEBIT_ACCOUNT = "update accounts set amount = amount + :amount where clientId = :clientId";
    public static final String GET_ACCOUNT_INFO = "select id, accountToken, amount from accounts where clientId = :clientId";
    public static final String CREDIT_ACCOUNT = "update accounts set amount = amount - :amount where clientId = :clientId";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public UserInfo createUser(Client request) {
        val keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", request.getLogin());
        params.addValue("email", request.getEmail());
        namedParameterJdbcTemplate.update(CREATE_USER, params, keyHolder, new String[]{"id"});
        Integer id = keyHolder.getKeyAs(Integer.class);
        return new UserInfo(Long.valueOf(id));
    }

    @Override
    public List<ProductInfo> getProducts() {
        return namedParameterJdbcTemplate.query("select id, name, cost from products", new ProductMap());
    }

    @Override
    public void buy(OrderInfo info) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", info.getClientId());
        params.addValue("productId", info.getProductId());
        params.addValue("count", info.getCount());
        namedParameterJdbcTemplate.update("insert into orders(clientId, productId, count) values(:clientId, :productId, :count)", params);
    }

    @Override
    public ProductInfo getProductById(Long productId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", productId);
        return namedParameterJdbcTemplate.queryForObject("select id, name, cost from products where id = :id", params, new ProductMap());
    }

    @Override
    public List<Param> getOrderByClientId(Long clientId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", clientId);
        return namedParameterJdbcTemplate.query("select orders.id, clientId, name, count from orders " +
                "inner join products on orders.productid = products.id where clientid = :clientId", params, new OrderMap());
    }

    @Override
    public Client getClientByClientId(Long clientId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("clientId", clientId);
        return namedParameterJdbcTemplate.queryForObject("select id, login, email from users where id = :clientId", params, new ClientMap());
    }
}
