<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 목록</title>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th><th>이메일</th><th>비밀번호</th>
			<th>이름</th><th>닉네임</th><th>전화번호</th><th>가입일</th>
		</tr>
		<c:forEach var="user" items="${users }">
		<tr>
			<td>${user.userId }</td>
			<td>${user.email }</td>
			<td>${user.password }</td>
			<td>${user.name }</td>
			<td>${user.nickname }</td>
			<td>${user.phoneNumber }</td>
			<td>${user.registerDate }</td>
			<td><a href="<c:url value="changePassword/${user.userId }" />">
				비밀번호 변경</a></td>
			<td><a href="<c:url value="changeInfo/${user.userId }" />">
				회원 정보 수정</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>