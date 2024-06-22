package ru.aao.orderservice.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.aao.orderservice.model.dto.ProductInfo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMap implements RowMapper<ProductInfo> {

    @Override
    public ProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        BigDecimal amount = rs.getBigDecimal("cost");
        return new ProductInfo(id, name,amount);
    }
}
