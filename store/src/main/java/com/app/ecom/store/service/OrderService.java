package com.app.ecom.store.service;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.List;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderDto;
import com.app.ecom.store.dto.ProductDto;
import com.app.ecom.store.model.Order;
import com.app.ecom.store.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
	OrderDto addOrder(java.util.List<ProductDto> productDtos, User user, Double totalPrice, Long addressId);

	OrderDto getOrder(Long id);

	Page<Order> getOrders(Pageable pageable);
	
	ByteArrayOutputStream createOrderPdf(Long id);

	Long countByOrderDate(ZonedDateTime orderDate);
	
	Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate);

	CustomPage<OrderDto> searchOrders(String orderNumber, String fromDate, String toDate, Long userId, Pageable pageable);
	
	Long countOrderByProductIdIn(List<Long> ids);
}
