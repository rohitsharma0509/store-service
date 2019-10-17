package com.app.ecom.store.service;

import com.app.ecom.store.dto.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart getShoppingCart();
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void removeShoppingCart();
}
