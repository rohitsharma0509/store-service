<!DOCTYPE HTML>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body style="background-color: #F2F3F8;">
  <tiles:insertAttribute name="header" />
  <div class="container">
    <tiles:insertAttribute name="body" />
  </div>
</body>
</html>