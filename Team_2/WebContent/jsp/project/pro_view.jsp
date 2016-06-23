<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
function formsubject(){
	var form1 = document.d_form;
	form1.submit();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: ${pDto.p_subject}#${pDto.p_id} - PlanBinder ::</title>
<%@include file="./../../header/menu.jsp"%>
</head>
<body>
<div id="content">
<h2>상세보기</h2>
	<div style="margin-left: 40px; margin-top: 60px">
		<div class="subject"></div>
		<div class="value">${pDto.p_subject}#${pDto.p_id}
		</div>
		<div class="value">${pDto.p_contents}</div>
		<div class="contextual">상태 : ${pDto.p_type}</div>
		<div class="contextual">우선순위 : ${pDto.p_priority}</div>
		<div class="contextual">담당자 ID : ${pDto.u_id}</div>
		<div class="contextual">이름 : ${uDto.u_name}</div>
		<div class="contextual">E-mail : ${uDto.u_email}</div>
		<div class="contextual">시작 시간 : ${pDto.p_sdate}</div>
		<div class="contextual">완료 기한 : ${pDto.p_edate}</div>
		<div class="contextual">진척도 : ${pDto.p_progress}</div>
		<div class="contextual">하위 프로젝트 : ${pDto.p_depth}</div>
		<div class="contextual">상위 프로젝트 : ${pDto.p_upper}</div>

	<br>
	
		<form name="d_form" method="post" action="${path}/project.do?code=del">
		<input type="hidden" name="p_id" value="${pDto.p_id}">
	</form>
	<a href="${path}/project.do?code=project" class="button">목록</a> 
	<a class="button aa" href="${path}/project.do?code=mod&p_id=${pDto.p_id}" >편집</a>
	<a href="#" onclick="formsubject(); return false;" class="button danger">삭제</a>
	</div>
	</div>
</body>
</html>