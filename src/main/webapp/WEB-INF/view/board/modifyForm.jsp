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
	<form action="do" method="post">
		<input hidden name="boardId" value="${detail.boardId }">
		<div>
			<select name="type">
				<option value="1" <c:if test ="${detail.type eq '1'}">selected="selected"</c:if>>자유</option>
				<option value="2" <c:if test ="${detail.type eq '2'}">selected="selected"</c:if>>질문</option>
				<option value="3" <c:if test ="${detail.type eq '3'}">selected="selected"</c:if>>토론</option>
				<option value="4" <c:if test ="${detail.type eq '4'}">selected="selected"</c:if>>정보 공유</option>
			</select>
			제목 : <input type="text" name="title" value="${detail.title }">
		</div>
		<div>
			내용 : <textarea name="content">${detail.content }</textarea>
		</div>
		<button>글 수정</button>
		<button type="button" onclick="location.href='../detail/${boardId}';">돌아가기</button>
	</form>
</body>
</html>