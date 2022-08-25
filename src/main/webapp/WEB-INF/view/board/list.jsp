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
			<th>글 번호</th><th>제목</th>
			<th>작성자</th><th>조회수</th><th>작성일</th>
			<th>추천</th>
		</tr>
		<c:forEach var="board" items="${lists }">
		<tr>
			<td>${board.boardId }</td>
			<td><a href="<c:url value="/board/${board.boardId}" />" >${board.title }</a></td>
			<td>${board.writerName }</td>
			<td>${board.views }</td>
			<td>${board.writtenDate }</td>
			<td>${board.upVotes }</td>
		</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="/board/write" />" >글 작성</a><br>
</body>
</html>