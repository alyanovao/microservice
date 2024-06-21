package ru.aao.deliveryservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aao.deliveryservice.dto.DeliveryRequest;
import ru.aao.deliveryservice.exception.ApplicationException;

@Slf4j
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @PostMapping("/send")
    public void reserve(@RequestBody DeliveryRequest request) {
        log.info("Начата доставка заказа: {}", request.toString());
        if (request.getAddress().equals("тест")) {
            throw new ApplicationException("Не удалось выполнить доставку");
        }
        log.info("Выполнена доставка заказа: {}", request);
    }
}
