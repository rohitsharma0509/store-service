<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="../js/actions.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Manage Settings" text="Manage Settings" /></li>
</ol>
<div class="row" style="height: 10px;"></div>
<div class="row">
  <div class="col-sm-12">
    <a class="btn btn-sm btn-primary-outline" href="${contextPath}<%=RequestUrls.ADD_SETTING %>"><spring:message code="Add Setting" text="Add Setting" /></a>
  </div>
</div>
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
            <th><spring:message code="Action" text="Action" /></th>
          </tr>
          <c:forEach var="setting" items="${page.getContent()}" varStatus="loop">
            <tr>
              <td>${setting.name}</td>
              <td>${setting.description}</td>
              <td>${setting.value}</td>
              <td>
                <a href="#" class="pover" rel="moreActions" data-popover-content="#singleRecordAction${loop.index}" data-placement="left" data-toggle="popover" ><i class="fa fa-list" aria-hidden="true"></i></a>
                <div id="singleRecordAction${loop.index}" class="d-none">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_SETTING %>?<%=FieldNames.ID %>=${setting.id}"><spring:message code="Edit" text="Edit" /></a></li>
                    <li class="list-group-item list-group-item-action"><a href="#" class="deleteBtn" data-flag="SINGLE" data-url="<%=RequestUrls.SETTINGS %>/${setting.id}"><spring:message code="Delete" text="Delete" /></a></li>
                  </ul>
                </div>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <hr>
    <div class="row norecord">
      <div class="col-sm-12"><spring:message code="No Records Found" text="No Records Found" />.</div>
    </div>
  </c:otherwise>
</c:choose>
<div class="row">
  <div class="modal fade" id="deleteConfirmation" role="dialog" aria-labelledby="deleteConfirmationLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog delete-modal" role="document">
      <div class="modal-content">
        <div class="modal-body mx-3">
          <div align="center">
            <div><i class="fa fa-times-circle delete-icon"></i></div>
            <div class="delete-modal-header"><spring:message code="Are you sure?" text="Are you sure?" /></div>
            <div class="delete-modal-content"><spring:message code="Do you really want to delete these records? This process cannot be undone" text="Do you really want to delete these records? This process cannot be undone" />.</div>
          <div>
        <form id="deleteForm">
          <input type="hidden" id="deleteType">
          <button type="submit" class="btn btn-lg btn-danger"><spring:message code="Delete" text="Delete" /></button>
          <button type="button" class="btn btn-lg btn-default" data-dismiss="modal"><spring:message code="Close" text="Close" /></button>
        </form>
      </div>
      </div>
        </div>
      </div>  
    </div>
  </div>
</div>
