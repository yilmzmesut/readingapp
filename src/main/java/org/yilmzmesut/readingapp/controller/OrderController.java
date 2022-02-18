package org.yilmzmesut.readingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yilmzmesut.readingapp.model.dto.OrderDTO;
import org.yilmzmesut.readingapp.model.request.CreateOrderRequest;
import org.yilmzmesut.readingapp.model.request.SearchOrderRequest;
import org.yilmzmesut.readingapp.model.response.WrapperResponse;
import org.yilmzmesut.readingapp.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<WrapperResponse<OrderDTO>> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        var order = orderService.createOrder(request);
        return ResponseEntity.ok(new WrapperResponse<>(order, HttpStatus.CREATED.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable("id") Long id) {
        var order = orderService.findById(id);
        return ResponseEntity.ok(new WrapperResponse<>(order, HttpStatus.OK.value()));
    }

    @PostMapping("/search")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> searchOrders(@Valid @RequestBody SearchOrderRequest searchOrderRequest) {
        var orders = orderService.searchOrders(searchOrderRequest);
        return ResponseEntity.ok(new WrapperResponse<>(orders, HttpStatus.OK.value()));
    }
}
