package com.app.ecom.store.repository;

import java.time.ZonedDateTime;
import java.util.List;

import com.app.ecom.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Long countByOrderDate(ZonedDateTime orderDate);
	
	Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate);

	@Query(value = "select count(o.order_id) from orders o left join order_details od on o.order_id = od.order_id where od.product_id in(:productIds)", nativeQuery = true)
	Long countOrderByProductIdIn(@Param(value="productIds") List<Long> productIds);
}
