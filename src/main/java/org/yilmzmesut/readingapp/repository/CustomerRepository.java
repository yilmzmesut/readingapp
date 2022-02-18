package org.yilmzmesut.readingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yilmzmesut.readingapp.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
