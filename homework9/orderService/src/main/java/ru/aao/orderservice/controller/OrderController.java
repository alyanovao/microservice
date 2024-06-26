package ru.aao.orderservice.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import ru.aao.orderservice.exception.ApplicationException;
import ru.aao.orderservice.exception.NotValidIdempotentException;
import ru.aao.orderservice.model.FingerPrintResponse;
import ru.aao.orderservice.model.dto.*;
import ru.aao.orderservice.external.BillingService;
import ru.aao.orderservice.service.OrderService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<OrderInfo> createOrder(HttpServletRequest request,  @RequestBody OrderInfo info) {
        String fingerprint = request.getHeader("X-Request-ID");
        if (Objects.isNull(fingerprint)) {
            throw new NotValidIdempotentException("Ключ идемпотентности не найден");
        }
        val orders = orderService.getOrderByClientId(info.getClientId()).toString();
        String originalFingerprint = getFingerprint(orders);
        if (!fingerprint.equals(originalFingerprint)) {
            throw new NotValidIdempotentException("Ключи идемпотентности не совпадают");
        }
        OrderInfo order = orderService.createOrderSaga(info);
        return ResponseEntity.status(200).body(order);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Param>> getOrders() {
        val s = orderService.getOrders();
        return ResponseEntity.status(200).body(s);
    }

    @GetMapping("/get")
    public ResponseEntity<FingerPrintResponse> getFingerprint(@RequestParam Long clientId) {
        val s = orderService.getOrderByClientId(clientId).toString();
        String fingerprint = getFingerprint(s);
        return ResponseEntity.status(200).body(new FingerPrintResponse(fingerprint));
    }

    private String getFingerprint(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    @GetMapping("/getOrder")
    public ResponseEntity<List<Param>> getOrder(@RequestParam Long clientId) {
        val s = orderService.getOrderByClientId(clientId);
        return ResponseEntity.status(200).body(s);
    }
}
