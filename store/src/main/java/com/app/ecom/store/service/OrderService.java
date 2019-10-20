package com.app.ecom.store.service;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.AddressDto;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderDto;
import com.app.ecom.store.dto.UserDto;
import com.app.ecom.store.dto.orderservice.OrderDetailDto;
import com.app.ecom.store.model.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
	OrderDto addOrder(java.util.List<OrderDetailDto> orderDetailDtos, User user, Double totalPrice, Long addressId);

	OrderDto getOrder(Long id);

	CustomPage<OrderDto> getOrders(Pageable pageable, Map<String, String> params);
	
	ByteArrayOutputStream createOrderPdf(Long id);

	Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate);
	
	Long countOrderByProductIdIn(List<Long> ids);

	UserDto getUserDtoByUserId(Long userId);
	
	AddressDto getAddressDtoByAddressId(Long addressId);
}
