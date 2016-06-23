<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="./../../header/menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: 모니터링 - PlanBinder ::</title>
<link href="/favicon.ico" rel="shortcut icon">
<link href="css/application.css" rel="stylesheet" media="all">
<link href="css/sidebar_hide.css" rel="stylesheet" media="all">
<style type="text/css">
.chart{
	

}
</style>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>


<script type="text/javascript">
      // [START script_body]
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
  		var data = google.visualization.arrayToDataTable([
  		                                				[ '유형별', '진행중', '완료', '총' ],
  		                                				[ '${TYPE[0].type_name}', '${TYPE[0].type_nF}',
  		                                						'${TYPE[0].type_finish}', '${TYPE[0].type_tt}' ],
  		                                				[ '${TYPE[1].type_name}', '${TYPE[1].type_nF}',
  		                                						'${TYPE[1].type_finish}', '${TYPE[1].type_tt}' ],
  		                                				[ '${TYPE[2].type_name}', '${TYPE[2].type_nF}',
  		                                						'${TYPE[2].type_finish}', '${TYPE[2].type_tt}' ],
  		                                				[ '${TYPE[3].type_name}', '${TYPE[3].type_nF}',
  		                                						'${TYPE[3].type_finish}', '${TYPE[3].type_tt}' ],
  		                                				[ '${TYPE[4].type_name}', '${TYPE[4].type_nF}',
  		                                						'${TYPE[4].type_finish}', '${TYPE[4].type_tt}' ],
  		                                				[ '${TYPE[5].type_name}', '${TYPE[5].type_nF}',
  		                                						'${TYPE[5].type_finish}', '${TYPE[5].type_tt}' ],
  		                                				[ '${TYPE[6].type_name}', '${TYPE[6].type_nF}',
  		                                						'${TYPE[6].type_finish}', '${TYPE[6].type_tt}' ],
  		                                				[ '${TYPE[7].type_name}', '${TYPE[7].type_nF}',
  		                                						'${TYPE[7].type_finish}', '${TYPE[7].type_tt}' ], ]);

        var options = {
          chart: {
	    title: 'BlueMine Monitor',
	    subtitle: '프로젝트 모니터',
	  }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, options);
      }
      // [END script_body]
    </script>

</head>
<body>
	<div id="content">
		<h2>개요</h2>
		<ul>
			<li>"PlanBinder"에 오신 것을 환영합니다.</li>
			<li>PlanBinder는 프로젝트 관리를위한 웹 어플리케이션으로써 보다 편리하고 쉬운 프로젝트 관리를 위해 서비스를 제공 하고 있습니다.</li>
		</ul>

		<div class="box">
			<h3>구성원</h3>
			<c:choose>
				<c:when test="${not empty USERS}">
					<table style="width: 100%">
						<tr>
							<c:forEach items="${USERS}" var="ulist">
								<td style="font-size: 10pt">
									<table>
										<tr>
											<td style="font-size: 12pt"><a
												href="project.do?code=project&pm=${ulist.u_id}">${ulist.u_id}</a>
											</td>
										</tr>
										<tr>
											<td>${ulist.u_name}</td>
										</tr>
										<tr>
											<td><a href="mailto:${ulist.u_email}">${ulist.u_email}</a>
											</td>
										</tr>
									</table>
								</td>
							</c:forEach>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
					<td colspan="6">자료가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="box">
			<h3>최근 뉴스</h3>
			<c:choose>
				<c:when test="${not empty PROJECTS}">
					<c:forEach items="${PROJECTS}" var="plist">
						<p>
							<a href="project.do?code=view&p_id=${plist.p_id}">
								#${plist.p_id}.[${plist.p_type}] ${plist.p_subject}</a> <br>
						</p>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="6">자료가 없습니다.</td>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="box">
			<h3>프로젝트 모니터링</h3>
			
				<div id="columnchart_material" style="width: 900px; height: 500px; left: 600px;"></div>
			<!--<div id="columnchart_stacked" style="width: 600px; height: 300px;"></div>-->
			<table class="list">
				<thead>
					<tr>
						<th>유형</th>
						<th>진행중</th>
						<th>완료됨</th>
						<th>합계</th>
					</tr>
				<thead>
				<tbody>


					<c:forEach items="${TYPE}" var="tlist">
						<tr class="odd">
							<td><a href="#">${tlist.type_name}</a></td>
							<td>${tlist.type_nF}</td>
							<td>${tlist.type_finish}</td>
							<td>${tlist.type_tt}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
