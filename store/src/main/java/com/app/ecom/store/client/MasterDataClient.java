package com.app.ecom.store.client;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDtos;
import com.app.ecom.store.dto.masterdata.ProductCategorySearchRequest;
import com.app.ecom.store.dto.masterdata.SettingDto;
import com.app.ecom.store.dto.masterdata.SettingDtos;
import com.app.ecom.store.dto.masterdata.SettingSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class MasterDataClient {
	
	private static final String CATEGORY = "/category";
	private static final String COUNT_CATEGORY = "/countCategory";
	private static final String SETTING = "/setting";
	
	@Value("${application.master-data-service.name}")
	private String serviceName;
	
	@Value("${application.master-data-service.context-path}")
	private String contextPath;
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	//@Autowired
	//private CacheManager cacheManager;
	
	//@CachePut(cacheNames = "productCategoryCache", key = "{#productCategoryDto.getId(), #productCategoryDto.getName()}")
	//@CacheEvict(cacheNames = "productCategoryCountCache", key = "{#productCategoryDto.getId(), #productCategoryDto.getName()}")
	public ProductCategoryDto addUpdateProductCategory(ProductCategoryDto productCategoryDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, CATEGORY);
		ExternalApiRequest<ProductCategoryDto> externalApi = commonUtil.getExternalApiRequest(ProductCategoryDto.class, url, HttpMethod.PUT, null, null, productCategoryDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	//@Cacheable(value = "productCategoryCache", key = "{#productCategorySearchRequest.getId(), #productCategorySearchRequest.getName()}")
	public ProductCategoryDtos getProductCategories(ProductCategorySearchRequest productCategorySearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, CATEGORY);
		ExternalApiRequest<ProductCategoryDtos> externalApi = commonUtil.getExternalApiRequest(ProductCategoryDtos.class, url, HttpMethod.POST, null, null, productCategorySearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	//@Cacheable(value = "productCategoryCountCache", key = "{#productCategoryDto.getId(), #productCategoryDto.getName()}")
	public Long countProductCategories(ProductCategoryDto productCategoryDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_CATEGORY);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, productCategoryDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteProductCategories(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, CATEGORY);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
		/*if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			cacheManager.getCache("productCategoryCache").clear();
		} else {
			cacheManager.getCache("productCategoryCache").evict(idsDto.getIds());
		}*/
	}
	
	public SettingDto addUpdateSetting(SettingDto settingDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, SETTING);
		ExternalApiRequest<SettingDto> externalApi = commonUtil.getExternalApiRequest(SettingDto.class, url, HttpMethod.PUT, null, null, settingDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public SettingDtos getSettings(SettingSearchRequest settingSearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, SETTING);
		ExternalApiRequest<SettingDtos> externalApi = commonUtil.getExternalApiRequest(SettingDtos.class, url, HttpMethod.POST, null, null, settingSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteSettings(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, SETTING);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
}
