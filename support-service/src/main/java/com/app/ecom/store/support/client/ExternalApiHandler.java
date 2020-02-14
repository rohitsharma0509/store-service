package com.app.ecom.store.support.client;

import java.net.URI;
import java.util.Map.Entry;

import com.app.ecom.store.support.dto.ExternalApiRequest;
import com.app.ecom.store.support.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class contains methods to invoke any external API.
 *
 */
@Component
public class ExternalApiHandler {

    private static final Logger LOGGER = LogManager.getLogger(ExternalApiHandler.class);

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private CommonUtil commonUtil;
    
    public String getExternalServiceUri(String serviceName, String contextPath, String requestUrl) {
    	StringBuilder uri = new StringBuilder("http://");
		uri.append(serviceName).append(contextPath).append(requestUrl);
		return uri.toString();
    }

    /**
     * Public method to invoke external API for a given ExternalApi object.Prepares request and invokes method for API call based on the values passed in the object. 
     * @param externalApiRequest ExternalApi request wrapper object
     * @return T API response
     */
    public <T> T callExternalApi(ExternalApiRequest<T> externalApiRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!CollectionUtils.isEmpty(externalApiRequest.getHeaders())) {
        	for(Entry<String, String> entry: externalApiRequest.getHeaders().entrySet()) {
        		headers.set(entry.getKey(), entry.getValue());    	
	        }
        }
        MultiValueMap<String, String> params = null;
        if (!CollectionUtils.isEmpty(externalApiRequest.getParameterMap())) {
            params = new LinkedMultiValueMap<>();
            for (Entry<String, String> entry : externalApiRequest.getParameterMap().entrySet()) {
                params.add(entry.getKey(), entry.getValue());
            }
        }
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(externalApiRequest.getUrl()).queryParams(params).build();
        try {
        	LOGGER.info(new StringBuilder(externalApiRequest.getMethod().name()).append(" ").append(uriComponents.toUri()));
        	ResponseEntity<T> responseEntity = callExternalApi(uriComponents.toUri(), headers, externalApiRequest.getMethod(), externalApiRequest.getType(), externalApiRequest.getBody());
        	if(responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
        		LOGGER.info(new StringBuilder("API call success with status code: ").append(responseEntity.getStatusCode()).toString());
        		return responseEntity.getBody();
        	} else {
        		LOGGER.error(new StringBuilder("API call has been failed with status code: ").append(responseEntity.getStatusCode()).toString());
        	}
        } catch(ResourceAccessException e) {
        	StringBuilder msg = new StringBuilder("API call failed due to service is not accessible.\n");
        	msg.append(commonUtil.getStackTraceAsString(e));
        	LOGGER.error(msg.toString());
        } catch (Exception e) {
        	StringBuilder msg = new StringBuilder("API call failed due to : ");
        	msg.append(commonUtil.getStackTraceAsString(e));
        	LOGGER.error(msg.toString());
        }
        return null;
    }
    
    /**
     * @param uri URL to be invoked
     * @param headers request headers
     * @param method request method i.e. GET,POST etc
     * @param type ResponseObject type
     * @param postObject request Body object.
     * @return ResponseEntity API response Object API response mapped as per the type parameter
     */
    private <T> ResponseEntity<T> callExternalApi(URI uri, HttpHeaders headers, HttpMethod method, Class<T> type, Object postObject) {
        HttpEntity<String> entity = null;
        if (null != postObject) {
            try {
                entity = new HttpEntity<>(objectMapper.writeValueAsString(postObject), headers);
            } catch (JsonProcessingException exception) {
                LOGGER.error("Exception while parsing request body: {}", exception);
            }
        } else {
            entity = new HttpEntity<>(headers);
        }
        return restTemplate.exchange(uri, method, entity, type);
    }
}
