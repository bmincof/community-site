<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.title }</title>
</head>
<style>
	form{
		display: inline;
	}
</style>
<body>
	<div>
		글번호 : ${detail.boardId } &nbsp; 
		작성자 : ${detail.writerName } &nbsp; 
		작성일 : ${detail.writtenDate } &nbsp;
		조회수 : ${detail.views }<br>
		
		제목 : ${detail.title }<br>
	
		내용 : ${detail.content }
	</div><br>
	
	<div>
		<button type="button" onclick="location.href='../like/${detail.boardId}';">추천 ${detail.votes.up }</button>
		<button type="button" onclick="location.href='../hate/${detail.boardId}';">비추천 ${detail.votes.down }</button>
	</div><br>
	
	<div>
	<c:if test="${!empty loginUserInfo }">
		<c:if test="${loginUserInfo.userId eq detail.writer or loginUserInfo.isAdmin}">
			<form action="/board/modify" method="post">
				<input type="hidden" name="boardId" value="${detail.boardId }">
				<input type="hidden" name="title" value="${detail.title }">
				<input type="hidden" name="content" value="${detail.content }">
				<input type="hidden" name="type" value="${detail.type }">
				<input type="submit" value="글 수정">
			</form>
			<form action="/board/delete" onsubmit="return recheck();" method="post">
				<input type="hidden" name="boardId" value="${detail.boardId }">
				<input type="hidden" name="boardType" value="${detail.type }">
				<input type="submit" value="글 삭제">
			</form>
		</c:if>
	</c:if>
		<button type="button" onclick="location.href='../list/${detail.type}';">글 목록</button>
	</div><br><hr>
	
	<div>
		<c:forEach var="replyP" items="${detail.replies }">
			작성자 <b>${replyP.writerName }</b>
			<c:if test="${!empty loginUserInfo }">
				<button onclick="reply('<c:out value="${replyP.replyId }" />','<c:out value="${replyP.writerName }" />');">답글 작성</button> 
				<c:if test="${loginUserInfo.userId eq replyP.writer and !replyP.isDeleted }">
					<button onclick="location.href='/board/reply/modify?replyId=${replyP.replyId}';">수정</button>
					<button onclick="location.href='/board/reply/delete?replyId=${replyP.replyId}&boardId=${detail.boardId }';">삭제</button>
				</c:if>
			</c:if><br>
			<c:choose>
				<c:when test="${!replyP.isDeleted }">${replyP.content }</c:when>
				<c:otherwise>삭제된 댓글입니다.</c:otherwise>
			</c:choose><br>
			${replyP.writtenDate }<hr>
			<c:forEach var="replyC" items="${replyP.childReplies }">
				&nbsp;&nbsp;&nbsp;작성자 <b>${replyC.writerName }</b> 
				<c:if test="${loginUserInfo.userId eq replyC.writer and !replyC.isDeleted }">
					<button onclick="location.href='/board/reply/modify?replyId=${replyC.replyId}';">수정</button>
					<button onclick="location.href='/board/reply/delete?replyId=${replyC.replyId}&boardId=${detail.boardId }';">삭제</button>
				</c:if><br>
				<c:choose>
					<c:when test="${!replyC.isDeleted }">&nbsp;&nbsp;&nbsp;${replyC.content }</c:when>
					<c:otherwise>&nbsp;&nbsp;&nbsp;삭제된 댓글입니다.</c:otherwise>
				</c:choose><br>
				&nbsp;&nbsp;&nbsp;${replyC.writtenDate }<hr>
			</c:forEach>
		</c:forEach>
	</div>
	
	<div>	
		<button onclick="comment();">댓글 작성</button>
		<form id="cf" action="../reply/add" method="post" style="display:none">
			<input type="hidden" name="boardId" value="${detail.boardId }">
			<input id="ref" type="hidden" name="ref" value="">
			<span id="guide"></span><br>
			<input id="textbox" type="text" name="content" style="width:70%" placeholder="내용을 입력하세요.">
			<input type="submit" value="등록">
		</form>
	</div>
</body>
<script>
	function recheck() {
		return result = confirm("정말 삭제하시겠습니까?");
	}
	
	function reply(rId, name) {
		const to = '<b>' + name + '</b>님에게 답글 작성';
		
		document.getElementById("cf").style.display="inline";		
		document.getElementById("ref").value = rId;
		document.getElementById("guide").innerHTML = to;
	}
	
	function comment() {
		const to = '댓글 작성';
		
		document.getElementById("cf").style.display="inline";
		document.getElementById("ref").value = 0;
		document.getElementById("guide").innerHTML = to;
	}
</script>
</html>