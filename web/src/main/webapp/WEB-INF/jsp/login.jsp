<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

<style type="text/css" title="currentStyle">
	@import "/malert/css/demo_page.css";
	@import "/malert/css/demo_table.css";
	@import "/malert/css/jquery.dataTables.css";
	@import "/malert/css/jquery.dataTables_themeroller.css";
	@import "/malert/css/demo_table_jui.css";
</style>
<script type="text/javascript" language="javascript" src="/malert/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="/malert/js/jquery.dataTables.js"></script>


<style type="text/css">
	#dt_example{background-color: #f7f7f7 !important;}
	#container { width: 98% ! important;}
	#table.display thead th { text-align: left!important;}
	
</style>
</head>
<body id="dt_example" bgcolor="#f7f7f7" >
<table border="0" style="width:100%;">
	<tr>
		<td colspan="3">
			<table border="0" style="width:100%;">
				<tr>
					<td width="25%"><img src="/malert/images/Moslogo.jpg" width="200px"/></td>
					<td width="50%">
						<div style="min-height: 50px;">
							<h1 style="font-size: 25px; border-bottom: 0px solid #B0BED9; clear: both; color: #4E6CA3; line-height: 2; margin-top: 0em; text-align: center;">
								Workshop Management System
							</h1>
						</div>
					</td>
					<td width="25%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="3"><div style="width: 98%; padding-left: 7px; "><hr/></div></td></tr>
	<tr><td colspan="3" align="center" style="padding-top: 100px;">
			<div style="min-height: 400px;">
				<c:set var="title" value="Login" />
				<form  name="loginForm" method="post" >
				<table >
					<tr>
					  	<td colspan="2">
					  		<c:if test="${(! empty param.invalidLogin && param.invalidLogin == 'Y')}">
					  			<font color="red"><fmt:message key="label.invalid.credencials"/></font>
					  		</c:if>
						    
						</td>
					</tr>
					<tr>
						<td>Username</td><td><input type="text" name="j_username"/></td>
					</tr>
					<tr>
						<td>Password</td><td><input type="password" name="j_password"/></td>
					</tr>
					<tr>
						<td></td><td><input type="submit" value="Login"></td>
					</tr>
				</table>
				</form>
			</div>
		</td></tr>
		<tr><td colspan="3"><div style="min-height: 10px;"></div></td></tr>
	</table>
</body>
</html>