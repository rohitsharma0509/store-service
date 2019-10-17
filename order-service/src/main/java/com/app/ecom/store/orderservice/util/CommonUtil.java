package com.app.ecom.store.orderservice.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CommonUtil {
	
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
}
