package com.app.ecom.store.support.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Random;

import com.app.ecom.store.support.dto.ExternalApiRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CommonUtil {
	
	public String getStackTraceAsString(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
	
	public String generateRandomDigits(String prefix, int n, String postfix) {
	    String number = "";
	    if(!StringUtils.isEmpty(prefix)) {
	        number = prefix + number;
	    }
	    int m = (int) Math.pow(10, n - 1.0);
	    number = number + (m + new Random().nextInt(9 * m));
	    if(!StringUtils.isEmpty(postfix)) {
	        number = number + postfix;
	    }
	    return number;
	}
	
	public <T> ExternalApiRequest<T> getExternalApiRequest(Class<T> type, String url, HttpMethod method, Map<String, String> headers, Map<String, String> parameterMap, Object body) {
		ExternalApiRequest<T> externalApiRequest = new ExternalApiRequest<>();
		externalApiRequest.setType(type);
		externalApiRequest.setUrl(url);
		externalApiRequest.setMethod(method);
		externalApiRequest.setHeaders(headers);
		externalApiRequest.setParameterMap(parameterMap);
		externalApiRequest.setBody(body);
		return externalApiRequest;
	}
	
}
