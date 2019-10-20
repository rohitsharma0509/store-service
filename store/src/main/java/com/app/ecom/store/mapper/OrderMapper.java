package com.app.ecom.store.mapper;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.app.ecom.store.dto.OrderDto;
import com.app.ecom.store.dto.ProductDto;
import com.app.ecom.store.enums.OrderStatusEnum;
import com.app.ecom.store.model.Address;
import com.app.ecom.store.model.Order;
import com.app.ecom.store.model.OrderDetails;
import com.app.ecom.store.model.Product;
import com.app.ecom.store.model.User;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class OrderMapper {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired 
	private AddressMapper addressMapper;

	@Autowired
	private CommonUtil commonUtil;
	
	public Order convertToOrder(java.util.List<ProductDto> productDtos, User user, Double totalAmount, Address address) {
		Order order = new Order();
		order.setOrderDate(ZonedDateTime.now());
		order.setOrderNumber(commonUtil.generateRandomDigits("ORD", 10, ""));
		order.setTotalAmount(totalAmount);
		order.setStatus(OrderStatusEnum.ORDERED.getStatus());
		order.setUser(user);
		order.setOrderDetails(productDtosToOrderDetails(productDtos, order));
		order.setAddress(address);
		return order;
	}
	
	public List<OrderDto> ordersToOrderDtos(List<Order> orders) {
		if(CollectionUtils.isEmpty(orders)){
			Collections.emptyList();
		}
		List<OrderDto> orderDtos = new ArrayList<>();
		orders.stream().forEach(order -> orderDtos.add(orderToOrderDto(order)));
		return orderDtos;
	}

	public OrderDto orderToOrderDto(Order order) {
		if(null == order) {
			return null;
		}
		
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		//orderDto.setOrderDate(commonUtil.convertZonedDateTimeToString(order.getOrderDate(), Constants.DATETIME_FORMAT_YYYYMMDDHHMMSS));
		orderDto.setStatus(order.getStatus());
		orderDto.setOrderNumber(order.getOrderNumber());
		orderDto.setTotalAmount(order.getTotalAmount());
		//orderDto.setUserDto(userMapper.userToUserDto(order.getUser(), false));
		//orderDto.setProductDtos(orderDetailsToProductDtos(order.getOrderDetails()));
		//orderDto.setAddressDto(addressMapper.addressToAddressDto(order.getAddress()));
		return orderDto;
	}

	public Set<ProductDto> orderDetailsToProductDtos(Set<OrderDetails> orderDetails) {
		Set<ProductDto> productDtos = new HashSet<>();
		orderDetails.stream().filter(Objects::nonNull).forEach(orderDetail -> {
			ProductDto productDto = new ProductDto();
			productDto.setQuantity(orderDetail.getQuantity());
			Product product = orderDetail.getProduct();
			productDto.setName(product.getName());
			productDto.setCode(product.getCode());
			productDto.setPerProductPrice(product.getPerProductPrice());
			productDtos.add(productDto);
		});
		return productDtos;
	}

	private Set<OrderDetails> productDtosToOrderDetails(List<ProductDto> productDtos, Order order) {
		Set<OrderDetails> orderDetails = new HashSet<>();

		productDtos.stream().filter(Objects::nonNull).forEach(productDto -> {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setQuantity(productDto.getQuantity());
			Product product = productMapper.productDtoToProduct(productService.getProductById(productDto.getId()));
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);
			orderDetails.add(orderDetail);
		});

		return orderDetails;
	}
}
