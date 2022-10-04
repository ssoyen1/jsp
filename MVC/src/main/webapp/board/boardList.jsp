<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@page import="com.itwillbs.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>boardList.jsp</h1>
		<!-- 전달된 정보 확인 
		request안 저장된 것 확인하기 
		1) jsp 코드 2) EL코드-->
		
<%-- 	[JSP] <%=request.getAttribute("boardListAll")%> --%>
<%-- 	[EL] ${requestScope.boardListAll } --%>

		<h3><a href="./BoardWrite.bo">글쓰기</a></h3>


		<h3> 전체 글 개수 : ${requestScope.totalCnt }개 </h3>
		
		<table border="1">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>조회수</td>
				<td>작성일</td>
				<td>ip</td>
			</tr>
			
			<c:forEach var="dto" items="${boardListAll }">
			<tr>	
				<tr>${dto.bno }</td>
				<td>${dto.subject }</td>
				<td>${dto.name }</td>
				<td>${dto.readcount }</td>
				<td>${dto.date }</td>
				<td>${dto.ip }</td>
			</tr>
			</c:forEach>
			
			
		</table>
 		
 		
 		
		
</body>
</html>