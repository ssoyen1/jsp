<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionTest.jsp</h1>
	<h2> 클라이언트 </h2>
	<!--  세션 가장 많이 쓰이는 곳 : 로그인
	항상 유지되어야하는 정보들을 사용할 때 사용! -->

	<%
		//변수 만들기
		String id = (String)session.getAttribute("ID");
		String name = (String)session.getAttribute("name");
	%>
	<h3> 정보 : <%=id %></h3>
	<h3> 정보 : <%=name %></h3>
<!-- 변수, 계산식, 메소드 호출 결과를 문자열 형태로 출력 -->

	<!--  버튼 클릭시 sessiongSet.jsp페이지 이동, 세션 정보 생성 -->
<!-- 	<form action="" method=""></form> submit 이 아니므로 폼액션으로 보낼 수 없음 \
		onclick 으로 써야함-->
	
	<input type= "button" value="세션값 생성" onclick=" location.href='sessionSet.jsp' "> 
	<input type= "button" value="세션값 삭제" onclick=" location.href='sessionDel.jsp' "> 
	<input type= "button" value="세션값 초기화" onclick=" location.href='sessionInv.jsp' "> 
	



</body>
</html>