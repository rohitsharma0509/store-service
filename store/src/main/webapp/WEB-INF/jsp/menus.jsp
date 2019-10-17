<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
  <div class="col-sm-12">
    <nav class="navbar navbar-expand-sm navbar-dark" style="background-color:#009688;">
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="${contextPath}/home"><spring:message code="Dashboard" text="Dashboard" /></a></li>
        <li class="nav-item"><a class="nav-link" href="${contextPath}/allProducts"><spring:message code="Products" text="Products" /></a></li>
        <li class="nav-item"><a class="nav-link" href="${contextPath}/shoppingCart"><spring:message code="Shopping Cart" text="Shopping Cart" /></a>
        <security:authorize access="hasAuthority('ADMIN')">
          <li class="nav-item"><a class="nav-link" href="${contextPath}/stock"><spring:message code="Stock" text="Stock" /></a></li>
        </security:authorize>
        <li class="nav-item"><a class="nav-link" href="${contextPath}/orders"><spring:message code="Orders" text="Orders" /></a></li>
      </ul>
    </nav>
  </div>
</div>