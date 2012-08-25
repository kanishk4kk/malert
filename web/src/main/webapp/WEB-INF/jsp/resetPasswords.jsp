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
						aaSorting: [[0,'asc']]
		            });
		
		            $('#workshopVisitObservations_wrapper').show();
		        } );
	    </script>
		<div id="container">
			<div id="dynamic">
				<table id="workshopVisitObservations" class="display">
		        <thead>
		        <tr>
		          <th>Name</th>
		          <th>Email</th>
		          <th>Action</th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:if test="${loggedInUser.type == 'admin'}">
			        <c:forEach items="${Users}" var="user">
			          <tr>
			            <td valign="top" style="white-space:nowrap !important;">${user.fname}&nbsp;${user.lname}</td>
			            <td valign="top" >${user.email}</td>
			            <td valign="top" style="white-space:nowrap !important;"><a href="resetPassword.html?userId=${user.id}">Reset</a></td>
			          </tr>
			        </c:forEach>
		        </c:if>
		        </tbody>
		      </table>
			</div>
		</div>
		<c:if test="${not empty User}">
			<div style="padding-left: 11px; width:100%; color: #4E6CA3;">
				Password successfully reset for User:<b>${User.fname}&nbsp;${User.lname}</b>, now user's password is: <b>${User.password}</b>
			</div>
		</c:if>
		<br>
		<c:if test="${loggedInUser.type == 'admin'}">
			<div class="anchor_btn" style="padding-left: 15px; width:80px;"><a href="addUser.html" style="color: #333333 !important;">New User</a></div>
		</c:if>

<%@include file="/WEB-INF/jsp/footer.jsp" %>