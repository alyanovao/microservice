package ru.aao.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aao.orderservice.model.dto.*;
import ru.aao.orderservice.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public UserInfo createUser(Client request) {
        return orderRepository.createUser(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInfo> getProducts() {
        return orderRepository.getProducts();
    }

    @Override
    @Transactional
    public void buy(OrderInfo info) {
        orderRepository.buy(info);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInfo getProductById(Long productId) {
        return orderRepository.getProductById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Param> getOrderByClientId(Long clientId) {
        return orderRepository.getOrderByClientId(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientByClientId(Long clientId) {
        return orderRepository.getClientByClientId(clientId);
    }
}
