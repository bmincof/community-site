<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p> loginReq
	아이디 ${loginReq.email }
	비밀번호 ${loginReq.password }


<p> loginUserInfo
	아이디 ${loginUserInfo.userId }
	이메일 ${loginUserInfo.email }
	닉네임 ${loginUserInfo.nickname }
</body>
</html>