<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<li id="csel-li">세번째 domino<br>
	<select name="cNum" id="csel">
		<option hidden>선택하세요</option>
		<c:forEach items="${ clists }" var="dto">
			<option value="${ dto.c1 }">${ dto.c2 }</option>
		</c:forEach>
	</select>
</li>