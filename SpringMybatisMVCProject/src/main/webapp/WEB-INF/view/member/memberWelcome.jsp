<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register" /></title>
</head>
<body>
	<h1>
		<spring:message code="register.done" arguments="${ memberCommand.userName }, ${ memberCommand.userEmail }" />
	</h1>
	<p><a href='<c:url value="/" />'><spring:message code="go.main" /></a></p>
</body>
</html>