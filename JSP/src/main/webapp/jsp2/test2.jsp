<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- top/bottom/main(body)/left-->

	<h1>Test1.jsp</h1>
	<table border="1" width="800" height="1000">
	
		<tr>
			<td colspan="2" height="200">
<!-- 				<h1> TOP </h1> -->
			<jsp:inClude page="top.jsp">
			
			</td>
		</tr>
		<tr>
			<td width="200">
				<h1>LEFT</h1>
			</td>
			<td>
		 		<H1>MAIN</H1>
			</td>
		</tr>
		<tr>
			<td colspan="2" height="200">
				<H1>BOTTOM</H1>
			</td>
		</tr>
		
			
	</table>
</body>
</html>