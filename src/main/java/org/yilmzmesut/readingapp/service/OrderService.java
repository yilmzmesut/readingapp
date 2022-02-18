package org.yilmzmesut.readingapp.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yilmzmesut.readingapp.exception.ReadingAppErrorCode;
import org.yilmzmesut.readingapp.exception.ReadingAppException;
import org.yilmzmesut.readingapp.mapper.CustomerMapper;
import org.yilmzmesut.readingapp.mapper.OrderMapper;
import org.yilmzmesut.readingapp.model.dto.BookDTO;
import org.yilmzmesut.readingapp.model.dto.OrderDTO;
import org.yilmzmesut.readingapp.model.enums.OrderStatus;
import org.yilmzmesut.readingapp.model.request.CreateOrderRequest;
import org.yilmzmesut.readingapp.model.request.SearchOrderRequest;
import org.yilmzmesut.readingapp.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final BookService bookService;
    private final CustomerMapper customerMapper;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, BookService bookService,
                        OrderMapper orderMapper, CustomerMapper customerMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.bookService = bookService;
        this.customerMapper = customerMapper;
    }

    public List<OrderDTO> getCustomerOrders(Long customerId, int page, int size) {
        var customerDTO = customerService.findById(customerId);
        var pageable = PageRequest.of(page, size, Sort.by("id"));
        var customerOrders = orderRepository.findByCustomer(customerMapper.toEntity(customerDTO), pageable);
        return orderMapper.toDTOList(customerOrders.orElse(null));
    }

    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request) {
        Objects.requireNonNull(request);
        if (!CollectionUtils.isEmpty(request.getBooks())) {
            var customerDTO = customerService.findById(request.getCustomerId());
            OrderDTO order = new OrderDTO();
            request.getBooks()
                    .forEach(bookOrderRequest -> bookService.decreaseStocks(bookOrderRequest.getBookId(), bookOrderRequest.getQuantity()));
            order.setCustomer(customerDTO);
            order.setTotalPrice(bookService.getTotalPrice(request.getBooks()));
            order.setBooks(request.getBooks().stream()
                    .map(bookOrderRequest -> bookService.findById(bookOrderRequest.getBookId())).collect(Collectors.toSet()));
            order.setStatus(OrderStatus.CREATED.ordinal());
            var savedOrder = orderRepository.save(orderMapper.toEntity(order));
            return orderMapper.toDTO(savedOrder);
        }
        return null;
    }

    public OrderDTO findById(Long id) {
        Objects.requireNonNull(id);
        var order = orderRepository.findById(id);
        if (order.isPresent()) {
            return orderMapper.toDTO(order.get());
        }
        throw new ReadingAppException(ReadingAppErrorCode.ENTITY_NOT_FOUND, "Order is not found by id = " + id);
    }

    public List<OrderDTO> findByStatus(Integer status) {
        return orderMapper.toDTOList(orderRepository.findByStatus(status).orElse(null));
    }

    public List<OrderDTO> searchOrders(SearchOrderRequest searchOrderRequest) {
        return orderMapper.toDTOList(
                orderRepository.findByCreateDateAndModifyDate(
                                searchOrderRequest.getCreateDate(),
                                searchOrderRequest.getModifyDate(),
                                PageRequest.of(searchOrderRequest.getPage(), searchOrderRequest.getSize(), Sort.by("id")))
                        .orElse(null)
        );
    }

    public long getTotalOrderCount() {
        return orderRepository.count();
    }

    public BigDecimal getTotalPurchasedOrderAmount() {
        return orderRepository.sumAllTotalPrices();
    }

    public Long getTotalPurchasedOrderCount() {
        return orderRepository.countByStatus(OrderStatus.DELIVERED.ordinal());
    }

    @Transactional
    public void updateOrderForOnDelivery(OrderDTO orderDTO) {
        orderDTO.setStatus(OrderStatus.ON_DELIVERY.ordinal());
        orderRepository.save(orderMapper.toEntity(orderDTO));
    }

    @Transactional
    public void updateOrderForDelivered(OrderDTO orderDTO) {
        orderDTO.setStatus(OrderStatus.DELIVERED.ordinal());
        orderRepository.save(orderMapper.toEntity(orderDTO));
    }
}
