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
		글번호 : ${detail.boardId } 
		작성자 : ${detail.writerName } 
		작성일 : ${detail.writtenDate }
		조회수 : ${detail.views }
	</div>
	<div>
		제목 : ${detail.title }
	</div>
	<div>
		내용 : ${detail.content }
	</div>
	<div>
		<button type="button">추천 ${detail.upVotes }</button>
		<button type="button">비추천 ${detail.downVotes }</button>
	</div>
	<div>
		<c:if test="${loginUserInfo.userId eq detail.writer }">
			글 수정
		</c:if>
	</div>
</body>
</html>