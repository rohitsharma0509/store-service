<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
var pageId = 1;
var totalRecords = ${page.totalRecords};

$(window).scroll(function () {
	var scrollData;
	if(totalRecords > 12*pageId) {
		scrollData="yes";
	}else{
		scrollData="no";
	}
	if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 50) {
		if(scrollData=="yes"){
			$('#loadingData1').show();
			setTimeout(function() {
			$('#loadingData1').hide();
			getProductList();
		},500);
	}
}});
function getProductList(){
 	var count = totalRecords;
 	var totalPages = count/12;
 	if(count%12 !=0) {
 		totalPages=totalPages+1;
 	}
 	pageId = pageId+1;
 	if(pageId <= totalPages) {
	   	$.ajax({    
			type: 'GET',
	        url: "/products/ajax",
	        data: {"page":pageId},
	        success: function(data){
	        	console.log(data);
	        	$('#contentDiv').append(data);
	        }
	    });  		
	}
}
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><spring:message code="All Products" text="All Products" /></li>
</ol>
<section id="filters">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
       <form method="GET" class="form-horizontal" action="<%=RequestUrls.PRODUCT_ALL %>">
        <div class="row">
          <div class="col-sm-2 p-0">
            <select id="<%=FieldNames.CATEGORY_ID %>" name="<%=FieldNames.CATEGORY_ID %>" class="form-control">
            <option value=""><spring:message code="Select Category" text="Select Category" /></option>
                <c:forEach var="category" items="${categories}">
                  <c:choose>
                  <c:when test="${param.categoryId == category.id}">
                    <option selected value="${category.id}">${category.name}</option>
                  </c:when>
                  <c:otherwise>
                    <option value="${category.id}">${category.name}</option>
                  </c:otherwise>
                  </c:choose>
                </c:forEach>
            </select>
          </div>
          <div class="col-sm-2 p-0">
            <spring:message code="Brand Name" text="Brand Name" var="label" />
            <input type="text" id="<%=FieldNames.BRAND_NAME %>" name="<%=FieldNames.BRAND_NAME %>" value="${param.brandName}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <spring:message code="Product Name" text="Product Name" var="label" />
            <input type="text" id="<%=FieldNames.PRODUCT_NAME %>" name="<%=FieldNames.PRODUCT_NAME %>" value="${param.productName}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <spring:message code="Price" text="Price" var="label" />
            <input type="text" id="<%=FieldNames.PRICE %>" name="<%=FieldNames.PRICE %>" value="${param.price}" class="form-control" placeholder="${label}" />
          </div>
          <div class="col-sm-2 p-0">
            <button type="submit" class="btn btn-info"><i class="fa fa-search" style="font-size: 18px;"></i></button>
          </div>
        </div>
       </form>
      </div>
    </div>
  </div>
</section>
<div class="row" style="height: 20px;"></div>
<div class="row">
	<div class="col-sm-12" id="contentDiv">
		<jsp:include page="productListing.jsp">
			<jsp:param name="page" value="${page}" />
		</jsp:include>
		<div class="row" id="loadingData1" style="display:none; width:100%;"><img src = 'images/loading-Image.gif'></div>
	</div>
</div>
<div class="row" style="height: 50px;"></div>