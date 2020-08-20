<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
{
	"num": ${ cart.cartNum },
	"qty": ${ cart.qty },
	"sum": "<fmt:formatNumber value="${cart.sumTotalPrice }" type="currency"/>"
}