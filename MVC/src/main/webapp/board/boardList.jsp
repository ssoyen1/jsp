<%@page import="java.util.ArrayList"%>
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
				<td>
					<a href="./BoardContent.bo?bno=${dto.bno }&pageNum=${pageNum}">${dto.subject }</a>
				</td>
				<td>${dto.name }</td>
				<td>${dto.readcount }</td>
				<td>${dto.date }</td>
				<td>${dto.ip }</td>
			</tr>
			</c:forEach>
			
			
		</table>
 		
 		
	  <c:if test="${totalCnt != 0}"> <!-- 올렸던 글의 갯수가 몇개인지 판단 -->
   
      <!-- 이전 -->   
      <c:if test="${startPage > pageBlock }">
         <a href = "./boardList.bo?pageNum=${startPage-pageBlock }">[이전]</a>
      </c:if>



  			<!-- 페이지번호(1,2,3...) -->
  			<c:forEach var="i" begin="${startPage }" end="${endPage}" step="1">
  				<a href="./BoardList.bo?pageNum=${i }">${i }</a>
	  		</c:forEach>
  			<!-- 다음 -->
  			<c:if test="${endPage < pageCount}">
  				<a href=".BoardList.bo?pageNum=${startPage+pageBlock }"> [다음]</a>
  			</c:if>
  		
	</c:if>
 		
		
</body>
</html>