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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceClient {
	
	private static final String PRODUCT = "/product";
	private static final String COUNT_PRODUCT = "/countProduct";
	private static final String STOCK = "/stock";
	private static final String COUNT_STOCK = "/countStock";
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Value("${base-urls.product-service-api}")
	private String productServiceApiBaseUrl;
	
	public ProductDto addUpdateProduct(ProductDto productDto) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(PRODUCT).toString();
		ExternalApiRequest<ProductDto> externalApi = commonUtil.getExternalApiRequest(ProductDto.class, url, HttpMethod.PUT, null, null, productDto);
		return externalApiHandler.callExternalApi(externalApi);
	}

	public ProductDtos getProducts(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(PRODUCT).toString();
		ExternalApiRequest<ProductDtos> externalApi = commonUtil.getExternalApiRequest(ProductDtos.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countProducts(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(COUNT_PRODUCT).toString();
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteProducts(IdsDto idsDto) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(PRODUCT).toString();
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
	
	public Integer getProductsQuantity(Long productId, QuantityStatus status) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(PRODUCT).toString();
		Map<String, String> queryParamMap = new HashMap<>();
		queryParamMap.put("productId", String.valueOf(productId));
		queryParamMap.put("status", status.name());
		ExternalApiRequest<Integer> externalApi = commonUtil.getExternalApiRequest(Integer.class, url, HttpMethod.GET, null, queryParamMap, null);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public StockDtos getStockDetails(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(STOCK).toString();
		ExternalApiRequest<StockDtos> externalApi = commonUtil.getExternalApiRequest(StockDtos.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Integer countStockDetails(ProductSearchRequest productSearchRequest) {
		String url = new StringBuilder(productServiceApiBaseUrl).append(COUNT_STOCK).toString();
		ExternalApiRequest<Integer> externalApi = commonUtil.getExternalApiRequest(Integer.class, url, HttpMethod.POST, null, null, productSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
}
