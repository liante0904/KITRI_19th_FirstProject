<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="<%=request.getContextPath()%>" />
<table class="type10">
<thead>
	<tr>
		<th>번호</th>
		<th>유형</th>
		<th>우선순위</th>
		<th style="width: 412px">&nbsp;&nbsp;&nbsp;제목</th>
		<th>진행도</th>
		<th>상위프로젝트</th>
		<th>담당자</th>
		<th>시작기간</th>
		<th>마감기간</th>
	</tr>
</thead>
<tbody id="main-a2">
	<c:if test='${list.size() eq 0 || list.size() eq 0}'>
				<td colspan="9" align="center">검색된 결과가 없습니다.</td>
			</c:if>
	<c:forEach items="${list}" var="list">
		<c:choose>
			<c:when test="${list.p_use_yn eq 'Y'}">	
				<tr>
					<td style="text-align:center;">${list.p_id}</td>
					<td style="text-align:center;">${list.p_type}</td>
					<td style="text-align:center;">${list.p_priority}</td>
					<td><a href="${path}/project.do?code=view&p_id=${list.p_id}">${list.p_subject}</a></td>
					<td style="text-align:center;">${list.p_progress}</td>
					<td style="text-align:center;">${list.p_upper}</td>
					<td style="text-align:center;">${list.u_id}</td>
					<td style="text-align:center;">${list.p_sdate}</td>
					<td style="text-align:center;">${list.p_edate}</td>
				</tr>
			</c:when>
			

			<c:otherwise>
				<tr>
					<td colspan="9" align="center">#${list.p_id}."${list.p_subject}"는 삭제된 프로젝트
						입니다.</td>
				</tr>

			</c:otherwise>
		</c:choose>
	</c:forEach>
</tbody>
<tfoot>
<tr>
<td colspan="9" style="text-align: right;">
<a class="button" href="${path}/project.do?code=search_u_id">프로젝트 추가</a>
</td>
</tr>
</tfoot>
</table>