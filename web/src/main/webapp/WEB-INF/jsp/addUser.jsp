<%@include file="/WEB-INF/jsp/header.jsp" %>
<c:set var="title" value="Add User" />
<h2>Add User</h2>
<c:if test="${not empty user && not empty user.warnings }">
<div style=" color: red;">
	<c:forEach items="${user.warnings}" var="warning" >
		${warning}</br>
	</c:forEach>
</div>
</c:if>
<form:form method="post" commandName="user">
<table>
	<tr>
		<td align="right"><form:label path="fname"><spring:message code="label.firstname"/></form:label><span style=" color: red;">*</span></td>
		<td><form:input path="fname" cssStyle="width:250px;" /></td> 
	</tr>
	<tr>
		<td align="right"><form:label path="lname"><spring:message code="label.lastname"/></form:label><span style=" color: red;">*</span></td>
		<td><form:input path="lname" cssStyle="width:250px;" /></td>
	</tr>
	<tr>
		<td align="right"><form:label path="email"><spring:message code="label.email"/></form:label><span style=" color: red;">*</span></td>
		<td><form:input path="email" cssStyle="width:250px;" /></td>
	</tr>
	<tr>
		<td align="right"><form:label path="password"><spring:message code="label.password"/></form:label><span style=" color: red;">*</span></td>
		<td><form:password path="password" cssStyle="width:250px;" /></td>
	</tr>
	<tr><td><input type="hidden" name="type" value="regular"></td>
		<td>
			<input type="submit" value="<spring:message code="label.save"/>"/>
		</td>
	</tr>
</table>	
</form:form>
<%@include file="/WEB-INF/jsp/footer.jsp" %>