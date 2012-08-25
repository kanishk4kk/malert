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
<div>
	<strong>All pending visit observations after target date.</strong>
</div>	    
<div id="container">
	<div id="dynamic">
		<table id="workshopVisitObservations" class="display">
        <thead>
        <tr>
          <th>Workshop</th>
          <th>Observation</th>
          <th>Action Planed</th>
          <th>Target Date</th>
          <th>TSM Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${WorkshopVisitObservations}" var="workshopVisitObservation">
          <tr>
            <td valign="top" style="white-space:nowrap !important;">${workshopVisitObservation.workshop.name}&nbsp;(<b>${workshopVisitObservation.workshop.code}</b>)</td>
            <td valign="top" width="400px">${workshopVisitObservation.observation}</td>
            <td valign="top" width="400px">${workshopVisitObservation.actionPlan}</td>
            <td valign="top" style="white-space:nowrap !important;"><fmt:formatDate value="${workshopVisitObservation.targetDate}" pattern="dd-MMM-yyyy" /></td>
            <td valign="top" style="white-space:nowrap !important;">${workshopVisitObservation.workshop.tsmName}</td>
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