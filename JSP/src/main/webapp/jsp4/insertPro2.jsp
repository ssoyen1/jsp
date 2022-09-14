<%@page import="java.sql.PreparedStatement"%>
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
// 		String sql = "insert into itwill_member (name,gender,age,jumin) values ('"+name+"','"+gender+"',"+age+"'"+jumin+"')";
																	 // 스트링 + 스트링 이어주는 것 '"++""
		String sql = "insert into itwill_member (name,gender,age,jumin) values (?,?,?,?)";
																	
		System.out.println(" SQL 구문 작성완료! ");
// 		Statement stmt = con.createStatement();          // 이거 안쓰고 밑에꺼 쓸 것!
// 		PreparedStatement : SQL구문을 실행하도록 도와주는 객체 (사전작업)
			// 차이점 : sql이라는 변수를 가져감. 원래 실행할때(5.) 가져가서 실행토록했으나  미리 사전에 가져가서 준비시킴
// 						속도. 보안 더 뛰어남

		PreparedStatement pstmt = con.prepareStatement(sql); 
		
		
		System.out.println( " stmt 객체 생성 완료! ");
		
		
		// ???? (실행하기 전 물음표 채우기) (SQL 구문에 ?가 있는 경우 실행)
		 	 // sql 은 현재 pstmt 가 가지고 있음 . 그래서 pstmt 에게 물어봐야함
		// pstmt.setXXXXX(?의 위치, ?에 들어갈 값);
		// -> DB에 저장되는 타입에 따라서 메서드가 변경 (String, int ...)
		// -> set()개수는 ??개수와 동일
		
		pstmt.setString(1, name);
		pstmt.setString(2, gender);
		pstmt.setInt(3, age);
		pstmt.setString(4, jumin);
// 		pstmt.setString(parameterIndex, x) // parameterIndex 는 물음표 위치
		
		// 4. SQL문 실행 (하면 들어감)
//  		stmt.executeUpdate(sql);
		pstmt.executeUpdate();
		System.out.println( "SQL 실행 완료! ");
						
		// 5. 폼태그해서 가져와보기											
								
		// 여기까지하면 홍길동 입력한게 SQL에 적지 않았으므로 위에 String 에 넣어줌						
								
		// select.jsp 페이지로 이동
		response.sendRedirect("select.jsp");
			
	
	%>
	
	
	
</body>
</html>