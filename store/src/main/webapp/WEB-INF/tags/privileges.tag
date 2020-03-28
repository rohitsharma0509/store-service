<%@ attribute name="childs" type="java.util.HashSet" required="true" %>
<%@ attribute name="parentId" type="java.lang.Long" required="true" %>
<%@ attribute name="noOfParentPriv" type="java.lang.Long" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ct" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty childs}">
    <c:forEach var="child" items="${childs}" varStatus="counter">
        <tr>
           <td></td>
           <td>
            <table class="inner-table">
		          <tbody>
		            <tr><td>${child.name}</td></tr>
		          </tbody>
		        </table>
           </td>
           <td>
            <table class="inner-table">
              <tbody>
                <tr><td>${child.description}</td></tr>
              </tbody>
            </table>
           </td>
			     <td>
			       <c:choose>
			         <c:when test="${child.isInRole}">
			           <input type="hidden" name="privilegeDtos[${counter.index+noOfParentPriv+1}].isInRole" id="privilegeDtos[${counter.index+noOfParentPriv+1}].isInRole" value="true"/>
			           <input class="checkbox child" data-parent="${parentId}" type="checkbox" name="privilegeDtos[${counter.index+noOfParentPriv+1}].id" id="${child.id}" value="${child.id}" checked onChange="setPrivileges(this, ${counter.index+noOfParentPriv+1})" />
			         </c:when>
			         <c:otherwise>
			           <input type="hidden" name="privilegeDtos[${counter.index+noOfParentPriv+1}].isInRole" id="privilegeDtos[${counter.index+noOfParentPriv+1}].isInRole" value="false"/>
			           <input class="checkbox child" data-parent="${parentId}" type="checkbox" name="privilegeDtos[${counter.index+noOfParentPriv+1}].id" id="${child.id}" value="${child.id}" onChange="setPrivileges(this, ${counter.index+noOfParentPriv+1})" />
			         </c:otherwise>
			       </c:choose>
			     </td>
			  </tr>
		  <ct:privileges parentId="${child.id}" noOfParentPriv="${counter.index+noOfParentPriv+1}" childs="${child.getChildPrivileges()}"/>
    </c:forEach>
</c:if>