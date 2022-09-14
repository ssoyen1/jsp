<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>Test1.jsp</h1>
	<!-- ctrl + shift + f : 코드정렬 -->
	<!-- tap 오른쪽이동, shift  + tap 왼쪽으로 이동 -->
		
	<h2> JSP구성요소 </h2>
	
	
	<H3> JSP 지시어 (Directive) p150~ </H3>
	<%-- <% page, include, taglib %> --%>
	
	
	<h3> JSP 선언문 (Declaration) p160~ </h3>
	<%-- <%!   멤버 변수선언, 메서드선언   %> --%>
	
	
	<%!
		// 전역 변수 선언 (멤버변수)
		int age = 100;
// 		out.println(age);  선언가능, 호출(사용불가능)
	%>
		
		
		
	<h3> JSP 스크립틀릿(Scriptlet)</h3>
	<%-- <%    지역 변수 선언    %> --%>
	
	<%!
		// 지역 변수 선언
		String name;
// 		out.println(age);
	
	%>
	
	<h3> JSP 표현식 (Expression) </h3>표
	<!-- JSP 코드로 작성된 데이터를 화면에 출력 -->
	<%-- <%=값 %> --%>
	
	
	<%=age %>
	
	<!-- 스크립틀릿에 지역변수로 내 전화번호를 저장, 표현식을 사용해서 출력 -->
	
	
	<h4> 전화번호 : <%=tel %> </h4>
	
	<%!
		String tel = "010-1234-1234";	
	%>
		
</body>
</html>