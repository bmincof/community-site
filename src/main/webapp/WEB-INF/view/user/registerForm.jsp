<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<form:form action="registerDo" onsubmit="return checkPwd()" modelAttribute="userRegisterRequest">
		이메일 : <input type="text" name="email" value="${userRegisterRequest.email }">
		<form:errors path="email" /><br>
		
		비밀번호 : <input type="password" name="password" id="pwd" onchange="checkPwd()"/>
		<span id="length"></span><form:errors path="password" /><br>
		
		비밀번호 확인 : <input type="password" name="confirmPassword" id="check" onchange="checkPwd()">
		<span id="same"></span><br>
		
		이름 : <input type="text" name="name" value="${userRegisterRequest.name }">
		<form:errors path="name" /><br>
		
		닉네임 : <input type="text" name="nickname" value="${userRegisterRequest.nickname }">
		<form:errors path="nickname" /><br>
		
		전화번호 : <input type="text" name="phoneNumber" value="${userRegisterRequest.phoneNumber }">
		<form:errors path="phoneNumber" /><br>
		<input type="submit" value="회원가입">
		<button type="button" onclick="location.href='/main';">돌아가기</button>
	</form:form>
</body>
<script>
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