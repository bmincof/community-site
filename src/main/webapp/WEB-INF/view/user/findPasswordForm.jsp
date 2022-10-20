<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
</head>
<body>
	<h2>비밀번호 찾기</h2>
	<form:form action="findPwd" method="post" modelAttribute="userInfoFindRequest">
		회원정보에 등록된 이메일, 이름, 휴대폰번호를 입력해주세요.<br>
		이메일 : <input type="text" name="email"><form:errors path="email" /><br>
		이름 : <input type="text" name="name"><form:errors path="name" /><br>
		휴대폰 번호 : <input type="text" name="phoneNumber"> (-를 포함하여 입력해주세요.)<form:errors path="phoneNumber" /><br>
		<input type="submit" value="찾기">
		<button type="button" onclick="location.href='/main';">돌아가기</button>
	</form:form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>