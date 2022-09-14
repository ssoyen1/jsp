<%@page import="com.practice.BoardDAO2"%>
<%@page import="com.practice.BoardDTO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>writePro2.jsp</h1>


		<%
		request.setCharacterEncoding("UTF-8");		
		%>
		
		<jsp:useBean id="dto" class="com.practice.BoardDTO2"/>
		<jsp:setProperty property="*" name="dto"/>
		
		
		<%
		dto.setIp(request.getParameter("ip"));
		%>
		
		<%=dto.toString() %>
		
		<%
		
		BoardDAO2 dao = new BoardDAO2();
		
		dao.insertBoard(dto);
		
		response.sendRedirect("boardList.jsp");
		
		
		%>



</body>
</html>