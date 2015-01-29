<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@include file="../includes/taglib.jsp"%>
<%@ page import="com.google.appengine.api.users.UserService, com.google.appengine.api.users.UserServiceFactory,com.google.appengine.api.users.User"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${param.title}</title>
		<%@include file="general-link.jsp"%>
	    <script src="../js/microBiz/${param.module}.js"></script>
	</head>
	<body>
		<div id="wrapper">
			<%@include file="nav.jsp"%>
		    <div id="page-wrapper">
				<div id="bodyContentDIV" class="container-fluid">
					<jsp:include page="../${param.module}/${param.body}" flush="true"/>
				</div>
			</div>
		</div>
		<div>
		</div>
	</body>
</html>	
