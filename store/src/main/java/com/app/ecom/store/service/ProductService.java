package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.StockDto;
import com.app.ecom.store.dto.productservice.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	ProductDto getProductById(Long id);

	ProductDto addProduct(ProductDto product);

	CustomPage<ProductDto> searchProducts(Pageable pageable, Map<String, String> params);
	
	List<ProductDto> getAllProducts();

	Long getNumberOfProducts();

	CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params, Boolean isExcel);
	
	CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params);
	
	Long getAlertProductQuantity();
	
	Long getOutOfStockProductQuantity();

	void importProducts(MultipartFile multiPartFile, String fileType);
	
	Integer getAvailableQuantity(Long id);
	
	void deleteProducts(IdsDto idsDto);
	
	Long countByCategoryId(Long categoryId);
	
	Long countByCategoryIdIn(List<Long> categoryIds);
}
