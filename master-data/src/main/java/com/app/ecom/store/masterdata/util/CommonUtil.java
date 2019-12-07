package com.app.ecom.store.masterdata.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	public String getStackTraceAsString(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
	
}
