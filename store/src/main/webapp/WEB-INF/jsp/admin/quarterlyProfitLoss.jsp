<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-3">
		<form method="GET" class="form-horizontal" name="dailyForm" onsubmit="submitForm(event, this, 'quarterlyProfitLossTab')">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-12">
							<label for="quarter" class="control-label text-right"><spring:message code="Quarter" text="Quarter" /></label>${quarters}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="year" class="control-label text-right"><spring:message code="Year" text="Year" /></label>${years}
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="<spring:message code="Search" text="Search" />" style="margin-top: 15px;" class="btn btn-sm btn-info form-control">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="col-sm-9">
		<c:choose>
			<c:when test="${page.getContent().size() > 0}">
				<div class="row">
					<div class="col-sm-12">
						<table class="table content-table">
							<tr>
								<th><spring:message code="Quarter" text="Quarter" /></th>
								<th><spring:message code="No of Orders" text="No of Orders" /></th>
								<th><spring:message code="Sold Quantity" text="Sold Quantity" /></th>
								<th><spring:message code="Amount Received" text="Amount Received" /></th>
								<th><spring:message code="Profit / Loss" text="Profit / Loss" /></th>
							</tr>
							<c:forEach var="profitLossDto" items="${page.getContent()}">
								<tr>
									<td>${profitLossDto.orderDate}</td>
									<td>${profitLossDto.noOfOrders}</td>
									<td>${profitLossDto.soldQuantity}</td>
									<td>${profitLossDto.amountReceived}</td>
									<td>${profitLossDto.profitOrLoss}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<c:choose>
					<c:when test="${page.getTotalPages() > 1}">
						<div class="row">
							<div class="col-sm-12">${pagging}</div>
						</div>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-sm-12"><spring:message code="No Records Found" text="No Records Found" />.</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>