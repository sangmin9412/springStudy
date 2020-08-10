<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="findPasswordPro" method="poat" name="frm">
	아이디 : <input type="text" name="userId"><br />
	이메일 : <input type="text" name="userEmail"><br />
	<div>${err }</div>
	<input type="submit" value="전송">	
</form>
</body>
</html>