<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 목록</title>
</head>
<body>
	<h2>회원 목록</h2>
	<table border="1" width="80%">
		<tr>
			<th>아이디</th><th>이메일</th><th>비밀번호</th>
			<th>이름</th><th>닉네임</th><th>전화번호</th><th>가입일</th>
			<th>삭제 여부</th><th>비밀번호 변경</th><th>탈퇴 처리</th>
		</tr>
		<c:forEach var="user" items="${users }">
			<c:if test="${!user.isAdmin }">
				<tr>
					<td>${user.userId }</td>
					<td>${user.email }</td>
					<td>${user.password }</td>
					<td>${user.name }</td>
					<td>${user.nickname }</td>
					<td>${user.phoneNumber }</td>
					<td>${user.registerDate }</td>
					<td><c:if test="${user.isDeleted }">O</c:if></td>
					<c:if test="${!user.isDeleted }">
						<td><button onclick="location.href='/admin/changeRequest?userId=${user.userId }';">변경</button></td>
						<td><button onclick="location.href='/admin/delete?userId=${user.userId }';">탈퇴</button></td>
					</c:if>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<button onclick="location.href='/main';">메인으로</button>
</body>
</html>