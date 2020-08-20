<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script src="/js/jquery.form.js"></script>
<script type="text/javascript">
function checkQty(num, qty){
	if(qty > 1){
		location.href="goodsCartQtyDown.gd?goodsNum="+num;
	}else{
		return false;
	}
}

function goodsCartAdd(goodsNum) {
	$.ajax({
		type: "post",
		url: "/cart/goodsCartAdd",
		dataType: "text",
		data: { goodsNum: goodsNum },
		success: function(res) {
			if (res.indexOf("1") > -1) {
				location.href = "/cart/goodsCartList";
			}
		},
		error: function(e) {
			console.log(e);
		}
	});
}

function goodsCartQty(cartNum, condition, qty) {
	$.ajax({
		type: "post",
		url: "/cart/goodsCartQty",
		data: { cartNum: cartNum, condition: condition },
		success: function(res) {
			var data = JSON.parse(res);
			console.log(data);
			$("#"+data.num).find(".qty").text(data.qty);
			$("#sumPrice").text(data.sum);
		},
		error: function(e) {
			console.log(e);
		}
	});
}
</script>
</head>
<body>
<form action="goodsCartRemove" method="post" name="frm">
<table align="center" width="600" border="1">
	<tr align="center" bgcolor="orange">
		<td>번호	</td>
		<td>상품이미지</td>
		<td>상품명</td>
		<td>가격</td>
		<td>수량</td>
		<td align="center">
		<input type="submit"  value="삭제" />
		</td>
	</tr>
<c:forEach items="${cartList }" var="dto" varStatus="cnt">
	<tr align="center" bgcolor="orange" id="${dto.cartNum }">
		<td>${cnt.count }</td>
		<td><img src = "../goodsView/upload/${dto.goodsImage }" 
					width="30"/>
		</td>
		<td>${dto.goodsName }</td>
		<td>
			<fmt:formatNumber value="${dto.goodsPrice }" 
										type="currency"/>
		</td>
		<td>
		<!-- <a href="javascript:goodsCartAdd('${dto.goodsNum }')"> + </a> -->
		<a href="javascript:goodsCartQty('${dto.cartNum}', 'Up', '${ dto.qty } }');"> + </a> 
		<span class="qty">${ dto.qty }</span>
		<a href="javascript:goodsCartQty('${dto.cartNum}', 'Down', '${ dto.qty } }');" > - </a></td>
		<td align="center">
		<input type="checkbox" name="delete" value="${dto.cartNum }" />
		</td>
	</tr>	
</c:forEach>
</table>
</form>
<table align="center" width="600" border="0">
	<tr align="center" bgcolor="yellow">
		<td align="right" colspan="6">
		<font color ="gray" size="5">
			총 결제금액 :
			<span id="sumPrice"><fmt:formatNumber value="${cartList[0].sumTotalPrice }" type="currency"/></span>
		</font>
		<font color ="black" size="5">원</font>
		</td>
	</tr>
</table>
<a href="/gd/goodsList" >목록으로 가기</a>
</body>
</html>