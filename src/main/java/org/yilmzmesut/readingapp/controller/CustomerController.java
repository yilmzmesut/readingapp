package org.yilmzmesut.readingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yilmzmesut.readingapp.model.dto.CustomerDTO;
import org.yilmzmesut.readingapp.model.dto.OrderDTO;
import org.yilmzmesut.readingapp.model.request.LoginCustomerRequest;
import org.yilmzmesut.readingapp.model.request.SignUpRequest;
import org.yilmzmesut.readingapp.model.response.TokenResponse;
import org.yilmzmesut.readingapp.model.response.WrapperResponse;
import org.yilmzmesut.readingapp.service.CustomerService;
import org.yilmzmesut.readingapp.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<WrapperResponse<CustomerDTO>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        var customer = customerService.signUp(signUpRequest);
        return ResponseEntity.ok(new WrapperResponse<>(customer, HttpStatus.CREATED.value()));
    }

    @PostMapping("/signIn")
    public ResponseEntity<WrapperResponse<TokenResponse>> signIn(@Valid @RequestBody LoginCustomerRequest request) {
        var tokenResponse = customerService.signIn(request);
        return ResponseEntity.ok(new WrapperResponse<>(tokenResponse, HttpStatus.OK.value()));
    }

    @GetMapping("/orders")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> getOrders(
            @RequestParam("customerId") Long customerId,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        var customerOrders = orderService.getCustomerOrders(customerId, page, size);
        return ResponseEntity.ok(new WrapperResponse<>(customerOrders, HttpStatus.OK.value()));
    }
}
