package com.app.ecom.store.client;

import java.net.URI;
import java.util.Map.Entry;

import com.app.ecom.store.dto.ExternalApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    /**
     * Public method to invoke external API for a given ExternalApi object.Prepares request and invokes method for API call based on the values passed in the object. 
     * @param externalApi ExternalApi request wrapper object
     * @return ResponseEntity API response
     */
    public <T> ResponseEntity<T> callExternalApi(ExternalApi<T> externalApi) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!CollectionUtils.isEmpty(externalApi.getHeaders())) {
        	for(Entry<String, String> entry: externalApi.getHeaders().entrySet()) {
        		headers.set(entry.getKey(), entry.getValue());    	
	        }
        }
        MultiValueMap<String, String> params = null;
        if (!CollectionUtils.isEmpty(externalApi.getParameterMap())) {
            params = new LinkedMultiValueMap<>();
            for (Entry<String, String> entry : externalApi.getParameterMap().entrySet()) {
                params.add(entry.getKey(), entry.getValue());
            }
        }
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(externalApi.getUrl()).queryParams(params).build();
        return callExternalApi(uriComponents.toUri(), headers, externalApi.getMethod(), externalApi.getType(), externalApi.getBody());
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
        ResponseEntity<T> responseEntity;
        HttpEntity<String> entity = null;
        if (null != postObject) {
            try {
                entity = new HttpEntity<>(objectMapper.writeValueAsString(postObject), headers);
            } catch (JsonProcessingException exception) {
                LOGGER.error("Exception while hitting external API: {}", exception);
            }
        } else {
            entity = new HttpEntity<>(headers);
        }
        LOGGER.info("URL : " + uri);
        LOGGER.info("entity : " + entity);
        responseEntity = restTemplate.exchange(uri, method, entity, type);
        LOGGER.info("responseEntity : " + responseEntity.getStatusCode());
        return responseEntity;
    }
}
