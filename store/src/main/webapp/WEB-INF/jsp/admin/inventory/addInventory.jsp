<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/numeric.js"></script>
<script>
$(document).ready(function () {
	sumAllSummableTableColumn();
	
    $("#addRow").on("click", function () {
    	var counter = $(this).data('count');
        var newRow = $("<tr>");
        var cols = "";
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].name" name="inventoryItems[' + counter + '].name" class="form-control input-sm" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].code" name="inventoryItems[' + counter + '].code" class="form-control input-sm" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].mrp" name="inventoryItems[' + counter + '].mrp" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].quantity" name="inventoryItems[' + counter + '].quantity" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].freeQuantity" name="inventoryItems[' + counter + '].freeQuantity" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].rate" name="inventoryItems[' + counter + '].rate" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].amount" name="inventoryItems[' + counter + '].amount" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].schemeDiscount" name="inventoryItems[' + counter + '].schemeDiscount" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].tradeDiscount" name="inventoryItems[' + counter + '].tradeDiscount" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].otherDiscount" name="inventoryItems[' + counter + '].otherDiscount" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].taxableAmount" name="inventoryItems[' + counter + '].taxableAmount" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].cgst" name="inventoryItems[' + counter + '].cgst" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].sgst" name="inventoryItems[' + counter + '].sgst" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="text" id="inventoryItems[' + counter + '].igst" name="inventoryItems[' + counter + '].igst" class="form-control input-sm sumable" /></td>';
		cols += '<td><input type="text" id="inventoryItems[' + counter + '].totalAmount" name="inventoryItems[' + counter + '].totalAmount" class="form-control input-sm sumable" /></td>';
        cols += '<td><input type="button" id="deleteRow' + counter + '" class="btn btn-sm btn-danger btn-delete-row"  value="-"></td>';
        newRow.append(cols);
        $("#inventoryItemTable").append(newRow);
        if(counter != 1) {
        	$("#deleteRow" + (counter - 1)).hide();
        }
        $(this).data('count', (counter + 1));
    });

    $("#inventoryItemTable").on("click", ".btn-delete-row", function (event) {
    	var counter = $("#addRow").data('count');
    	var deleteBtnId = $(this).attr('id');
    	var index = deleteBtnId.substring(9);
        $(this).closest("tr").remove();
        if(index != 1) {
        	$("#deleteRow" + (index - 1)).show();
        }
        $("#addRow").data('count', (counter - 1));
        sumAllSummableTableColumn();
    });
    
    $(document).on("blur", ".sumable", function (event) {
    	var td = $(this).parent()[0];
    	var tdIndex = td.cellIndex;
    	sumTableColumn(tdIndex);
    });
    
    function sumAllSummableTableColumn() {
    	for(var i=2;i<15;i++) {
    		sumTableColumn(i);
    	}
    }
    
    function sumTableColumn(tdIndex) {
    	var sum = 0;
    	$("#inventoryItemTable tbody tr").each(function() {
    		var cell = $("td", this).eq(tdIndex);
    		var simpleText = cell.find("input[type='text']").val();
    		var colValue = parseFloat(simpleText);
    		if(!isNaN(colValue)) {
    			sum += colValue;
    		}
    	});
    	
    	$("#inventoryItemTable tfoot #totalRow th").eq(tdIndex).text(sum.toFixed(2));
    }
    
});
</script>
<script>
	function markDelete(index) {
		$("#inventoryItems"+index+"\\.isDeleted").val(true);
	}
</script>
<%
String action = "Save";
%>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.INVENTORY %>"><spring:message code="Inventory" text="Inventory" /></a></li>
  <c:choose>
    <c:when test="${empty inventoryDto.id}">
      <li class="breadcrumb-item active"><spring:message code="Add Inventory" text="Add Inventory" /></li>
    </c:when>
    <c:otherwise>
      <li class="breadcrumb-item active"><spring:message code="Edit Inventory" text="Edit Inventory" /></li>
      <% action = "Edit"; %>
    </c:otherwise>
  </c:choose>
</ol>
<form:form method="POST" modelAttribute="<%=FieldNames.INVENTORY_DTO %>" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.INVENTORY %>">
<form:hidden path="<%=FieldNames.ID %>"/>
<h6><spring:message code="Agency Details" text="Agency Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.AGENCY_EMAIL %>"><spring:message code="Agency Email" text="Agency Email" />&nbsp;<span class="urgent_fields">*</span></label>
				  <form:input type="text" path="<%=FieldNames.AGENCY_EMAIL %>" id="<%=FieldNames.AGENCY_EMAIL %>" class="form-control form-control-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.AGENCY_EMAIL %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.AGENCY_CONTACT_NUMBER %>"><spring:message code="Agency Contact Number" text="Agency Contact Number" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.AGENCY_CONTACT_NUMBER %>" id="<%=FieldNames.AGENCY_CONTACT_NUMBER %>" class="form-control form-control-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.AGENCY_CONTACT_NUMBER %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.AGENCY_NAME %>"><spring:message code="Agency Name" text="Agency Name" />&nbsp;<span class="urgent_fields">*</span></label>
				  <form:input type="text" path="<%=FieldNames.AGENCY_NAME %>" id="<%=FieldNames.AGENCY_NAME %>" class="form-control form-control-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.AGENCY_NAME %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.SALESMAN_NAME %>"><spring:message code="Salesman Name" text="Salesman Name" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.SALESMAN_NAME %>" id="<%=FieldNames.SALESMAN_NAME %>" class="form-control form-control-sm" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.SALESMAN_NAME %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-12">
                <label for="<%=FieldNames.AGENCY_ADDRESS %>"><spring:message code="Agency Address" text="Agency Address" /></label>
                <form:textarea path="<%=FieldNames.AGENCY_ADDRESS %>" rows="5" cols="60" class="form-control form-control-sm" />
              </div>
          </div>
        </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<h6><spring:message code="Bill Details" text="Bill Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.BILL_NUMBER %>"><spring:message code="Bill Number" text="Bill Number" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.BILL_NUMBER %>" id="<%=FieldNames.BILL_NUMBER %>" class="form-control form-control-sm numeric" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.BILL_NUMBER %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.BILL_DATE %>"><spring:message code="Bill Date" text="Bill Date" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.BILL_DATE %>" id="<%=FieldNames.BILL_DATE %>" class="form-control form-control-sm numeric" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.BILL_DATE %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.GST_NUMBER %>"><spring:message code="GST Number" text="GST Number" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.GST_NUMBER %>" id="<%=FieldNames.GST_NUMBER %>" class="form-control form-control-sm numeric" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.GST_NUMBER %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PAN_NUMBER %>"><spring:message code="PAN Number" text="PAN Number" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:input type="text" path="<%=FieldNames.PAN_NUMBER %>" id="<%=FieldNames.PAN_NUMBER %>" class="form-control form-control-sm numeric" autocomplete="off"/>
                  <form:errors path="<%=FieldNames.PAN_NUMBER %>" class="help-inline has-error"></form:errors>
              </div>
          </div>
        </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<h6><spring:message code="Payment Details" text="Payment Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-10 mx-auto">
          <div class="form-group row">
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PAYMENT_STATUS %>"><spring:message code="Payment Status" text="Payment Status" />&nbsp;<span class="urgent_fields">*</span></label>
                  <form:select path="<%=FieldNames.PAYMENT_STATUS %>" id="<%=FieldNames.PAYMENT_STATUS %>" class="form-control form-control-sm">
		          	<form:option value=""><spring:message code="Select Payment Status" text="Select Payment Status" /></form:option>
		            <c:forEach var="status" items="${paymentStatuses}">
		            	<form:option value="${status.name()}">${status.name()}</form:option>
		            </c:forEach>
		          </form:select>
		          <form:errors path="<%=FieldNames.PAYMENT_STATUS %>" class="help-inline has-error"></form:errors>
              </div>
              <div class="col-sm-6">
                  <label for="<%=FieldNames.PAYMENT_MODE %>"><spring:message code="Mode of Payment" text="Mode of Payment" /></label>
                  <form:select path="<%=FieldNames.PAYMENT_MODE %>" id="<%=FieldNames.PAYMENT_MODE %>" class="form-control form-control-sm">
		          	<form:option value=""><spring:message code="Select Payment Mode" text="Select Payment Mode" /></form:option>
		            <c:forEach var="mode" items="${paymentModes}">
		            	<form:option value="${mode.name()}">${mode.name()}</form:option>
		            </c:forEach>
		          </form:select>
              </div>
          </div>
        </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<h6><spring:message code="Item Details" text="Item Details" /></h6>
<div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col-md-11 mx-auto">
          <div class="form-group row">
              <div class="col-sm-12">
                  <table class="table" id="inventoryItemTable">
                  	<thead>
					<tr>
						<th><spring:message code="Name" text="Name" /></th>
						<th><spring:message code="Code" text="Code" /></th>
						<th><spring:message code="MRP" text="MRP" /></th>
						<th><spring:message code="Quantity" text="Quantity" /></th>
						<th><spring:message code="Free Quantity" text="Free Quantity" /></th>
						<th><spring:message code="Rate" text="Rate" /></th>
						<th><spring:message code="Amount" text="Amount" /></th>
						<th><spring:message code="Scheme Discount" text="Scheme Discount" /></th>
						<th><spring:message code="Trade Discount" text="Trade Discount" /></th>
						<th><spring:message code="Other Discount" text="Other Discount" /></th>
						<th><spring:message code="Taxable Amount" text="Taxable Amount" /></th>
						<th><spring:message code="CGST" text="CGST" /></th>
						<th><spring:message code="SGST" text="SGST" /></th>
						<th><spring:message code="IGST" text="IGST" /></th>
						<th><spring:message code="Total Amount" text="Total Amount" /></th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${inventoryDto.inventoryItems}" varStatus="loop">
						<form:hidden path="inventoryItems[${loop.index}].id" value="${item.id}"/>
						<form:hidden path="inventoryItems[${loop.index}].isDeleted" value="false"/>
						<tr>
						<td><form:input type="text" path="inventoryItems[${loop.index}].name" value="${item.name}" class="form-control input-sm" required="required" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].code" value="${item.code}" class="form-control input-sm" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].mrp" value="${item.mrp}" class="form-control input-sm sumable" required="required" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].quantity" value="${item.quantity}" class="form-control input-sm sumable" required="required" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].freeQuantity" value="${item.freeQuantity}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].rate" value="${item.rate}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].amount" value="${item.amount}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].schemeDiscount" value="${item.schemeDiscount}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].tradeDiscount" value="${item.tradeDiscount}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].otherDiscount" value="${item.otherDiscount}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].taxableAmount" value="${item.taxableAmount}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].cgst" value="${item.cgst}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].sgst" value="${item.sgst}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].igst" value="${item.igst}" class="form-control input-sm sumable" autocomplete="off" /></td>
						<td><form:input type="text" path="inventoryItems[${loop.index}].totalAmount" value="${item.totalAmount}" class="form-control input-sm sumable" required="required" autocomplete="off" /></td>
						<td>
						<c:if test="${inventoryDto.inventoryItems.size() > 1}">
							<c:choose>
							    <c:when test="${loop.index == inventoryDto.inventoryItems.size()-1}">
							      <input type="button" id="deleteRow${loop.index}" class="btn btn-sm btn-danger btn-delete-row" onclick="markDelete(${loop.index});" value="-">
							    </c:when>
							    <c:otherwise>
							      <input type="button" id="deleteRow${loop.index}" class="btn btn-sm btn-danger btn-delete-row" style="display:none;" onclick="markDelete(${loop.index});" value="-">
							    </c:otherwise>
							</c:choose>
						</c:if>
						</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr id="totalRow">
							<th></th><th></th><th></th>
							<th></th><th></th><th></th>
							<th></th><th></th><th></th>
							<th></th><th></th><th></th>
							<th></th><th></th><th></th><th></th>
						</tr>
						<tr>
							<td><input type="button" class="btn btn-sm btn-info" id="addRow" data-count="${inventoryDto.inventoryItems.size()}" value="<spring:message code="Add More" text="Add More" />" /></td>
							<td colspan="15"></td>
						</tr>
					</tfoot>
				  </table>
              </div>
          </div>
        </div>
    </div>
  </div>
</div>
<div class="row spacer"></div>
<div class="row">
    <div class="col-sm-2"><button type="submit" class="btn btn-info"><spring:message code="<%=action %>" text="<%=action %>" /></button></div>
    <div class="col-sm-10"></div>
</div>
</form:form>