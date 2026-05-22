package com.tech.springboot.ecom.repo;

import com.tech.springboot.ecom.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Optional<Order> findByOrderId(String orderId);
}
