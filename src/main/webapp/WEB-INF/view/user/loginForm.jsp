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
		<!-- 뒤로가기 시 login과 loginDo 처럼 다른 url이지만 같은 폼을 사용할 때는 histroy.back() 사용하면 같은화면 두번 봐야함 -->
	</form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>