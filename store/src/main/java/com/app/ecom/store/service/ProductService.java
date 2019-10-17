package com.app.ecom.store.service;

import java.util.List;
import java.util.Map;

import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.ProductDto;
import com.app.ecom.store.dto.StockDto;
import com.app.ecom.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
	
	ProductDto getProductByIdForCart(Long id);

	ProductDto getProductById(Long id);

	Product addProduct(ProductDto product);

	Page<Product> getProducts(Pageable pageable);
	
	CustomPage<Product> searchProducts(Pageable pageable, Map<String, String> params);
	
	CustomPage<ProductDto> searchProductDtos(Pageable pageable, Map<String, String> params);

	List<ProductDto> getAllProducts();

	void deleteProductById(Long id);

	Product editProduct(Product product);
	
	Long getNumberOfProducts();

	CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params, Boolean isExcel);
	
	CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params);
	
	Long getAlterProductQuantity();
	
	Long getOutOfStockProductQuantity();

	void importProducts(MultipartFile multiPartFile, String fileType);
	
	Integer getAvailableQuantity(Long id);
	
	boolean deleteAllProducts();

	boolean deleteProducts(List<Long> ids);
	
	Long countByCategoryId(Long categoryId);
	
	Long countByCategoryIdIn(List<Long> categoryIds);
}
