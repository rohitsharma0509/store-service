package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.app.ecom.store.client.ProductServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.OrderByClause;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.dto.productservice.ProductDtos;
import com.app.ecom.store.dto.productservice.ProductSearchRequest;
import com.app.ecom.store.dto.productservice.StockDto;
import com.app.ecom.store.dto.productservice.StockDtos;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.ProductStatus;
import com.app.ecom.store.enums.QuantityStatus;
import com.app.ecom.store.enums.SortOrder;
import com.app.ecom.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductServiceClient productServiceClient;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public ProductDto getProductById(Long id) {
		ProductSearchRequest productSearchRequest = getProductSearchRequest(Arrays.asList(id), null, null);
		ProductDtos productDtos = productServiceClient.getProducts(productSearchRequest);
		if(null != productDtos && !CollectionUtils.isEmpty(productDtos.getProducts())) {
			Optional<ProductDto> optional = productDtos.getProducts().stream().filter(Objects::nonNull).findFirst();
			return optional.isPresent() ? optional.get() : null;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public ProductDto addProduct(ProductDto productDto) {
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		if(productDto.getId() != null) {
			ProductDto existingProductDto = getProductById(productDto.getId());
			productDto.setCreatedBy(existingProductDto.getCreatedBy());
			productDto.setCreatedTs(existingProductDto.getCreatedTs());
			productDto.setLastModifiedBy(userDto.getUsername());
			productDto.setLastModifiedTs(ZonedDateTime.now());
		} else {
			productDto.setCreatedBy(userDto.getUsername());
			productDto.setCreatedTs(ZonedDateTime.now());
			productDto.setLastModifiedBy(userDto.getUsername());
			productDto.setLastModifiedTs(ZonedDateTime.now());
		}
		return productServiceClient.addUpdateProduct(productDto);
	}
	
	@Override
	public CustomPage<ProductDto> searchProducts(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		
		List<Long> categoryIds = new ArrayList<>();
		if(!StringUtils.isEmpty(params.get(FieldNames.CATEGORY_ID))) {
			categoryIds.add(Long.parseLong(params.get(FieldNames.CATEGORY_ID)));
		}
		ProductSearchRequest productSearchRequest = getProductSearchRequest(null, categoryIds, params);
		List<OrderByClause> orderByClauses = new ArrayList<>();
		OrderByClause orderByClause = new OrderByClause();
		orderByClause.setSortBy(FieldNames.CREATED_TS);
		orderByClause.setSortOrder(SortOrder.DESC);
		orderByClauses.add(orderByClause);
		productSearchRequest.setOrderByClauses(orderByClauses);
		productSearchRequest.setOffset(offset);
		productSearchRequest.setLimit(limit);
		ProductDtos productDtos = productServiceClient.getProducts(productSearchRequest);
		
		Long totalRecords = productServiceClient.countProducts(productSearchRequest);
		CustomPage<ProductDto> page = new CustomPage<>();
		if(productDtos != null) {
			productDtos.getProducts().stream().filter(Objects::nonNull).forEach(productDto -> productDto.setAvailableQuantity(productServiceClient.getProductsQuantity(productDto.getId(), QuantityStatus.AVAILED)));
			page.setContent(productDtos.getProducts());
		}
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalRecords(totalRecords == null ? 0 : totalRecords.intValue());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		ProductDtos productDtos = productServiceClient.getProducts(new ProductSearchRequest());
		return productDtos == null ? null : productDtos.getProducts();
	}
	
	@Override
	public Long getNumberOfProducts() {
		return productServiceClient.countProducts(new ProductSearchRequest());
	}

	@Override
	public CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params) {
		return getStockDetails(pageable, params, false);
	}
	
	@Override
	public CustomPage<StockDto> getStockDetails(Pageable pageable, Map<String, String> params, Boolean isExcel) {
		CustomPage<StockDto> page = new CustomPage<>();
		List<Long> categoryIds = new ArrayList<>();
		if(null != params && !StringUtils.isEmpty(params.get(FieldNames.CATEGORY_ID))) {
			categoryIds = Arrays.asList(Long.parseLong(params.get(FieldNames.CATEGORY_ID)));
		}
		ProductSearchRequest productSearchRequest = getProductSearchRequest(null, categoryIds, params);
		productSearchRequest.setIsExcel(isExcel);
		if(null != pageable) {			
			int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
			int limit = pageable.getPageSize();
			productSearchRequest.setOffset(offset);
			productSearchRequest.setLimit(limit);
		}
		
		StockDtos stockDtos = productServiceClient.getStockDetails(productSearchRequest);
		if(!isExcel && null != pageable){
			Integer totalRecords = productServiceClient.countStockDetails(productSearchRequest);
			page.setPageNumber(pageable.getPageNumber() - 1);
			page.setSize(pageable.getPageSize());
			page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		}
		page.setContent(stockDtos == null ? null : stockDtos.getStocks());
		return page;
	}

	@Override
	public Long getAlertProductQuantity() {
		ProductSearchRequest productSearchRequest = new ProductSearchRequest();
		productSearchRequest.setStatus(ProductStatus.ALERT);
		return productServiceClient.countProducts(productSearchRequest);
	}

	@Override
	public Long getOutOfStockProductQuantity() {
		ProductSearchRequest productSearchRequest = new ProductSearchRequest();
		productSearchRequest.setStatus(ProductStatus.OUT_OF_STOCK);
		return productServiceClient.countProducts(productSearchRequest);
	}
	
	@Override
	public void importProducts(MultipartFile multiPartFile, String fileType){
		productServiceClient.importProducts(multiPartFile, fileType);
	}
	
	@Override
	public Integer getAvailableQuantity(Long id) {
		return productServiceClient.getProductsQuantity(id, QuantityStatus.AVAILED);
	}
	
	@Override
	public void deleteProducts(IdsDto idsDto) {
		productServiceClient.deleteProducts(idsDto);
	}
	
	@Override
	public Long countByCategoryIdIn(List<Long> categoryIds) {
		return productServiceClient.countProducts(getProductSearchRequest(null, categoryIds, null));
	}
	
	@Override
	public Long countByCategoryId(Long categoryId) {
		return productServiceClient.countProducts(getProductSearchRequest(null, Arrays.asList(categoryId), null));
	}
	
	private ProductSearchRequest getProductSearchRequest(List<Long> productIds, List<Long> categoryIds, Map<String, String> params) {
		ProductSearchRequest productSearchRequest = new ProductSearchRequest();
		productSearchRequest.setProductIds(productIds);
		productSearchRequest.setCategoryIds(categoryIds);
		productSearchRequest.setProductName(params == null ? null : params.get(FieldNames.PRODUCT_NAME));
		productSearchRequest.setBrandName(params == null ? null : params.get(FieldNames.BRAND_NAME));
		return productSearchRequest;
	}
}