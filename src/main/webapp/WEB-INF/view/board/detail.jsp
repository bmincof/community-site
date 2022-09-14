<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.title }</title>
</head>
<body>
	<div>
		글번호 : ${detail.boardId } &nbsp; 
		작성자 : ${detail.writerName } &nbsp; 
		작성일 : ${detail.writtenDate } &nbsp;
		조회수 : ${detail.views }
	</div><br>
	<div>
		제목 : ${detail.title }
	</div><br>
	<div>
		내용 : ${detail.content }
	</div><br>
	<div>
		<button type="button" onclick="location.href='../like/${detail.boardId}';">추천 ${detail.votes.up }</button>
		<button type="button" onclick="location.href='../hate/${detail.boardId}';">비추천 ${detail.votes.down }</button>
	</div><br>
	<div>
		<c:if test="${loginUserInfo.userId eq detail.writer }">
			<button type="button" onclick="location.href='../modify/${detail.boardId}';">글 수정</button>
			<button type="button" onclick="location.href='delete/${detail.boardId}';">글 삭제</button>
		</c:if>
		<button type="button" onclick="location.href='../list/${detail.type}';">글 목록</button>
	</div>
</body>
</html>