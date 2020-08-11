<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
	src="http://code.jquery.com/jquery-latest.js" ></script>
<script type="text/javascript">
$(function(){
	$("#modify").click(function(){
		location.href="<c:url value='/edit/memberModify?userId=${memberCommand.userId }' />"
	});
	$("#memDel").click(function(){
		if(confirm("정말 진짜로 사실 리얼 탈퇴?")){
			location.href="<c:url value='/edit/memberUserDel' />"
		}
	});
});
</script>
</head>
<body>
이름 : ${memberCommand.userName }<br />
아이디 : ${memberCommand.userId }<br />
이메일  : ${memberCommand.userEmail }<br />
생년월일  : ${memberCommand.userBirth }<br />
성별 : <c:if test="${memberCommand.userGender == 'M' }">남자</c:if>
	  <c:if test="${memberCommand.userGender == 'F' }">여자</c:if> 
<br />
연락처 1 : ${memberCommand.userPh1 }<br />
연락처 2 : ${memberCommand.userPh2 }<br />
등록일 : ${memberCommand.userRegist }<br />
주소  : ${memberCommand.userAddr }<br />
<input type="button" name="modify" id ="modify" value="수   정" >
<input type="button" value="취  소" 
				onclick = "javascript:history.back();" />
<input type="button" value="탈퇴" id ="memDel"/>
</body>
</html>