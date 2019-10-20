package com.app.ecom.store.orderservice.service.impl;

import java.util.List;

import com.app.ecom.store.orderservice.dto.IdsDto;
import com.app.ecom.store.orderservice.dto.OrderDto;
import com.app.ecom.store.orderservice.dto.OrderDtos;
import com.app.ecom.store.orderservice.dto.OrderSearchRequest;
import com.app.ecom.store.orderservice.dto.QueryRequest;
import com.app.ecom.store.orderservice.handler.QueryHandler;
import com.app.ecom.store.orderservice.mapper.OrderMapper;
import com.app.ecom.store.orderservice.model.Order;
import com.app.ecom.store.orderservice.repository.OrderRepository;
import com.app.ecom.store.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private QueryHandler<Order> queryHandler;

	@Override
	@Transactional
	public OrderDto createUpdateOrder(OrderDto orderDto) {
		return orderMapper.orderToOrderDto(orderRepository.save(orderMapper.orderDtoToOrder(orderDto)));
	}

	@Override
	public OrderDtos getOrders(OrderSearchRequest orderSearchRequest) {
		queryHandler.setType(Order.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(orderMapper.orderSearchRequestToWhereClauses(orderSearchRequest));
		queryRequest.setOrderByClauses(orderSearchRequest.getOrderByClauses());
		queryRequest.setOffset(orderSearchRequest.getOffset());
		queryRequest.setLimit(orderSearchRequest.getLimit());
		List<Order> orders = queryHandler.findByQueryRequest(queryRequest);
		return orderMapper.ordersToOrderDtos(orders);
	}

	@Override
	public Long countOrders(OrderSearchRequest orderSearchRequest) {
		queryHandler.setType(Order.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(orderMapper.orderSearchRequestToWhereClauses(orderSearchRequest));
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	public void deleteOrders(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			orderRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			orderRepository.deleteById(idsDto.getIds().get(0));
		} else {
			orderRepository.deleteByIdIn(idsDto.getIds());
		}

	}

}
