<%@page import="com.app.ecom.store.enums.ProductStatus"%>
<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="js/sockjs.min.js"></script>
<script src="js/stomp.min.js"></script>
<link href="css/chat.css" rel="stylesheet">
<script src="js/chat.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $('.carousel').carousel({
      interval: 10000
    });
    
    $.get('<%=RequestUrls.COUNT_PRODUCT %>', function(total) {
    	$.get('<%=RequestUrls.COUNT_PRODUCT %>?status=<%=ProductStatus.OUT_OF_STOCK %>', function(outOfStock) {
    		  var availableProducts = total - outOfStock;
    		  stopSpinnerAndUpdateValue("#out-of-stock-products", outOfStock);
    		  stopSpinnerAndUpdateValue("#available-products", availableProducts);
      }).fail(function() {
    	  stopSpinnerAndUpdateValue("#out-of-stock-products", "0");
        stopSpinnerAndUpdateValue("#available-products", "0");
      });
    	
      stopSpinnerAndUpdateValue("#total-products", total);
    }).fail(function() {
    	stopSpinnerAndUpdateValue("#total-products", "0");
    });    
    
    $.get('<%=RequestUrls.COUNT_PRODUCT %>?status=<%=ProductStatus.ALERT %>', function(response) {
      stopSpinnerAndUpdateValue("#alert-products", response);
    }).fail(function() {
      stopSpinnerAndUpdateValue("#alert-products", "0");
    });
    
    stopSpinnerAndUpdateValue = function(selector, val) {
    	$(selector).removeClass("spinner-border");
      $(selector).removeClass("spinner-border-sm");
      $(selector).text(val);
    } 
  });
</script>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><i class="fa fa-home breadcrum-icon"></i>&nbsp;&nbsp;<spring:message code="Dashboard" text="Dashboard" /></li>
  <!-- <li class="ml-auto">
    <div class="chat-wrapper">
      <a href="#" onclick="openChatWindow()"><i class="fa fa-comments" aria-hidden="true"></i>&nbsp;<spring:message code="Chat Now" text="Chat Now" /></a>
    </div>
  </li> -->
</ol>
<c:choose>
	<c:when test="${isAdmin}">
		<div class="row">
		  <div class="col-sm-12">
		    <div class="card-group">
		      <div class="card">
		        <div class="card-body text-center">
		          <h6><div id="total-products" class="spinner-border spinner-border-sm" role="status"></div></h6>
		          <h5><spring:message code="Total Products" text="Total Products" /></h5>
		        </div>
		      </div>
		      <div class="card">
		        <div class="card-body text-center">
		          <h6><div id="available-products" class="spinner-border spinner-border-sm" role="status"></div></h6>
		          <h5><spring:message code="Available Products" text="Available Products" /></h5>
		        </div>
		      </div>
		      <div class="card">
		        <div class="card-body text-center">
		          <h6 class="card-title"><div id="out-of-stock-products" class="spinner-border spinner-border-sm" role="status"></div></h6>
		          <h5 class="card-text"><spring:message code="Out of Stock Products" text="Out of Stock Products" /></h5>
		        </div>
		      </div>
		      <div class="card">
		        <div class="card-body text-center">
		          <h6><div id="alert-products" class="spinner-border spinner-border-sm" role="status"></div></h6>
		          <h5><spring:message code="Alert Products" text="Alert Products" /></h5>
		        </div>
		      </div>
		      <div class="card">
		        <div class="card-body text-center">
		          <h6>${todayOrder}</h6>
		          <h5><spring:message code="Today's Order" text="Today's Order" /></h5>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row" style="margin-top:50px;">
		  <div class="col-sm-12">
		    <div class="card">
		      <div class="card-body" style="padding:0px;">
		        <div class="card-img-top"><img style="max-width: 100%;" alt="Sales Comparision" src="data:image/png;base64,${salesComparisionGraph}"/></div>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row" style="margin-top:50px;">
		  <div class="col-sm-12">
		    <div class="card-deck">
		      <div class="card">
		        <div class="card-body" style="padding:0px;">
		          <div class="card-img-top"><img style="max-width: 100%;" alt="Last 5 days Sales" src="data:image/png;base64,${last5DaysSalesGraph}"/></div>
		        </div>
		      </div>
		      <div class="card">
		        <div class="card-body" style="padding:0px;">
		          <div class="card-img-top"><img style="max-width: 100%;" alt="Support Ticket Status" src="data:image/png;base64,${supportTicketStatusGraph}"/></div>
		        </div>
		      </div>
		      <div class="card">
		        <div class="card-body" style="padding:0px;">
		          <div class="card-img-top"><img style="max-width: 100%;" alt="Current Year Monthly Sales Graph" src="data:image/png;base64,${monthlySalesGraph}"/></div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	</c:when>
	<c:otherwise>
	  <div class="row">
		  <div class="col-sm-12">
		    <div class="card">
		      <div class="row">
		        <div class="col-sm-12">
		          <div class="card-group">
		            <div class="card">
		              <div class="card-body text-center">
		                <p class="card-title"><spring:message code="Today's Order" text="Today's Order" /></p>
		                <p class="card-text">${todayOrder}</p>
		              </div>
		            </div>
		            <div class="card">
		              <div class="card-body text-center">
		                <p class="card-title"><spring:message code="Last 5 Orders" text="Last 5 Orders" /></p>
		                <p class="card-text">Some text inside the first card</p>
		              </div>
		            </div>
		            <div class="card">
		              <div class="card-body text-center">
		                <p class="card-title"><spring:message code="Your Recent Tickets" text="Your Recent Tickets" /></p>
		                <p class="card-text">Some text inside the first card</p>
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row" style="margin-top:50px;">
		  <div class="col-sm-12">
		    <div class="card">
		      <div class="row">
		        <div class="col-sm-12">
		          <div class="card-group">
		            <div class="card">
		              <div class="card-body text-center">
		                <p class="card-title"><b><spring:message code="No. of Support Tickets in Various Status"/></b></p>
		                <p class="card-text">
		                  <table class="table text-center">
		                    <tr>
		                      <th><spring:message code="Status"/></th>
		                      <th><spring:message code="No. of Support Tickets"/></th>
		                    </tr>
		                    <c:forEach var="entry" items="${supportTicketStatusCounterMap}">
		                    <tr>
		                      <td>${entry.key}</td>
		                      <td>${entry.value}</td>
		                    </tr>
		                    </c:forEach>
		                  </table>
		                </p>
		              </div>
		            </div>
		            <div class="card">
		              <div class="card-body text-center">
		                <div class="card-img-top"><img style="max-width: 100%;" alt="Support Ticket Status" src="data:image/png;base64,${supportTicketStatusGraph}"/></div>
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	</c:otherwise>
</c:choose>
<div class="row">
  <div id="chatWindow" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">How may help you ?</h4>
          <button type="button" onclick="disconnect()" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <div id="chat-page">
            <div class="chat-container">
              <div class="connecting d-none">Connecting...</div>
              <ul id="messageArea"></ul>
              <br>
              <div class="form-group">
                <div class="input-group clearfix">
                  <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control" />
                  <input type="hidden" id="name" value="${pageContext.request.userPrincipal.name}" class="form-control" />
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <button class="btn btn-info btn-sm" id="btn-send" onclick="sendMessage()">Send</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" style="height: 50px;"></div>