<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="/js/tab.js"></script>
<script>
$(function() {
  showTabOnStartUp('accountTab');
});
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><spring:message code="My Account" text="My Account" /></li>
</ol>
<div class="row">
  <div class="col-sm-12">
    <ul class="nav nav-tabs">
      <li class="nav-item"><a class="nav-link" data-toggle="tab" id="accountTab" href="#account"><spring:message code="Account Setting" text="Account Setting" /></a></li>
      <li class="nav-item"><a class="nav-link" data-toggle="tab" id="pswrdTab" href="${contextPath}<%=RequestUrls.CHANGE_PSWRD %>?<%=FieldNames.IS_VALIDATION_REQUIRED %>=false" data-target="#pswrd"><spring:message code="Change Password" text="Change Password" /></a></li>
      <li class="nav-item"><a class="nav-link" data-toggle="tab" id="personalTab" href="#personal"><spring:message code="Personal Information" text="Personal Information" /></a></li>
    </ul>
    <div class="tab-content">
      <div class="tab-pane container fade" id="account">
        <div class="row" style="height:20px;"></div>
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="Email" text="Email" /> : </div>
          <div class="col-sm-3 text-left">${userDto.email}</div>
          <div class="col-sm-3 text-right"><spring:message code="Username" text="Username" /> : </div>
          <div class="col-sm-3 text-left">${userDto.username}</div>
        </div>
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="Language" text="Language" /> : </div>
          <div class="col-sm-3 text-left">${userDto.language}</div>
          <div class="col-sm-6"></div>
        </div>
      </div>
      <div class="tab-pane container fade" id="pswrd"></div>
      <div class="tab-pane container fade" id="personal">
        <div class="row" style="height: 20px;"></div>
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="First Name" text="First Name" /> : </div>
          <div class="col-sm-3 text-left">${userDto.firstName}</div>
          <div class="col-sm-3 text-right"><spring:message code="Last Name" text="Last Name" /> : </div>
          <div class="col-sm-3 text-left">${userDto.lastName}</div>
        </div>
        <div class="row">
          <div class="col-sm-3 text-right"><spring:message code="Mobile" text="Mobile" /> : </div>
          <div class="col-sm-3 text-left">${userDto.mobile}</div>
          <div class="col-sm-6"></div> 
        </div>
        <div class="heading"><spring:message code="Address" text="Address" /></div>
        <c:choose>
          <c:when test="${userDto.getAddressDtos().size() > 0}">
            <div class="row">
              <div class="col-sm-12">
                <table class="table">
                  <tr>
                    <th><spring:message code="Address Line1" text="Address Line1" /></th>
                    <th><spring:message code="Address Line2" text="Address Line2" /></th>
                    <th><spring:message code="City" text="City" /></th>
                    <th><spring:message code="State" text="State" /></th>
                    <th><spring:message code="Pincode" text="Pincode" /></th>
                    <th><spring:message code="Country" text="Countr" /></th>
                  </tr>
                  <c:forEach var="addressDto" items="${userDto.getAddressDtos()}">
                    <tr>
                      <td>${addressDto.addressLine1}</td>
                      <td>${addressDto.addressLine2}</td>
                      <td>${addressDto.city}</td>
                      <td>${addressDto.state}</td>
                      <td>${addressDto.pincode}</td>
                      <td>${addressDto.country}</td>
                    </tr>
                  </c:forEach>
                </table>
                <div class="row" style="height: 20px;"></div>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <div class="row norecord">
              <div class="col-sm-12">
                <spring:message code="No Records Found" text="No Records Found" />
              </div>
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</div>
