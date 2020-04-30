<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.app.ecom.store.dto.NotificationDtos"%>
<%@page import="com.app.ecom.store.dto.NotificationDto"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@page import="com.app.ecom.store.service.NotificationService"%>
<%@page import="com.app.ecom.store.service.impl.NotificationServiceImpl"%>
<%@page import="com.app.ecom.store.dto.userservice.UserDto"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
UserDto userDto = (UserDto) session.getAttribute("user");
String name = String.format("%1$s %2$s", userDto.getFirstName(), userDto.getLastName());

ApplicationContext applicationContext = RequestContextUtils.findWebApplicationContext(request);
NotificationService notificationService = (NotificationService) applicationContext.getBean("notificationService");
NotificationDtos notificationDtos = notificationService.getNotifications();
%>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>
* {
  font-size: 12px;
}

.navbar-dark .navbar-nav .nav-link {
  color: white;
}

.urgent_fields {
  color: red;
}

.main-center {
  background: white;
  color: #000000;
  text-shadow: none;
  -webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  -moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

.content-table {
  background: white;
  -webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  -moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

.has-error {
    color: red;
}

.norecord {
  font-size: 15px;
  font-weight: bold;
  text-align: center;
}

.modal-dialog {
  max-width: 800px;
}

.delete-modal {
  max-width: 500px;
}

.delete-icon {
  font-size: 100px;
  color:red;
  margin: 20px;
}

.delete-modal-header {
  font-size: 25px;
  margin-bottom: 10px;
}

.delete-modal-content {
  font-size: 16px;
  margin: 20px;
}

.warning-icon {
  font-size: 100px;
  color:#ffc107;
  margin: 20px;
}

.warning-modal-header {
  font-size: 25px;
  margin-bottom: 10px;
}

.warning-modal-content {
  font-size: 16px;
  margin: 20px;
}

.breadcrumb {
  background-color: #F2F3F8;
  margin-top: 10px;
  padding: .75rem 0rem;
}

.nav-icon {
  font-size: 20px;
  margin-right: 10px;
}

.dropdown-toggle::after {
  content: none;
}

.breadcrum-icon {
  font-size: 15px;
}

.spacer {
  margin-top: 10px;
}

.mt-40 {
  margin-top: 40px;
}
.heading {
    padding: 10px;
    background-color: #f2f3f8;
    margin-top: 20px;
    margin-bottom: 10px;
    font-weight: bold;
}
.tab-content>.active {
    background-color: #fff;
}
</style>
<div class="row">
  <div class="col-sm-12">
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <!-- <a class="navbar-brand" href="#"><img src="../images/logo.jpg" alt="Store" style="width:40px;"></a> -->
        <ul class="navbar-nav">
           <li class="nav-item"><a class="nav-link" href="${contextPath}<%=RequestUrls.HOME %>"><spring:message code="Dashboard" text="Dashboard" /></a></li>
           <security:authorize access="hasAuthority('CAN_VIEW_PRODUCT_MODULE')">
             <li class="nav-item"><a class="nav-link" href="${contextPath}<%=RequestUrls.PRODUCT_ALL %>"><spring:message code="Products" text="Products" /></a></li>
           </security:authorize>
           <security:authorize access="hasAuthority('CAN_VIEW_STOCK_MODULE')">
             <li class="nav-item"><a class="nav-link" href="${contextPath}<%=RequestUrls.STOCK %>"><spring:message code="Stock" text="Stock" /></a></li>
           </security:authorize>
	         <security:authorize access="hasAuthority('CAN_VIEW_SUPPORT_MODULE')">
	           <li class="nav-item"><a class="nav-link" href="${contextPath}<%=RequestUrls.SUPPORT_TICKETS %>"><spring:message code="Support" text="Support" /></a></li>
	         </security:authorize>
        </ul>
        <ul class="navbar-nav ml-auto nav-flex-icons">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" id="notification-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-bell nav-icon">
		            <%if(notificationDtos != null && !CollectionUtils.isEmpty(notificationDtos.getNotifications())) {%>
		              <span class="badge badge-danger" style="font-size: 45%;"><%=notificationDtos.getNotifications().size() %></span>
		            <%} %>
		            </i>
            </a>
            <div class="dropdown-menu dropdown-menu-lg-right dropdown-secondary" aria-labelledby="notification-dropdown">
              <div class="dropdown-item"><b><spring:message code="Notifications" text="Notifications" /></b></div>
              <%if(notificationDtos != null && !CollectionUtils.isEmpty(notificationDtos.getNotifications())) {%>
	              <%for(NotificationDto notificationDto : notificationDtos.getNotifications()) {%>
	                <div class="dropdown-divider"></div>
	                <div class="dropdown-item"><%=notificationDto.getMessage() %></div>
	              <%} %>
              <%} else {%>
                <div class="dropdown-divider"></div>
                <div class="dropdown-item">You have 0 notifications.</div>
              <%} %>
            </div>
		      </li>
		      
		      
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" id="profile-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-user-circle nav-icon"></i>
		        </a>
		        <div class="dropdown-menu dropdown-menu-lg-right dropdown-secondary" aria-labelledby="profile-dropdown">
		          <div class="dropdown-item"><b><%=name %></b></div>
		          <div class="dropdown-divider"></div>
		          <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}<%=RequestUrls.LOGOUT %>">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <security:authorize access="hasAuthority('ADMIN')">
                    <a class="dropdown-item" href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a>
                  </security:authorize>
                  <a class="dropdown-item" href="${contextPath}<%=RequestUrls.MY_ACCOUNT %>"><spring:message code="My Account" text="My Account" /></a>
                  <security:authorize access="hasAuthority('CAN_VIEW_PRODUCT_MODULE')">
                    <a class="dropdown-item" href="${contextPath}<%=RequestUrls.SHOPPING_CART %>"><spring:message code="Shopping Cart" text="Shopping Cart" /></a>
                    <a class="dropdown-item" href="${contextPath}<%=RequestUrls.ORDERS %>"><spring:message code="Your Orders" text="Your Orders" /></a>
                  </security:authorize>
                  <a class="dropdown-item" href="#" onclick="document.forms['logoutForm'].submit()"><spring:message code="Logout" text="Logout" /></a>
                </form>
              </c:if>
		        </div>
		      </li>
		    </ul>
        <!-- <div id="navbarNavDropdown" class="navbar-collapse collapse justify-content-end">
				    <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="side-dropdown-menus" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=name %></a>
                    <div class="dropdown-menu" aria-labelledby="side-dropdown-menus">
                      <c:if test="${pageContext.request.userPrincipal.name != null}">
								        <form id="logoutForm" method="POST" action="${contextPath}<%=RequestUrls.LOGOUT %>">
									        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                        <security:authorize access="hasAuthority('ADMIN')">
							              <a class="dropdown-item" href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a>
							            </security:authorize>
							            <a class="dropdown-item" href="${contextPath}<%=RequestUrls.MY_ACCOUNT %>"><spring:message code="My Account" text="My Account" /></a>
							            <a class="dropdown-item" href="#" onclick="document.forms['logoutForm'].submit()"><spring:message code="Logout" text="Logout" /></a>
						            </form>
                      </c:if>
                    </div>
                </li>
            </ul>
        </div> -->
    </nav>
  </div>
</div>