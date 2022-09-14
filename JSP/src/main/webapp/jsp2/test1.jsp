<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="<%=col2%>">
<!-- top/bottom/main(body)/left-->

	<h1>Test1.jsp</h1>
	<%=col1 %>
	
	<h2> include 액션태그 (jsp:inClude) </h2>
	=> 각각의 페이지를 컴파일 한 후에 페이지를 추가 (JSP)
	
	<h2> inClude 지시어 (@ include)</h2>
	=> 코드를 병합해서 컴파일 수행
	
	
	<table border="1" width="800" height="1000">
	
<!-- 	<th> 표의 제목 -->
<!-- 	<tr> 가로줄 -->
<!-- 	<td> 셀 -->
		<tr>
			<td colspan="2" height="200">
				<!--  colspan  : 가로줄 합치는 개수 지정 (colunm span 약자)
					  rowspan  : 세로줄 합치는 개수 지정 -->
				<!--  <h1> TOP </h1> -->

				<!--  top.jsp? id=ITWILL -->
				<jsp:include page="top.jsp">
					<jsp:param value="ITWILL" name="id"/>
				</jsp:include>
			
			
			</td>
		</tr>
		<tr>
			<td width="200">
<!-- 			<h1>LEFT</h1> -->
				<jsp:include page="left.jsp"></jsp:include>
			</td>
			<td>
		 		<H1>MAIN</H1>
			</td>
		</tr>
		<tr>
			<td colspan="2" height="200">
<!-- 			<H1>BOTTOM</H1> -->
				<jsp:include page="bottom.jsp"></jsp:include>
			</td>
		</tr>
		
			
	</table>
</body>
</html>