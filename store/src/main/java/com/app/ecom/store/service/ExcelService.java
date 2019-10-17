package com.app.ecom.store.service;

import java.io.ByteArrayOutputStream;

public interface ExcelService {
	ByteArrayOutputStream getExcelAsBytes(String reportName);
	
	ByteArrayOutputStream getFileAsBytes(String fileType);
}
