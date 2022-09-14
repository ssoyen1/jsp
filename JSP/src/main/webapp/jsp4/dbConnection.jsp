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

	<h1>dbConnection.jsp</h1>
<!-- 	p.372 / p.414 / p.428 JDBC -->
	<h2>JDBC( Java DataBase Connectivity ) : 자바와 DB(MySQL)를 연결하는 API(라이브러리)</h2>
	<%
	
	
	// 0. JDBC 라이브러리(드라이버) 설치 
	// mysql-Connector-java-8.0 30.jar 파일을
	// /src/main/webapp/WEB-INF/lib 폴더에 저장 (설치)
	// => 프로젝트당 1번만 수행
	
	
	
	// 1. 드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 주소를 불러오겠다.
	
	System.out.println("드라이버 로드 성공!"); // 라이브러리 없는 상태로 하면 에러(Class not found~)
											   // 연결되는지 테스트 해본 것
	// jdbc:mysql://localhost:3306/jspdb				
    // http://localhost:8088/JSP/jsp4/dbConnection.jsp // local호스트 자리에 아이피 주소 들어가도 ㅇ 
    													// = 내컴퓨터가 아닌 지정한 아이피 주소로 들어가는것 ㅇ
    
    
    													
	// jdbc:mysql://localhost:3306/jspdb 
	// 	프로토콜:// IP주소(DB서버의 주소) : DB프로그램 포트번호 / DB명
	   											
    				
	
	
	
	// 2. 디비 연결 
// 	DriverManager.getConnection("jdbc:mysql://localhost:3306/jspdb", "root", "1234"); //DriberMA 3번째 것
				// 이 구문만으로 계속 연결되는거 아님. 한번 연결 다녀온 것일뿐 .
				//Connection java.sql.DriverManager.getConnection~ : 매서드 리턴값 
				// so
				
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspdb", "root", "1234");
				// java.sql = package 비슷한 것끼리 모아놓은 것
				// 연결정보 저장했다가 필요할때마다 꺼내쓸 수 있도록 하는 것. 할때마다 연결하는게 아니라!
				// 커넥션 변수에 저장!
				
	System.out.println("디비 연결 성공!"); // 실행이 다온다면 나오는 문구
	
	
	
	
	// 3. SQL 쿼리 작성 (insert, select, update, delete)
	
	
	
	
	
	

	
	%>
</body>
</html>