<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>logout.jsp</h1>
		
		<%
			// 로그아웃 => 세션정보 초기화
			session.invalidate();
		%>
		
		<script type="text/javascript">
			alert('회원님의 정보가 안전하게 로그아웃 되었습니다.');
			location.href='main.jsp'; //다시 main페이지로 보내는 이유. 로그아웃을 체크하는 로직이 있음. main 으로 돌렸을 경우 정상적이라면 로그인페이지로 갈것.
									  // but 메인페이지에 머물러있다면  로직이 제대로 안되어있는거라 문제가 있는 것..?
		</script>
</body>
</html>