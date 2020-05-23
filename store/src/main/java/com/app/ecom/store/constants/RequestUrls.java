package com.app.ecom.store.constants;

public class RequestUrls {
	private RequestUrls() {
	}
	
	public static final String DEFAULT = "/";
	public static final String PRODUCT_ALL = "/allProducts";
	public static final String PRODUCTS_AJAX = "/products/ajax";
	public static final String ADD_PRODUCT = "/admin/addProduct";
	public static final String VIEW_PRODUCT = "/admin/viewProduct";
	public static final String PRODUCTS = "/admin/products";
	public static final String DELETE_PRODUCT = PRODUCTS+"/{id}";
	public static final String DELETE_BULK_PRODUCT = "/admin/bulkproducts";
	public static final String DELETE_ALL_PRODUCT = "/admin/allproducts";
	public static final String COUNT_PRODUCT = "/countProduct";
	
	public static final String PRODUCTS_IMPORT = "/admin/importProducts";
	public static final String PRODUCTS_SAVE = "/admin/addProducts";
	
	public static final String BUY = "/buy";
	public static final String GET_ORDER = "/orders/{id}";
	public static final String ORDERS = "/orders";
	public static final String DOWNLOAD_ORDER = "/orders/download/{id}";
	
	public static final String STOCK = "/stock";
	
	public static final String ADMIN = "/admin";
	
	public static final String COMPOSE_EMAIL = "/composeEmail";
	public static final String SEND_EMAIL = "/sendEmail";
	public static final String GET_EMAIL_ACCOUNT = "/getEmailAccount";
	public static final String ADD_EMAIL_ACCOUNT = "/addEmailAccount";
	public static final String TEMPLATES = "/admin/templates";
	public static final String ADD_TEMPLATE = "/admin/addTemplate";
	public static final String DELETE_TEMPLATE = TEMPLATES+"/{id}";
	public static final String DELETE_BULK_TEMPLATES = "/admin/bulkTemplates";
	public static final String DELETE_ALL_TEMPLATES = "/admin/allTemplates";
	
	public static final String EXCEL = "/excel";
	public static final String SAMPLE = "/sample";
	
	public static final String ADD_CATEGORY = "/admin/addCategory";	
	public static final String CATEGORIES = "/admin/categories";	
	public static final String CATEGORIES_ALL = "/admin/categories/all";	
	public static final String DELETE_CATEGORY = CATEGORIES+"/{id}";
	public static final String DELETE_BULK_CATEGORY = "/admin/bulkcategories";
	public static final String DELETE_ALL_CATEGORY = "/admin/allcategories";
	
	public static final String ADD_TO_CART = "/addToCart";
	public static final String DELETE_FROM_CART = "/shoppingCart/{id}";
	public static final String SHOPPING_CART = "/shoppingCart";
	public static final String CHECKOUT = "/checkout";
	
	public static final String ADD_SUPPLIER = "/addSupplier";
	public static final String SUPPLIERS = "/suppliers";
	public static final String SUPPLIERS_ALL = "/suppliers/all";
	public static final String DELETE_SUPPLIER = "/suppliers/{id}";
	
	public static final String REGISTRATION = "/registration";
	public static final String REGISTRATION_CONFIRM = "/registration/confirm";
	public static final String REGISTRATION_CONFIG = "/registration/config";
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	public static final String FORGET_PASSWORD = "/forgetPassword";
	public static final String CHANGE_PASSWORD = "/changePassword";
	public static final String RESET_PASSWORD = "/resetPassword";
	public static final String CHANGE_PSWRD = "/changePswrd";
	public static final String HOME = "/home";
	public static final String FAILURE = "/failure";
	public static final String ACCESS_DENIED = "/accessDenied";
	public static final String USERS = "/admin/users";
	public static final String USER_WITH_ID = USERS + "/{id}";
	public static final String EDIT_USER = "/admin/editUser";
	public static final String VIEW_USER = "/admin/viewUser";
	public static final String EDIT_USERS = "/editUsers";
	public static final String ACCOUNT_SETTING = "/admin/accountSetting";
	public static final String PERSONAL_INFORMATION = "/admin/personalInformation";
	
	public static final String SUPPORT_TICKETS = "/supportTickets";
	public static final String CREATE_SUPPORT_TICKET = "/createSupportTicket";
	public static final String VIEW_SUPPORT_TICKET = "/viewSupportTicket";
	public static final String UNCLOSED_SUPPORT_TICKET = "/unclosedSupportTicket";
	public static final String DELETE_SUPPORT_TICKETS = SUPPORT_TICKETS+"/{id}";
	public static final String SUPPORT_TICKET_ASSIGNMENT_STRATEGY = "/supportTicketAssignmentStrategy";
	public static final String POST_SUPPORT_TICKET_ACTIVITY = "/postSupportTicketActivity";
	public static final String SUPPORT_TICKET_ACTIVITY_HISTORY = "/supportTicketActivityHistory";
	
	public static final String ROLES = "/admin/roles";
	public static final String ADD_ROLE = "/admin/addRole";
	public static final String DELETE_ROLE = ROLES+"/{id}";
	public static final String DELETE_BULK_ROLES = "/admin/bulkroles";
	public static final String DELETE_ALL_ROLES = "/admin/allroles";
	
	public static final String CHANGE_LANGUAGE = "/changeLanguage";
	public static final String CHANGE_LOCALE = "/changeLocale";
	public static final String EDIT_PROFILE = "/editProfile";
	public static final String MY_ACCOUNT = "/myAccount";
	
	public static final String ROLE_PRIVILEGES = "/admin/rolePrivileges";
	public static final String PRIVILEGES = "/admin/privileges";
	public static final String ADD_PRIVILEGE = "/admin/addPrivilege";
	public static final String DELETE_PRIVILEGE = PRIVILEGES+"/{id}";
	public static final String DELETE_BULK_PRIVILEGES = "/admin/bulkprivileges";
	public static final String DELETE_ALL_PRIVILEGES = "/admin/allprivileges";
	
	public static final String SETTINGS = "/admin/settings";
	public static final String ADD_SETTING = "/admin/addSetting";
	public static final String DELETE_SETTING = SETTINGS+"/{id}";
	
	public static final String PROFIT_LOSS = "/admin/profitLoss";
	public static final String DAILY_PROFIT_LOSS = "/admin/dailyProfitLoss";
	public static final String MONTHLY_PROFIT_LOSS = "/admin/monthlyProfitLoss";
	public static final String QUARTERLY_PROFIT_LOSS = "/admin/quarterlyProfitLoss";
	public static final String YEARLY_PROFIT_LOSS = "/admin/yearlyProfitLoss";
	
	public static final String MANAGE_ADDRESS = "/manageAddress";
	public static final String ADD_ADDRESS = "/addAddress";
	public static final String USER_SEARCH = "/users/search";
}
