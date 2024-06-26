package ru.aao.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aao.orderservice.exception.ApplicationException;
import ru.aao.orderservice.exception.ExternalException;
import ru.aao.orderservice.external.*;
import ru.aao.orderservice.model.dto.*;
import ru.aao.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BillingService billingService;
    private final StoreService storeService;
    private final DeliveryService deliveryService;
    private final NotificationService notificationService;

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
    public void createOrder(OrderInfo info) {
        orderRepository.createOrder(info);
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

    @Override
    @Transactional(readOnly = true)
    public List<Param> getOrders() {
        return orderRepository.getOrders();
    }

    @Override
    @Transactional
    public OrderInfo createOrderSaga(OrderInfo info) {
        ProductInfo res = orderRepository.getProductById(info.getProductId());
        Client user = orderRepository.getClientByClientId(info.getClientId());
        orderRepository.createOrder(info);
        BigDecimal totalAmount = res.getCost().multiply(BigDecimal.valueOf(info.getCount()));
        TransferInfo transferInfo = new TransferInfo(user.getId(), info.getAccountId(), totalAmount);
        boolean successPay = billingService.creditAccount(transferInfo);
        if (!successPay) {
            notificationService.sendMessage(new Notification(info.getClientId(), user.getEmail(), "Не удалось выполнить заказ"));
            throw new ExternalException("Ошибка при оплате");
        }
        boolean successStore = storeService.reserve(new ReserveRequest(totalAmount));
        if (!successStore) {
            billingService.creditAccount(transferInfo);
            notificationService.sendMessage(new Notification(info.getClientId(), user.getEmail(), "Не удалось выполнить заказ"));
            throw new ExternalException("Ошибка при бронировании на складе");
        }
        boolean successDelivery = deliveryService.send(new DeliveryRequest(info.getAddress()));
        if (!successDelivery) {
            storeService.unseserve(new ReserveRequest(totalAmount));
            billingService.debitAccount(transferInfo);
            notificationService.sendMessage(new Notification(info.getClientId(), user.getEmail(), "Не удалось выполнить заказ"));
            throw new ExternalException("Ошибка при отправке");
        }
        notificationService.sendMessage(new Notification(info.getClientId(), user.getEmail(), "Заказ успешно зарегистрирован"));
        return new OrderInfo(user.getId(), info.getAccountId(), info.getProductId(), info.getCount(), info.getAddress());
    }
}
