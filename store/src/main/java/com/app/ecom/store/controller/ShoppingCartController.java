package com.app.ecom.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.ProductDto;
import com.app.ecom.store.dto.ShoppingCart;
import com.app.ecom.store.model.User;
import com.app.ecom.store.service.AddressService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		Optional<ProductDto> optionalProductDto = shoppingCart.getProductDtos().stream().filter(productDto -> productDto.getId()==id).findFirst();
		Integer availableQuantity = productService.getAvailableQuantity(id);
		if(optionalProductDto.isPresent()){
			if(availableQuantity > optionalProductDto.get().getQuantity()) {
				optionalProductDto.get().setQuantity(optionalProductDto.get().getQuantity()+1);
			}
			optionalProductDto.get().setAvailableQuantity(availableQuantity);
		}else{
			ProductDto productDto = productService.getProductByIdForCart(id);
			productDto.setAvailableQuantity(availableQuantity);
			shoppingCart.getProductDtos().add(productDto);
		}
		
		shoppingCart.setTotalPrice(0.0);
		if(!CollectionUtils.isEmpty(shoppingCart.getProductDtos())){
			for(ProductDto productDto : shoppingCart.getProductDtos()){
				shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + (productDto.getPerProductPrice()*productDto.getQuantity()));
			}
		}
		model.addAttribute(FieldNames.SHOPPING_CART, shoppingCart);
		return "redirect:"+FieldNames.SHOPPING_CART;
	}
	
	@DeleteMapping(value = RequestUrls.DELETE_FROM_CART)
	public String removeFromCart(Model model, @PathVariable(value = FieldNames.ID) Long id) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
		
		ProductDto productDtoToDelete = null;
		if(shoppingCart != null && !CollectionUtils.isEmpty(shoppingCart.getProductDtos())){
			for(ProductDto productDto : shoppingCart.getProductDtos()){
				if(id.equals(productDto.getId())){
					productDtoToDelete = productDto;
				}
			}
			if(null != productDtoToDelete){
				shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()-productDtoToDelete.getPerProductPrice());
				shoppingCart.getProductDtos().remove(productDtoToDelete);
				
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
		User user = (User) httpSession.getAttribute(FieldNames.USER);
		if(null == id){
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCart();
			model.addAttribute(FieldNames.PRODUCTDTOS, shoppingCart.getProductDtos());
			model.addAttribute(FieldNames.TOTAL_PRICE, shoppingCart.getTotalPrice());
		}else {
			List<ProductDto> productDtos = new ArrayList<>();
			ProductDto productDto = productService.getProductByIdForCart(id);
			productDtos.add(productDto);
			model.addAttribute(FieldNames.PRODUCTDTOS, productDtos);
			model.addAttribute(FieldNames.TOTAL_PRICE, productDto.getPerProductPrice());
		}
		model.addAttribute("addresses", addressService.getAddressByUser(user));
		return "shoppingCartConfirm";
	}
	
	@PostMapping(value = RequestUrls.SHOPPING_CART)
	public String updateShoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart) {
		model.addAttribute(FieldNames.SHOPPING_CART, shoppingCartService.updateShoppingCart(shoppingCart));
		return "redirect:checkout";
	}
}