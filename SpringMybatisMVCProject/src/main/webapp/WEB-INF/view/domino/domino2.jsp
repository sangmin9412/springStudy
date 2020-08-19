<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<li id="bsel-li">두번째 domino<br>
	<select name="bNum" id="bsel">
		<option hidden>선택하세요</option>
		<c:forEach items="${ blists }" var="dto">
			<option value="${ dto.b1 }">${ dto.b2 }</option>
		</c:forEach>
	</select>
</li>