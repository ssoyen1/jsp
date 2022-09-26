<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 0926 TestServlet2에서 보낸데이터 -->
		<h1>Arrays.jsp</h1>
		
				<%
					// TestServlet2에서 받아온 request.setAttribute("foods",foods); 가 실행되므로 
		// 			String[] foods = request.getAttribute("foods"); // 에러발생! Type mismatch: cannot convert from Object to String[]
					String[] foods = (String[])request.getAttribute("foods"); 
				
				%>
				
				[jsp]<br>
				
				
				<%
					for(int i=0;i<foods.length;i++){
						%>
							음식 : <%=foods[i] %><br>
						
						<%
					}
				%>
				
				[el]: JSP 반복문 코드를 사용 불가 => JSTL 코드 사용<br>
<%-- 				음식 : ${requestScope.foods } <!-- 음식 : [Ljava.lang.String;@c45866  = 스트링배열안에 객체가 존재한다.--> --%>
				음식 : ${requestScope.foods[0] }<br>
				음식 : ${requestScope.foods[1] }<br>
				음식 : ${foods[2] }<br> <!--  영역객체 지워도 됨 -->
				음식 : ${requestScope.foods[3] }<br>
				음식 : ${requestScope.foods[4] }<br>
				
				
				
		<%-- 		<%
				// jsp도 되는지 확인해보기
				for(int i=0;i<foods.length;i++) {
					%>
						${requestScope.foods[i] }<br>
					<% 
				}
							// 주석걸때 jsp주석을 걸어야 실행이안됨(<%%>) [ctrl+shift+/ ] / <!----!> 은 실행될위험이 있음.
				--%>
				
				
				
				<hr>
				[JSP] <br>
				<%
					List sports = (List) request.getAttribute("sports"); // 업캐스팅했으니 다운캐스팅 해야함
				
					for(int i=0; i<sports.size();i++) {
						%>
							<%=sports.get(i) %><br>
						<% 
					}
				
					for(Object s: sports) { // 이렇게 쓰는걸 더 권장 , sports가 object므로 String이아닌 Object로 받아씀
						%>
							<%=s %>  <!-- ??????????????????????? -->
						<%
					} 
					
					
				%>
				
				<br>
				[el]
				${requestScope.sports[0] }
				${requestScope.sports[1] }
				${requestScope.sports[2] }
				${sports[3] }
				${sports[4] }
				
				
				
				
				
				
				
				
</body>
</html>