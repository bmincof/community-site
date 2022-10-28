<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
	<h2>댓글 수정</h2>
	수정할 댓글<br><br>
	
	<form action="modifyDo" method="post">
		<div>
			작성자 <b>${reply.writerName }</b><br>
			${reply.content }<br>
			${reply.writtenDate }
		</div><hr>
		
		댓글 수정<br>
		<input type="hidden" name="boardId" value="${reply.boardId }">
		<input type="hidden" name="replyId" value="${reply.replyId }">
		<input type="text" name="content" placeholder="수정할 내용을 입력하세요.">
		<button type="submit">수정</button>
	</form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>