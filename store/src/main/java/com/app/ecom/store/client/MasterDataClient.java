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
	
	public ProductCategoryDto addUpdateProductCategory(ProductCategoryDto productCategoryDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, CATEGORY);
		ExternalApiRequest<ProductCategoryDto> externalApi = commonUtil.getExternalApiRequest(ProductCategoryDto.class, url, HttpMethod.PUT, null, null, productCategoryDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public ProductCategoryDtos getProductCategories(ProductCategorySearchRequest productCategorySearchRequest) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, CATEGORY);
		ExternalApiRequest<ProductCategoryDtos> externalApi = commonUtil.getExternalApiRequest(ProductCategoryDtos.class, url, HttpMethod.POST, null, null, productCategorySearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countProductCategories(ProductCategoryDto productCategoryDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, COUNT_CATEGORY);
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, productCategoryDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteProductCategories(IdsDto idsDto) {
		String url = externalApiHandler.getExternalServiceUri(serviceName, contextPath, CATEGORY);
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
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
