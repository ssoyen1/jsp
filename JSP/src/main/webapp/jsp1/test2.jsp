<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp1/test2.jsp</title>
</head>
<body>
	<h1>Test2.jsp</h1>
	
	<h2> 객체(Object) </h2>
	 => 내 눈 앞에 보이는 모든 대상
	 
	 객체 추상화 
	 
	 객체 - 1) 속성 : 객체의 특징을 표현하기위한 정보
	 			 =>   변수표현
	 		2) 동작 : 객체가 수행하는 모든 행동
				 =>	  함수(메서드) 표현
				 
				 
	<h2> 내장 객체 (p.177~)</h2>
	=> JSP 페이지 -> 서블릿 클래스 필요한 정보(객체)를 미리 생성
	
	
	<h3>javax.servlet 패키지</h3>
		1) request : 클라이언트의 HTTP요청 정보를 저장한 객체 <br>
		2) response : HTTP요청에 해당하는 응답정보를 저장한 객체<br>
		3) session : 클라이언트의 세션정보를 저장객체<br>
		4) pageContext : 페이지 실행에 필요한 컨텍스트정보 저장한 객체<br>
<!-- 							Context 대신 Project라고 생각하기 -->
		5) out 응답페이지를 전송하기위한 출력스트림 객체<br>
		6) application : 동일한 애플리케이션의 컨텍스트 정보를 저장한 객체:<br>
		7) config : 페이지에 필요한 서블릿 설정정보(초기화)<br>
		8) page : 해당 페이지의 서블릿 객체<br>
		


	<h3>java.lang 패키지</h3>
		9) exception : 예외 처리 객체 <br> 
<!-- 		코드상에서 예측하기 어려운 상황 -->


	<hr>
	
	
	<h3>request 객체</h3>
<!-- 웬만한 정보는 다 리퀘스트가 들고있다 -->
	
	
	서버 도메인명 :  <%=request.getServerName() %> <br>
	<!-- 	서버 도메인명 : localhost -->
	URL : <%=request.getRequestURL() %> <br>
	<!-- 	URL : http://localhost:8088/JSP/jsp1/test2.jsp -->
	URI : <%=request.getRequestURI() %> <br> <!-- URL에서 프로토콜,호스트네임,포트주소 뺀 것 -->
	<!-- 	URI : /JSP/jsp1/test2.jsp -->
	클라이언트 IP주소 : <%=request.getRemoteAddr() %>
	프로젝트의 물리적 경로 : <%=request.getRealPath("/") %>
	<!-- 	클라이언트 IP주소 : 0:0:0:0:0:0:0:1 프로젝트의 물리적 경로 : D:\workspace_jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSP\ -->
	
	
	
	<h3>response 객체</h3>
	<%
		//response.setHeader(arg0, arg1);
		//response.addCookie(cooki);
 		//response.sendRedirect("http://www.naver.com"); //프로토콜없이 하면
		//http://localhost:8088/JSP/jsp1/www.naver.com 이렇게 됨
		//response.setContentType("text/html; charset=UTF-8");
		
		//response.addHeader("Refresh", "5"); // 5번 새로고침
		//response.addHeader("Refresh", "3; url=test1.jsp"); //3초뒤 test.jsp로 이동


	%>



	<h3> session 객체</h3>
	
	세션 ID 값 : <%=session.getId() %> <br>
	<!-- 브라우저당 하나씩 생김/ 크롬에서 열었던 것을 엣지에서 열면 세션값다름 -->
	<!--  브라우저 창에서 F12번 누르면 개발자도구? 어플리케이션-쿠키  -->
	<!-- 세션 ID 값 : 99B259C120AC68DEB0A74957AFB0ADC0 -->
	세션 생성시간 : <%=session.getCreationTime()%> <br>
	<!-- 세션 생성시간 : 1659938965354 / 밀리세터 -->
	<%=new Date(session.getCreationTime())%> <br><!-- java.util~ 선택-->
	<!-- 	Mon Aug 08 15:09:25 KST 2022 -->
	세션 최종접속시간 : <%=session.getLastAccessedTime() %> <br>
	<!-- 	세션 최종접속시간 : 1659939596233 -->
	세션 유지시간 : <%=session.getMaxInactiveInterval() %>s<br>
	<!-- 	세션 유지시간 : 1800s / 30분-->
	
	<%
		session.setMaxInactiveInterval(600);
	// get(가져오는 것) set(값 설정하는 것)만 다름
	%>
	세션 유지시간 : <%=session.getMaxInactiveInterval() %>s<br>
	
	<!-- 	slack 개발할 때 쓰는 카톡같은 것 -->
	
	
	
	
</body>
</html>