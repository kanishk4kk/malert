<%@include file="/WEB-INF/jsp/header.jsp" %>
<c:set var="title" value="Add VisitObservation" />
<style type="text/css">
	body {
		font-family: sans-serif;
	}
	.data, .data td {
		border-collapse: collapse;
		border: 0px solid #aaa;
		margin: 2px;
		padding: 2px;
	}
	.data th {
		font-weight: bold;
		background-color: #4E6CA3;
		color: white;
	}
	.error {
		color: red !important;;
	}
</style>
<span style="color: #4E6CA3; font-weight: bold; font-size: 15px;"><spring:message code="label.add.observation" text="Add Observation"/></span>
<br><br>
<form:form commandName="visitObservationsVO" method="post" action="addVisitObservation.html">
	<div>
		<span class="title"><spring:message code="label.workshop"/>  : </span>${workshop.name},${workshop.location}&nbsp;(<b>${workshop.code}</b>)&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="title"><spring:message code="label.visitdate"/> : </span><form:input path="visitDate" />&nbsp;&nbsp;(dd-mm-yyyy)
		<form:errors path="visitDate" cssClass="error"/>
		<input type="hidden" name="workshopId" value="${workshop.id}">
	</div>
	<br>
	<table border="0" style="width: 98%" class="data">
		<thead>
	        <tr>
	          <th width="40%"><spring:message code="label.observation"/></th>
	          <th width="40%"><spring:message code="label.action.planned"/></th>
	          <th width="20%"><spring:message code="label.targetdate"/></th>
	        </tr>
		</thead>
	    <tbody>
	    	<c:forEach items="${visitObservationsVO.visitObservations}" var="visitObservation" varStatus="count"> 
				<tr>
					<td valign="top" width="40%">
						<form:textarea path="visitObservations[${count.index}].observation" cssStyle="width:95%;" />
						<form:errors path="visitObservations[${count.index}].observation" cssClass="error"/>
					</td>
					<td valign="top" width="40%">
						<form:textarea path="visitObservations[${count.index}].actionPlan" cssStyle="width:95%;"/>
						<form:errors path="visitObservations[${count.index}].actionPlan" cssClass="error"/>
					</td>
					<td valign="top" width="20%">
						<form:input path="visitObservations[${count.index}].targetDate" cssStyle="width:70%;"/>&nbsp;&nbsp;(dd-mm-yyyy)
						<form:errors path="visitObservations[${count.index}].targetDate" cssClass="error"/>
						<input type="hidden" name="visitObservations[${count.index}].status" value="pending"/>
					</td>
				</tr>
			</c:forEach>
	    </tbody>
		<tfoot>	        
			<tr><td><input type="submit" value="Add More" name="act" style="width: 120px;"/></td>
				<td colspan="3">
					<input type="submit" value="<spring:message code="label.save"/>" name="act" style="width: 100px;"/>
				</td>
			</tr>
		</tfoot>
	</table>	
</form:form>
<%@include file="/WEB-INF/jsp/footer.jsp" %>