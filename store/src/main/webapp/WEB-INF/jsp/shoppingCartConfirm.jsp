<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="/js/address.js"></script>
<script>
function validate(form) {
	var addressId = $('input[name=addressId]:checked').val();
	if(addressId == "" || addressId == null) {
		$('#alertMessages').modal({show:true});
		return false
	}
	form.submit();
}
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><i class="fa fa-address-book breadcrum-icon"></i>&nbsp;&nbsp;<a href="${contextPath}<%=RequestUrls.PRODUCT_ALL %>"><spring:message code="Products" text="Products" /></a></li>
	<% 
	String productId = "";
	if(null != request.getParameter("id")){ 
		productId = request.getParameter("id");
	%> 
	<%}else{%>
	  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.SHOPPING_CART %>"><spring:message code="Shopping Cart" text="Shopping Cart" /></a></li>
	<%} %>
	<li class="breadcrumb-item active"><spring:message code="Address" text="Address" /></li>
</ol>
<div class="row" style="height:10px;"></div>
<form method="GET" id="customerForm" class="form-horizontal" enctype="multipart/form-data" action="buy" onSubmit="return validate(this);">
<input type="hidden" name="<%=FieldNames.PRODUCT_ID %>" value="<%=productId %>">
<div class="row">
	<div class="col-sm-6">
		<div class="card">
			<div class="card-header">
	    		<i class="fa fa-user" aria-hidden="true"></i>&nbsp;<spring:message code="Address" text="Address" />
	 			<div class="clearfix"></div>
	 	</div>
		<div class="card-body main-center">
			<c:choose>
			<c:when test="${addresses.size() > 0}">
			<c:forEach var="address" items="${addresses}" varStatus="loop">
			<div class="row">
				 <div class="col-sm-12">
					 <div class="checkbox checkbox-success">
	             <input type="radio" name="addressId" value="${address.id}">
	             <label for="address">${address.addressLine1}, ${address.addressLine2}, ${address.city}, ${address.state} - ${address.pincode} &nbsp;&nbsp;</label>
	             <i class="fa fa-edit" onclick="editAddress(${address.id});" style="font-size: 13px;color: blue;"></i>
	         </div>
		      </div>
			</div>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="row address">
	       <div class="col-sm-12"><spring:message code="Address not available" text="Address not available" />.</div>
				</div>
			</c:otherwise>
			</c:choose>
		  <div class="row">
         <div class="col-sm-12"><a href="#" id="addAddressBtn" onclick="addAddress();"><spring:message code="Add new address" text="Add new address" /></a></div>
       </div>
			</div>
		</div>
	</div>
	<div class="col-sm-6">
	<div class="card">
		<div class="card-header">
	    	<i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;<spring:message code="Items" text="Items" />
	   		<div class="clearfix"></div>
	   </div>
       <div class="card-body">
       		<c:choose>
			<c:when test="${orderDetailDtos.size() > 0}">	
       		<c:forEach var="orderDetailDto" items="${orderDetailDtos}" varStatus="loop">
		   		<div class="row">
			   		<div class="col-sm-4 text-center">
			            <img class="img-responsive" src="http://placehold.it/120x80" alt="prewiew" width="120" height="80">
			        </div>
			        <div class="col-sm-3">
			            <h5><strong>${orderDetailDto.name}</strong></h5>
			            <h6>
			                <small>${orderDetailDto.code}</small>
			            </h6>
			        </div>
			        <div class="col-sm-5 text-md-right">
			        	<h6>${orderDetailDto.perProductPrice} <span class="text-muted">x</span> ${orderDetailDto.quantity} = <strong>${ orderDetailDto.perProductPrice*orderDetailDto.quantity }</strong></h6>
			        </div>
		    	</div>
	        	<hr>
        		</c:forEach>
	        	<div class="row">
			   		<div class="col-sm-7 text-center">
			         
			        </div>
			        <div class="col-sm-5 text-md-right">
			        	<h6><spring:message code="Total" text="Total" /> : <strong>${totalPrice}</strong></h6>
			        </div>
		    	</div>
        	</c:when>
			<c:otherwise>
				<spring:message code="There is no items in cart" text="There is no items in cart" />.
			</c:otherwise>
			</c:choose>
     	</div>
     	<c:if test="${orderDetailDtos.size() > 0}">
     	<div class="card-footer">
	         <div class="row">
	        	<div class="col-sm-9 text-center"></div>
	        	<div class="col-sm-3 text-center"><button type="submit" class="btn btn-sm btn-info"> <spring:message code="Buy Now" text="Buy Now" /></button></div>
			</div>
    	</div>
    	</c:if>
     	
	</div>
	</div>
 </div>
<div class="row">
  <div class="modal fade" id="alertMessages" role="dialog" aria-labelledby="alertMessagesLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog delete-modal" role="document">
      <div class="modal-content ">
        <div class="modal-body mx-3">
          <div align="center">
            <div><i class="fa fa-warning warning-icon"></i></div>
            <div class="warning-modal-header"><spring:message code="Sorry" text="Sorry" />!</div>
            <div class="warning-modal-content"><spring:message code="Please select your address" text="Please select your address" /></div>
            <button type="button" class="btn btn-lg btn-default" data-dismiss="modal"><spring:message code="OK" text="OK" /></button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div id="addressModal"></div>
 </form>