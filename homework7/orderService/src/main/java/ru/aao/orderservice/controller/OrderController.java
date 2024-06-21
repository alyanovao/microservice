package ru.aao.orderservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aao.orderservice.exception.NoAmountAmountException;
import ru.aao.orderservice.model.dto.*;
import ru.aao.orderservice.service.BillingService;
import ru.aao.orderservice.service.NotificationService;
import ru.aao.orderservice.service.OrderService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orderService")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final BillingService billingService;
    private final NotificationService notificationService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createAccount(@RequestBody Client request) {
        val userInfo = orderService.createUser(request);
        billingService.createAccount(userInfo);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getUser")
    public ResponseEntity<Client> getUser(@RequestParam Long clientId) {
        val client = orderService.getClientByClientId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductInfo>> getProducts() {
        val products = orderService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody OrderInfo info) {
        ProductInfo res = orderService.getProductById(info.getProductId());
        Client user = orderService.getClientByClientId(info.getClientId());
        BigDecimal totalAmount = res.getCost().multiply(BigDecimal.valueOf(info.getCount()));
        Exchange exchange = billingService.creditAccount(new TransferInfo(info.getClientId(), totalAmount));
        if (exchange.getStatus() == 0) {
            orderService.buy(info);
            notificationService.sendMessage(new Notification(info.getClientId(), user.getEmail(), "Заказ успешно зарегистрирован"));
            return ResponseEntity.status(204).build();
        } else {
            notificationService.sendMessage(new Notification(info.getClientId(), user.getEmail(), "Не удалось выполнить заказ"));
            throw new NoAmountAmountException(exchange.getMessage());
        }
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Param>> getOrder(@RequestParam Long clientId) {
        val s = orderService.getOrderByClientId(clientId);
        return ResponseEntity.status(200).body(s);
    }
}
