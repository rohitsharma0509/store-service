<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="css/shoppingCart.css" rel="stylesheet">
<script src="js/shoppingCart.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}/allProducts"><spring:message code="Products" text="Products" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Shopping Cart" text="Shopping Cart" /></li>
</ol>
<div class="row" style="height:10px;"></div>
<div class="row">
	<div class="col-sm-12">
	<div class="card">
	<form:form method="POST" modelAttribute="shoppingCart" class="form-horizontal" enctype="multipart/form-data" action="shoppingCart">
		<div class="card-header">
	    	<i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;<spring:message code="Shopping cart" text="Shopping cart" />
	   		<div class="clearfix"></div>
	   </div>
       <div class="card-body">
       		<c:choose>
			<c:when test="${shoppingCart.getProductDtos().size() > 0}">	
       		<c:forEach var="product" items="${shoppingCart.productDtos}" varStatus="loop">
       		<form:hidden path="productDtos[${loop.index}].id" value="${product.id}"/>
       		<form:hidden path="productDtos[${loop.index}].name" value="${product.name}"/>
       		<form:hidden path="productDtos[${loop.index}].code" value="${product.code}"/>
       		<form:hidden path="productDtos[${loop.index}].perProductPrice" value="${product.perProductPrice}"/>
       		<form:hidden path="productDtos[${loop.index}].availableQuantity" value="${product.availableQuantity}"/>
       		<form:hidden path="totalPrice"/>
		   		<div class="row">
			   		<div class="col-sm-3 text-center">
			            <img class="img-responsive" src="http://placehold.it/120x80" alt="prewiew" width="120" height="80">
			        </div>
			        <div class="col-sm-3">
			            <h5><strong>${product.name}</strong></h5>
			            <h6>
			                <small>${product.code}</small>
			            </h6>
			        </div>
			        <div class="col-sm-2 text-md-right">
			        	<h6><strong>${product.perProductPrice} <span class="text-muted">x</span></strong></h6>
			        </div>
			        <div class="col-sm-1">
			        	<div class="quantity">
			        		<input type="button" value="+" class="plus btn-number" data-type="plus" data-field="productDtos[${loop.index}].quantity">
          					<input type="number" id="productDtos${loop.index}.quantity" name="productDtos[${loop.index}].quantity" step="1" max="${product.availableQuantity}" min="1" value="${product.quantity}" title="Qty" class="qty input-number" size="4" readonly>
              				<input type="button" value="-" class="minus btn-number" data-type="minus" data-field="productDtos[${loop.index}].quantity">
		                </div>
			        </div>
			        <div class="col-sm-2">
			        	<input type="text" name="subTotal[${loop.index}]" value="${product.perProductPrice*product.quantity}" data-field="${product.perProductPrice}" class="form-control input-sm" readonly />
			        </div>
			        <div class="col-sm-1">
			        	<button type="button" class="btn btn-outline-danger btn-xs" onclick="callAjaxForDelete('${contextPath}/shoppingCart/${product.id}')">
				        	<i class="fa fa-trash" aria-hidden="true"></i>
				        </button>
			        </div>
		    	</div>
	        	<c:if test="${shoppingCart.getProductDtos().size() != (loop.index+1)}">
	        	<hr>
	        	</c:if>
        	</c:forEach>
        	</c:when>
			<c:otherwise>
				<spring:message code="There is no items in cart" text="There is no items in cart" />.
	            <a href="${contextPath}/allProducts" class="pull-right"><spring:message code="Click here to add" text="Click here to add" /></a>
			</c:otherwise>
			</c:choose>
     	</div>
     	<c:if test="${shoppingCart.getProductDtos().size() > 0}">
     	<div class="card-footer">
	         <div class="pull-right" style="margin: 10px">
	         	 <button type="submit" class="btn btn-info pull-right"> <spring:message code="Checkout" text="Checkout" /></button>
	             <div class="pull-right" style="margin: 5px">
	                 <spring:message code="Total price" text="Total price" />: <b id="totalPriceLabel">${shoppingCart.totalPrice}</b>
	             </div>
	        </div>
    	</div>
    	</c:if>
    </form:form>
	</div>
	</div>
 </div>