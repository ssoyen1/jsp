<%@page import="java.sql.ResultSet"%>
<%@page import="com.itwillbs.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>main.jsp</h1>
		
		<h2> 메인페이지 </h2>
		<%
			// 로그인을 해야만 정보를 확인가능한 페이지
			// 로그인 X -> 사용자 페이지 사용 X => 로그인페이지로 이동
			// 로그인 O -> "ㅇㅇㅇㅇ"님 환영합니다.
			
			String id = (String)session.getAttribute("id");
								//앞에서 보낸 session~ 로그인이 되었다면 보내졌을거고 안됐다면 안보내졌을 것.

			
			if(id == null){ // 로그인 X 
				response.sendRedirect("loginForm.jsp");
			}else {
				System.out.println("ㅇㅇㅇㅇ님 환영합니다.");
			}
		
		%>
		
			<h3><%=id %>님 환영합니다.</h3>
			
			
			<!--  로그아웃 버튼 방법 3가지  -->
			
			<input type="button" value="로그아웃" onclick="location.href='logout.jsp';">

			<a href="logout.jsp">로그아웃2</a>
			
			<a href="javascript:location.href='logout.jsp';">로그아웃3</a>
			
			<hr><hr>
			
			<h4><a href="memberInfo.jsp">회원정보 조회</a></h4>
			
			<h4><a href="memberUpdate.jsp">회원정보 수정</a></h4>
		
			<h4><a href="memberDelete.jsp">회원정보 삭제</a></h4>
			
			<h4><a href="../board/writeForm.jsp">게시판 글쓰기</a></h4>
			
			<%
			
			// jsp 페이지는 실행 순서
			// JSP(java) - HTML - JS
			
			// jsp 코드가 처음부터 끝까지 실행될 때 null 비교불가
			//		 => 객체 레퍼런스를 사용할 때는 항상 null 값 체크
			
// 			if(id != null){
// 				 if(id.equals("admin")){ // null 값은 비교할 수 없는데 비교해라고 넣어서 오류 뜸
// 			이거 두개 합치기 =>					 

			if(id != null && id.equals("admin")) { 
//   (X)	if(id.equals("admin" && id != null)) { 
					 %>
						<!-- 관리자 전용 메뉴 -->
						<h4><a href="memberList.jsp">회원정보 목록</a></h4>
						<hr><hr>
					 <% 
					 

			}
			%>
			
</body>
</html>