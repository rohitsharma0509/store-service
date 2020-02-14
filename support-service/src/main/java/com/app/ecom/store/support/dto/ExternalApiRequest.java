package com.app.ecom.store.support.dto;

import java.util.Map;

import org.springframework.http.HttpMethod;

public class ExternalApiRequest<T> {

	private Map<String, String> headers;

	private HttpMethod method;

	private Class<T> type;

	private String url;

	private Object body;

	private Map<String, String> parameterMap;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExternalApi [");
		builder.append("headers=").append(headers).append(", ");
		builder.append("method=").append(method).append(", ");
		builder.append("type=").append(type).append(", ");
		builder.append("url=").append(url).append(", ");
		builder.append("body=").append(body).append(", ");
		builder.append("parameterMap=").append(parameterMap).append(", ");
		builder.append("]");
		return builder.toString();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
}
