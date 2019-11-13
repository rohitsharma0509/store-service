<%@page import="com.app.ecom.store.dto.userservice.UserDto"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
UserDto userDto = (UserDto) session.getAttribute("user");
String name = String.format("%1$s %2$s", userDto.getFirstName(), userDto.getLastName());
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
.main-row {
  min-height: 400px;
}
.usermenulistcol {
  padding-right: 0px;
}

.submenus {
  background-color: #222222a8;
}

.menubar>li>a {
  margin-right: 0px;
  border: 1px solid #000000;
}

.navbar-dark .navbar-nav .nav-link {
  color: white;
}

.urgent_fields {
  color: red;
}

.form-group-row {
  padding-bottom: 10px;
}

.main-center {
  background: #009edf1a;
  color: #000000;
  text-shadow: none;
  -webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  -moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

.shadow {
  text-shadow: none;
  -webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  -moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

.content-table {
  background: #009edf1a;
  -webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  -moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
  box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}
.has-error {
    color: red;
}
.btn-primary-outline {
  background-color: transparent;
  border-color: #ccc;
  float: right;
  margin-left: 5px;
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
</style>
<div class="row">
  <div class="col-sm-12">
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <!-- <a class="navbar-brand" href="#"><img src="../images/logo.jpg" alt="Store" style="width:40px;"></a> -->
        <div id="navbarNavDropdown" class="navbar-collapse collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="side-dropdown-menus" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=name %></a>
                    <div class="dropdown-menu" aria-labelledby="side-dropdown-menus">
                      <c:if test="${pageContext.request.userPrincipal.name != null}">
								        <form id="logoutForm" method="POST" action="${contextPath}/logout">
									        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                        <security:authorize access="hasAuthority('ADMIN')">
							              <a class="dropdown-item" href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a>
							            </security:authorize>
							            <a class="dropdown-item" href="${contextPath}<%=RequestUrls.MY_ACCOUNT %>"><spring:message code="My Account" text="My Account" /></a>
							            <a class="dropdown-item" href="#" onclick="document.forms['logoutForm'].submit()"><spring:message code="Logout" text="Logout" /></a>
						            </form>
                      </c:if>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
  </div>
</div>