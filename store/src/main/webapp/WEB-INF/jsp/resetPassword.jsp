<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
  <form method="POST" action="${contextPath}<%=RequestUrls.CHANGE_PASSWORD %>" class="form-signin">
    <h2 class="form-signin-heading">Change Your Password</h2>
    <div class="form-group">
      <input type="password" name="password" class="form-control" placeholder="Password" required />
    </div>
    <div class="form-group">
      <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm Password" required />
    </div>
    <button class="btn btn-lg btn-primary" type="button" onclick="window.history.back()">Back</button>
    <button class="btn btn-lg btn-primary" type="submit">Change Password</button>
  </form>
</div>