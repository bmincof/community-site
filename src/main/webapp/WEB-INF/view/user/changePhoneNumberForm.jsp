<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원정보 변경</title>
</head>
<body>
	<h2>전화번호 변경</h2>
	<form:form action="changePhoneNumber" method="post" modelAttribute="userInfoChangeRequest">
		새로운 전화번호 : <input type="text" name="newPhoneNumber"><form:errors path="newPhoneNumber" /><br>
		<input type="submit" value="변경">
		<button type="button" onclick="location.href='myPage';">돌아가기</button>
	</form:form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>