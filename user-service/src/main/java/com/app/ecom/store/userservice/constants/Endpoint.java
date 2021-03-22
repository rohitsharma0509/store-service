package com.app.ecom.store.userservice.constants;

public class Endpoint {

	private Endpoint() {

	}

	private static final String ID = "/{id}";

	public static final String USER = "/user";
	public static final String ROLE = "/role";
	public static final String USER_WITH_ID = USER + ID;
	public static final String COUNT_USER = "/countUser";
	public static final String USER_ROLE = USER_WITH_ID + ROLE;

	public static final String GET_ROLE = ROLE + ID;
	public static final String COUNT_ROLE = "/countRole";

	public static final String PRIVILEGE = "/privilege";
	public static final String GET_PRIVILEGE = PRIVILEGE + ID;
	public static final String COUNT_PRIVILEGE = "/countPrivilege";
	
	public static final String ADDRESS = "/address";
	public static final String COUNT_ADDRESS = "/countAddress";
	
	public static final String TOKEN = "/token";
}
