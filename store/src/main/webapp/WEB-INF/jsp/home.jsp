<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="js/sockjs.min.js"></script>
<script src="js/stomp.min.js"></script>
<link href="css/chat.css" rel="stylesheet">
<script src="js/chat.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $('.carousel').carousel({
      interval: 10000
    })
  });
</script>
<style>
.indicator {
  background-color: #dee2e6!important;
  margin-bottom: 15px;
}
.indicator-body {
  color: black;
  min-height: 123px;
}
</style>
<ol class="breadcrumb">
  <li class="breadcrumb-item active"><spring:message code="Dashboard" text="Dashboard" /></li>
  <li class="ml-auto">
    <div class="chat-wrapper">
      <a href="#" onclick="openChatWindow()"><i class="fa fa-comments" aria-hidden="true"></i>&nbsp;<spring:message code="Chat Now" text="Chat Now" /></a>
    </div>
  </li>
</ol>
<div class="row" style="height: 10px;"></div>
<security:authorize access="hasAuthority('GUEST')">
<div class="row">
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${totalProducts}</h6>
        <h5><spring:message code="Today's Order" text="Today's Order" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${availableProducts}
        </h6>
        <h5><spring:message code="Current Week's Order" text="Current Week's Order" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6 class="card-title">
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${outOfStockProducts}
        </h6>
        <h5 class="card-text"><spring:message code="Current Month's Order" text="Current Month's Order" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${alertProducts}
        </h6>
        <h5><spring:message code="Current Year's Order" text="Current Year's Order" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${todayOrder}
        </h6>
        <h5><spring:message code="Offer Available" text="Offer Available" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;0
        </h6>
        <h5><spring:message code="Favarite Products" text="Favarite Products" /></h5>
      </div>
    </div>
  </div>
</div>
<div class="row">
  <div class="col-sm-4">
    <div class="card">
	    <div class="card-header">
	     <i class="fa fa-history" aria-hidden="true"></i>&nbsp;<spring:message code="Recent Orders" text="Recent Orders" />
	     <div class="clearfix"></div>
	    </div>
	    <div class="card-body">
	       <table class="table">
           <tr>
             <th><spring:message code="Order Number" text="Order Number" /></th>
             <th><spring:message code="Order Date" text="Order Date" /></th>
           </tr>
           <tr>
             <td>ORD0001</td>
             <td>2019-10-10</td>
           </tr>
         </table>
	    </div>
    </div>
  </div>
  <div class="col-sm-4">
    <div class="card">
      <div class="card-header">New Products</div>
      <div class="card-body">
				<div id="newProductsCarousel" class="carousel slide" data-ride="carousel">
				  <!-- Indicators -->
				  <ul class="carousel-indicators">
				    <li data-target="#newProductsCarousel" data-slide-to="0" class="active"></li>
				    <li data-target="#newProductsCarousel" data-slide-to="1"></li>
				    <li data-target="#newProductsCarousel" data-slide-to="2"></li>
				  </ul>
				
				  <!-- The slideshow -->
				  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <div class='text-center'>
			          <h6 class="card-title">1st</h6>
			          <h6 class="card-title">Brand1</h6>
			          <h6 class="card-title">10.0</h6>
			        </div>
				    </div>
				    <div class="carousel-item">
				      <div class='text-center'>
		            <h6 class="card-title">2nd</h6>
		            <h6 class="card-title">Brand1</h6>
		            <h6 class="card-title">10.0</h6>
			        </div>
				    </div>
				    <div class="carousel-item">
				      <div class='text-center'>
			          <h6 class="card-title">3rd</h6>
			          <h6 class="card-title">Brand1</h6>
			          <h6 class="card-title">10.0</h6>
				      </div>
				    </div>
				  </div>
				
				  <!-- Left and right controls -->
				  <a class="carousel-control-prev" href="#newProductsCarousel" data-slide="prev">
				    <span class="carousel-control-prev-icon"></span>
				  </a>
				  <a class="carousel-control-next" href="#newProductsCarousel" data-slide="next">
				    <span class="carousel-control-next-icon"></span>
				  </a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4">
    <div class="card">
      <div class="card-header">
       <i class="fa fa-history" aria-hidden="true"></i>&nbsp;<spring:message code="Offers" text="Offers" />
       <div class="clearfix"></div>
      </div>
      <div class="card-body">
         <table class="table">
           <tr>
             <th><spring:message code="Product Code" text="Product Code" /></th>
             <th><spring:message code="Product Name" text="Product Name" /></th>
           </tr>
           <tr>
             <td>PRD00001</td>
             <td>Dummy</td>
           </tr>
         </table>
      </div>
    </div>
  </div>
</div>
</security:authorize>
<security:authorize access="hasAuthority('ADMIN')">
<div class="row">
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${totalProducts}</h6>
        <h5><spring:message code="Total Products" text="Total Products" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${availableProducts}
        </h6>
        <h5><spring:message code="Available Products" text="Available Products" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6 class="card-title">
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${outOfStockProducts}
        </h6>
        <h5 class="card-text"><spring:message code="Out of Stock Products" text="Out of Stock Products" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${alertProducts}
        </h6>
        <h5><spring:message code="Alert Products" text="Alert Products" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${todayOrder}
        </h6>
        <h5><spring:message code="Today's Order" text="Today's Order" /></h5>
      </div>
    </div>
  </div>
  <div class="col-sm-2">
    <div class="card indicator">
      <div class="card-body indicator-body shadow">
        <h6>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;0
        </h6>
        <h5><spring:message code="Feedback Received" text="Feedback Received" /></h5>
      </div>
    </div>
  </div>
</div>
<div class="row" style="margin-top:15px;">
  <div class="col-sm-12">
    <div class="card">
      <div class="card-body shadow" style="padding:0px;">
        <div class="card-img-top"><img style="max-width: 100%;" alt="Stock Status" src="data:image/png;base64,${compareGraph}"/></div>
      </div>
    </div>
  </div>
</div>
<div class="row" style="margin-top:15px;">
  <div class="col-sm-4">
    <div class="card">
      <div class="card-body shadow" style="padding:0px;">
        <div class="card-img-top"><img style="max-width: 100%;" alt="Stock Status" src="data:image/png;base64,${stockStatus}"/></div>
      </div>
    </div>
  </div>
  <div class="col-sm-4">
    <div class="card">
      <div class="card-body shadow" style="padding:0px;">
        <div class="card-img-top"><img style="max-width: 100%;" alt="Yearly Sales Graph" src="data:image/png;base64,${yearlySalesGraph}"/></div>
      </div>
    </div>
  </div>
  <div class="col-sm-4">
    <div class="card">
      <div class="card-body shadow" style="padding:0px;">
        <div class="card-img-top"><img style="max-width: 100%;" alt="Monthly Sales Graph" src="data:image/png;base64,${monthlySalesGraph}"/></div>
      </div>
    </div>
  </div>
</div>
</security:authorize>
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