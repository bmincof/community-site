<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<form action="writeDo" method="post">
		<c:if test="${!loginUserInfo.isAdmin }">
			<h2>게시글 작성</h2>
			<input type="hidden" name="isNotice" value="false">
		</c:if>
		<c:if test="${loginUserInfo.isAdmin }">
			<h2>공지 작성</h2>
			<input type="hidden" name="isNotice" value="true">
		</c:if>
			<div>
				글 제목 : <input type="text" name="title" style="width:600px">
				<select name="type">
					<option value="0">자유</option>
					<option value="1">질문</option>
					<option value="2">토론</option>
					<option value="3">정보 공유</option>
				</select>
			</div><br>
			<div>
				글 내용 :<br>
				<textarea name="content" rows=10 style="width:50%; resize:none"></textarea>
			</div>
			<input type="submit" value="작성">
			<button type="button" onclick="location.href='/board/list';">돌아가기</button>
	</form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>