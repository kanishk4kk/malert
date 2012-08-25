<%@include file="/WEB-INF/jsp/header.jsp" %>
<c:set var="title" value="Dashboard" />
<style type="text/css" title="currentStyle">
			@import "/malert/css/demo_page.css";
			@import "/malert/css/demo_table.css";
			@import "/malert/css/jquery.dataTables.css";
			@import "/malert/css/jquery.dataTables_themeroller.css";
			@import "/malert/css/demo_table_jui.css";
		</style>
		<script type="text/javascript" language="javascript" src="/malert/js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="/malert/js/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf-8">
		        jQuery.fn.dataTableExt.oSort['num-asc']  = function(a,b) {
		            var x = a.replace( /<.*?>/g, "" );
		            var y = b.replace( /<.*?>/g, "" );
		            x = parseFloat( x );
		            y = parseFloat( y );
		            return ((x < y) ? -1 : ((x > y) ?  1 : 0));
		        };
		
		        jQuery.fn.dataTableExt.oSort['num-desc'] = function(a,b) {
		            var x = a.replace( /<.*?>/g, "" );
		            var y = b.replace( /<.*?>/g, "" );
		            x = parseFloat( x );
		            y = parseFloat( y );
		            return ((x < y) ?  1 : ((x > y) ? -1 : 0));
		        };
		
		        $(document).ready(function() {
		            $('#workshopVisitObservations').dataTable({
		            	oLanguage: {sProcessing: ""},
		                bAutoWidth: false,
		                bJQueryUI : false,
		                bProcessing: false,
		                sPaginationType: "full_numbers",
		                bStateSave : false,
		                aLengthMenu: [10, 15, 25, 50, 100, 500],
						iDisplayLength: 10,
						sDom: '<"H" plr> t <"F" i>',
						aaSorting: []
		            });
		
		            $('#workshopVisitObservations_wrapper').show();
		        } );
	    </script>
<c:set var="rType"/>
<c:choose>
	<c:when test="${status == 'pending' }">
		<c:set var="rType" value="pending"/>
	</c:when>
	<c:when test="${status == 'implemented' }">
		<c:set var="rType" value="implemented"/>
	</c:when>
</c:choose>
<div>
	<strong>${workshop.name},${workshop.location}&nbsp;(${workshop.code})</strong> ${rType} - Visit Observations
	<c:if test="${not empty visitDate}">
		, visited on <strong><fmt:formatDate value="${visitDate}" pattern="dd-MMM-yyyy" /></strong>
	</c:if>
</div>	    
<div id="container">
	<div id="dynamic">
		<table id="workshopVisitObservations" class="display">
        <thead>
        <tr>
          <th>Observation</th>
          <th>Action Planed</th>
          <th>Target Date</th>
          <c:if test="${status == 'pending' }">
          <th>Action</th>
          </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${WorkshopVisitObservations}" var="workshopVisitObservation">
          <tr>
            <td width="40%" valign="top" style="white-space:nowrap !important;">${workshopVisitObservation.observation}</td>
            <td width="35%" valign="top" style="white-space:nowrap !important;">${workshopVisitObservation.actionPlan}</td>
            <td width="10%" valign="top" style="white-space:nowrap !important;"><fmt:formatDate value="${workshopVisitObservation.targetDate}" pattern="dd-MMM-yyyy" /></td>
            <c:if test="${status == 'pending' }">
            <td width="15%" valign="top" style="white-space:nowrap !important;">
            	<c:if test="${status == 'pending' }">
            		<a href="markAsImplemented.html?voId=${workshopVisitObservation.id}&workshopId=${workshop.id}">Mark as implemented</a>
            	</c:if>
            </td>
            </c:if>
          </tr>
        </c:forEach>
        </tbody>
      </table>
	</div>
</div>
<div style="width:98%; padding-left: 15px;">
	<a href="JavaScript:window.print();">Print this page</a>
</div>
<%@include file="/WEB-INF/jsp/footer.jsp" %>