package com.app.ecom.store.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.ExternalApiRequest;
import com.app.ecom.store.dto.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CommonUtil {
	
	private static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Environment enviroment;
	
	public String convertLocalDateTimeToString(LocalDateTime localDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}	
	
	public String convertZonedDateTimeToString(ZonedDateTime zonedDateTime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(convertZonedDateTimeToDate(zonedDateTime));
	}
	
	public Date convertZonedDateTimeToDate(ZonedDateTime zonedDateTime) {
		return Date.from(zonedDateTime.toInstant());
	}
	
	public Date convertStringToDate(String inputDate, String format){
		Date date = null;
		if(!StringUtils.isEmpty(inputDate)) {			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				date = sdf.parse(inputDate);
			} catch (ParseException e) {
				logger.error(new StringBuilder("Error while converting String to Date: ").append(e));
			}
		}
		return date;
	}
	
	public ZonedDateTime convertDateToZonedDateTime(Date date) {
		if(null == date) {
			return null;
		}
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public String formatDate(String date, String inputDateFormat, String outputDateFormat) {
		SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);
		Date utilDate = convertStringToDate(date, inputDateFormat);
		return outputFormat.format(utilDate);
	}
	
	public String getStackTraceAsString(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
	
	public String getPagging(String baseUrl, int currentPage, int totalPage, Map<String, String> parameters) {
		return getPagging(baseUrl, currentPage, totalPage, parameters, false, null);
	}
	public String getPagging(String baseUrl, int currentPage, int totalPage, Map<String, String> parameters, boolean isTab, String tabId) {
		int begin = Math.max(1, currentPage - 5);
		int end = Math.min(begin + 10, totalPage);
		
		StringBuilder params = new StringBuilder("");
		if(null != parameters){
			for (Map.Entry<String, String> entry : parameters.entrySet()){
				if(!StringUtils.isEmpty(entry.getValue())){
					params.append(entry.getKey()+"="+entry.getValue()+"&");
				}
			}
		}
		
		StringBuilder pagging = new StringBuilder("<ul class=\"pagination pagination-sm justify-content-end\">");
		
		if(currentPage == 1){
			pagging.append(getPageItem(true, false, "#", "First"));
			pagging.append(getPageItem(true, false, "#", "Previous"));
		}else {
			pagging.append(getPageItem(false, false, baseUrl+"?page=1&"+params, "First", isTab, tabId));
			pagging.append(getPageItem(false, false, baseUrl+"?page="+(currentPage-1)+"&"+params, "Previous", isTab, tabId));
		}
		
		for(int i = begin; i<= end; i++){
			if(i==currentPage){
				pagging.append(getPageItem(false, true, baseUrl+"?page="+i+"&"+params, ""+i, isTab, tabId));
			}else {
				pagging.append(getPageItem(false, false, baseUrl+"?page="+i+"&"+params, ""+i, isTab, tabId));
			}
		}
		
		if(currentPage == totalPage){
			pagging.append(getPageItem(true, false, "#", "Next"));
			pagging.append(getPageItem(true, false, "#", "Last"));
		}else {
			pagging.append(getPageItem(false, false, baseUrl+"?page="+(currentPage + 1)+"&"+params, "Next", isTab, tabId));
			pagging.append(getPageItem(false, false, baseUrl+"?page="+totalPage+"&"+params, "Last", isTab, tabId));
		}
		pagging.append("</ul>");
		return pagging.toString();
	}
	
	private String getPageItem(boolean isDisabled, boolean isActive, String baseUrl, String name) {
		return getPageItem(isDisabled, isActive, baseUrl, name, false, null);
	}
	private String getPageItem(boolean isDisabled, boolean isActive, String baseUrl, String name, boolean isTab, String tabName) {
		StringBuilder item = new StringBuilder("<li class=\"page-item");
		if(isDisabled) {
			item.append(" disabled");
		} else if(isActive) {
			item.append(" active");
		}
		item.append("\">");
		if(isTab) {
			item.append("<a class=\"page-link\" href=\"#\" onclick=\"getPage('").append(baseUrl).append("', '").append(tabName).append("')\">");
		} else {
			item.append("<a class=\"page-link\" href=\"").append(baseUrl).append("\">");
		}
		item.append(name).append("</a></li>");
		return item.toString();
	}
	
	public boolean isDouble(String number) {
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public boolean isInteger(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public Boolean isValid(String value){
		return !(StringUtils.isEmpty(value) || "-1".equals(value));
	}
	
	public String[] convertStringToArray(String str, String delimeter) {
		if(StringUtils.isEmpty(str)){
			return null;
		} else {
			return str.split(delimeter);
		}
	}
	
	public String getValue(String value) {
		return messageSource.getMessage(value, new Object[]{value},  LocaleContextHolder.getLocale());
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
	
	public Response getResponse(boolean flag, String errorCode) {
		Response response = new Response();
		if(flag) {
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage(errorCode);
			response.setDescription(enviroment.getProperty(errorCode));
		} else {
			response.setCode(HttpStatus.OK.value());
			response.setMessage(Constants.EMPTY_STRING);
			response.setDescription(Constants.EMPTY_STRING);
		}
		return response;
	}
	
	public String getMessage(String errorCode) {
		return enviroment.getProperty(errorCode);
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
	
	public boolean isValidEmails(String[] emails) {
		if (null != emails && emails.length > 0) {

			for (String email : emails) {
				if (null != email && !isValidEmail(email)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValidEmail(String email) {
		return email.matches(EMAIL_PATTERN);
	}
}
