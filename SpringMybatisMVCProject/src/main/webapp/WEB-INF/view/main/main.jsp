<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
	src="http://code.jquery.com/jquery-latest.js" ></script>
<script>
$(function(){
	$("#imgSubmit").click(function(){
		if($("#userId").val()== ""){
			$("#idmsg").html("아이디를 입력하세요.");
			$("#userId").focus();
			return false;
		}
		if($("#userPw").val()== ""){
			$("#pwmsg").html("비밀번호 입력하세요.");
			$("#userPw").focus();
			return false;
		}
		$("#frm").submit();
	});
});

function ajaxFunc() {
	$.ajax({
		url: "ajaxTest",
		success: function(res, textStatus, request) {
			console.log(1, res);
			console.log(2, textStatus);
			console.log(3, request);
		},
		error: function(e) {
			console.log(e);
		}
	});
}
</script>
</head>
<body>
<c:if test="${empty authInfo }">
<!-- 로그인 안 된 경우 -->
<form:form action="login" name="frm" id="frm" method="post" commandName="loginCommand">
<table border="1">
<tr><td colspan="3">
자동로그인<input type="checkbox" name="autoLogin">
&nbsp;&nbsp;&nbsp;&nbsp;
아이디 저장 <input type="checkbox" name="idStore" 
<c:if test="${!empty isId}"> checked </c:if>  /></td>
</td></tr>
<tr>
<td>아이디</td>
<td><form:input path="userId" id="userId" value="${ isId }" />
	<form:errors path="userId" />
</td>
<td rowspan=2><input type="image" src="images/img1.jpg" width="80" height="80" id="imgSubmit">
</td>
<tr>
<td>비밀번호</td>
<td><form:password path="userPw" id="userPw" />
	<form:errors path="userPw" />
</div>
</td>
</tr>

<tr>
<td colspan="3">
<a href="register/agree">회원가입</a>&nbsp;
<a href="<c:url value='/edit/findUserId' />">아이디 찾기</a>&nbsp;
<a href="<c:url value='/edit/findPassword' />">비밀번호 찾기</a>&nbsp;
</td>
</tr>
</table>
</form:form>
</c:if>
<c:if test="${!empty authInfo }">
<!-- 로그인 된 경우 -->
<a href="mem/memberDetail">내정보</a>
<a href="<c:url value="/login/logout" />">로그아웃</a>
<a href="mem/memberList">회원리스트</a>
<a href="qna/boardList">공지사항 게시판</a>
<a href="lib/libBoard">자료 게시판</a>
<a href="ans/ansBoardList"">답변형 게시판</a>
<a href="gd/goodsList">상품목록</a>
<a href="cb/commentList">댓글 게시판</a>
<a href="survey/survey">설문조사</a>
<a href="survey/surveyForm">설문내용 등록</a>
<a href="ajaxTest">ajaxTest</a>
<a href="ajaxTest1">ajaxTest1</a>
<a href="domino">dominoMain</a>
</c:if>
</body>
</html>