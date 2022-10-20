<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="main" /></title>
</head>
<body>
	<h2><spring:message code="main" /></h2>
	<div>
		<c:if test="${empty loginUserInfo }">
			<a href="<c:url value="/user/login" />" ><spring:message code="login" /></a><br>
			<a href="<c:url value="/user/findEmail" />" ><spring:message code="find.email" /></a>
			<a href="<c:url value="/user/findPwd" />" ><spring:message code="find.password" /></a>
			<a href="<c:url value="/user/register" />" ><spring:message code="register" /></a>
		</c:if>
		
		<c:if test="${!empty loginUserInfo }">
			<spring:message code="welcome" arguments="${loginUserInfo.nickname }"/>
			<div>
				<a href="<c:url value="/user/myPage" />" ><spring:message code="MyPage" /></a>
				<a href="<c:url value="/user/logout" />" ><spring:message code="logout" /></a>	
			</div><br>
			<c:if test="${loginUserInfo.isAdmin }">
			<h3><spring:message code="AdminPage" /></h3>
			<div>
				<a href="<c:url value="/user/list" />" ><spring:message code="manage.user" /></a>
				<a href="<c:url value="/user/myPage" />" ><spring:message code="manage.board" /></a>	
			</div>
			</c:if>	
		</c:if>
		
	</div><br>
	<div>
		<ul>
			<li><a href="<c:url value="/board/list/0" />" ><spring:message code="board.all" /></a><br></li>
			<li><a href="<c:url value="/board/list" />" ><spring:message code="board.hot" /></a><br></li>
			<li><a href="<c:url value="/board/list/1" />" ><spring:message code="board.free" /></a><br></li>
			<li><a href="<c:url value="/board/list/2" />" ><spring:message code="board.ask" /></a><br></li>
			<li><a href="<c:url value="/board/list/3" />" ><spring:message code="board.discuss" /></a><br></li>
			<li><a href="<c:url value="/board/list/4" />" ><spring:message code="board.info" /></a></li>
		</ul>
	</div>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>