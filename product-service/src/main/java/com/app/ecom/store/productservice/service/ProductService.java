package com.app.ecom.store.productservice.service;

import com.app.ecom.store.productservice.dto.IdsDto;
import com.app.ecom.store.productservice.dto.ProductDto;
import com.app.ecom.store.productservice.dto.ProductDtos;
import com.app.ecom.store.productservice.dto.ProductSearchRequest;
import com.app.ecom.store.productservice.dto.StockDtos;
import com.app.ecom.store.productservice.enums.QuantityStatus;

public interface ProductService {

	ProductDto createUpdateProduct(ProductDto productDto);

	ProductDtos getProducts(ProductSearchRequest productSearchRequest);

	Long countProducts(ProductSearchRequest productSearchRequest);

	void deleteProducts(IdsDto idsDto);

	Integer getProductQuantity(Long productId, QuantityStatus status);
	
	StockDtos getStockDetails(ProductSearchRequest productSearchRequest);

	Integer countStockDetails(ProductSearchRequest productSearchRequest);
}
