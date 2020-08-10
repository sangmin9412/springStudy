<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<style>
	div#memberTab {width:610px;margin:0 auto;}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.8.1.min.js"></script>
<script>
	$(function(){
		/*
		$("#frm").submit(function(){
			if($("#userId").val() == "" || $("#userId").val() == null ){
				alert("아이디를 입력해 주세요");
				$("#userId").focus();
				return false;
			}
			if($("#userPw").val() == "" || $("#userPw").val() == null ){
				alert("비밀번호를 입력해 주세요");
				$("#userPw").focus();
				return false;
			}
			if($("#userPw").val() != $("#userPwCon").val() ){
				alert("비밀번호가 일치하지 않습니다.");
				$("#userPw").val("");
				$("#userPwCon").val("");
				$("#userPw").focus();
				return false;
			}
			if($("#userName").val() == "" || $("#userName").val() == null ){
				alert("이름을 입력해 주세요");
				$("#userName").focus();
				return false;
			}
			if($("#userBirth").val() == "" || $("#userBirth").val() == null ){
				alert("생년월일을 입력해 주세요");
				$("#userBirth").focus();
				return false;
			}
			if(!$("input:radio[name='userGender']").is(":checked")){
				alert("사용자 성별을 선택하세요");
				$("input:radio[name='userGender']:radio[value='M']").attr("checked",true);
				return false;
			}
			if($("#userEmail").val() == "" || $("#userEmail").val() == null ){
				alert("이메일을 입력해 주세요");
				$("#userEmail").focus();
				return false;
			}
			if($("#userAddr").val() == "" || $("#userAddr").val() == null ){
				alert("주소를 입력해 주세요");
				$("#userAddr").focus();
				return false;
			}
			if($("#userPh1").val() == "" || $("#userPh1").val() == null ){
				alert("연락처를 입력해 주세요");
				$("#userPh1").focus();
				return false;
			}
			if($("#userIdChk").val() == 1){
				alert("중복체크를 해주세요");
				return false;
			}			
		});
		

		$("#idChk").click(function(){
			if($("#userId").val() == ""){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			open("userConfirm.mem?userId="+$("#userId").val(),"아이디 확인",
			"width=300,height=200,lef=200,top=300");
		});
		*/
	});
</script>
</head>
<body>
<form:form action='memberJoin' method='post' name='frm' id="frm" commandName="memberCommand">
<div id='memberTab'>
<fieldset>
	<legend>회원가입 정보</legend>
<table width = '600' align = 'center' border = '1' >
	<caption><strong>회원 가입</strong></caption>
	<colgroup>
	    <col width="25%" />
		<col width="75%"  />
    </colgroup>
	<tfoot>
	<tr>
		<th colspan = 2>
			<input type='submit' value='가입완료' />&nbsp;&nbsp;
			<input type='reset' value='다시 입력' />&nbsp;&nbsp;
			<input type='button' value='가입 안함' /></th>
	</tr>
	</tfoot>
	<tbody>
	<tr>
		<th colspan= '2'>사용자 ID와 비밀번호 입력</th>
	</tr>
	<tr>
		<th ><label for='userId'>사용자 ID</label></th><!-- id:#userId1, .userId2 -->
		<td ><form:input path="userId" id='userId' class='userId' size='15' maxlength='10' autofocus="autofocus" />
			<form:errors path="userId" />
		</td>
	</tr>
	<tr>
		<th ><label for='userPw'>비밀번호</label></th>
		<td ><form:password path="userPw" id='userPw' size='15' maxlength='12'/>
			<form:errors path="userPw" />
		</td>
	</tr>
	<tr>
		<th><label for='userPwCon'>비밀번호확인</label></th>
		<td><form:password path="userPwCon" id='userPwCon' size='15' maxlength='12'/>
			<form:errors path="userPwCon" />
		</td>
	</tr>
	<tr>
		<th colspan= '2'>사용자 기본 정보</th>
	</tr>
	<tr>
		<th><label for='userName'>사용자 이름</label></th>
		<td><form:input path="userName" id='userName' size='15' maxlength='12'/><form:errors path="userName" /></td>
	</tr>
 	<tr>
		<th><label for='userBirth'>생년월일</label></th>
		<td>
			<input type='date' name='userBirth' id='userBirth' placeholder='1999-12-12' value="<fmt:formatDate value="${ memberCommand.userBirth }" pattern="yyyy-MM-dd" />">
			<form:errors path="userBirth" />
		</td>
	</tr>
 	<tr>
		<th>성별</th>	
		<td><input type='radio' name='userGender' id='userGender' value='M' checked /> 남자 &nbsp;&nbsp;&nbsp;&nbsp; 
		<input type='radio' name='userGender' id='userGender' value='F' />여자</td>
	</tr>
	<tr>
		<th colspan= '2'>사용자 연락처</th>
	</tr>
 	<tr>
		<th><label for='userEmail'>사용자 이메일</label></th>
		<td>
			<input type='email' name='userEmail' id='userEmail' value="${ memberCommand.userEmail }" />
			<form:errors path="userEmail" />
		</td>
	</tr>
	<tr>
		<th><label for='userAddr'>사용자 주소</label></th>
		<td><form:input path="userAddr" id ='userAddr' /><form:errors path="userAddr" /></td>
	</tr>
	<tr>
		<th ><label for='userPh1'>연락처 1</label></th>
		<td><form:input path="userPh1" id ='userPh1' placeholder='123-123-1234 ,123-1234-1234' size= '26' maxlength='13' />
			<form:errors path="userPh1" /></td>
	</tr>
	<tr>
		<th><label for='userPh2'>연락처 2</label></th>
		<td><form:input path="userPh2" id ='userPh2' placeholder='123-123-1234 ,123-1234-1234' size= '26' maxlength='13' /></td>
	</tr>
	<tr>
		<th>관심 분야</th>
		<td>
			<input type="checkbox" value="HTML" name="interest" />HTML
			<input type="checkbox" value="CSS" name="interest" />CSS
			<input type="checkbox" value="Javascript" name="interest" />Javascript
		</td>
	</tr>
	</tbody>
</table>
<fieldset>
</div>
</form:form>
</body>
</html>
