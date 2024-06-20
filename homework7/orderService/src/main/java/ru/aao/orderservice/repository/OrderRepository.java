package ru.aao.orderservice.repository;

import ru.aao.orderservice.model.dto.*;

import java.util.List;

public interface OrderRepository {
    UserInfo createUser(Client request);
    List<ProductInfo> getProducts();

    void buy(OrderInfo info);

    ProductInfo getProductById(Long productId);

    List<Param> getOrderByClientId(Long clientId);

    Client getClientByClientId(Long clientId);
}
