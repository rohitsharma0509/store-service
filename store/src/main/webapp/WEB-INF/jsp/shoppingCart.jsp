<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="/css/shoppingCart.css" rel="stylesheet">
<script src="/js/shoppingCart.js"></script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.PRODUCT_ALL %>"><spring:message code="Products" text="Products" /></a></li>
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
			   <c:when test="${shoppingCart.getOrderDetailDtos().size() > 0}">	
       		<c:forEach var="orderDetailDto" items="${shoppingCart.orderDetailDtos}" varStatus="loop">
       		<form:hidden path="orderDetailDtos[${loop.index}].productId" value="${orderDetailDto.productId}"/>
       		<form:hidden path="orderDetailDtos[${loop.index}].name" value="${orderDetailDto.name}"/>
       		<form:hidden path="orderDetailDtos[${loop.index}].code" value="${orderDetailDto.code}"/>
       		<form:hidden path="orderDetailDtos[${loop.index}].perProductPrice" value="${orderDetailDto.perProductPrice}"/>
       		<form:hidden path="orderDetailDtos[${loop.index}].availableQuantity" value="${orderDetailDto.availableQuantity}"/>
       		<form:hidden path="totalPrice"/>
		   		<div class="row">
			   		<div class="col-sm-3 text-center">
			            <img class="img-responsive" src="http://placehold.it/120x80" alt="prewiew" width="120" height="80">
			        </div>
			        <div class="col-sm-3">
			            <h5><strong>${orderDetailDto.name}</strong></h5>
			            <h6>
			                <small>${orderDetailDto.code}</small>
			            </h6>
			        </div>
			        <div class="col-sm-2 text-md-right">
			        	<h6><strong>${orderDetailDto.perProductPrice} <span class="text-muted">x</span></strong></h6>
			        </div>
			        <div class="col-sm-1">
			        	<div class="quantity">
			        		<input type="button" value="+" class="plus btn-number" data-type="plus" data-field="orderDetailDtos[${loop.index}].quantity">
          					<input type="number" id="orderDetailDtos${loop.index}.quantity" name="orderDetailDtos[${loop.index}].quantity" step="1" max="${orderDetailDto.availableQuantity}" min="1" value="${orderDetailDto.quantity}" title="Qty" class="qty input-number" size="4" readonly>
              				<input type="button" value="-" class="minus btn-number" data-type="minus" data-field="orderDetailDtos[${loop.index}].quantity">
		                </div>
			        </div>
			        <div class="col-sm-2">
			        	<input type="text" name="subTotal[${loop.index}]" value="${orderDetailDto.perProductPrice*orderDetailDto.quantity}" data-field="${orderDetailDto.perProductPrice}" class="form-control input-sm" readonly />
			        </div>
			        <div class="col-sm-1">
			        	<button type="button" class="btn btn-outline-danger btn-xs removeFromCartBtn" data-url="<%=RequestUrls.SHOPPING_CART %>/${orderDetailDto.productId}">
				        	<i class="fa fa-trash" aria-hidden="true"></i>
				        </button>
			        </div>
		    	</div>
	        	<c:if test="${shoppingCart.getOrderDetailDtos().size() != (loop.index+1)}">
	        	<hr>
	        	</c:if>
        	</c:forEach>
        	</c:when>
			<c:otherwise>
				<spring:message code="There is no items in cart" text="There is no items in cart" />.
	            <a href="${contextPath}<%=RequestUrls.PRODUCT_ALL %>" class="pull-right"><spring:message code="Click here to add" text="Click here to add" /></a>
			</c:otherwise>
			</c:choose>
     	</div>
     	<c:if test="${shoppingCart.getOrderDetailDtos().size() > 0}">
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
<div class="row">
  <div class="modal fade" id="deleteConfirmation" role="dialog" aria-labelledby="deleteConfirmationLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog delete-modal" role="document">
      <div class="modal-content">
        <div class="modal-body mx-3">
          <div align="center">
            <div><i class="fa fa-times-circle delete-icon"></i></div>
            <div class="delete-modal-header"><spring:message code="Are you sure?" text="Are you sure?" /></div>
            <div class="delete-modal-content"><spring:message code="Do you really want to remove these item from cart?" text="Do you really want to remove these item from cart?" /></div>
          <div>
        <form:form id="deleteForm" method="post">
          <button type="submit" class="btn btn-lg btn-danger"><spring:message code="Delete" text="Delete" /></button>
          <button type="button" class="btn btn-lg btn-light" data-dismiss="modal"><spring:message code="Close" text="Close" /></button>
        </form:form>
      </div>
      </div>
        </div>
      </div>  
    </div>
  </div>
</div>