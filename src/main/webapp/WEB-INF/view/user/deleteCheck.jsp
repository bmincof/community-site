<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
	<h2>회원탈퇴</h2>
	 작성한 게시글, 댓글의 내용을 제외환 모든 회원 정보가 사라집니다. 정말 탈퇴하시겠습니까?
	<form action="/user/deleteDo" method="post">
	<input type="checkbox" name="check" value="true"> 확인 <br>
	<input type="submit" value="탈퇴">
	</form>
</body>
</html>