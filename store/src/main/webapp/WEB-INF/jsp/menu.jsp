<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="list-group">
  <a class="list-group-item" href="${contextPath}<%=RequestUrls.HOME %>"><spring:message code="Dashboard" text="Dashboard" /></a>
  <a class="list-group-item" href="${contextPath}<%=RequestUrls.PRODUCT_ALL %>"><spring:message code="Products" text="Products" /></a>
  <security:authorize access="hasAuthority('ADMIN')">
    <a class="list-group-item" href="${contextPath}<%=RequestUrls.STOCK %>"><spring:message code="Stock" text="Stock" /></a>
  </security:authorize>
  <a class="list-group-item" href="${contextPath}<%=RequestUrls.ORDERS %>"><spring:message code="Orders" text="Orders" /></a>
</div>