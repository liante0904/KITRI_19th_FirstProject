<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<script type="text/javascript">
	function modify_ok(){
		alert("수정이 완료되었습니다.");
		opener.location.href='login.do?code=';
	    window.close();
	}
</script>
<title>:: 수정완료 - PROJECT SEED::</title>
</head>
<body onload='modify_ok()'>
</body>
</html>