package ru.aao.orderservice.external;

public interface DeliveryService {
    boolean send(DeliveryRequest request);
}
