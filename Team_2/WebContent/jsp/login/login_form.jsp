<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%String path=request.getContextPath();%>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="<%=path%>/css/login.css" rel="stylesheet" media="all">
<style>
/* body {
	margin-left:35%;
	margin-top:5%;
	background-color:#bfe2e6;
	align:center; */

/* 로그인form css */
</style>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=path%>/js/form.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript">
	function login() {
		//Controller
		//p_code=특정 값이 들어가도록 입력
		//로그인시 p_code value : login_chk
		if (document.loginForm.u_id.value == "") {
			alert("ID를 입력하시오.");
			document.loginForm.u_id.focus();
		} else if (document.loginForm.u_pw.value == "") {
			alert("PW를 입력하시오.");
			document.loginForm.u_pw.focus();
		} else {
			document.loginForm.code.value = "login_chk";
			document.loginForm.action='login.do';
			document.loginForm.submit();
		}
	}
	function join() {
		//Controller
		//p_code value : user_reg		
		document.loginForm.code.value = "join";
		document.loginForm.action='login.do';
		document.loginForm.submit();
	}
	function keyevent() {
		if (event.keyCode == 13) {
			login();
		}
	}
	function error() {
		if ('${error}' != "") {
			alert('${error}');		
		}
		if('${code1}'=="duplicateid"){
			 $( '.active' ).removeClass('active');
			  $( '.show' )
		        .removeClass('show')
		        .addClass('hide')
		        .hide();
			  $("a[href='#register']").addClass('active');
			  $("div[id='register']").removeClass('hide')
		        .addClass( 'show')
		        .hide()
		        .fadeIn( 550 );
			  
			  $('.flat-form').css("height","470px");
			}
		}
	
	
	$(function() {
		  // constants
		  var SHOW_CLASS = 'show',
		      HIDE_CLASS = 'hide',
		      ACTIVE_CLASS = 'active';
		  
		  $( '.tabs' ).on( 'click', 'li a', function(e){
		    e.preventDefault();		    
		    var $tab = $( this ),
		         href = $tab.attr( 'href' );
		  		;
		     $( '.active' ).removeClass( ACTIVE_CLASS );
		     $tab.addClass( ACTIVE_CLASS );
		  
		     $( '.show' )
		        .removeClass( SHOW_CLASS )
		        .addClass( HIDE_CLASS )
		        .hide();		     	
		    
		      $(href)
		        .removeClass( HIDE_CLASS )
		        .addClass( SHOW_CLASS )
		        .hide()
		        .fadeIn( 550 );
		        
		     
		  	if( $('.active').attr('href')=='#register'){
		  		$('.flat-form').css("height","470px");
		  	}	
		  	else if($('.active').attr('href')=='#login'){
		  		$('.flat-form').css("height","340px");
		  	}
		  });
		});
</script>
<title>:: 로그인 - PlanBinder ::</title>
</head>
<body onload='error()'>


	<div class="container">
		<div class="aaa">
		<div class="flat-form">
			<ul class="tabs">
				<li><a href="#login" class="active">Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
				<li><a href="#register">Register</a></li>

			</ul>
			<div id="login" class="form-action show">
				<h1>로그인</h1>
				<p>프로젝트 지원자로 등록하신 아이디와 패스워드를 입력하세요</p>
				<form method="post" name="loginForm">
					<ul>
						<li><input type="text" name="u_id" placeholder="아이디"	onkeydown="keyevent()" /></li>
						<li><input type="password" name="u_pw" placeholder="비밀번호" onkeydown="keyevent()" /></li>
						<li><input name="code" type="hidden"> <input type="button" value="로그인" class="button" onClick="login()" /></li>
					</ul>
				</form>
			</div>
			<!--/#login.form-action-->
			<div id="register" class="form-action hide">
				<h1>구성원 신청</h1>
				<p>본 프로젝트의 가입은 관리자의 승인 이후 사용할 수 있습니다.</p>
				<form action="login.do" name="frm1" method="post">
					<ul>
						<li><input name="u_name" placeholder="사용자명" type="text" />
						</li>
						<li><input type="text" name="u_id" placeholder="아이디" /></li>
						<li><input type="password" placeholder="비밀번호"
							name="u_pw" /></li>
						<li><input type="password" placeholder="비밀번호 확인"
							name="pass2" /></li>
						<li><input type="text" name="p_email" size="6"
							placeholder="Email" /></li>
						<li><input type="hidden" name="code" value="join_ok" /> <input
							type="button" class="button" value="가입 신청" onclick="register()" />
							<input type="button" class="button" value="재입력"
							onclick="reset()" /></li>
					</ul>
				</form>
			</div>

			<!--/#register.form-action-->
		</div>
		</div>
	</div>
	<script class="cssdeck"	src="<%=path%>/js/jquery.min.js"></script>



</body>
</html>