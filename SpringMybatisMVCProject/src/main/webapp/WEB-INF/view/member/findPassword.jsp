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
	���̵� : <input type="text" name="userId"><br />
	�̸��� : <input type="text" name="userEmail"><br />
	<div>${err }</div>
	<input type="submit" value="����">	
</form>
</body>
</html>