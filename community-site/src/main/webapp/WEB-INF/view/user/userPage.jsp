<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty loginUserInfo }">
		로그인이 필요한 서비스입니다.
	</c:if>
	<c:if test="${!empty loginUserInfo }">
		환영합니다 ${loginUserInfo.nickname }님!
		<a href="<c:url value="logout" />" >로그아웃</a>
		<a href="<c:url value=" ?? " />" >회원정보 관리</a>
	</c:if>
</body>
</html>