package com.app.ecom.store.productservice.util;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.app.ecom.store.productservice.dto.jaxb.ProductsType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CommonUtil {
	
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);
	
	@Autowired
	private Unmarshaller unmarshaller;
	
	@Autowired
	private Marshaller marshaller;
	
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
	
	public String convertProductsTypeToXml(ProductsType productsType) {
		StringWriter xml = null;

		try {
			xml = new StringWriter();
			marshaller.marshal(productsType, xml);
		} catch (JAXBException e) {
			logger.error(new StringBuilder("Exception while marshaling: ").append(getStackTraceAsString(e)));
		}
		return xml.toString();
	}

	public ProductsType convertXmlToProductsType(String response) throws JAXBException {
		return (ProductsType) unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));
	}
}
