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
			function populateDatatable() {
				var columns ='id,visitDate,observation,actionPlan,targetDate,updateDate,status';
				
	        	var oTable = $('#voTbl').dataTable( {
		                oLanguage: {sProcessing: ""},
		                bAutoWidth: false,
		                bJQueryUI : false,
		                bProcessing: false,
		                sPaginationType: "full_numbers",
		                bStateSave : false,
		                aLengthMenu: [10, 15, 25, 50, 100, 500],
						iDisplayLength: 10,
						sDom: '<"H" plr> t <"F" i>',
						aaSorting: [[0,'asc']],
		                aoColumns: [
		                            { sTitle: "Id", sName: "id", "bVisible": false},
		                            { sTitle: "WS Name", sName: "workshop.name"},
		                            { sTitle: "Location", sName: "workshop.location"},
		                            { sTitle: "TSM Name", sName: "workshop.tsmName"},
		                            { sTitle: "Observation", sName: "observation"},
		                            { sTitle: "Action Plan", sName: "actionPlan"},
		                            { sTitle: "Target Date", sName: "targetDate"},
		                            { sTitle: "Visit Date", sName: "visitDate"},
		                            { sTitle: "Update Date", sName: "updateDate", "bVisible": false},
		                            { sTitle: "Status", sName: "status"},
		                            { sTitle: "Actions", sName: "null", "bSortable": false}
		                        ],
	                "bServerSide": false,
	        	    "sAjaxSource": "<c:url value='/home/getDataTable.ws'/>",
	        	    "fnServerData": function ( sSource, aoData, fnCallback ) {
	        	      $.ajax( {
	        	        "dataType": 'json', 
	        	        "type": "POST", 
	        	        "url": sSource, 
	        	        "data": { "columns":columns }, 
	        	        "success": fnCallback
	        	      } );
	        	    },
	        	    "fnDrawCallback": function() {
	        	      }
	        	  } );
	        	  $('#voTbl_wrapper').show();
			}
			
			 $(document).ready(function() {
				populateDatatable();
			 });
		</script>
<div id="container">
	<div id="dynamic">
		<table id="voTbl" class="display"></table>
	</div>
</div>
<div style="padding-left: 16px; ">
	<a href="addVisitObservation.html" >Add New VisitObservation</a>
</div>
<%@include file="/WEB-INF/jsp/footer.jsp" %>