package com.app.ecom.store.client;

import java.util.Map;

import com.app.ecom.store.dto.ExternalApi;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDtos;
import com.app.ecom.store.dto.masterdata.ProductCategorySearchRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MasterDataClient {
	
	private static final Logger logger = LogManager.getLogger(MasterDataClient.class);
	
	private static final String CATEGORY = "/category";
	
	private static final String COUNT_CATEGORY = "/countCategory";
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Value("${base-urls.master-data-api}")
	private String masterDataApiBaseUrl;
	
	public ProductCategoryDto addUpdateProductCategory(ProductCategoryDto productCategoryDto) {
		String url = new StringBuilder(masterDataApiBaseUrl).append(CATEGORY).toString();
		ExternalApi<ProductCategoryDto> externalApi = getExternalApi(ProductCategoryDto.class, url, HttpMethod.PUT, null, null, productCategoryDto);
		ResponseEntity<ProductCategoryDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public ProductCategoryDtos getProductCategories(ProductCategorySearchRequest productCategorySearchRequest) {
		String url = new StringBuilder(masterDataApiBaseUrl).append(CATEGORY).toString();
		ExternalApi<ProductCategoryDtos> externalApi = getExternalApi(ProductCategoryDtos.class, url, HttpMethod.POST, null, null, productCategorySearchRequest);
		ResponseEntity<ProductCategoryDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countProductCategories(ProductCategoryDto productCategoryDto) {
		String url = new StringBuilder(masterDataApiBaseUrl).append(COUNT_CATEGORY).toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, productCategoryDto);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deleteProductCategories(IdsDto idsDto) {
		String url = new StringBuilder(masterDataApiBaseUrl).append(CATEGORY).toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Product categories has been deleted successfully.").toString());
		} else {
			logger.error(new StringBuilder("Call to DElETE API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
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
