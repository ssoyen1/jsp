<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<h1>core_foreach.jsp</h1>
			
<%-- 		<c:forEach begin="초기식" end="끝값" step="증감"> --%>
<!-- 				반복 -->
<%-- 		</c:forEach> --%>
			
			<h3> "안녕" X 5번 출력하기</h3>
			<c:forEach begin="1" end="5" step="1">
					안녕!<br>
			</c:forEach>
			
			<hr> 
			[jsp]<br>
			<%
			for(int i=1; i<=5; i++){
				out.println("안녕!<br>");
			}
			%>
			
			
			<hr>
			<b>1~10까지 숫자를 가로로 출력하기</b><br>
			<c:forEach var="i" begin="1" end="10" step="1"> <!-- 반복문작성할때 필요한게 i에 들어가게 됨?-->
				${i }
			</c:forEach><br>
			
			<b>1~10까지 숫자(홀수만)를 가로 출력하기</b><br>
			<c:forEach var="i" begin="1" end="10" step="2">
				${i }
			</c:forEach>
			
			
			<b>배열 출력하기</b><br>
			<!-- JSTLServlet 에서 arraylist 가져와 출력 -->
            <!-- request.setAttribute("memberList", memberList); -->
			<c:forEach var="member" items="${memberList }" >
								<!-- items : 배열안의 정보들을 가져오는 것? 
									 배열의 갯수만큼 알아서 왼쪽 member에 저장될 것-->
					${member.id }<br>
					${member.pw }<br>
					${member.name }<br>
					${member.gender }<br>
					${member.email }<br>
					------------------<br>
					
			</c:forEach>
			
			
			
</body>
</html>