<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<h2>메인페이지</h2>
	<div>
		<c:if test="${empty loginUserInfo }">
			<a href="<c:url value="/user/login" />" >로그인하기</a><br>
			<a href="<c:url value="/user/findEmail" />" >아이디찾기</a>
			<a href="<c:url value="/user/findPwd" />" >비밀번호찾기</a>
		</c:if>
		
		<c:if test="${!empty loginUserInfo }">
			${loginUserInfo.nickname }님, 환영합니다!
			<div>
				<a href="<c:url value="/user/myPage" />" >회원정보 관리</a>
				<a href="<c:url value="/user/logout" />" >로그아웃</a>	
			</div>
		</c:if>
	</div><br>
	<div>
		<a href="<c:url value="/board/list/0" />" >전체 게시글</a><br>
		<a href="<c:url value="/board/list" />" >인기 게시글</a><br>
		<a href="<c:url value="/board/list/1" />" >자유 게시판</a><br>
		<a href="<c:url value="/board/list/2" />" >질문 게시판</a><br>
		<a href="<c:url value="/board/list/3" />" >토론 게시판</a><br>
		<a href="<c:url value="/board/list/4" />" >정보 공유 게시판</a>
	</div>
</body>
</html>