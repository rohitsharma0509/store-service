<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<style>
  .login-page-font {
    font-size: 12px;
  }
  .outer-line {
    width: 38%;
    border-bottom: 1px solid red;
    display: inline-block;
    vertical-align: middle;
  }
  .user-circle {
    font-size: 50px;
    vertical-align: middle;
    display: inline-block;
  }
  .form-check {
    float: left;
  }
  .login-top {
    margin: 25px;
  }
  .rememberMe {
    height: 12px;
  }
  .btn-signin {
    margin-bottom: 20px;
  }
</style>
<div class="container">
 <div class="row">
  <div class="col-sm-4"></div>
  <div class="col-sm-4">
    <div class="card pmd-card text-center">
      <div class="card-body">
        <div class="login-top">
          <span class="outer-line"></span>
          <i class="fa fa-user-circle user-circle"></i>
          <span class="outer-line"></span>
        </div>
        <p class="card-subtitle mb-3">
          <strong>Sign in</strong>
        </p>
        <c:if test="${not empty message}">
          <div id="logoutMsg" class="alert alert-success hide login-page-font">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
          <div id="loginErrorMsg" class="alert alert-danger hide login-page-font">${error}</div>
        </c:if>
        <form method="POST" action="${contextPath}/login">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="form-group">
            <input type="text" class="form-control login-page-font" id="username" name="username" required placeholder="Username">
            <span class="help-block"></span>
          </div>
          <div class="form-group">
            <input type="password" class="form-control login-page-font" id="password" name="password" required placeholder="Password">
            <span class="help-block"></span>
          </div>
          <div class="form-group form-check">
             <input type="checkbox" class="form-check-input rememberMe" name="rememberMe" id="rememberMe">
             <label class="form-check-label login-page-font" for="rememberMe">Remember login</label>
          </div>
          <button type="submit" class="btn btn-sm btn-info btn-block login-page-font btn-signin">Sign In</button>
          <div class="text-left">
            <a href="${contextPath}<%=RequestUrls.FORGET_PASSWORD %>" class="login-page-font">Need help signing in?</a>
          </div>
          <hr>
          <div class="login-page-font">Don't have an account?<span>&nbsp;&nbsp;<a href="${contextPath}<%=RequestUrls.REGISTRATION %>">Sign up</a></span></div>
        </form>
      </div>
    </div>
  </div>
  <div class="col-sm-4"></div>
 </div> 
</div>
