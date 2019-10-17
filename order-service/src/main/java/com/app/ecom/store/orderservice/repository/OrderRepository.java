package com.app.ecom.store.orderservice.repository;

import java.util.List;

import com.app.ecom.store.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	void deleteByIdIn(List<Long> ids);
}
