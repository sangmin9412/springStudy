<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요!</h1>
	<p>이름 : ${ name }</p>
	<p>나이 : ${ age }</p>
	<p id="count">0</p>
	<script>
		let countVal = 0;
		let rafId;
		function countHandler() {
			const count = document.querySelector("#count");
			count.innerHTML = countVal += 1;
			
			rafId = requestAnimationFrame(countHandler);
			
			if (countVal >= 100) {
				cancelAnimationFrame(rafId);
			}
		}
		countHandler();
	</script>
</body>
</html>