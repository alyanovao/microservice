package ru.aao.orderservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aao.orderservice.model.dto.*;
import ru.aao.orderservice.external.BillingService;
import ru.aao.orderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orderService")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final BillingService billingService;

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

    @PostMapping("/createOrder")
    public ResponseEntity<OrderInfo> createOrder(@RequestBody OrderInfo info) {
        OrderInfo order = orderService.createOrderSaga(info);
        return ResponseEntity.status(200).body(order);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Param>> getOrders() {
        val s = orderService.getOrders();
        return ResponseEntity.status(200).body(s);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<List<Param>> getOrder(@RequestParam Long clientId) {
        val s = orderService.getOrderByClientId(clientId);
        return ResponseEntity.status(200).body(s);
    }
}
