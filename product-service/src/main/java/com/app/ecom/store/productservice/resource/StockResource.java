package com.app.ecom.store.productservice.resource;

import com.app.ecom.store.productservice.constants.Endpoint;
import com.app.ecom.store.productservice.dto.ProductSearchRequest;
import com.app.ecom.store.productservice.dto.StockDtos;
import com.app.ecom.store.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockResource {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = Endpoint.STOCK)
	public ResponseEntity<StockDtos> getStockDetails(@RequestBody ProductSearchRequest productSearchRequest) {
		try {
			StockDtos stockDtos = productService.getStockDetails(productSearchRequest);
			return new ResponseEntity<>(stockDtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_STOCK)
	public ResponseEntity<Integer> countStockDetails(@RequestBody ProductSearchRequest productSearchRequest) {
		try {
			Integer noOfProducts = productService.countStockDetails(productSearchRequest);
			return new ResponseEntity<>(noOfProducts, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
