<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>이메일 찾기</title>
</head>
<body>
	<form action="findEmail" method="post">
		회원정보에 등록된 이름과 휴대폰번호를 입력해주세요.<br>
		이름 : <input type="text" name="name"><br>
		휴대폰 번호 : <input type="text" name="phoneNumber"> (-를 포함하여 입력해주세요.)<br>
		<input type="submit" value="찾기">
	</form>
</body>
</html>