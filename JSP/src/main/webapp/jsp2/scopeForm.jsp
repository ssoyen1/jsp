<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>scopeForm.jsp</h1>
	
	<h2> 영역(scope) : 내장객체 중에서 데이터를 공유하는 객체의 범위</h2>
	<h2> 속성(attribute) : 영역에서 공유되는 데이터값</h2>


	<h3> 영역 </h3>
	page  <  request  <  session  <  application <br>

	<h3> 영역객체 </h3>
	pageContext  <  request  <  session   <  application <br>
	
	
	* page-pageContext : 해당페이지가 클라이언트에 서비스를 제공할때만 사용<br>
						<!--  = 이 페이지 사용할 때만 / 책상 -->
	* request-request : 클라이언트의 요청이 처리되는 상황에만 사용<!-- 옆자리 -->
	* session-session : 세션이 유지되는 동안에만 사용(브라우저당 1개의 영역) <br><!-- 정수기 -->
	* application-application : 웹 애플리케이션이 실행되는 동안 사용<br><!-- 건물엘베 -->
 						<!-- 	영역의 크기가 점점커짐 -->
	
	<hr>
	
	<form action="scopePro.jsp" method="get">
		아이디 : <input type="text" name="id"><br>
		<input type="submit" value="전송">
	</form>
	
	

</body>
</html>