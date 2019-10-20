package com.app.ecom.store.client;

import java.util.Map;

import com.app.ecom.store.dto.ExternalApi;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.OrderDto;
import com.app.ecom.store.dto.orderservice.OrderDtos;
import com.app.ecom.store.dto.orderservice.OrderSearchRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceClient {
	
	private static final Logger logger = LogManager.getLogger(OrderServiceClient.class);
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Value("${base.url.order-service-api}")
	private String orderServiceApiBaseUrl;
	
	public OrderDto createUpdateOrder(OrderDto orderDto) {
		String url = new StringBuilder(orderServiceApiBaseUrl).append("/order").toString();
		ExternalApi<OrderDto> externalApi = getExternalApi(OrderDto.class, url, HttpMethod.PUT, null, null, orderDto);
		ResponseEntity<OrderDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public OrderDtos getOrders(OrderSearchRequest orderSearchRequest) {
		String url = new StringBuilder(orderServiceApiBaseUrl).append("/order").toString();
		ExternalApi<OrderDtos> externalApi = getExternalApi(OrderDtos.class, url, HttpMethod.POST, null, null, orderSearchRequest);
		ResponseEntity<OrderDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countOrders(OrderSearchRequest orderSearchRequest) {
		String url = new StringBuilder(orderServiceApiBaseUrl).append("/countOrder").toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, orderSearchRequest);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deleteOrders(IdsDto idsDto) {
		String url = new StringBuilder(orderServiceApiBaseUrl).append("/order").toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, idsDto);
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Orders has been deleted successfully.").toString());
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
