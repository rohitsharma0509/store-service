<%@ attribute name="childs" type="java.util.HashSet" required="true" %>
<%@ attribute name="parentId" type="java.lang.Long" required="true" %>
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
			           <input type="hidden" name="privilegeDtos[${counter.index}].isInRole" id="privilegeDtos[${counter.index}].isInRole" value="true"/>
			           <input class="checkbox child" data-parent="${parentId}" type="checkbox" name="privilegeDtos[${counter.index}].id" id="${child.id}" value="${child.id}" checked onChange="setPrivileges(this, ${counter.index})" />
			         </c:when>
			         <c:otherwise>
			           <input type="hidden" name="privilegeDtos[${counter.index}].isInRole" id="privilegeDtos[${counter.index}].isInRole" value="false"/>
			           <input class="checkbox child" data-parent="${parentId}" type="checkbox" name="privilegeDtos[${counter.index}].id" id="${child.id}" value="${child.id}" onChange="setPrivileges(this, ${counter.index})" />
			         </c:otherwise>
			       </c:choose>
			     </td>
			  </tr>
		  <ct:privileges parentId="${child.id}" childs="${child.getChildPrivileges()}"/>
    </c:forEach>
</c:if>