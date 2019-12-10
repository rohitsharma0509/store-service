package com.app.ecom.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.ShoppingCart;
import com.app.ecom.store.dto.orderservice.OrderDetailDto;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.AddressService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = RequestUrls.ADD_TO_CART)
	public String addToCart(Model model, @RequestParam(value = FieldNames.ID, required=true) Long id) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
		Optional<OrderDetailDto> optionalOrderDetailDto = shoppingCart.getOrderDetailDtos().stream().filter(orderDetailDto -> id.equals(orderDetailDto.getProductId())).findFirst();
		Integer availableQuantity = productService.getAvailableQuantity(id);
		ProductDto productDto = productService.getProductById(id);
		if(optionalOrderDetailDto.isPresent()) {
			if(availableQuantity > optionalOrderDetailDto.get().getQuantity()) {
				optionalOrderDetailDto.get().setQuantity(optionalOrderDetailDto.get().getQuantity() + 1);
				shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()==null? 0:shoppingCart.getTotalPrice() + productDto.getPerProductPrice());
			}
			optionalOrderDetailDto.get().setAvailableQuantity(availableQuantity);
		} else {
			OrderDetailDto orderDetailDto = new OrderDetailDto();
			orderDetailDto.setQuantity(1);
			orderDetailDto.setProductId(id);
			orderDetailDto.setName(productDto.getName());
			orderDetailDto.setCode(productDto.getCode());
			orderDetailDto.setPerProductPrice(productDto.getPerProductPrice());
			orderDetailDto.setAvailableQuantity(availableQuantity);
			shoppingCart.getOrderDetailDtos().add(orderDetailDto);
			shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()==null?0:shoppingCart.getTotalPrice() + productDto.getPerProductPrice());
		}
		model.addAttribute(FieldNames.SHOPPING_CART, shoppingCart);
		return "redirect:"+FieldNames.SHOPPING_CART;
	}
	
	@PostMapping(value = RequestUrls.DELETE_FROM_CART)
	public String removeFromCart(Model model, @PathVariable(value = FieldNames.ID) Long id) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
		
		OrderDetailDto orderDetailDtoToDelete = null;
		if(shoppingCart != null && !CollectionUtils.isEmpty(shoppingCart.getOrderDetailDtos())){
			for(OrderDetailDto orderDetailDto : shoppingCart.getOrderDetailDtos()){
				if(id.equals(orderDetailDto.getProductId())){
					orderDetailDtoToDelete = orderDetailDto;
				}
			}
			if(null != orderDetailDtoToDelete){
				shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()-(orderDetailDtoToDelete.getPerProductPrice()*orderDetailDtoToDelete.getQuantity()));
				shoppingCart.getOrderDetailDtos().remove(orderDetailDtoToDelete);
				
			}
		}
		model.addAttribute(FieldNames.SHOPPING_CART, shoppingCart);
		return FieldNames.SHOPPING_CART;
	}
	
	@GetMapping(value = RequestUrls.SHOPPING_CART)
	public String getShoppingCart(Model model) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
		model.addAttribute(FieldNames.SHOPPING_CART, shoppingCart);
		return FieldNames.SHOPPING_CART;
	}
	
	@GetMapping(value = RequestUrls.CHECKOUT)
	public String checkout(Model model, HttpServletRequest request, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		HttpSession httpSession = request.getSession();
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		if(null == id){
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
			model.addAttribute(FieldNames.ORDER_DETAIL_DTOS, shoppingCart.getOrderDetailDtos());
			model.addAttribute(FieldNames.TOTAL_PRICE, shoppingCart.getTotalPrice());
		}else {
			List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
			ProductDto productDto = productService.getProductById(id);
			OrderDetailDto orderDetailDto = getOrderDetailDto(id, productDto);
			orderDetailDtos.add(orderDetailDto);
			model.addAttribute(FieldNames.ORDER_DETAIL_DTOS, orderDetailDtos);
			model.addAttribute(FieldNames.TOTAL_PRICE, productDto.getPerProductPrice());
		}
		model.addAttribute(FieldNames.ADDRESSES, addressService.getAddressByUserId(userDto.getId()));
		return View.SHOPPING_CART_CONFIRM;
	}
	
	@PostMapping(value = RequestUrls.SHOPPING_CART)
	public String updateShoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart) {
		model.addAttribute(FieldNames.SHOPPING_CART, shoppingCartService.updateShoppingCart(shoppingCart));
		return "redirect:"+RequestUrls.CHECKOUT;
	}
	
	private OrderDetailDto getOrderDetailDto(Long productId, ProductDto productDto) {
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		orderDetailDto.setQuantity(1);
		orderDetailDto.setProductId(productId);
		orderDetailDto.setName(productDto.getName());
		orderDetailDto.setCode(productDto.getCode());
		orderDetailDto.setPerProductPrice(productDto.getPerProductPrice());
		return orderDetailDto;
	}
}