<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionLoginForm.jsp</h1>
	
<!-- 	sessionLoginForm.jsp
		1. 아이디, 비밀번호 입력 입력창
		2. 아이디, 비밀번호 입력
		(3. 데이터 유효성 체크) 
		
		sessionLoginPro.jsp
		3. 아이디, 비밀번호 일치여부 체크 
		3-1. 아이디 O
			  비밀번호 O => 본인 로그인
			  비밀번호 X => 비밀번호 오류
		3-2. 아이디 X => 비회원
-->
	
		<form action="sessionLoginPro.jsp" method="post">
			아이디 : <input type="text" name="id" ><br>
			비밀번호 : <input type="text" name="pw" ><br>
			
			<input type= "submit" value="로그인">
		</form>
	
	
	
</body>
</html>