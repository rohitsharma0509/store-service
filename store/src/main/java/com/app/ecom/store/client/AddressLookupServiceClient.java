package com.app.ecom.store.client;

import java.util.Map;

import com.app.ecom.store.dto.ExternalApi;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.addresslookupservice.AddressDto;
import com.app.ecom.store.dto.addresslookupservice.AddressDtos;
import com.app.ecom.store.dto.addresslookupservice.AddressSearchRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressLookupServiceClient {
	
	private static final Logger logger = LogManager.getLogger(AddressLookupServiceClient.class);
	
	private static final String ADDRESS = "/address";
	
	private static final String COUNT_ADDRESS = "/countAddress";
	
	@Autowired
	private ExternalApiHandler externalApiHandler;
	
	@Value("${base-urls.address-lookup-service-api}")
	private String addressLookupServiceApiBaseUrl;
	
	public AddressDto createUpdateAddress(AddressDto addressDto) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(ADDRESS).toString();
		ExternalApi<AddressDto> externalApi = getExternalApi(AddressDto.class, url, HttpMethod.PUT, null, null, addressDto);
		ResponseEntity<AddressDto> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to PUT API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public AddressDtos getAddresses(AddressSearchRequest addressSearchRequest) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(ADDRESS).toString();
		ExternalApi<AddressDtos> externalApi = getExternalApi(AddressDtos.class, url, HttpMethod.POST, null, null, addressSearchRequest);
		ResponseEntity<AddressDtos> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public Long countAddresses(AddressSearchRequest addressSearchRequest) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(COUNT_ADDRESS).toString();
		ExternalApi<Long> externalApi = getExternalApi(Long.class, url, HttpMethod.POST, null, null, addressSearchRequest);
		ResponseEntity<Long> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity.getBody();
		} else {
			logger.error(new StringBuilder("Call to POST API ").append(url).append(" failed with status: ").append(responseEntity.getStatusCode()));
			return null;
		}
	}
	
	public void deleteAddresses(IdsDto idsDto) {
		String url = new StringBuilder(addressLookupServiceApiBaseUrl).append(ADDRESS).toString();
		ExternalApi<Void> externalApi = getExternalApi(Void.class, url, HttpMethod.DELETE, null, null, (idsDto == null ? new IdsDto() : idsDto));
		ResponseEntity<Void> responseEntity = externalApiHandler.callExternalApi(externalApi);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.info(new StringBuilder("Addresses has been deleted successfully.").toString());
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
