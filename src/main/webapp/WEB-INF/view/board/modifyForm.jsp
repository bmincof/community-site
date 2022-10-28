<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${modifyRequest.title }</title>
</head>
<body>
	<form action="modifyDo" method="post">
		<div>
			<input type="hidden" name="boardId" value="${modifyRequest.boardId }">
			<input type="hidden" name="title" value="${modifyRequest.title }">
			<input type="hidden" name="content" value="${modifyRequest.content }">
			<select name="type">
				<option value="0" <c:if test ="${modifyRequest.type eq '0'}">selected="selected"</c:if>>자유</option>
				<option value="1" <c:if test ="${modifyRequest.type eq '1'}">selected="selected"</c:if>>질문</option>
				<option value="2" <c:if test ="${modifyRequest.type eq '2'}">selected="selected"</c:if>>토론</option>
				<option value="3" <c:if test ="${modifyRequest.type eq '3'}">selected="selected"</c:if>>정보 공유</option>
			</select>
			제목 : <input type="text" name="newTitle" value="${modifyRequest.title }" style="width:600px" <c:if test="${loginUserInfo.isAdmin }">readOnly</c:if>>
			내용 : <textarea name="newContent" rows=10 style="width:50%; resize:none" <c:if test="${loginUserInfo.isAdmin }">readOnly</c:if>>${modifyRequest.content }</textarea>
		</div>
		<button>글 수정</button>
		<button type="button" onclick="location.href='/board/detail/${modifyRequest.boardId}';">돌아가기</button>
	</form>
</body>
<script>
	var message = '<c:out value ="${msg}" />';
	if(message != '')	alert(message);
</script>
</html>