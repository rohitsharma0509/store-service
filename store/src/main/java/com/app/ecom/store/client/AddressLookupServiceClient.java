package com.app.ecom.store.client;

import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.addresslookupservice.AddressDto;
import com.app.ecom.store.dto.addresslookupservice.AddressDtos;
import com.app.ecom.store.dto.addresslookupservice.AddressSearchRequest;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class AddressLookupServiceClient {
	
	private static final String ADDRESS = "/address";
	private static final String COUNT_ADDRESS = "/countAddress";
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Value("${base-urls.address-lookup-service-api}")
	private String addressLookupServiceApiBaseUrl;
	
	public AddressDto createUpdateAddress(AddressDto addressDto) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(ADDRESS).toString();
		ExternalApiRequest<AddressDto> externalApi = commonUtil.getExternalApiRequest(AddressDto.class, url, HttpMethod.PUT, null, null, addressDto);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public AddressDtos getAddresses(AddressSearchRequest addressSearchRequest) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(ADDRESS).toString();
		ExternalApiRequest<AddressDtos> externalApi = commonUtil.getExternalApiRequest(AddressDtos.class, url, HttpMethod.POST, null, null, addressSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public Long countAddresses(AddressSearchRequest addressSearchRequest) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(COUNT_ADDRESS).toString();
		ExternalApiRequest<Long> externalApi = commonUtil.getExternalApiRequest(Long.class, url, HttpMethod.POST, null, null, addressSearchRequest);
		return externalApiHandler.callExternalApi(externalApi);
	}
	
	public void deleteAddresses(IdsDto idsDto) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(ADDRESS).toString();
		ExternalApiRequest<Void> externalApi = commonUtil.getExternalApiRequest(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		externalApiHandler.callExternalApi(externalApi);
	}
}
