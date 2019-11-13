<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../js/actions.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Manage Settings" text="Manage Settings" /></li>
</ol>
<div class="row" style="height: 10px;"></div>
<div class="row">
  <div class="col-sm-12">
    <a class="btn btn-sm btn-primary-outline" href="${contextPath}<%=RequestUrls.ADD_SETTING %>"><spring:message code="Add Setting" text="Add Setting" /></a>
  </div>
</div>
<hr>
<c:choose>
  <c:when test="${page.getContent().size() > 0}">
    <div class="row" style="height: 10px;"></div>
    <div class="row">
      <div class="col-sm-12">
        <table class="table content-table">
          <tr>
            <th><spring:message code="Name" text="Name" /></th>
            <th><spring:message code="Description" text="Description" /></th>
            <th><spring:message code="Value" text="Value" /></th>
          </tr>
          <c:forEach var="setting" items="${page.getContent()}" varStatus="loop">
            <tr>
              <td>${setting.name}</td>
              <td>${setting.description}</td>
              <td>${setting.value}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
    <div class="row">
        <div class="col-sm-2"><button type="submit" class="btn btn-success"><spring:message code="Save" text="Save" /></button></div>
        <div class="col-sm-10"></div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="row norecord">
      <div class="col-sm-12"><spring:message code="No Records Found" text="No Records Found" />.</div>
    </div>
  </c:otherwise>
</c:choose>
