<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function mod_chk() {
		var loopback = document.modForm;
		if (loopback.u_id.value == "") {
			alert("아이디를 입력 해주세요.")
			loopback.u_id.focus;
		} else if (loopback.u_pw.value == "") {
			alert("비밀번호를 입력 해주세요.")
			loopback.u_pw.focus;
		} else if (loopback.u_pw.value != loopback.u_pwchk.value) {
			alert("비밀번호가  일치하지 않습니다. (비밀번호 확인)")
		} else if (loopback.u_name.value == "") {
			alert("이름을 입력 해주세요.")
			loopback.u_name.focus;
		} else {
			loopback.code.value = "mod_chk";
			alert(loopback.code.value);
			loopback.submit();
		}
	}
</script>
<title>:: 회원 정보 수정 - PROJECT SEED ::</title>
</head>
<body>
	<form action="user.do" method="post" name="modForm">




		<h2>회원 정보 수정 페이지</h2>

		*아이디 : &nbsp;&nbsp;<input type="text" name="u_id" size="10"
			value="<%=session.getAttribute("u_id")%>" readonly="readonly"><br>
		<br> *비밀번호 : <input type="password" name="u_pw" size="15"
			placeholder="Password"> *비밀번호 확인 : <input type="password"
			name="u_pwchk" value="" size="15" placeholder="Password"><br>
		<br> *이름 : <input type="text" name="u_name" size="8"
			value="${name }" readonly="readonly"> <br> <br>

		<p>

			*이메일 <input type="text" name="u_email" value="${u_email }"><br>
		</p>

		<input type="button" value="회원정보 수정" name="name" onclick="mod_chk()"> 
		<input type="hidden" name="code">


	</form>

</body>
</html>