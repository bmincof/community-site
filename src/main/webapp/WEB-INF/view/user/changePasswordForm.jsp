<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원 비밀번호 변경</title>
</head>
<body>
	<form action="../changePasswordPro/${id }" method="post">
		새로운 비밀번호 : <input type="text" name="newPassword"><br>
		비밀번호 확인 : <input type="text" name="confirmNewPassword"><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>
<!-- 아까난 오류 Bean 설정 제대로 안해서 난 오류임 -->