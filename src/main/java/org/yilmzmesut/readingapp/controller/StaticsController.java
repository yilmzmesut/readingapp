package org.yilmzmesut.readingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yilmzmesut.readingapp.model.response.WrapperResponse;
import org.yilmzmesut.readingapp.service.OrderService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/statics")
public class StaticsController {

    private final OrderService orderService;

    public StaticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-count")
    public ResponseEntity<WrapperResponse<Long>> getTotalOrderCount() {
        return ResponseEntity.ok(new WrapperResponse<>(orderService.getTotalOrderCount(), HttpStatus.OK.value()));
    }

    @GetMapping("/purchased-order-amount")
    public ResponseEntity<WrapperResponse<BigDecimal>> getTotalPurchasedOrderAmount() {
        var totalPurchasedOrderAmount = orderService.getTotalPurchasedOrderAmount();
        return ResponseEntity.ok(new WrapperResponse<>(totalPurchasedOrderAmount, HttpStatus.OK.value()));
    }

    @GetMapping("/purchased-order-count")
    public ResponseEntity<WrapperResponse<Long>> getTotalPurchasedOrderCount() {
        var totalPurchasedOrderCount = orderService.getTotalPurchasedOrderCount();
        return ResponseEntity.ok(new WrapperResponse<>(totalPurchasedOrderCount, HttpStatus.OK.value()));
    }
}
