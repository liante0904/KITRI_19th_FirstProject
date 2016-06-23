<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function chkBox() {
		var flg = false;
		var fm = document.chk1;
		var i = 0;
		for (i; i < fm.grant.length; i++) {
			if (fm.grant[i].checked) {
				flg = true;
				break;
			}
		}
		if (!flg) {
			alert("변경될 사항이 없습니다.");
			return false;
		}
		return true;
	}
</script>

<title>:: 관리자 페이지 - PlanBinder ::</title>
<%@include file="./../../header/menu.jsp"%>
<link href="/favicon.ico" rel="shortcut icon">
<link href="css/application.css" rel="stylesheet" media="all">
<link href="css/sidebar_hide.css" rel="stylesheet" media="all">
</head>
<body>
	<div id="content">
		<h2>USER Management Page</h2>
		<ul>
			<li>관리자 페이지 입니다.</li>
			<li>회원의 AUTH_YN을 누르시면 계정이 활성화/비활성화 됩니다.</li>
		</ul>
		<div class="box">
			<h3>회원</h3>
			<form name="chk1" action=adminCont.do onsubmit='return chkBox()'>
				<table class="list">
					<thead>
						<tr>
							<th>NAME</th>
							<th>ID</th>
							<th>EMAIL</th>
							<th>AUTH_YN</th>
							<th>승인/휴먼</th>
						</tr>
					<thead>
					<tbody>
						<c:choose>
							<c:when test="${USERS ne null}">
								<c:forEach items="${USERS}" var="ulist">
									<tr style="text-align: center;">
										<td>${ulist.u_name}</td>
										<td>${ulist.u_id}</td>
										<td>${ulist.u_email}</td>
										<td>${ulist.AUTH_YN}</td>
										<td><input type="checkbox" name="grant"
											value='${ulist.u_id}'></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td colspan="4">데이터가 없습니다.</td>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<input type="submit" value='회원가입 승인' class="button"> <input type="hidden"
					value='allGrand' name='code'>
			</form>

		</div>
	</div>


</body>
</html>
