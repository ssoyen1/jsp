<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionInv.jsp</h1>
	
	<h2> 서버 (세션값 초기화) </h2>
	<!--  일반적으로 삭제보다 초기화 더 많이 사용
		삭제(일부데이터 남아있는 것 -> 서버에 부담 )
		초기화(ex. 은행 세션만료 했을때 뜨는것.) -->
	
	<%
		// 세션값 초기화
		session.invalidate();
	%>
	
	<script type= "text/javascript">
		alert("세션값 초기화 완료");
		location.href="sessionTest.jsp";
	</script>
	
	
	
	
	
</body>
</html>