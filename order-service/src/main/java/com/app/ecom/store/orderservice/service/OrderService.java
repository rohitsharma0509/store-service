package com.app.ecom.store.orderservice.service;

import com.app.ecom.store.orderservice.dto.IdsDto;
import com.app.ecom.store.orderservice.dto.OrderDto;
import com.app.ecom.store.orderservice.dto.OrderDtos;
import com.app.ecom.store.orderservice.dto.OrderSearchRequest;

public interface OrderService {

	OrderDto createUpdateOrder(OrderDto orderDto);

	OrderDtos getOrders(OrderSearchRequest orderSearchRequest);
	
	Long countOrders(OrderSearchRequest orderSearchRequest);
	
	void deleteOrders(IdsDto idsDto);
	
}
