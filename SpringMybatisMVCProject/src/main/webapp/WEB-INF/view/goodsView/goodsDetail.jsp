<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script src="/js/jquery.form.js"></script>
<style>
	.thumImg {
		display:none;
		cursor: pointer;
	}
	.thumImg.active {
		display:block;
	}
</style>
<script>
	function imgChange(num) {
		var img = document.querySelectorAll('.thumImg');
		var leng = img.length - 1;
		
		img[num].classList.remove("active");
		if (num === leng) {
			num = -1;
		}
		img[num + 1].classList.add("active");
	}
	
	function goodsCardAdd(goodsNum) {
		$.ajax({
			type: "post",
			url: "/cart/goodsCartAdd",
			dataType: "text",
			data: { goodsNum: goodsNum },
			success: function(res) {
				var res = res.trim();
				console.log(res);
				if (res.indexOf("1") > -1) {
					if (confirm("장바구니로 이동 하시겠습니까?")) {
						location.href = "/cart/goodsCartList";
					}
				}
			},
			error: function(e) {
				console.log(e);
			}
		});
	}
	
	function goodsWishAdd(goodsNum) {
		$.ajax({
			type: "post",
			url: "/cart/goodsWishAdd",
			dataType: "text",
			data: { goodsNum: goodsNum },
			success: function(res) {
				console.log(res);
				if (res.indexOf("1") > -1) {
					alert("관심 상품에 등록 되었습니다.");
				} else {
					alert("관심 상품에서 삭제 되었습니다.");
				}
			},
			error: function(e) {
				console.log(e);
			}
		});
	}
	
</script>
</head>
<body>
${ test }
<table align="center" width="600" border="1">
	<tr bgcolor="orange">
		<td align="right">조회수 : ${goods.readCount }
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="goodsWishAdd('${goods.goodsNum }')">관심상품</a>
		<a href="javascript:void(0);" onclick="goodsCardAdd('${goods.goodsNum }')">장바구니 담기</a>
	</tr>
</table>

<table align="center" width="600">
	<tr>
		<td rowspan="4">
		<c:forTokens items="${ goods.goodsImage}" delims="`" var="src" varStatus="cnt">
			<c:if test="${ cnt.index == 0 }">
				<img src="/goodsView/upload/${ src }" width="50" class="thumImg active" onclick="imgChange(${cnt.index})" />
			</c:if>
			<c:if test="${ cnt.index != 0 }">
				<img src="/goodsView/upload/${ src }" width="50" class="thumImg" onclick="imgChange(${cnt.index})" />
			</c:if>
		</c:forTokens>
		</td>
		<td>상품명 : ${ goods.goodsName } </td>
	</tr>
	<tr>
	 <td>가격: <fmt:formatNumber value="${goods.goodsPrice }" type="currency" /></td>
	</tr>
	<tr>
	 <td>상품 설명: ${fn:replace(goods.goodsContent, cn, br)}</td>
	</tr>
    <tr><td colspan="2" align="center">
    		<a href="../goodsList">목록보기</a> |
    		<a href="/cart/goodsDelete?goodsNum=${ goods.goodsNum }">
    		상품 삭제</a> |
    		<a href="goodsModify?goodsNum=${goods.goodsNum }">상품 수정</a>
    	</td>
    </tr>
</table>
</body>
</html>