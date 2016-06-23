<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./../../header/menu.jsp"%>
<script type="text/javascript">
	function write_b(a) {
		var form1=document.board_form;
		if(a=="mod"){
			
			form1.p_code.value="modify_ok";
			
		}else if(a=="add"){
		form1.p_code.value="write_ok";

		}	
		form1.submit();
	
	}
	
</script>

<link rel="stylesheet" type="text/css" href="./css/table.css" />
<meta charset="UTF-8">
<title>
<c:choose>
<c:when test="${dto ne null}">
<c:choose><c:when test="${q_code ne reply}">
:: 댓글작성 - PlanBinder ::
</c:when>
</c:choose>
:: 글수정 - PlanBinder ::</c:when>
		<c:otherwise> :: 글작성 - PlanBinder ::</c:otherwise></c:choose>
</title>
</head>
<body>
<script>
$(function() {
	
	if('${dto.q_secret}'=='Y'){
		$("input[name=q_secret]").prop("checked", true);
		//$("div[id=div_table][class=${list.q_reply_level}]").css("padding-left",'${(list.q_reply_level+1)*25}'+"px")
	}else{
		$("input[name=q_secret]").prop("checked", false);
	}
	});
window.onresize = function() { 
	//$("div[id=div_table][class=${list.q_reply_level}]").css("")
}
</script>
<BR>
<div id="content">
<h2>
<c:choose>
<c:when test="${dto ne null}">
<c:choose><c:when test="${q_code ne reply}">
답변작성
</c:when>
<c:otherwise>
수정하기
</c:otherwise>
</c:choose></c:when>


		<c:otherwise> 질문작성 </c:otherwise></c:choose></h2>
<Br>
<form action="Board.do"  name="board_form" method="post">
<table class="type09" >
<thead>
<tr><th rowspan="3">글제목</th><th id="id">비밀글</th></tr>
</thead>
<tbody>
<tr>
<td><input type="text" name="q_title" style="width: 400px;" value='<c:if test="${dto ne null}"> ${dto.q_title}</c:if>' ></td>


<td id="id"><input type="checkbox" name="q_secret" value="Y" class="onoffswitch-checkbox" id="myonoffswitch" checked>
    <label class="onoffswitch-label" for="myonoffswitch">
    <div class="onoffswitch-inner">
    <div class="onoffswitch-active"><div class="onoffswitch-switch">ON</div></div>
    <div class="onoffswitch-inactive"><div class="onoffswitch-switch">OFF</div></div>
    </div>
    </label>
    </td>
</tr>
</tbody>
<thead>
<tr><th colspan="4">본문내용</th></tr>
</thead>
<tbody>
<tr>
<td colspan="4"><textarea name="q_contents"  rows="20" cols="115" ><c:if test="${dto ne null}"> ${dto.q_contents}</c:if></textarea></td>
</tr>
</tbody>
<thead >
<tr><th colspan="4">첨부파일</th></tr>
</thead>
<tbody><tr><th colspan="4"><input type="file" name="q_pds_link" value="<c:if test="${dto ne null}"> ${dto.q_pds_link}</c:if>" ></th></tr>
</tbody>
<tfoot>
<tr><td colspan="4">
<c:choose><c:when test="${dto ne null}">
<input type="hidden" value="${dto.q_board_id}" name="q_board_id">
<input type="hidden" name="q_reply_level" value="${dto.q_reply_level}">
<input type="hidden" name="q_ref_id" value="${dto.q_ref_id}">
<input type="hidden" name="q_ref_group" value="${dto.q_ref_group}">

<input type="hidden" name="p_cnt" value="${p_cnt}">


<c:choose><c:when test="${q_code ne reply}">
<input type="button" value="댓글" onclick="write_b('add')" class="button">

</c:when>
<c:otherwise>
<input type="button" value="수정" onclick="write_b('mod')" class="button aa">
</c:otherwise>
</c:choose>
</c:when>
<c:otherwise><input type="button" value="작성" onclick="write_b('add')" class="button">
</c:otherwise>
</c:choose>
<input type="button" value="목록" onclick="javascript: location.replace('Board.do?p_code=list&page_cnt=1')" class="button"> 
</td>
</tfoot>

</table>
<br>
<input type="hidden" name="p_code" value="">

</form>
</div>
</body>
</html>