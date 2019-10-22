package com.app.ecom.store.client;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.dto.ExternalApi;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.dto.productservice.ProductDtos;
import com.app.ecom.store.dto.productservice.ProductSearchRequest;
import com.app.ecom.store.dto.productservice.StockDtos;
import com.app.ecom.store.enums.QuantityStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceClient {
	
	private static final Logger logger = LogManager.getLogger(ProductServiceClient.class);
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Value("${base.url.product-service-api}")
	private String productServiceApiBaseUrl;
	
	public ProductDto addUpdateProduct(ProductDto productDto) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/product").toString();
		ExternalApi<ProductDto> externalApi = getExternalApi(ProductDto.class, url, HttpMethod.PUT, null, null, productDto);
		ResponseEntity<ProductDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}

	public ProductDtos getProducts(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/product").toString();
		ExternalApi<ProductDtos> externalApi = getExternalApi(ProductDtos.class, url, HttpMethod.POST, null, null, productSearchRequest);
		ResponseEntity<ProductDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countProducts(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/countProduct").toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, productSearchRequest);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deleteProducts(IdsDto idsDto) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/product").toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, idsDto);
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Orders has been deleted successfully.").toString());
		} else {
			logger.error(new StringBuilder("Call to DElETE API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
		}
	}
	
	public Integer getProductsQuantity(Long productId, QuantityStatus status) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/product").toString();
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put("productId", String.valueOf(productId));
		queryParamMap.put("status", status.name());
		ExternalApi<Integer> externalApi = getExternalApi(Integer.class, url, HttpMethod.GET, null, queryParamMap, null);
		ResponseEntity<Integer> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public StockDtos getStockDetails(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/stock").toString();
		ExternalApi<StockDtos> externalApi = getExternalApi(StockDtos.class, url, HttpMethod.POST, null, null, productSearchRequest);
		ResponseEntity<StockDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Integer countStockDetails(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append("/countStock").toString();
		ExternalApi<Integer> externalApi = getExternalApi(Integer.class, url, HttpMethod.POST, null, null, productSearchRequest);
		ResponseEntity<Integer> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	private <T> ExternalApi<T> getExternalApi(Class<T> type, String url, HttpMethod method, Map<String, String> headers, Map<String, String> parameterMap, Object body) {
		ExternalApi<T> externalApi = new ExternalApi<>();
		externalApi.setType(type);
		externalApi.setUrl(url);
		externalApi.setMethod(method);
		externalApi.setHeaders(headers);
		externalApi.setParameterMap(parameterMap);
		externalApi.setBody(body);
		return externalApi;
	}

}
