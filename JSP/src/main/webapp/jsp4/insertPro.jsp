<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>insertPro.jsp</h1>
	
 
	<% 
	
	// 한글처리 
	request.setCharacterEncoding("UTF-8");
	
	// 전달된 정보 저장하기 -> 출력하기
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String jumin = request.getParameter("jumin");
	int age = Integer.parseInt(request.getParameter("age")); // 정수데이터이므로 int 로 저장해주기
															 // Q. string 으로 하면 안되는가?
															 // A. DB에서 int 임. DB실행하기전에 타입맞춰줘야 실행가능
															 // 문자는 string 정수는 int로 해주기
															 
	%>
	
	<%=name %>,<%=gender %>,<%=jumin %>,<%=age %>
	<h2> 전달받은 정보를 DB에 저장</h2>
	
	<%
			
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		
		
		
		// 1. 드라이버 로드 (설치된 것 불러오기)
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("드라이버 로드 성공!");
		
		
		
		// 2. 디비 연결
		
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW); 
		System.out.println(" con : " + con);
		
		
		
		
		// 3. SQL 쿼리문 작성 & stmt (statement)객체 생성
		
// 		String sql = "insert into itwill_member (name,gender,age,jumin) values ('name','gender',age,'jumin')";
		String sql = "insert into itwill_member (name,gender,age,jumin) values ('"+name+"','"+gender+"',"+age+"'"+jumin+"')";
																	 // 스트링 + 스트링 이어주는 것 '"++""
		System.out.println(" SQL 구문 작성완료! ");
		Statement stmt = con.createStatement();
		System.out.println( " stmt 객체 생성 완료! ");
		
		
		
		
		// 4. SQL문 실행 (하면 들어감)
		stmt.executeUpdate(sql);
		System.out.println( "SQL 실행 완료! ");
						
		// 5. 폼태그해서 가져와보기											
								
		// 여기까지하면 홍길동 입력한게 SQL에 적지 않았으므로 위에 String 에 넣어줌						
								
			
			
	
	%>
	
	
	
</body>
</html>