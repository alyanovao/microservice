package ru.aao.storeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aao.storeservice.dto.ReserveRequest;
import ru.aao.storeservice.dto.UnReserveRequest;
import ru.aao.storeservice.exception.StockException;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {

    @PostMapping("/reserve")
    public void reserve(@RequestBody ReserveRequest request) {
        log.info("Резервирование товаров");
        if (request.getPrice().compareTo(BigDecimal.valueOf(500)) == 0) {
            throw new StockException("Имитация ошибки при бронировании заказа");
        }
    }

    @PostMapping("/unreserve")
    public void unreserve(@RequestBody UnReserveRequest request) {
        log.info("Отмена резервирования товаров");
    }
}
