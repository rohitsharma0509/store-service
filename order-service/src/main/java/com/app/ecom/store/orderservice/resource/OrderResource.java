package com.app.ecom.store.orderservice.resource;

import com.app.ecom.store.orderservice.constants.Endpoint;
import com.app.ecom.store.orderservice.dto.IdsDto;
import com.app.ecom.store.orderservice.dto.OrderDto;
import com.app.ecom.store.orderservice.dto.OrderDtos;
import com.app.ecom.store.orderservice.dto.OrderSearchRequest;
import com.app.ecom.store.orderservice.service.OrderService;
import com.app.ecom.store.orderservice.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderResource {
	
	private static final Logger logger = LogManager.getLogger(OrderResource.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.ORDER)
	public ResponseEntity<OrderDto> createUpdateOrder(@RequestBody OrderDto orderDto) {
		try {
			OrderDto createdOrderDto = orderService.createUpdateOrder(orderDto);
			return new ResponseEntity<>(createdOrderDto, orderDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while creating order : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.ORDER)
	public ResponseEntity<OrderDtos> getAllOrders(@RequestBody OrderSearchRequest orderSearchRequest) {
		try {
			OrderDtos orderDtos = orderService.getOrders(orderSearchRequest);
			return new ResponseEntity<>(orderDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting orders : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_ORDER)
	public ResponseEntity<Long> countOrders(@RequestBody OrderSearchRequest orderSearchRequest) {
		try {
			Long noOfOrders = orderService.countOrders(orderSearchRequest);
			return new ResponseEntity<>(noOfOrders, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting order : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.ORDER)
	public ResponseEntity<Void> deleteOrders(@RequestBody IdsDto idsDto) {
		try {
			orderService.deleteOrders(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting order : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}