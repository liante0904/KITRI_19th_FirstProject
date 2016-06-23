<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login form</title>
<script type="text/javascript">
function register(){
	//이름 검사
	var r = document.frm1;
	if(r.u_name.value==""){
		alert("이름을 입력하세요.")
		r.u_name.focus();
	}
	//아이디 검사
	else if(r.u_id.value==""){
		alert("아이디를 입력하세요.")
		r.u_id.focus();
	}
	//비밀번호 검사
	else if(r.u_pw.value=="" || r.pass2.value==""){
		alert("비밀번호를 입력하세요.")
		r.u_pw.focus();
	}
	//비밀번호 일치 검사
	else if(r.u_pw.value != r.pass2.value){
		alert("비밀번호가 같지 않습니다.")
		r.pass2.focus();
	}else{

			r.code.value='mod_ok';
			alert("mod_ok");
			r.action='modify.do';
		
		r.submit();
		
	}


}

function reset(){
	var a = document.frm1;
	a.u_name.value='';
	a.u_id.value='';
	a.u_pw.value='';
	a.pass2.value='';
	a.u_email.value='';

}



	function error() {
		if('${error}'!=""){
			alert('${error}');
		}
		if ('${USERS}' != null){
			var a = document.frm1;
			a.u_name.value='${USERS.u_name}';
			a.u_id.value='${USERS.u_id}';
			a.u_email.value='${USERS.u_email}';
		}
	}
</script>
<style>
table {
	background-color: #f1f1f1;
	border: 1px solid;
	border-color: highlight;
}

table tr th {
	background-color: #f4f4f4;
	font-size: small;
}

table td input::-webkit-input-placeholder {
	font-size: 11px;
	font-style: oblique;
}

table td input::-ms-input-placeholder {
	font-size: 11px;
	font-style: oblique;
}
</style>
</head>

<body onload=error()>
	<h4>정보수정</h4>
	<form action="login.do" name="frm1" method="post">

		<table>
			<tr>
				<th>이름</th>
				<td><input name="u_name" placeholder="이름 입력" type="text" /></td>
			</tr>

			<tr>
				<th>아이디</th>
				<td><input name="u_id" placeholder="아이디 입력" type="text" /></td>
			</tr>

			<tr>
				<th>암호</th>
				<td><input type="password" placeholder="비밀번호 입력" name="u_pw" /></td>
			</tr>



			<tr>
				<th>암호확인</th>
				<td><input type="password" placeholder="비밀번호 확인" name="pass2" /></td>
			</tr>

			<tr>
				<th>이메일</th>
				<td><input type="text" name="u_email" size="6"
					placeholder="이메일 입력" />
			</tr>

			<tr>
				<td><input type="button" value="확인" onclick="register()"
					name='aa' /></td>
				<td><input type="button" value="다시입력" onclick="reset()" /></td>
			</tr>

		</table>
		<input type="hidden" name="code" value="join_ok" />

	</form>
</body>
</html>

