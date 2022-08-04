<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 관리</title>
</head>
<body>
	<div>
		<c:if test="${empty loginUserInfo }">
			로그인이 필요한 서비스입니다.
		</c:if>
		
		<c:if test="${!empty loginUserInfo }">
			<a href="<c:url value="/user/changeInfo/${loginUserInfo.userId }" />" >회원정보 변경</a>
			<a href="<c:url value="/user/changePassword/${loginUserInfo.userId }" />" >비밀번호 변경</a>	
		</c:if>
	</div>
</body>
</html>