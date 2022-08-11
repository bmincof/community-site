<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 찾기</title>
</head>
<body>
	<c:if test="${empty foundEmails }">
		일치하는 회원 정보가 없습니다.<br>
	</c:if>
	
	<c:if test="${!empty foundEmails }">
		회원님의 정보로 가입된 이메일입니다.<br>
		<c:forEach var="foundEmail" items="${foundEmails }">
			${foundEmail } <br>
		</c:forEach>
	</c:if>
	
	<a href="<c:url value="/" />" >메인페이지로 돌아가기</a>
</body>
</html>