package com.app.ecom.store.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.inventory.InventoryDto;
import com.app.ecom.store.dto.inventory.InventoryDtos;
import com.app.ecom.store.dto.inventory.InventorySearchRequest;
import com.app.ecom.store.util.CommonUtil;

@Component
public class InventoryServiceClient {
	
	private static final Logger logger = LogManager.getLogger(InventoryServiceClient.class);
	
	private static final String INVENTORY = "/inventory";
	private static final String COUNT_INVENTORY = "/countInventory";
	
	@Value("${application.inventory-service.name}")
	private String serviceName;
	
	@Value("${application.inventory-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public InventoryDto addUpdateInventory(InventoryDto inventoryDto) {
		logger.info("adding to inventory");
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, INVENTORY);
		ExternalApiRequest<InventoryDto> externalApi = commonUtil.getExternalApiRequest(InventoryDto.class, url, HttpMethod.PUT, null, null, inventoryDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public InventoryDto getInventoryById(Long id) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, INVENTORY + "/" + id);
		ExternalApiRequest<InventoryDto> externalApi = commonUtil.getExternalApiRequest(InventoryDto.class, url, HttpMethod.GET, null, null, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public InventoryDtos searchInventories(InventorySearchRequest inventorySearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, INVENTORY);
		ExternalApiRequest<InventoryDtos> externalApi = commonUtil.getExternalApiRequest(InventoryDtos.class, url, HttpMethod.POST, null, null, inventorySearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countInventory(InventorySearchRequest inventorySearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_INVENTORY);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, inventorySearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
}
