package com.app.ecom.store.inventory.util;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CommonUtil {
	
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);
		
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
	
	public String getStackTraceAsString(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
}
