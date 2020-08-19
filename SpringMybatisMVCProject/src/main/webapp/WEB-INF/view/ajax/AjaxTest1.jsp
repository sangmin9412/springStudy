<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest1.jsp</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="js/jquery.form.js"></script>
<script>
	function testDiv(num) {
		// location.href = "ajaxTest2?num="+num;
		$.ajax({
			type: "post",
			url: "ajaxTest2",
			dataType: "html",
			data: { num: num },
			success: function(res) {
				$("#result-div").empty();
				$("#result-div").append(res);
			},
			error: function(e) {
				console.log(e);
			}
		});
	}
	
	function testDiv1(num) {
		var options = {
			type: "post",
			url: "ajaxTest2",
			dataType: "html",
			data: { num: num },
			success: function(res) {
				$("#result-div").empty();
				$("#result-div").append(res);
			},
			error: function(e) {
				console.log(e);
			}
		}
		$.ajax(options);
	}
	
	$(function(){
		$(".submit").click(function(e){
			e.preventDefault();
			var form = $("#frm");
			
			var data = new FormData(form[0]);
			console.log(data);
			console.log(data.get("num"));
			 
			// form.submit();
			form.ajaxSubmit({
				url: "ajaxTest2",
				dataType: "html",
				beforeSubmit: function() {
					if ($("[name='num']").val() === "") {
						alert("입력하세요.")
						$("[name='num']").focus();
						return false;
					}
				},
				success: function(res) {
					$("#result-div").empty();
					$("#result-div").append(res);
				},
				error: function(e) {
					console.log(e);
				}
			});
		});
		
		$("#btn4").click(function(){
			$("#frm").ajaxSubmit({
				type: "post",
				url: "ajaxTest2",
				dataType: "html",
				beforeSubmit: beforeTest, // okTest()함수
				success: okTest,
				error: function(e) {
					console.log(e);
				}
			});
		});
		
		$("#btn5").click(function(){
			var options = {
				type: "post",
				url: "ajaxTest2",
				dataType: "html",
				beforeSubmit: beforeTest, // okTest()함수
				success: okTest,
				error: function(e) {
					console.log(e);
				}
			}
			$("#frm").ajaxSubmit(options);
		});
	});
	
	function beforeTest() {
		console.log("beforeTest");
		if ($("[name='num']").val() === "") {
			alert("입력하세요.")
			$("[name='num']").focus();
			return false;
		}
	}
	
	function okTest(res, status, xhr, form) {
		console.log("response", res);
		console.log("status", status);
		console.log("xhr", xhr);
		console.log("form", form);
	}
</script>
</head>
<body>
	<form action="ajaxTest2" id="frm" method="post">
		<input type="text" name="num" />
		<button class="submit">폼 버튼1</button>
	</form>
	<hr>
	<div id="button_content">
		<button onclick="javascript:testDiv(1)">버튼1</button>
		<button onclick="javascript:testDiv(2)">버튼2</button>
		<button onclick="javascript:testDiv(3)">버튼3</button>
		<button onclick="javascript:testDiv1(1)">버튼4</button>
		<button id="btn4">폼 버튼2</button>
		<button id="btn5">폼 버튼3</button>
	</div>
	<div id="result-div"></div>
</body>
</html>