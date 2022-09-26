<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>paramPro.jsp</h1>

		<% 
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
// 			String menu1 = request.getParameter("menu1");
// 			String menu2 = request.getParameter("menu2"); // 앞에서 여러개를 가져오므로
			String[] menu = request.getParameterValues("menu"); // 배열로 받는다.
			
		%>\
		[jsp]
		이름 : <%=name %><br>
		
		[el]
<%-- 	이름(el표현식-jsp코드값 사용x) : ${name }<br> <!-- 이대로하면 출력이 안됨. 위의 정보를 못가져옴. jsp코드값을 바로다이렉트로 출력할 수 없음 -->
													훨씬 더 간결하게, static 메서드도 출력할 수 있음 --%>
													<!--https://creamilk88.tistory.com/117-->
		이름 : ${param.name }<br> <!-- 한글처리는 jsp든 el이든 무조건 해야함! -->
		* el 표현식은 null값을 공백문자로 표시
		
		<hr>
		
		<h3>오늘의 추천메뉴</h3>
		[jsp]<br>
		메뉴1 : <%=menu[0] %><br>
		메뉴2 : <%=menu[1] %><br>

				
		<h3>오늘의 추천메뉴</h3>
		[el]<br>
<%-- 	메뉴1 : ${paramValues.menu}<br> <!-- [Ljava.lang.String;@1957e14f = 배열의 캐시값 --> --%>
		메뉴1 : ${paramValues.menu[0]}<br>
		메뉴2 : ${paramValues.menu[1]}<br>
		
		
		<hr>
		<%
			String[] hobbys = request.getParameterValues("hobby");
		
		%>
		
		<h3> 취미 정보</h3>
<!-- 		[jsp]<br> -->
<%-- 		취미1 : <%=hobbys[0] %><br> --%>
<%-- 		취미2 : <%=hobbys[1] %><br> --%>
<%-- 		취미3 : <%=hobbys[2] %><br>  <!--  배열의 데이터를 직접 출력하는것 좋지않은 거임!!!!! --> --%>
		
		[jsp]
		<%
		if(hobbys != null) {
			for (int i=0; i<hobbys.length; i++) {
				%>
					취미<%= i+1 %> : <%=hobbys[i] %><br>
				<%
			}
		%>
		
		
		<%
			for(String h:hobbys){ // 오른쪽 배열에서 하나를 꺼내서 왼쪽에 넣겠다 . 향상된 for문
								  // (왼)타입 변수명 (오른)배열이름
				%>
				 취미 : <%=h %><br>
				<%
			}
		}
		%>
		
		
		
		[el]<br>
		취미1 : ${paramValues.hobby[0] }<br>
		취미2 : ${paramValues.hobby[1] }<br>
		취미3 : ${paramValues.hobby[2] }<br>
		
		
		
		
		
</body>
</html>