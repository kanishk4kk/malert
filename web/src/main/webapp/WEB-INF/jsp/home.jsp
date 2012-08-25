<%-- 
// From http://datatables.net/usage/options#sDom
        /*
                The following options are allowed:

                    'l' - Length changing
                    'f' - Filtering input
                    't' - The table!
                    'i' - Information
                    'p' - Pagination
                    'r' - pRocessing

                The following constants are allowed:

                    'H' - jQueryUI theme "header" classes ('fg-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix')
                    'F' - jQueryUI theme "footer" classes ('fg-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix')

                The following syntax is expected:

                    '<' and '>' - div elements
                    '<"class" and '>' - div with a class
                    '<"#id" and '>' - div with an ID

        */
--%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<c:set var="title" value="List Workshops" />
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
	function renderActionLink(functionName, recordPk, tableCellDisplay){
        if(functionName == null){
            return "";
        } else {
            return "<a href=\"javascript: " + functionName + "('" + recordPk + "')\">" +
                       tableCellDisplay +
                   "</a>";
        }
    }
	function populateDatatable() {
		var columns ='id,name,location,code,tsmName,active,null';
		
       	var oTable = $('#wTbl').dataTable( {
                bAutoWidth: false,
                bJQueryUI : false,
                bProcessing: false,
                bServerSide: false,
                sPaginationType: "full_numbers",
                bStateSave : false,
                aLengthMenu: [10, 15, 25, 50, 100],
				iDisplayLength: 10,
				sDom: '<"H" plr> t <"F" i>',
				aaSorting: [[0,'asc']],
                aoColumns: [
                            { sTitle: "Name"}, // 00
                            { sTitle: "Location"}, // 01
                            { sTitle: "Code"}, // 02
                            { sTitle: "Tsm Name"}, // 03
                            <c:if test="${loggedInUser.type == 'admin'}">
                            	{ sTitle: "Status", "bSortable": false}, // 04
                            </c:if>
                            { sTitle: "Actions", "bSortable": false} // 05
                        ]
       	  } );
       	  $('#wTbl_wrapper').show();
	}
	
	 $(document).ready(function() {
		populateDatatable();
	 });
</script>
<div id="container">
	<div id="dynamic">
		<table id="wTbl" class="display">
	        <thead>
	        <tr>
	          <th>Name</th>
	          <th>Location</th>
	          <th>Code</th>
	          <th>TSM Name</th>
	          <c:if test="${loggedInUser.type == 'admin'}"><th>Status</th></c:if>
	          <th>Actions</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${Workshops}" var="workshop">
	          <tr>
	            <td width="10%" style="white-space:nowrap !important;">${workshop.name}</td>
	            <td width="20%">${workshop.location}</td>
	            <td width="10%">
					<a href='workshopVisitObservations.html?workshopId=${workshop.id}'>${workshop.code}</a>
	            </td>
	            <td width="15%">${workshop.tsmName}</td>
	            <c:if test="${loggedInUser.type == 'admin'}">
		            <td width="10%">
						<c:choose>
							<c:when test="${workshop.active}">
								<img src="/malert/images/active.png" alt="Active" />
							</c:when>
							<c:otherwise>
								<img src="/malert/images/deactive.png" alt="Deactivate" />
							</c:otherwise>
						</c:choose>
		            </td>
		        </c:if>
	            <td width="35%" style="white-space:nowrap !important;">
					<a href='workshopVisitObservationsByStatus.html?workshopId=${workshop.id}&status=pending'>Pending</a>&nbsp;&nbsp;
					<a href='workshopVisitObservationsByStatus.html?workshopId=${workshop.id}&status=implemented'>Implemented</a>&nbsp;&nbsp;
					<c:if test="${workshop.active}">
						<a href='addVisitObservation.html?workshopId=${workshop.id}' >New VisitObservation</a>&nbsp;&nbsp;
					</c:if>
					<c:if test="${loggedInUser.type == 'admin'}">
						<c:choose>
							<c:when test="${workshop.active}">
								<a href='editWorkshop.html?id=${workshop.id}'>Edit</a>&nbsp;&nbsp;
								<a href='deactivateWorkshop.html?id=${workshop.id}'>Deactivate</a>
							</c:when>
							<c:otherwise>
								<a href='activateWorkshop.html?id=${workshop.id}'>Activate</a>
							</c:otherwise>
						</c:choose> 
					</c:if>
	            </td>
	          </tr>
	        </c:forEach>
	        </tbody>
		</table>
	</div>
</div>
<c:if test="${loggedInUser.type == 'admin'}">
	<div class="anchor_btn" style="padding-left: 11px; width:100px;"><a href="addWorkshop.html" style="color: #333333 !important;"><spring:message code="label.add.workshop"/></a></div>
</c:if>
<%@include file="/WEB-INF/jsp/footer.jsp" %>