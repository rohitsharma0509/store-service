<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<style>
body {
    font-family: 'Ropa Sans', sans-serif;
    margin-top: 30px;
    background-color: #331cf34f;
    text-align: center;
    color: #fff;
}
.error-heading {
  padding-left: 15px;
	margin: 50px auto;
	width: 250px;
	border: 5px solid #fff;
	font-size: 126px;
	line-height: 126px;
	border-radius: 30px;
	text-shadow: 6px 6px 5px #000;
}

.error-heading img {
	width: 100%;
}

.error-main h1 {
	text-align: center;
	font-size: 72px;
	margin: 0px;
	color: #F3661C;
	text-shadow: 0px 0px 5px #fff;
}

.error-actions {text-align: center;margin-top:15px;margin-bottom:15px;}
.error-actions .btn { margin-right:10px; }

</style>
<body>
<div class="error-main">
	<h1><spring:message code="Oops" text="Oops" />!</h1>
	<div class="error-heading">${code}</div>
	<p style="text-align: center;">${message}</p>
	<div class="error-actions">
		<a href="${contextPath}/home" class="btn btn-success btn-lg">
			<i class="fa fa-home" aria-hidden="true"></i><spring:message code="Take Me Home" text="Take Me Home" />
		</a>
	</div>
</div>
</body>