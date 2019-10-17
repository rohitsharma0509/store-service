<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
  <div class="container">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="menu" />
    <tiles:insertAttribute name="body" />
    <!-- <tiles:insertAttribute name="footer" /> -->
  </div>
</body>
</html>