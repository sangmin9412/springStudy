<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="js/jquery.form.js"></script>
<script>
	$(function() {
		var list = $("#select-list");
		var aNum;
		var bNum;
		
		$(document).on("change", "#asel", function(){
			aNum = $(this).val();
			
			$.ajax({
				type: "post",
				url: "domino2",
				dataType: "html",
				data: { num: aNum },
				success: function(res) {
					if (list.find("#bsel-li")) {
						list.find("#bsel-li").remove();	
					}
					if (list.find("#csel-li")) {
						list.find("#csel-li").remove();	
					}
					list.append(res);
				},
				error: function(e) {
					console.log(e);
				}
			})
		});
		
		$(document).on("change", "#bsel", function(){
			bNum = $(this).val();
			
			$.ajax({
				type: "post",
				url: "domino3",
				dataType: "html",
				data: { aNum: aNum, bNum: bNum },
				success: function(res) {
					if (list.find("#csel-li")) {
						list.find("#csel-li").remove();	
					}
					list.append(res);
				},
				error: function(e) {
					console.log(e);
				}
			})
		})
	});
</script>
</head>
<style>
	ol li:first-child{
		margin-top: 0;
	}
	ol li {
		margin-top: 20px;
		font-size: 16px;
		font-weight: bold;
		color: #2b2b2b;
	}
	select {
		margin-top: 10px;
		height: 25px;
		font-size: 12px;
		border: 1px solid #ccc;
		-webkit-border-radius: 0;
		-moz-border-radius: 0;
		border-radius: 0;
	}
</style>
<body>
	<ol id="select-list">
		<li>첫번째 domino<br><!-- a1. 번호 a2. 지역이름 -->
			<select name="aNum" id="asel">
				<option hidden>선택하세요</option>
				<c:forEach items="${ lists }" var="dto">
					<option value="${ dto.a1 }">${ dto.a2 }</option>
				</c:forEach>
			</select>
		</li>
		<!-- 
		<li>두번째 domino<br>
			<select name="bNum" id="bsel">
				<option hidden>선택하세요</option>
			</select>
		</li>
		<li>세번째 domino<br>
			<select name="cNum" id="csel">
				<option hidden>선택하세요</option>
			</select>
		</li>
		 -->
	</ol>
</body>
</html>