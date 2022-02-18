package org.yilmzmesut.readingapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yilmzmesut.readingapp.entity.Customer;
import org.yilmzmesut.readingapp.entity.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findByCustomer(Customer customerId, Pageable pageable);

    @Query("SELECT sum(totalPrice) FROM Order WHERE status = 2")
    BigDecimal sumAllTotalPrices();

    Long countByStatus(Integer status);

    Optional<List<Order>> findByCreateDateAndModifyDate(Date createDate, Date modifyDate, Pageable pageable);

    Optional<List<Order>> findByStatus(Integer status);
}
