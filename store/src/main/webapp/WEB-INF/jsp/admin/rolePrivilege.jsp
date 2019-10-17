<c:forEach var="childPrivilegeDto" items="${privilegeDto.getChildPrivileges()}">
	<tr>
     <td>
       <c:choose>
         <c:when test="${childPrivilegeDto.isInRole}">
           <input type="hidden" name="privilegeDtos[${loop.index}].isInRole" id="privilegeDtos[${loop.index}].isInRole" value="true"/>
           <input class="checkbox" type="checkbox" name="privilegeDtos[${loop.index}].id" value="${childPrivilegeDto.id}" checked onChange="setPrivileges(this, ${loop.index})" />
         </c:when>
         <c:otherwise>
           <input type="hidden" name="privilegeDtos[${loop.index}].isInRole" id="privilegeDtos[${loop.index}].isInRole" value="false"/>
           <input class="checkbox" type="checkbox" name="privilegeDtos[${loop.index}].id" value="${childPrivilegeDto.id}" onChange="setPrivileges(this, ${loop.index})" />
         </c:otherwise>
       </c:choose>
     </td>
     <td>${childPrivilegeDto.name}</td>
     <td>${childPrivilegeDto.description}</td>
  </tr>
  <jsp:include page="rolePrivilege.jsp">
     <jsp:param name="privilegeDto" value="${childPrivilegeDto}" />
  </jsp:include>
</c:forEach>