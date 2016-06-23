<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 컴텍스트 루트 경로 -->
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/table.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../header/menu.jsp"%>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		pro_list();
		$("#s_call").click(function() {
			pro_search();
		});

	});// ${path}/project.do?code=search_plist?code=search_plist&s_type=''&s_query=''"
	function pro_search(){
		var param = "code=search_plist&s_type=" + $("#s_type").val()+"&s_query="+$("#searchQuery").val();
		$.ajax({
			type : "GET",
			url : "${path}/project.do?code=search_plist",
			data : param,
			success : function(msg) {
				$("#pro_list").html(msg);
				$("#searchQuery").val(""); // 텍스트입력상자를 클리어시킴
			}
		});
	}
	function pro_list() {

		if('<%=request.getParameter("pm")%>'!='null'){
			$.ajax({
				type : "GET",
				url : "${path}/project.do?code=listID&s_query="+"<%=request.getParameter("pm")%>",
				success : function(msg) {
					$("#pro_list").html(msg);
				}
			});	
		}
		else {
			$.ajax({
				type : "GET",
				url : "${path}/project.do?code=list",
				success : function(msg) {
					$("#pro_list").html(msg);
				}
			});
			}
	}

	
</script>
<title>:: 프로젝트 - PlanBinder ::</title>
</head>
<body>
	
<div id="content">
<h2>프로젝트</h2>

		
	<form method="GET" action="javascript:pro_search();" style="margin-left: 40px;margin-top: 50px;">
		<div class="pro_search">
			<select id=s_type>
				<option value="유형">유형</option>
				<option value="우선순위">우선순위</option>
				<option value="제목">제목</option>
				<option value="담당자">담당자</option>
				<option value="번호">프로젝트번호</option>
			</select> <input type="text" id="searchQuery"/>
			&nbsp;&nbsp;<a class="button" href="#"	id="s_call">검색</a>
		</div>
	</form>
	<br>
	
	<div id="pro_list"></div>
	
	</div>
	
</body>
</html>