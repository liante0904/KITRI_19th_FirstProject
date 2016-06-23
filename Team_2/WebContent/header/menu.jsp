<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function SessionChk(){
		if(<%= session.getAttribute("u_id")%>!=null){
		}
		else{
			alert("비정상적인 접근입니다[세션오류]");
			location.href="login.do?code=log_out";			
		}
	}
	function modify(){
		 window.open("modify.do?code=modify", "value", "width=450, height=550,scrollbars=1, menubar=1, resizable=1"); 
	}

</script>
<link href="./css/application.css" rel="stylesheet" media="all">
<link href="./css/button.css" rel="stylesheet" media="all">
</head>
<body onload='SessionChk()'>
	<div id="top-menu">
		<div id="account">
			<ul>
				<li><%= session.getAttribute("u_id") %>님이 로그인 하셨습니다.
					&nbsp;&nbsp;&nbsp;
					 <a href="javascript:modify()">정보수정</a>
					 <a href="login.do?code=log_out">로그아웃</a></li>
					
			</ul>
		</div>
	</div>

	<h1>PlanBinder</h1>

	<div id="main-menu">
		<ul>
			<li><a class="monitoring" href="monitorCont.do">모니터링</a></li>
			<li><a class="project" href="project.do?code=project">프로젝트</a></li>
			<li><a class="board" href="Board.do?p_code=list&page_cnt=1">게시판</a></li>
			<li><a class="gantt" href="#">Gantt차트</a></li>
			<c:if test="${u_id eq 'admin'}">
				<li><a class="admin" href="adminCont.do">관리자페이지</a></li>
			</c:if>

		</ul>
	</div>


</body>
