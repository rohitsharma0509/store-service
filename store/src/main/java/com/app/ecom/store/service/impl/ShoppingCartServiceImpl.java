package com.app.ecom.store.service.impl;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.ShoppingCart;
import com.app.ecom.store.service.ShoppingCartService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private static final Logger logger = Logger.getLogger(ShoppingCartServiceImpl.class);
	
	@Autowired
	private HttpSession session;

	@Override
	public ShoppingCart getShoppingCart() {
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(FieldNames.SHOPPING_CART);
		
		if(shoppingCart == null){
			session.setAttribute(FieldNames.SHOPPING_CART, new ShoppingCart());
		}
		return (ShoppingCart) session.getAttribute(FieldNames.SHOPPING_CART);
	}

	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		session.setAttribute(FieldNames.SHOPPING_CART, shoppingCart);
		logger.info("Product has been added to shopping cart.");
		return shoppingCart;
	}
	
	public void removeShoppingCart(){
		session.removeAttribute(FieldNames.SHOPPING_CART);
		logger.info("Product has been removed from shopping cart.");
	}
}
