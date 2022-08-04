<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="registerPro" method="post">
		이메일 : <input type="text" name="email"><br>
		비밀번호 : <input type="text" name="password"><br>
		비밀번호 확인 : <input type="text" name="confirmPassword"><br>
		이름 : <input type="text" name="name"><br>
		닉네임 : <input type="text" name="nickname"><br>
		전화번호 : <input type="text" name="phoneNumber"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>