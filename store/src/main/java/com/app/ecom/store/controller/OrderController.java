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
	public String buyNow(HttpServletRequest request, @RequestParam(value = "addressId", required=true) Long addressId, @RequestParam(value = "productId", required=false) String id) {
		OrderDto orderDto;
		User user = (User) httpSession.getAttribute("user");
		if(StringUtils.isEmpty(id)) {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
			orderDto = orderService.addOrder(shoppingCart.getProductDtos(), user, shoppingCart.getTotalPrice(), addressId);
			shoppingCartService.removeShoppingCart();
		} else {
			List<ProductDto> productDtos = new ArrayList<>();
			ProductDto productDto = productService.getProductByIdForCart(Long.parseLong(id));
			productDtos.add(productDto);
			orderDto = orderService.addOrder(productDtos, user, productDto.getPerProductPrice(), addressId);
		}
		return "redirect:orders/" + orderDto.getId();
	}
	
	@GetMapping(value = RequestUrls.GET_ORDERS)
	public String getOrder(Model model, @PathVariable(value = "id") Long id) {
		OrderDto orderDto = orderService.getOrder(id);
		model.addAttribute("orderDto", orderDto);
		return "order";
	}
	
	@GetMapping(value =RequestUrls.ORDERS)
	public String searchOrders(Model model, @RequestParam(required=false) String orderNumber, @RequestParam(required=false) String fromDate, @RequestParam(required=false) String toDate, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		User user = (User) httpSession.getAttribute("user");
		CustomPage<OrderDto> page = orderService.searchOrders(orderNumber, fromDate, toDate, user.getId(), pageable);
		
		Map<String, String> params = new HashMap<>();
		params.put("orderNumber", orderNumber);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		
		model.addAttribute("pagging", commonUtil.getPagging("orders", page.getPageNumber()+1, page.getTotalPages(), null));
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
}
