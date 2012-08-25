<%@include file="/WEB-INF/jsp/header.jsp" %>
<c:set var="title" value="Edit Workshop" />
<h2>Edit Workshop</h2>
<c:if test="${not empty workshop && not empty workshop.warnings }">
<div style=" color: red;">
	<c:forEach items="${workshop.warnings}" var="warning" >
		${warning}</br>
	</c:forEach>
</div>
</c:if>
<form:form method="post" commandName="workshop">

	<table>
		<tr>
			<td align="right"><form:label path="name"><spring:message code="label.name"/></form:label><span style=" color: red;">*</span></td>
			<td><form:input path="name" cssStyle="width:250px;"/></td> 
		</tr>
		<tr>
			<td align="right"><form:label path="location"><spring:message code="label.location" text="Location"/><span style=" color: red;">*</span></td></form:label></td>
			<td><form:textarea path="location" cssStyle="width:250px; height:50px;"/></td> 
		</tr>
		<tr>
			<td align="right"><form:label path="code"><spring:message code="label.code"/></form:label><span style=" color: red;">*</span></td>
			<td><form:input path="code" cssStyle="width:250px;" /></td>
		</tr>
		<tr>
			<td align="right">
				<form:label path="tsmName"><spring:message code="label.tsm.name"/></form:label><span style=" color: red;">*</span></td>
			<td><form:input path="tsmName" cssStyle="width:250px;" /></td>
		</tr>
		<tr>
			<td align="right">
				<form:label path="tsmEmail"><spring:message code="label.tsm.email"/></form:label><span style=" color: red;">*</span></td>
			<td><form:input path="tsmEmail" cssStyle="width:250px;" /></td>
		</tr>
		<tr><td></td>
			<td>
				<input type="submit" value="<spring:message code="label.update.workshop" text="Update Workshop"/>"/>
			</td>
		</tr>
	</table>	
</form:form>
<%@include file="/WEB-INF/jsp/footer.jsp" %>