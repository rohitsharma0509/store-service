package com.app.ecom.store.masterdata.constants;

public class Endpoint {
	
	private Endpoint() {
		
	}

	public static final String SETTING = "/setting";
	public static final String SETTING_WITH_ID = SETTING+"/{id}";
	
	public static final String CATEGORY = "/category";
	public static final String CATEGORY_WITH_ID = CATEGORY+"/{id}";
	public static final String COUNT_CATEGORY = "/countCategory";
	
}
