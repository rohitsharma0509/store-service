<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.invoice {
	font-size: 14px;
}
.table thead th {
    background-color: #e9ecef;
}
.table-bordered td, .table-bordered th {
    border: 1px solid #721c24;
}
.table thead th {
    border-bottom: 2px solid #1b1e21;
}
</style>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><spring:message code="Order Detail" text="Order Detail" /></li>
</ol>
<hr>
<div class="row" style="height: 10px;"></div>
<div class="container invoice">
	<div class="row">
		<div class="col-sm-6">
			<spring:message code="Order Number" text="Order Number" />: <strong>${orderDto.orderNumber}</strong>
			<div>
				<strong>${userDto.firstName} ${userDto.lastName}</strong>
			</div>
			<div>${addressDto.addressLine1},</div>
			<div>${addressDto.addressLine2}</div>
			<div>${addressDto.city}, ${addressDto.state}, ${addressDto.pincode}</div>
			<div><strong><spring:message code="Email" text="Email" />:</strong> ${userDto.email}</div>
			<div><strong><spring:message code="Mobile" text="Mobile" />:</strong> ${userDto.mobile}</div>
		</div>
		<div class="col-sm-6">
			<div style="float:right"><strong><spring:message code="Order Date" text="Order Date" />:</strong> ${orderDto.createdTs}</div>
		</div>
	</div>
	<div class="row" style="height: 30px;"></div>
	<div class="row">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="text-left">#</th>
					<th class="text-center"><spring:message code="Item" text="Item" /></th>
					<th class="text-center"><spring:message code="Description" text="Description" /></th>
					<th class="text-center"><spring:message code="Unit Cost" text="Unit Cost" /></th>
					<th class="text-center"><spring:message code="Qty" text="Qty" /></th>
					<th class="text-right"><spring:message code="Total" text="Total" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderDetailDto" items="${orderDto.orderDetailDtos}" varStatus="loop">
				<tr>
					<td class="text-left">${loop.index + 1}</td>
					<td class="text-center">${orderDetailDto.name}</td>
					<td class="text-center">${orderDetailDto.code}</td>
					<td class="text-center">${orderDetailDto.perProductPrice}</td>
					<td class="text-center">${orderDetailDto.quantity}</td>
					<td class="text-right">${orderDetailDto.perProductPrice * orderDetailDto.quantity}</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
			    	<td class="center"><strong><spring:message code="Subtotal" text="Subtotal" /></strong></td>
			    	<td class="text-right" colspan="5"><strong>${orderDto.totalAmount}</strong></td>
			    </tr>
			    <tr>
			    	<td class="center"><strong><spring:message code="Tax" text="Tax" /></strong></td>
			    	<td class="text-right" colspan="5"><strong>0.0</strong></td>
			    </tr>
			    <tr>
			    	<td class="center"><strong><spring:message code="Total" text="Total" /></strong></td>
			    	<td class="text-right" colspan="5"><strong>${orderDto.totalAmount}</strong></td>
				</tr>
			</tfoot>
		</table>
	</div>
	<div class="row">
		<div class="col-sm-10"></div>
		<div class="col-sm-2" align="right" style="padding-right: 0px;"><a href="${contextPath}/orders/download/${orderDto.id}" class="btn btn-sm btn-info"> <spring:message code="Download" text="Download" /></a></div>
	</div>
</div>