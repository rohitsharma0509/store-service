package com.app.ecom.store.client;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.dto.productservice.ProductDtos;
import com.app.ecom.store.dto.productservice.ProductSearchRequest;
import com.app.ecom.store.dto.productservice.StockDtos;
import com.app.ecom.store.enums.QuantityStatus;
import com.app.ecom.store.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProductServiceClient {
	
	private static final Logger logger = LogManager.getLogger(ProductServiceClient.class);
	
	private static final String PRODUCT = "/product";
	private static final String COUNT_PRODUCT = "/countProduct";
	private static final String IMPORT_PRODUCT = "/importProduct";
	private static final String STOCK = "/stock";
	private static final String COUNT_STOCK = "/countStock";
	
	@Value("${application.product-service.name}")
	private String serviceName;
	
	@Value("${application.product-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public ProductDto addUpdateProduct(ProductDto productDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRODUCT);
		ExternalApiRequest<ProductDto> externalApi = commonUtil.getExternalApiRequest(ProductDto.class, url, HttpMethod.PUT, null, null, productDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void importProducts(MultipartFile multiPartFile, String fileType) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, IMPORT_PRODUCT);
		Map<String, String> queryParamMap = new HashMap<>();
		try {
			queryParamMap.put("file", new String(multiPartFile.getBytes()));
		} catch(Exception e) {
			logger.error(new StringBuilder("Exception while converting file to String: ").append(commonUtil.getStackTraceAsString(e)));
		}
		queryParamMap.put("fileType", fileType);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.PUT, null, queryParamMap, null);
		externalApiHandler.callExternalApi(externalApi);
	}

	public ProductDtos getProducts(ProductSearchRequest productSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRODUCT);
		ExternalApiRequest<ProductDtos> externalApi = commonUtil.getExternalApiRequest(ProductDtos.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countProducts(ProductSearchRequest productSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_PRODUCT);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteProducts(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRODUCT);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
	
	public Integer getProductsQuantity(Long productId, QuantityStatus status) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, PRODUCT);
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put("productId", String.valueOf(productId));
		queryParamMap.put("status", status.name());
		ExternalApiRequest<Integer> externalApi = commonUtil.getExternalApiRequest(Integer.class, url, HttpMethod.GET, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public StockDtos getStockDetails(ProductSearchRequest productSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, STOCK);
		ExternalApiRequest<StockDtos> externalApi = commonUtil.getExternalApiRequest(StockDtos.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Integer countStockDetails(ProductSearchRequest productSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_STOCK);
		ExternalApiRequest<Integer> externalApi = commonUtil.getExternalApiRequest(Integer.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
}
