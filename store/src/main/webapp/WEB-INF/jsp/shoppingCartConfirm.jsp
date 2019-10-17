<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="js/address.js"></script>
<script>
function validate(form) {
	var addressId = $('input[name=addressId]:checked').val();
	if(addressId == "" || addressId == null) {
		alert("Please select your address");
		return false
	}
	form.submit();
}
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/allProducts"><spring:message code="Products" text="Products" /></a></li>
	<% 
	String productId = "";
	if(null != request.getParameter("id")){ 
		productId = request.getParameter("id");
	%> 
	<%}else{%>
	  <li class="breadcrumb-item"><a href="${contextPath}/shoppingCart"><spring:message code="Shopping Cart" text="Shopping Cart" /></a></li>
	<%} %>
	<li class="breadcrumb-item active"><spring:message code="Address" text="Address" /></li>
</ol>
<div class="row" style="height:10px;"></div>
<form method="GET" id="customerForm" class="form-horizontal" enctype="multipart/form-data" action="buy" onSubmit="return validate(this);">
<input type="hidden" name="productId" value="<%=productId %>">
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
	             <label for="address">${address.addressLine1}, ${address.addressLine2}, ${address.city}, ${address.state} - ${address.pincode}</label>
	         </div>
		      </div>
			</div>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="row address">
	       <div class="col-sm-12">Address not available. Click below link to add.</div>
				</div>
			</c:otherwise>
			</c:choose>
		  <div class="row">
         <div class="col-sm-12"><a href="#" id="addAddressBtn">Add new address</a></div>
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
			<c:when test="${productDtos.size() > 0}">	
       		<c:forEach var="product" items="${productDtos}" varStatus="loop">
		   		<div class="row">
			   		<div class="col-sm-4 text-center">
			            <img class="img-responsive" src="http://placehold.it/120x80" alt="prewiew" width="120" height="80">
			        </div>
			        <div class="col-sm-3">
			            <h5><strong>${product.name}</strong></h5>
			            <h6>
			                <small>${product.code}</small>
			            </h6>
			        </div>
			        <div class="col-sm-5 text-md-right">
			        	<h6>${product.perProductPrice} <span class="text-muted">x</span> ${product.quantity} = <strong>${ product.perProductPrice*product.quantity }</strong></h6>
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
     	<c:if test="${productDtos.size() > 0}">
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
 <div id="addressModal"></div>
 </form>