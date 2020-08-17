<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="libModifyPro" method="post" name="modifyform" commandName="libraryCommand" enctype="multipart/form-data" >
<form:hidden path="boardNum" />
<form:hidden path="userId" />
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			글쓴이 : ${ libraryCommand.boardName }&nbsp;&nbsp;&nbsp;&nbsp;
			접속 : ${ libraryCommand.ipAddr }
		</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">제 목</div>
		</td>
		<td>
			<form:input path="boardSubject" size="50" maxlength="100" />
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<form:textarea path="boardContent" cols="67" rows="15"></form:textarea>
		</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<form:password path="boardPass" />
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
				<input type="submit" value="수정" />&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()" />
			</font>
		</td>
	</tr>
</table>
</form:form>
</body>
</html>