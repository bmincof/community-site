<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
	<h2>관리자 페이지</h2>
	<div>
			<c:if test="${empty loginUserInfo }">
				<a href="<c:url value="/user/login" />" >로그인하기</a><br>
				<a href="<c:url value="/user/findEmail" />" >아이디찾기</a>
				<a href="<c:url value="/user/findPwd" />" >비밀번호찾기</a>
			</c:if>
			
			<c:if test="${!empty loginUserInfo }">
				접속중인 관리자 정보 : <b>${loginUserInfo.nickname }</b>님
				<div>
					<a href="<c:url value="/user/list" />" >회원정보 관리</a>
					<a href="<c:url value="" />">게시글 관리</a>
					<a href="<c:url value="/user/logout" />" >로그아웃</a>	
				</div>
			</c:if>
		</div><br>
</body>
</html>