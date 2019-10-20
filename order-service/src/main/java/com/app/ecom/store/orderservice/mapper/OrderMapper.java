package com.app.ecom.store.orderservice.mapper;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.app.ecom.store.orderservice.dto.OrderDetailDto;
import com.app.ecom.store.orderservice.dto.OrderDto;
import com.app.ecom.store.orderservice.dto.OrderDtos;
import com.app.ecom.store.orderservice.dto.OrderSearchRequest;
import com.app.ecom.store.orderservice.dto.WhereClause;
import com.app.ecom.store.orderservice.enums.OperationType;
import com.app.ecom.store.orderservice.enums.OrderStatus;
import com.app.ecom.store.orderservice.model.Order;
import com.app.ecom.store.orderservice.model.OrderDetail;
import com.app.ecom.store.orderservice.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class OrderMapper {
	
	@Autowired
	private CommonUtil commonUtil;
	
	public List<Order> orderDtosToOrders(OrderDtos orderDtos) {
		if(null != orderDtos && CollectionUtils.isEmpty(orderDtos.getOrders())) {
			return Collections.emptyList();
		}
		
		List<Order> orders = new ArrayList<>();
		orderDtos.getOrders().stream().forEach(orderDto -> orders.add(orderDtoToOrder(orderDto)));
		return orders;
		
	}
	
	public Order orderDtoToOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setOrderNumber(commonUtil.generateRandomDigits("ORD", 10, ""));
		order.setTotalAmount(orderDto.getTotalAmount());
		order.setStatus(OrderStatus.ORDERED.getStatus());
		order.setUserId(orderDto.getUserId());
		order.setOrderDetails(orderDetailsDtosToOrderDetails(orderDto.getOrderDetailDtos(), order));
		order.setAddressId(orderDto.getAddressId());
		order.setCreatedBy(orderDto.getCreatedBy());
		order.setCreatedTs(ZonedDateTime.now());
		order.setLastModifiedBy(orderDto.getLastModifiedBy());
		order.setLastModifiedTs(ZonedDateTime.now());
		return order;
	}
	
	public Set<OrderDetail> orderDetailsDtosToOrderDetails(List<OrderDetailDto> orderDetailDtos, Order order) {
		Set<OrderDetail> orderDetails = new HashSet<>();

		orderDetailDtos.stream().filter(Objects::nonNull).forEach(orderDetailDto -> {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(orderDetailDto.getQuantity());
			orderDetail.setProductId(orderDetailDto.getProductId());
			orderDetail.setOrder(order);
			orderDetails.add(orderDetail);
		});

		return orderDetails;
	}
	
	public OrderDtos ordersToOrderDtos(List<Order> orders) {
		if(CollectionUtils.isEmpty(orders)){
			Collections.emptyList();
		}

		OrderDtos orderDtos = new OrderDtos();
		List<OrderDto> listOfOrderDtos = new ArrayList<>();
		orders.stream().forEach(order -> listOfOrderDtos.add(orderToOrderDto(order)));
		orderDtos.setOrders(listOfOrderDtos);
		return orderDtos;
	}

	public OrderDto orderToOrderDto(Order order) {
		if(null == order) {
			return null;
		}
		
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setCreatedTs(order.getCreatedTs());
		orderDto.setStatus(order.getStatus());
		orderDto.setOrderNumber(order.getOrderNumber());
		orderDto.setTotalAmount(order.getTotalAmount());
		orderDto.setUserId(order.getUserId());
		orderDto.setOrderDetailDtos(orderDetailsToOrderDetailDtos(order.getOrderDetails()));;
		orderDto.setAddressId(order.getAddressId());
		return orderDto;
	}

	public List<OrderDetailDto> orderDetailsToOrderDetailDtos(Set<OrderDetail> orderDetails) {
		List<OrderDetailDto> orderDetailsDtos = new ArrayList<>();
		orderDetails.stream().filter(Objects::nonNull).forEach(orderDetail -> {
			OrderDetailDto orderDetailsDto = new OrderDetailDto();
			orderDetailsDto.setQuantity(orderDetail.getQuantity());
			orderDetailsDto.setProductId(orderDetail.getProductId());
			orderDetailsDtos.add(orderDetailsDto);
		});
		return orderDetailsDtos;
	}
	
	public List<WhereClause> orderSearchRequestToWhereClauses(OrderSearchRequest orderSearchRequest) {
		return getWhereClauses(orderSearchRequest.getOrderId(), orderSearchRequest.getOrderNumber(), orderSearchRequest.getFromDate(), orderSearchRequest.getToDate(), orderSearchRequest.getUserId(), orderSearchRequest.getProductIds());
	}
	
	private List<WhereClause> getWhereClauses(Long id, String orderNumber, ZonedDateTime fromDate, ZonedDateTime toDate, Long userId, List<Long> productIds) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (id != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(id), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else {
			if (!StringUtils.isEmpty(orderNumber)) {
				WhereClause whereClause = new WhereClause("orderNumber", orderNumber, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(fromDate)) {
				WhereClause whereClause = new WhereClause("createdTs", fromDate, OperationType.GREATER_OR_EQUAL);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(toDate)) {
				WhereClause whereClause = new WhereClause("createdTs", toDate, OperationType.LESS_OR_EQUAL);
				whereClauses.add(whereClause);
			}
			if (userId != null) {
				WhereClause whereClause = new WhereClause("userId", userId, OperationType.EQUALS);
				whereClauses.add(whereClause);
			}
			
		}
		return whereClauses;
	}
}
