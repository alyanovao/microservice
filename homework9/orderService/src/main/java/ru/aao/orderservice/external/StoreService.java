package ru.aao.orderservice.external;

public interface StoreService {
    boolean reserve(ReserveRequest request);
    boolean unseserve(ReserveRequest request);
}
