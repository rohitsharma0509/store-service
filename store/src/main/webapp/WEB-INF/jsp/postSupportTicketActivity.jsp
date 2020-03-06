<%@page import="com.app.ecom.store.constants.RequestUrls"%>
<%@page import="com.app.ecom.store.constants.FieldNames"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$('form[name=activityForm]').submit(function(e) {
  e.preventDefault();
  $.post({
        url : '<%=RequestUrls.SUPPORT_TICKET_ACTIVITY_HISTORY %>',
        data : $("form[name=activityForm]").serialize(),
        success : function(response) {
          window.parent.location.reload();
        }
    });
});
</script>
<div class="row">
  <div class="modal fade" id="modalPostActivity" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header text-center">
          <h4 class="modal-title w-100 font-weight-bold"><spring:message code="Post Message" text="Post Message" /></h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body mx-3">
          <form:form method="POST" modelAttribute="<%=FieldNames.SUPPORT_TICKET_ACTIVITY_HISTORY_DTO %>" name="activityForm" >
          <form:hidden path="<%=FieldNames.ID %>"/>
          <form:hidden path="<%=FieldNames.TICKET_ID %>" />
             <div class="row form-group">
               <div class="col-sm-12">
                  <spring:message code="Message" text="Message" var="label"/>
                  <form:textarea path="<%=FieldNames.MESSAGE %>" id="<%=FieldNames.MESSAGE %>" placeholder="${label}" rows="5" cols="60" class="form-control" required='required' autocomplete="off" />
               </div>
             </div>
             <div class="row form-group">
               <div class="col-sm-12">
                 <button type="submit" class="btn btn-info"><spring:message code="Post Message" text="Post Message" /></button>
               </div>
             </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>