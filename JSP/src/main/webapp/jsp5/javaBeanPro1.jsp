<%@page import="com.itwillbs.javabean.JavaBean1"%>
<%@page import="java.beans.JavaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>javaBeanPro1.jsp</h1>
		
				  <%
					String id = request.getParameter("id");
					// 자바빈 사용해서 정보 저장/처리
					
					// 자바빈 객체 생성
					JavaBean1 jb = new JavaBean1();
					
					// 자바빈에 객체정보를 저장
					jb.setId(id);
			
				  %>
				
					아이디 : <%=id%><br>
		  			아이디2 : <%=jb.getId()%><br> 
					
					<!--아이디 저장해서 겟파라미터로 가져오는 것 = 자바빈에 저장해서 가져오는 것  동일한 값으로 나옴 !!! -->

					<hr>
					<h2> 액션태그 - 자바빈 객체 </h2>
					
				<!--  자바빈 객체 생성(JSP코드)  -->
					<%-- <jsp:useBean id="객체명" class="객체위치"/> --%>
					<jsp:useBean id="jb2" class="com.itwillbs.javabean.JavaBean1"/>
					
					
					
					
				<!--  자바빈에 객체정보를 저장 -->
					<%-- <jsp:setProperty property="객체의 변수명" name="객체명" param="파라미터"/> --%>
					<%-- <jsp:setProperty property="id" name="jb2" param="id"/>  --%>
													<!--  위의 아이디가 네임으로 바뀐 것일 뿐 -->
					
					
					
				<!--  property, param값이 동일한 경우 param값을 생략가능 -->
					<jsp:setProperty property="id" name="id2"/>

				<!--  전달되는 모든 파라메터 값을 모든 변수에 저장 가능 -->
					<%-- <jsp:setProperty property="*" name="id2"/> --%>
					
					
					
					
				<!--  자바빈에 저장된 객체 정보 출력 -->
					<jsp:getProperty property="" name="jb2"/>
					
					
</body>
</html>