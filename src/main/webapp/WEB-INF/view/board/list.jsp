<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>
	<h2>전체 게시글</h2>
	<div>
		<table border="1" width="80%">
			<tr>
				<th>글 번호</th><th>제목</th>
				<th>작성자</th><th>조회수</th><th>작성일</th>
				<th>추천</th>
			</tr>
			<c:forEach var="board" items="${notices }">
			<tr>
				<td>공지</td>
				<td><a href="<c:url value="/board/detail/${board.boardId}" />" ><b>${board.title }</b></a></td>
				<td><b>${board.writerName }</b></td>
				<td>${board.views }</td>
				<td>${board.writtenDate }</td>
				<td>${board.votes.up }</td>
			</tr>
			</c:forEach>
			<c:forEach var="board" items="${hotPosts }">
			<tr bgcolor="#FFE4E1">
				<td>${board.boardId }</td>
				<td><a href="<c:url value="/board/detail/${board.boardId}" />" ><span style="color:red;">[인기]</span> ${board.title }</a></td>
				<td>${board.writerName }</td>
				<td>${board.views }</td>
				<td>${board.writtenDate }</td>
				<td>${board.votes.up }</td>
			</tr>
			</c:forEach>
			<c:forEach var="board" items="${lists }">
			<tr>
				<td>${board.boardId }</td>
				<td><a href="<c:url value="/board/detail/${board.boardId}" />" >${board.title }</a></td>
				<td>${board.writerName }</td>
				<td>${board.views }</td>
				<td>${board.writtenDate }</td>
				<td>${board.votes.up }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<form action="" method="get">
		<div>
			<c:if test="${not pageVo.isStartPage }">
				<button type="submit" name="page" value="1">&lt;&lt;</button>
				<button type="submit" name="page" value="${pageVo.startPage-1 }">&lt;이전</button>
			</c:if>
			<c:forEach var="p" begin="${pageVo.startPage }" end="${pageVo.endPage }" step="1">
				<input type="submit" name="page" value="${p }">
			</c:forEach>
			<c:if test="${not pageVo.isLastPage }">
				<button type="submit" name="page" value="${pageVo.endPage+1 }">다음&gt;</button>
				<button type="submit" name="page" value="${pageVo.totPage }">&gt;&gt;</button>
			</c:if>
			<input type="hidden" name="field" value="${searchVo.field }">
			<input type="hidden" name="keyword" value="${searchVo.keyword }">
		</div>
	</form>
	<div>
		<c:if test="${loginUserInfo.isAdmin }">
			<a href="<c:url value="/board/write" />">공지 작성</a>
		</c:if>
		<c:if test="${!loginUserInfo.isAdmin or empty loginUserInfo}">
			<a href="<c:url value="/board/write" />" >글 작성</a><br>
		</c:if>
	</div>
	<form action="" method="get">
		<input type="hidden" name="page" value="1">
		<select name="field">
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
			<option value="BOARDID">글번호</option>
			<option value="NICKNAME">작성자</option>
			<option value="WRITTEN_DATE">작성일</option>
		</select>
		<input type="text" name="keyword">
		<button type="submit">검색</button>
	</form>
</body>
</html>