<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>로그인</title>
</head>
<body>
	<h2>로그인</h2>
	<form action="loginDo" method="post">
		이메일 : <input type="text" name="email"><br>
		비밀번호 : <input type="text" name="password"><br>
		<input type="submit" value="로그인">
		<button type="button" onclick="location.href='/main';">돌아가기</button>
	</form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>