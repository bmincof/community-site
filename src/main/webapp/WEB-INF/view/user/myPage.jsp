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
		<h2>${loginUserInfo.nickname }님의 마이페이지</h2>
		<ul>
			<li><a href="<c:url value="/user/changePassword" />" >비밀번호 변경</a><br></li>
			<li><a href="<c:url value="/user/changeNickname" />" >닉네임 변경</a><br></li>
			<li><a href="<c:url value="/user/changePhoneNumber" />" >전화번호 변경</a><br></li>
			<li><a href="<c:url value="/user/delete" />" >회원탈퇴</a><br></li>
		</ul>
	</div>
	<button type="button" onclick="location.href='/main';">돌아가기</button>
</body>
</html>