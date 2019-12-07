package com.app.ecom.store.client;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.orderservice.OrderDto;
import com.app.ecom.store.dto.orderservice.OrderDtos;
import com.app.ecom.store.dto.orderservice.OrderSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceClient {
	
	private static final String ORDER = "/order";
	private static final String COUNT_ORDER = "/countOrder";
	
	@Value("${application.order-service.name}")
	private String serviceName;
	
	@Value("${application.order-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public OrderDto createUpdateOrder(OrderDto orderDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ORDER);
		ExternalApiRequest<OrderDto> externalApi = commonUtil.getExternalApiRequest(OrderDto.class, url, HttpMethod.PUT, null, null, orderDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public OrderDtos getOrders(OrderSearchRequest orderSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ORDER);
		ExternalApiRequest<OrderDtos> externalApi = commonUtil.getExternalApiRequest(OrderDtos.class, url, HttpMethod.POST, null, null, orderSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countOrders(OrderSearchRequest orderSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_ORDER);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, orderSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteOrders(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, ORDER);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
}
