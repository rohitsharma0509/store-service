<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.PRODUCTS %>"><spring:message code="Products" text="Products" /></a></li>
  <li class="breadcrumb-item active">${productDto.code}</li>
</ol>
<div class="row" style="height: 10px;"></div>
<div class="row">
  <div class="col-sm-12">
    <a class="float-right" href="${contextPath}<%=RequestUrls.ADD_PRODUCT %>?<%=FieldNames.ID %>=${productDto.id}"><spring:message code="Edit" text="Edit" /></a>
  </div>
</div>
<div class="row">
  <div class="col-sm-12">
    <div>
      <h5>${productDto.name}</h5>
      <h4>${productDto.code}</h4>
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item"><a class="nav-link active" id="about-tab" data-toggle="tab" href="#about" role="tab" aria-controls="about" aria-selected="true"><spring:message code="About" text="About" /></a></li>
        <li class="nav-item"><a class="nav-link" id="pricing-tab" data-toggle="tab" href="#pricing" role="tab" aria-controls="pricing" aria-selected="false"><spring:message code="c" text="Pricing" /></a></li>
        <li class="nav-item"><a class="nav-link" id="quantity-tab" data-toggle="tab" href="#quantity" role="tab" aria-controls="quantity" aria-selected="false"><spring:message code="Quantity" text="Quantity" /></a></li>
      </ul>
    </div>
  </div>
</div>
<div class="row">
  <div class="col-sm-12">
    <div class="tab-content" id="myTabContent">
      <div class="tab-pane fade show active" id="about" role="tabpanel" aria-labelledby="about-tab">
        <div class="row" style="height:50px;"></div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Name" text="Name" /></label></div>
          <div class="col-md-9"><p>${productDto.name}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Code" text="Code" /></label></div>
          <div class="col-md-9"><p>${productDto.code}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Brand Name" text="Brand Name" /></label></div>
          <div class="col-md-9"><p>${productDto.brandName}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Description" text="Description" /></label></div>
          <div class="col-md-9"><p>${productDto.description}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Created By" text="Created By" /></label></div>
          <div class="col-md-9"><p>${productDto.createdBy}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Creation Time" text="Creation Time" /></label></div>
          <div class="col-md-9"><p>${productDto.createdTs}</p></div>
        </div>
      </div>
      <div class="tab-pane fade" id="pricing" role="tabpanel" aria-labelledby="pricing-tab">
        <div class="row" style="height:50px;"></div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Purchase Price" text="Purchase Price" /></label></div>
          <div class="col-md-9"><p>${productDto.purchasePrice}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Sell Price" text="Sell Price" /></label></div>
          <div class="col-md-9"><p>${productDto.perProductPrice}</p></div>
        </div>
      </div>
      <div class="tab-pane fade" id="quantity" role="tabpanel" aria-labelledby="quantity-tab">
        <div class="row" style="height:50px;"></div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Total Quantity" text="Total Quantity" /></label></div>
          <div class="col-md-9"><p>${productDto.quantity}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Alert Quantity" text="Alert Quantity" /></label></div>
          <div class="col-md-9"><p>${productDto.alertQuantity}</p></div>
        </div>
        <div class="row">
          <div class="col-md-3"><label><spring:message code="Available Quantity" text="Available Quantity" /></label></div>
          <div class="col-md-9"><p>${productDto.availableQuantity}</p></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" style="height: 100px;"></div>