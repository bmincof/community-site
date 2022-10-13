<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원정보 변경</title>
</head>
<body>
	<h2>비밀번호 변경</h2>
	<form:form action="changePassword" onsubmit="return checkPwd()" method="post" modelAttribute="userInfoChangeRequest">
		<c:if test="${!empty loginUserInfo }">
			<input type="hidden" name="userId" value="${loginUserInfo.userId }">
		</c:if>
		<c:if test="${empty loginUserInfo }">
			<input type="hidden" name="userId" value="${userId }">
		</c:if>
		새로운 비밀번호 : <input type="text" name="newPassword" id="pwd" onchange="checkPwd()"><span id="length"></span>
		<form:errors path="newPassword" /><br>
		비밀번호 확인 : <input type="text" name="confirmNewPassword" id="check" onchange="checkPwd()"><span id="same"></span><br>
		<input type="submit" value="변경">
		<c:if test="${!empty loginUserInfo }">
			<button type="button" onclick="location.href='myPage';">돌아가기</button>
		</c:if>
		<c:if test="${empty loginUserInfo }">
			<button type="button" onclick="location.href='/main';">돌아가기</button>
		</c:if>
	</form:form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);

	function checkPwd() {
		 var p = document.getElementById('pwd').value;
		 var c = document.getElementById('check').value;
		 
		 if(p.length<8) {
			 document.getElementById('length').innerHTML='비밀번호의 길이는 8자 이상이어야 합니다.'
			 document.getElementById('length').style.color='red';
			 document.getElementById('same').innerHTML='';
			 return false;
		 } else {
			 document.getElementById('length').innerHTML='';
			 if(p != c) {
				 document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다.';
				 document.getElementById('same').style.color='red';
				 return false;
			 } else {
				 document.getElementById('same').innerHTML='비밀번호가 일치합니다.';
				 document.getElementById('same').style.color='green';
				 return true;
			 }
		 }
	}
</script>
</html>
<!-- 아까난 오류 Bean 설정 제대로 안해서 난 오류임 -->