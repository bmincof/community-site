<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
	
	 모든 정보가 사라집니다. 정말 탈퇴하겠습니까?
	<form action="../withdrawPro/${id }" method="post">
	<input type="checkbox" name="check" value="true"> 탈퇴 <br>
	<input type="submit" value="탈퇴">
	</form>
</body>
</html>