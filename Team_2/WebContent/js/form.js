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
		if('${USERS}' != null & '${USERS}' ==""){
			r.code.value='mod_ok';
			alert("mod_ok");
			r.action='modify.do';
		}
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


