<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./../../header/menu.jsp"%>
<script type="text/javascript">
	function write_board() {
		//글을 작성
		location.href="Board.do?p_code=write";
	}
</script>
<link rel="stylesheet" type="text/css" href="./css/table.css" />
<meta charset="UTF-8">
<title>:: 게시판 - PlanBinder ::</title>
</head>
<body>

<div id="content">
<h2>게시판</h2>
<Br>

<table class="type09" >
<thead>
<tr><th style="width:80px">번호</th><th style="width: 700px">제목</th><th id="id" style="width:80px">작성자</th><th style="width:130px;text-align: center;">작성일자</th><th id="id" style="width:50px ;text-align: center;">조회수</th></tr>
</thead>
<c:choose>
<c:when test="${count<1}">
<tbody>
<tr>
<td colspan="5">데이터가 없습니다.</td>
</tr>
</tbody>
</c:when>
<c:otherwise>
<tbody id="main-a2">

<c:forEach var="list" items="${list}">
<tr>
<td>${list.q_board_id}</td><td><a href="Board.do?p_code=contents&q_board_id=${list.q_board_id}">${list.q_title}</a>&nbsp;&nbsp;<c:if test="${list.q_secret=='Y'}"> <img src="./images/q_secret.png" style="width: 15px;height: 20px; vertical-align:middle;"></c:if></td><td>${list.u_id}</td><td style="text-align: center;">${list.q_wdate}</td><td style="text-align: center;">${list.q_read_cnt}</td>
</tr>
</c:forEach>

</tbody>
</c:otherwise>
</c:choose>
<tfoot>
<tr>
<td colspan="5" class="page" id="main-a2">

<c:choose>
<c:when test="${count>=11}">
<c:forEach var="i" begin="0" end="${(count-1)/10}" step="1">
<a href="Board.do?p_code=list&page_cnt=${i+1}">${i+1}</a>&nbsp&nbsp&nbsp
</c:forEach>
</c:when>
<c:when test="${count<=10}">
</c:when>
</c:choose>
</td>
</tr>
<tr>
<td  colspan="5">
<p><input type="button" onclick="write_board()" value="글쓰기" class="button"></p>
</td>
</tr>

</tfoot>
</table>

</div>
</body>
</html>