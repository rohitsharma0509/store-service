package com.app.ecom.store.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.OrderDto;
import com.app.ecom.store.dto.ProductDto;
import com.app.ecom.store.dto.ShoppingCart;
import com.app.ecom.store.dto.orderservice.OrderDetailDto;
import com.app.ecom.store.model.User;
import com.app.ecom.store.service.OrderService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.service.ShoppingCartService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value = RequestUrls.BUY)
	public String buyNow(HttpServletRequest request, @RequestParam(value = "addressId", required=true) Long addressId, @RequestParam(value = "productId", required=false) Long id) {
		OrderDto orderDto;
		User user = (User) httpSession.getAttribute("user");
		if(StringUtils.isEmpty(id)) {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
			orderDto = orderService.addOrder(shoppingCart.getOrderDetailDtos(), user, shoppingCart.getTotalPrice(), addressId);
			shoppingCartService.removeShoppingCart();
		} else {
			List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
			ProductDto productDto = productService.getProductByIdForCart(id);
			OrderDetailDto orderDetailDto = getOrderDetailDto(id, productDto);
			orderDetailDtos.add(orderDetailDto);
			orderDto = orderService.addOrder(orderDetailDtos, user, productDto.getPerProductPrice(), addressId);
		}
		return "redirect:orders/" + orderDto.getId();
	}
	
	@GetMapping(value = RequestUrls.GET_ORDERS)
	public String getOrder(Model model, @PathVariable(value = "id") Long id) {
		OrderDto orderDto = orderService.getOrder(id);
		model.addAttribute("orderDto", orderDto);
		model.addAttribute("userDto", orderService.getUserDtoByUserId(orderDto.getUserId()));
		model.addAttribute("addressDto", orderService.getAddressDtoByAddressId(orderDto.getAddressId()));
		return "order";
	}
	
	@GetMapping(value =RequestUrls.ORDERS)
	public String searchOrders(Model model, @RequestParam(required=false) String orderNumber, @RequestParam(required=false) String fromDate, @RequestParam(required=false) String toDate, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		User user = (User) httpSession.getAttribute("user");
		
		Map<String, String> params = new HashMap<>();
		params.put("orderNumber", orderNumber);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		params.put("userId", String.valueOf(user.getId()));
		CustomPage<OrderDto> page = orderService.getOrders(pageable, params);
		model.addAttribute("pagging", commonUtil.getPagging("orders", page.getPageNumber()+1, page.getTotalPages(), params));
		model.addAttribute("page", page);
		return "orders";	
	}
	
	@GetMapping(value = RequestUrls.DOWNLOAD_ORDER)
	public void downloadOrder(Model model, HttpServletResponse response,
			@PathVariable(value = "id") Long id) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+ id + ".pdf\"");
		ByteArrayOutputStream baos = orderService.createOrderPdf(id);
	    response.setContentLength(baos.size());
	    baos.writeTo(response.getOutputStream());
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
