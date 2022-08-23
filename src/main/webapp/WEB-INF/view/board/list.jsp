<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>
	<table border="1" width="80%">
		<tr>
			<th>아이디</th><th>제목</th><th>내용</th>
			<th>작성자</th><th>작성일</th><th>조회수</th>
			<th>추천</th><th>비추천</th>
		</tr>
		<c:forEach var="board" items="${boards }">
		<tr>
			<td>${board.boardId }</td>
			<td>${board.title }</td>
			<td>${board.content }</td>
			<td>${board.writer }</td>
			<td>${board.writtenDate }</td>
			<td>${board.views }</td>
			<td>${board.upVotes }</td>
			<td>${board.downVotes }</td>
		</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="/board/write" />" >글 작성</a><br>
</body>
</html>