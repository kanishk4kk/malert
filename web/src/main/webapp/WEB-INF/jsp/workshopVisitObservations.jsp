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
	    
<div><strong>${workshop.name},${workshop.location}&nbsp;(${workshop.code})</strong> visit observations.</div>
	    
<div id="container">
	<div id="dynamic">
		<table id="workshopVisitObservations" class="display">
        <thead>
        <tr>
          <th>Visit Date</th>
          <th>Visited By</th>
          <th>TSM Name</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${WorkshopVisitObservations}" var="workshopVisitObservation">
          <tr>
            <td width="15%">
            	<c:set var="visitDate"><fmt:formatDate value="${workshopVisitObservation.visitDate}" pattern="dd-MMM-yyyy" /></c:set>
            	${visitDate}
            </td>
            <td width="25%">${workshopVisitObservation.visitedBy.fname} ${workshopVisitObservation.visitedBy.lname}</td>
            <td width="25%">${workshopVisitObservation.workshop.tsmName}</td>
            <td width="35%">
            	<a href="workshopVisitObservationsByStatus.html?workshopId=${workshop.id}&visitDate=${visitDate}&status=pending">Pending</a>&nbsp;&nbsp;&nbsp;&nbsp;
            	<a href="workshopVisitObservationsByStatus.html?workshopId=${workshop.id}&visitDate=${visitDate}&status=implemented">Implemented</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
	</div>
</div>
<%@include file="/WEB-INF/jsp/footer.jsp" %>