<%@page import="org.springframework.util.StringUtils"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="/js/tab.js"></script>
<script type="text/javascript">
$(function() {
	showTabOnStartUp('dailyProfitLossTab');
});
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item"><a href="${contextPath}<%=RequestUrls.ADMIN %>"><spring:message code="Admin" text="Admin" /></a></li>
  <li class="breadcrumb-item active"><spring:message code="Profit & Loss" text="Profit & Loss" /></li>
</ol>
<div class="row">
	<div class="col-sm-12">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link" data-toggle="tab" id="dailyProfitLossTab" data-target="#daily" href="${contextPath}<%=RequestUrls.DAILY_PROFIT_LOSS %>"><spring:message code="Daily" text="Daily" /></a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab" id="monthlyProfitLossTab" data-target="#monthly" href="${contextPath}<%=RequestUrls.MONTHLY_PROFIT_LOSS %>"><spring:message code="Monthly" text="Monthly" /></a></li>
			<li class="nav-item"><a class="nav-link"  data-toggle="tab" id="quarterlyProfitLossTab" data-target="#quarterly" href="${contextPath}<%=RequestUrls.QUARTERLY_PROFIT_LOSS %>"><spring:message code="Quarterly" text="Quarterly" /></a></li>
			<li class="nav-item"><a class="nav-link"  data-toggle="tab" id="yearlyProfitLossTab" data-target="#yearly" href="${contextPath}<%=RequestUrls.YEARLY_PROFIT_LOSS %>"><spring:message code="Yearly" text="Yearly" /></a></li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane container" id="daily"></div>
			<div class="tab-pane container fade" id="monthly"></div>
			<div class="tab-pane container fade" id="quarterly"></div>
			<div class="tab-pane container fade" id="yearly"></div>
		</div>
		<div class="d-flex justify-content-center" style="height: 110px; align-items: flex-end;">
		  <div class="spinner-grow spinner-grow-lg text-muted"></div>
		  <div class="spinner-grow spinner-grow-lg text-muted"></div>
		  <div class="spinner-grow spinner-grow-lg text-muted"></div>
		  <div class="spinner-grow spinner-grow-lg text-muted"></div>
		  <div class="spinner-grow spinner-grow-lg text-muted"></div>
		</div>
	</div>
</div>