package com.app.ecom.store.productservice.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import com.app.ecom.store.productservice.dto.ProductDto;
import com.app.ecom.store.productservice.dto.ProductDtos;
import com.app.ecom.store.productservice.dto.ProductSearchRequest;
import com.app.ecom.store.productservice.dto.WhereClause;
import com.app.ecom.store.productservice.dto.jaxb.ProductType;
import com.app.ecom.store.productservice.dto.jaxb.ProductsType;
import com.app.ecom.store.productservice.enums.OperationType;
import com.app.ecom.store.productservice.model.Product;
import com.app.ecom.store.productservice.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class ProductMapper {
	
	private static final Logger logger = LogManager.getLogger(ProductMapper.class);
	
	@Autowired
	private CommonUtil commonUtil;
	
	public ProductDtos productsToProductDtos(List<Product> products) {
		ProductDtos productDtos = new ProductDtos();
		if(CollectionUtils.isEmpty(products)) {
			productDtos.setProducts(Collections.emptyList());
		} else {			
			List<ProductDto> listOfProductDto = new ArrayList<>();
			products.stream().forEach(product -> listOfProductDto.add(productToProductDto(product)));
			productDtos.setProducts(listOfProductDto);
		}
		return productDtos;
	}

	public ProductDto productToProductDto(Product product) {
		if(null == product) {
			return null;
		}
		
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCode(product.getCode());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setBrandName(product.getBrandName());
		productDto.setCategoryId(product.getCategoryId());
		productDto.setQuantity(product.getQuantity());
		productDto.setAlertQuantity(product.getAlertQuantity());
		productDto.setPurchasePrice(product.getPurchasePrice());
		productDto.setPerProductPrice(product.getPerProductPrice());
		if(null != product.getImage()) {
			productDto.setBase64Image(Base64.getEncoder().encodeToString(product.getImage()));
		}
		productDto.setCreatedBy(product.getCreatedBy());
		productDto.setCreatedTs(product.getCreatedTs());
		productDto.setLastModifiedBy(product.getLastModifiedBy());
		productDto.setLastModifiedTs(product.getLastModifiedTs());
		return productDto;
	}

	public Product productDtoToProduct(ProductDto productDto) {
		if(null == productDto) {
			return null;
		}
		
		Product product = new Product();
		product.setId(productDto.getId());
		product.setCode(commonUtil.generateRandomDigits("PRD", 10, ""));
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setBrandName(productDto.getBrandName());
		product.setCategoryId(productDto.getCategoryId());
		product.setQuantity(productDto.getQuantity());
		product.setAlertQuantity(productDto.getAlertQuantity());
		product.setPurchasePrice(productDto.getPurchasePrice());
		product.setPerProductPrice(productDto.getPerProductPrice());
		try {
			product.setImage(null != productDto.getImage() ? productDto.getImage().getBytes() : null);
		} catch (IOException e) {
			logger.error("unable to parse image to bytes: "+e);
		}
		product.setCreatedBy(productDto.getCreatedBy());
		product.setCreatedTs(productDto.getCreatedTs());
		product.setLastModifiedBy(productDto.getLastModifiedBy());
		product.setLastModifiedTs(productDto.getLastModifiedTs());
		return product;
	}

	public List<Product> convertProductsTypeToProducts(ProductsType productsType) {
		if (productsType == null) {
			return Collections.emptyList();
		}

		List<Product> products = new ArrayList<>();
		productsType.getProductTypes().stream().forEach(productType -> products.add(convertProductTypeToProduct(productType)));
		return products;
	}
	
	public Product convertProductTypeToProduct(ProductType productType) {
		if(null == productType) {
			return null;
		}
		
		Product product = new Product();
		product.setName(productType.getName());
		product.setCode(productType.getCode());
		product.setBrandName(productType.getBrandName());
		product.setImage("".getBytes());
		//ProductCategoryDto productCategoryDto = productCategoryService.getProductCategoryByIdOrName(null, productType.getCategory());
		//product.setCategoryId(productCategoryDto == null ? null : productCategoryDto.getId());
		product.setQuantity(Integer.parseInt(productType.getQuantity()));
		product.setAlertQuantity(Integer.parseInt(productType.getAlertQuantity()));
		product.setPurchasePrice(Double.parseDouble(productType.getPurchasePrice()));
		product.setPerProductPrice(Double.parseDouble(productType.getSellPrice()));
		return product;
	}
	
	public List<Product> convertCsvFileToProducts(InputStream is) {
		List<Product> products = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
			String line = null;
			int count = 1;
			while ((line = reader.readLine()) != null) {
				String[] attrs = line.split(",");
				if(count != 1){
					Product product = new Product();
					//ProductCategoryDto productCategoryDto = productCategoryService.getProductCategoryByIdOrName(null, attrs[0]);
					//product.setCategoryId(productCategoryDto == null ? null : productCategoryDto.getId());
					product.setName(attrs[1]);
					product.setCode(attrs[2]);
					product.setBrandName(attrs[3]);
					product.setImage("".getBytes());
					product.setQuantity(Integer.parseInt(attrs[4]));
					product.setAlertQuantity(Integer.parseInt(attrs[5]));
					product.setPurchasePrice(Double.parseDouble(attrs[6]));
					product.setPerProductPrice(Double.parseDouble(attrs[7]));
					products.add(product);
				}
				count++;
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return products;
	}

	public List<WhereClause> productSearchRequestToWhereClauses(ProductSearchRequest productSearchRequest) {
		return getWhereClauses(productSearchRequest.getProductIds(), productSearchRequest.getCategoryIds(), productSearchRequest.getProductName(), productSearchRequest.getBrandName());
	}

	private List<WhereClause> getWhereClauses(List<Long> productIds, List<Long> categoryIds, String productName, String brandName) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (!CollectionUtils.isEmpty(productIds)) {
			WhereClause whereClause = new WhereClause("id", (productIds.size()>1 ? productIds : productIds.get(0)), (productIds.size() > 1 ? OperationType.IN : OperationType.EQUALS));
			whereClauses.add(whereClause);
		} else {
			if (!CollectionUtils.isEmpty(categoryIds)) {
				WhereClause whereClause = new WhereClause("categoryId", (categoryIds.size()>1 ? categoryIds : categoryIds.get(0)), (categoryIds.size() > 1 ? OperationType.IN : OperationType.EQUALS));
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(productName)) {
				WhereClause whereClause = new WhereClause("name", productName, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
			if (!StringUtils.isEmpty(brandName)) {
				WhereClause whereClause = new WhereClause("brandName", brandName, OperationType.LIKE);
				whereClauses.add(whereClause);
			}
		}
		return whereClauses;
	}
}
