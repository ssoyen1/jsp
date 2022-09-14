<%@page import="java.sql.Statement"%>
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
	<h1>insert.jsp</h1>
	
	<h2> 정보를 DB에 저장(Create) </h2>
	
	<%
		// 0. 드라이버 설치 - 생략 (이미 프로젝트에 설치했으므로)
	
		
		// 디비 연결정보 (상수) : 한번 선언하면 고정값이 됨. 못바꿈
								// fianl + 대문자(변수이름) / 소문자 사용 가능하지만 개발자 암묵적인 룰
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		
	
	
		// 1. 드라이버 로드 (설치된 것 불러오기)
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("드라이버 로드 성공!");
		
		
		
		// 2. 디비 연결
// 		DriverManager.getConnection("jdbc:mysql://localhost:3306/jspdb", "root", "1234");
// 		System.out.println("디비 연결 성공!");
		// dbConnection.jsp 와 똑같음 ! so 번거롭게 할거없이 디비연결정보(위에상수!) 저장하기
		
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW); // 코드가 4줄이나 늘었지만 오타위험이 줄었음
		System.out.println("디비 연결 성공!");
		System.out.println(" con : " + con);
				// con : com.mysql.cj.jdbc.ConnectionImpl@583670cc : @숫자 주민번호같은 것. 씨리얼넘버
				// 형태 ConnectionImpl
				// 시리얼 넘버 : 583670cc
		
				
				
		// 3. SQL 쿼리문 작성 & stmt (statement)객체 생성
		// itwill_member 테이블에 정보 저장 - insert
// 		String sql = "insert into itwill_member (name,gender,age,jumin) values ('ITWILL','M',11,'900101-1111111')";
//		다른 내용 추가하고 싶을때
		String sql = "insert into itwill_member (name,gender,age,jumin) values ('부산','W',55,'900101-5555555')";
		System.out.println(" SQL 구문 작성완료! ");
		// Statement : SQL구문을 실행하도록 도와주는 객체 (쿼리실행하려면 필요함. 워크벤치 번개표시 하려면 필요한아이)
		//				DB연결한 것으로부터 쿼리 사용할 수 있도록 만들겠다. (con~)
		Statement stmt = con.createStatement();
		System.out.println( " stmt 객체 생성 완료! ");
				// java.sql 무조건선택 - 자동완성으로 추가하기 . 그래야 맨윗줄에 생성됨
		
				
		
		// 4. SQL문 실행 (하면 들어감)
		stmt.executeUpdate(sql);
		System.out.println( "SQL 실행 완료! ");
													// 		드라이버 로드 성공!
													// 		디비 연결 성공!
													// 		 con : com.mysql.cj.jdbc.ConnectionImpl@6389c1f5
													// 		 SQL 구문 작성완료! 
													// 		 stmt 객체 생성 완료! 
													// 		SQL 실행 완료! 
													// 워크벤치에 가서 실행해보면 내가 추가한 'ITWILL'~ 추가되어있음.
													
		// 5. 폼태그해서 가져와보기											
													
													
													
													
		
	%>
	
</body>
</html>