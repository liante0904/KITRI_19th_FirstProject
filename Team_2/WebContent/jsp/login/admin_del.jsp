<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./../../header/menu.jsp"%>
<script type="text/javascript">
	function clk(){
		location.href="adminCont.do";
	}
</script>
<title>:: 구성원 승인완료 - PlanBinder ::</title>
</head>
<body>
	<h3>변경완료</h3>
	<input type="button" value="메인으로 이동" onclick="clk()">
</body>
</html>