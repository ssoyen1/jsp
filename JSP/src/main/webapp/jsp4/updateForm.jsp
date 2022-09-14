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

	<h1>updateForm.jsp</h1>
	
	<h2> 정보수정 페이지 </h2>
	

	
	
	
	<%
		// idx=10 인 사람의 정보를 가져와서 화면에 출력
		// 디비연결정보 (상수)
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
	
	
		//1. 드라이버로드
		Class.forName(DRIVER);
		System.out.println("드라이버 로드 성공!");
		
		//2. 디비연결
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println(" 디비 연결 성공 ! ");
		
		//3. SQL 작성(select) & pstmt
		String sql = "select * from itwill_member where idx=10"; //PK가 걸려있기때문에 이 값은 1명뿐임! (null 도안되고 중복도 안됨)
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//4. SQL 실행
		ResultSet rs = pstmt.executeQuery();
		String name = "";
		int age = 0;
		
		//5. 데이터 처리 (1명밖에 없으므로 if로 출력하면 됨)
		if(rs.next()) { 
			name = rs.getString("name"); // 위에 전역변수로 name age 선언했으므로 앞에 데이터타입 안적어도 됨
			age = rs.getInt("age");
		}
		
		
	%>
	
	
		
		<form action="updatePro.jsp" method="post">
			<input type="hidden" name="idx" value="13"><br>
			<!--  hidden 화면에 안보이는 값을 전달할 때 -->
			<!-- 이름, 나이만 수정하기-->
			<!-- idx : <input type="text" name="idx"><br>-->
			이름 : <input type="text" name="name" value="<%=name%>"> <br> 
			나이 : <input type="text" name="age" value="<%=age%>"> <br> 
			<!--  name과 age 오류 뜸. if문 안의 지역변수이므로 so 밖에다가 전역변수 또 설정해줌 -->
			<input type="submit" value="디비에 수정하기">
		</form>
	
	
	

</body>
</html>