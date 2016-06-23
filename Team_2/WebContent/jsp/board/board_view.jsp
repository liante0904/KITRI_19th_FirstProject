<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./../../header/menu.jsp"%>
<script type="text/javascript">
	
	function b_modify(i) {
		if(document.getElementsByName('a').length>=2){
			var a1 = document.a[i-1];
			}
			else{
			var a1=document.a;
			}
		if(confirm('수정하시겠습니까?')){
			a1.p_code.value="modify";
			a1.submit();
		}
	}
	function b_delete(i) {
		if(document.getElementsByName('a').length>=2){
		var a1 = document.a[i-1];
		}
		else{
		var a1=document.a;
		}
		if(confirm('삭제하시겠습니까?')){
		a1.p_code.value="delete";
		a1.submit();
		}
		}
	function b_reply(i){
		if(document.getElementsByName('a').length>=2){
			var a1 = document.a[i-1];
			}
			else{
			var a1=document.a;
			}
		a1.p_code.value="reply";
		a1.submit();	
	}
	function b_list(){
		location.href="Board.do?p_code=list&page_cnt=1";	
	}	
</script>
<link rel="stylesheet" type="text/css" href="./css/table.css" />
<meta charset="UTF-8">
<title> :: 게시글 - PlanBinder ::</title>
</head>
<body>
<div id="content">
<h2>게시판</h2>
<c:set var="p_cnt" value="0" />
<c:set var="a_cnt" value="0" />
<c:forEach var="list" items="${list}">
<script>
		$(function() {
			
			if('${list.q_secret}'=='Y'){
				if('${list.q_reply_level}'!=0){
					if('${list.u_id}'!='${sessionScope.u_id}' && '${sessionScope.u_id}'!='admin'){
						$("div[id=div_table][class=${list.q_reply_level}][value=${list.q_ref_id}]").hide();
					}
				}else {					
					if('${list.u_id}'!='${sessionScope.u_id}' && '${sessionScope.u_id}'!='admin'){
						$("div[id=div_table]").hide();
						//list.u_id ==sessionScope.u_id
						//비밀글이고 관리자가 아니고 세션아이디가 게시글 아이디와 다를때에는 경고창을 띄운다.
							alert("비밀질문은 관리자와 작성자만 볼 수 있습니다.");
							location.href="Board.do?p_code=list&page_cnt=1";
					
					}
				}
			}
			$("div[id=div_table][class=${list.q_reply_level}]").css("padding-left",'${(list.q_reply_level+1)*45}'+"px")
							});
</script>
<div id="div_table" class='${list.q_reply_level}' value='${list.q_ref_id}'>
<form action="Board.do"  method="post"  name="a" >
<input type="hidden" name="q_ref_group" value="${list.q_ref_group}">
<input type="hidden" name="p_code">
<input type="hidden" name="q_reply_level" id="q_reply_level" value="${list.q_reply_level}">
<input type="hidden" name="q_ref_id" value="${list.q_ref_id}">
<input type="hidden" name="q_board_id" value="${list.q_board_id}">


<c:choose>
						<c:when test="${list.q_reply_level eq 0}">
						<c:set var="str" value="${list.q_title}"/>
							<h3>질문 ${list.q_title}</h3>
						</c:when>
						<c:otherwise>
							
							<h3>${str}<c:forEach var="i" begin="1" end="${list.q_reply_level}" step="1">의  답변</c:forEach>
							</h3>
						</c:otherwise>
					</c:choose>
<table class="type09">
<thead>
<tr><th style="width:80px">번호</th><th style="width: 350px">글제목</th><th style="width:80px">작성자</th><th style="width:130px;" id="id">작성일자</th><th id="id">조회수</th><th id="id">비밀글</th></tr>
</thead>
<tbody>
<tr><th>${list.q_board_id}</th><th>${list.q_title}</th><th>${list.u_id}</th><th id="id">${list.q_wdate}</th><th id="id">${list.q_read_cnt}</th><th style="vertical-align: middle;text-align: center;"><c:if test="${list.q_secret=='Y'}"> <img src="./images/q_secret.png" style="width: 15px;height: 20px; vertical-align:middle;"></c:if></th></tr>
</tbody>
<thead>
<tr><th  colspan="6">글내용</th></tr>
</thead>
<tbody>
<tr>
<td  colspan="6"><textarea rows="20" cols="115" readonly="readonly">${list.q_contents}</textarea></td>
</tr>
</tbody>
<thead>
<tr><th colspan="6">첨부파일</th></tr>
</thead>
<tbody>
	<tr><td colspan="6">
<c:choose>
<c:when test="${list.q_pds_link eq null}">첨부파일없음</c:when>
<c:otherwise>${list.q_pds_link}</c:otherwise>
</c:choose>
		</td>
	</tr>
</tbody>
<tfoot>
<tr>
<td colspan="1" style="text-align: left;"><c:set var="p_cnt" value="${p_cnt+1}" />
<input type="hidden" name="p_cnt" value="${p_cnt}">
<input type="button" value="목록" onclick="b_list()" class="button"> 
</td>
<td colspan="5" style="text-align: right;">
<input type="button" value="댓글" onclick="b_reply('${p_cnt}')" class="button"> 
<c:if test="${list.u_id ==sessionScope.u_id}">
<input type="button" value="수정" onclick="b_modify('${p_cnt}')" class="button aa">
<input type="button" value="삭제" onclick="b_delete('${p_cnt}')" class="button danger">
</c:if>
</td>
</tr>
</tfoot>
</table>

</form>
</div>
<br>

</c:forEach>
</div>

</body>
</html>