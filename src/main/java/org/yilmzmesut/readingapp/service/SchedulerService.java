package org.yilmzmesut.readingapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yilmzmesut.readingapp.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class SchedulerService {

    private final OrderService orderService;

    public SchedulerService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 15 * 1000) // runs every 15 second and update to processing
    public void updateOrderOnDeliveryScheduler() {
        var orders = orderService.findByStatus(OrderStatus.CREATED.ordinal());
        if (!CollectionUtils.isEmpty(orders)) {
            log.warn("This orders are updated to " + OrderStatus.ON_DELIVERY.name() + "->" + orders);
        } else {
            log.warn("There is no orders on status " + OrderStatus.ON_DELIVERY.name());
        }
        Optional.ofNullable(orders).orElse(new ArrayList<>())
                .forEach(orderService::updateOrderForOnDelivery);
    }

    @Scheduled(fixedDelay = 30 * 1000)
    public void updateOrderScheduler() { // runs every 30 second and update to finished
        var orders = orderService.findByStatus(OrderStatus.ON_DELIVERY.ordinal());
        if (!CollectionUtils.isEmpty(orders)) {
            log.warn("This orders are updated to " + OrderStatus.DELIVERED.name() + "->" + orders);
        } else {
            log.warn("There is no orders on status " + OrderStatus.DELIVERED.name());
        }
        Optional.ofNullable(orders).orElse(new ArrayList<>())
                .forEach(orderService::updateOrderForDelivered);
    }
}
