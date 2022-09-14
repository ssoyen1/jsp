<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>scopePro.jsp</h1>
	<%
		//영역객체 정보 저장 (파라미터가 아니므로 저장해줘야함? 아니면 null 로 나옴)
		request.setAttribute("id", "BUSAN");
	
		pageContext.setAttribute("p", "pageValue");
		request.setAttribute("r", "requestValue");
		session.setAttribute("s", "sessionValue");
		application.setAttribute("a", "applicationValue");
	
	%>
	
	
	
	
  <%
  String id = request.getParameter("id");
  %>

	<h3> 아이디 : <%=id %></h3>
	
	
	
 <% 
 	// 영역 객체 정보 가져오기
	// 	request.getAttribute("id"); 
 	String id2 = (String)request.getAttribute("id");

 %>
 	<h3>아이디: <%=id2 %></h3>
 	<hr>
 	
 	page : <%=pageContext.getAttribute("p") %> <br>
 	request : <%=request.getAttribute("r") %> <br>
 	session : <%=session.getAttribute("s") %> <br>
 	application : <%=application.getAttribute("a") %> <br>
 
 	<hr>
 	<hr>
 	<hr>
	
	
	
	
	<h2> 페이지 이동시 정보체크 </h2>
	<!--  파라미터, 어트리뷰트 -->
	
	
	
	
	
	<h3> HTML - a태그 </h3> 
	<h4> 사용가능 : 파라미터, session영역, application 영역</h4>
<!-- 	<a href=""> a태그 페이지 이동</a>  -->
	<a href="scopeAction.jsp?id=<%=id%>&pw=1234" > a태그 페이지 이동</a> 
		<!--  &pw1234 등등에 공백을 임의로 넣어주면 안됨/null 나옴 /주의 !-->
		<!-- 	//페이지 보내는 방법 = 새로고침 되는 것 = 이동할 곳이 없으면 자기자신 페이지를 호출하게 됨-->
		<!--  패스워드 추가 하는방법 & ~ 하면 됨 / 주소창에 뜸-->
		<!--  http://localhost:8088/JSP/jsp2/scopeAction.jsp?id=dd&pw=1234 -->
	a &nbsp; 											a
		<!-- 공백나타내기/html에서는 아무리 공간을 많이둬도 한칸만 뛰게됨 그래서 &nbsp; 쓰면 뜀 -->
		
		
		
		
		
	<h3> JavaScript - location.href </h3>
	<script type="text/javascript">
// 		alert("js-페이지 이동"); //창 뜸 
// 		location.href='scopeAction.jsp'; //다른페이지로 이동ㅇ
		location.href='scopeAction.jsp?id=<%=id%>&pw=1234'; 
// 		id~ 뒤에 붙이므로써 get방식으로 가능
	</script>




	* JSP페이지 실행순서
	JSP(JAVA) -> HTML -> JS
	=> JSP, JS 통한 페이지이동은 한가지 방식만 사용해야함.






	<h3> JSP - response.sendRedirect() </h3>
	<%
// 		System.out.println("JSP-페이지이동");
 		//response.sendRedirect("scopeAction.jsp");
// 		response.sendRedirect("scopeAction.jsp?id="+id+"&pw=1234");
		response.sendRedirect("scopeAction.jsp?id="+URLEncoder.encode(id)+"&pw=1234");
		//위랑 똑같이 하려고 해도 안됨. 화면출력이아니여서. 
	    //변수 저장되어 있으므로 연결자 사용해서 id 출력할 수 있음 .
	%>











	<h3> 액션태그 - forward </h3>
	<h4> 사용가능 : 파라미터, request영역, session영역, application 영역</h4>
	<!--  
	 포워딩 - 1) 전달하는 주소 변경X, 화면 변경O
	   			2) request 영역값 전달 가능
	   			
	 -->  
<%-- 	 <jsp:forward page="scopeAction.jsp"></jsp:forward> --%>
<%-- 	 <jsp:forward page="scopeAction.jsp?pw=1234"> --%>
	 <jsp:forward page="scopeAction.jsp">
	 	<jsp:param value="99999" name="pw"/>
	 </jsp:forward>
<!-- 나중 자바로도 사용ㅇ -->
 
 
 
</body>
</html>