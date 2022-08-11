<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<c:if test="${empty foundPwd }">
		일치하는 회원 정보가 없습니다.<br>
	</c:if>
	
	<c:if test="${!empty foundPwd }">
		회원님의 비밀번호는 ${foundPwd }입니다.<br>
	</c:if>
	
	<a href="<c:url value="/" />" >메인페이지로 돌아가기</a>
</body>
</html>