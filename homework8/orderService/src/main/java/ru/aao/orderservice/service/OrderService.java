package ru.aao.orderservice.service;

import ru.aao.orderservice.model.dto.*;

import java.util.List;

public interface OrderService {
    UserInfo createUser(Client request);
    List<ProductInfo> getProducts();

    void createOrder(OrderInfo info);

    ProductInfo getProductById(Long productId);

    List<Param> getOrderByClientId(Long clientId);

    Client getClientByClientId(Long clientId);

    List<Param> getOrders();

    OrderInfo createOrderSaga(OrderInfo info);
}
