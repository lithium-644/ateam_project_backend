<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>임시 홈페이지</title>
</head>
<body>
	<p>
		※ 임시 홈페이지 입니다. (로그인 확인용)		
	</p>
	<div>
		현재 로그인된 아이디: <strong>${sessionScope._LOGIN_USER_.email}</strong>
	</div>
	<div>
		현재 로그인된 이름: <strong>${sessionScope._LOGIN_USER_.nickname}</strong>
	</div>
</body>
</html>