
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>select2.jsp</h1>

	<h2> DB에 저장되어 있는 정보 조회</h2>
	
	
		<hr><hr>
		<table border="">
			<tr>
				<th>idx</th>
				<th>name</th>
				<th>gender</th>
				<th>age</th>
				<th>jumin</th>
			</tr>
		
	
	
		<%
		// 디비연결정보 (상수)
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
	
		// 1. 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println(" 드라이브 로드 성공!");
		
		
		// 2. 디비 연결
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW); 
		System.out.println(" 디비 연결 성공 ! ");
		System.out.println(" con : " + con);
		
		// 3. SQL 작성 & pstmt 객체 만들기
		String sql = "select * from itwill_member order by idx desc"; // 물음포 따옴표 필요 X (타입상관없이 받겠다)
		
		PreparedStatement pstmt = con.prepareStatement(sql);

	
		// 4. SQL 실행 
		ResultSet rs = pstmt.executeQuery(); // 레코드셋 = 리절트셋으로 저장됨 	
											 // 커서가 놓인 위치로 데이터 유무알 수 있음. 행 단위로 움직임 
		System.out.println(" SQL구문 실행 완료! ");
		

		
		
		while(rs.next()){ 

			int idx = rs.getInt(4);		// 1.index 2.name 3.gender 4.age..
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			int age = rs.getInt("age");
			String jumin = rs.getString("jumin");
			
			
			%>
		
				<tr>
					<td><%=idx%></td>
					<td><%=name%></td>
					<td><%=gender%></td>
					<td><%=age%></td>
					<td><%=jumin%></td>
				</tr>
			
			<%
				}
			%>
			
			
			</table>
					
		
	
	
	
</body>
</html>