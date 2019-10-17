<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="list-group">
  <a class="list-group-item" href="${contextPath}/home"><spring:message code="Dashboard" text="Dashboard" /></a>
  <a class="list-group-item" href="${contextPath}/allProducts"><spring:message code="Products" text="Products" /></a>
  <security:authorize access="hasAuthority('ADMIN')">
    <a class="list-group-item" href="${contextPath}/stock"><spring:message code="Stock" text="Stock" /></a>
  </security:authorize>
  <a class="list-group-item" href="${contextPath}/orders"><spring:message code="Orders" text="Orders" /></a>
  <!-- <a class="list-group-item" href="${contextPath}/customers"><spring:message code="Customers" text="Customers" /></a> -->
</div>