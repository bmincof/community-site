<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<form action="writePro" method="post">
		<div>
			글 제목 : <input type="text" name="title">
		</div>
		<div>
			글 내용 : <textarea name="content"></textarea>
		</div>
		<input type="submit" value="작성">
	</form>
</body>
</html>