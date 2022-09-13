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
			<select name="type">
				<option value="1">자유</option>
				<option value="2">질문</option>
				<option value="3">토론</option>
				<option value="4">정보 공유</option>
			</select>
			글 제목 : <input type="text" name="title">
		</div>
		<div>
			글 내용 : <textarea name="content"></textarea>
		</div>
		<input type="submit" value="작성">
		<button type="button" onclick="location.href='list';">돌아가기</button>
	</form>
</body>
</html>