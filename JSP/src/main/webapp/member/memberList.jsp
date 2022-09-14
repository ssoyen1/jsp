<%@page import="com.itwillbs.member.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>memberList.jsp</h1>
		
		<%
			// 로그인 체크  + 관리자 여부 체크
			// (로그인 안했거나, admin 아니면 로그인페이지로 이동)
			String id = (String)session.getAttribute("id");
		
			if(id == null || !id.equals("admin")) {
				response.sendRedirect("loginForm.jsp");
			}
			
			// DB에 저장된 회원정보를 모두 가져오기
			// MemberDAO 객체 생성
			MemberDAO dao = new MemberDAO();
			
			// dao 객체 안에 회원정보 전부를 조회하는 메서드
			ArrayList memberList = dao.memberList();
			
			
			// 정보 출력
			System.out.println(memberList);
			
			
		%>
			
			<table border="1">
				<tr>
					<td>아이디</td>
					<td>비밀번호</td>
					<td>이름</td>
					<td>나이</td>
					<td>성별</td>
					<td>이메일</td>
					<td>회원가입일</td>
				</tr>
				
			<% 
			  for(int i=0;i<memberList.size();i++) {
					MemberBean mb = (MemberBean) memberList.get(i);
					
					
					// a. admin 제외하고 출력하기
					if(mb.getId().equals("admin")) { // 추천하는 방법 X 조금 느림
						continue; //보조제어문 
					}
					
					// b. admin 제외하고 출력하기 - 디비에서부터 admin 계정을 빼고 가져오기
							// -> MemberDAO.jsp 에서 
							//sql="select * from itwill_member where id !=?";
							//pstmt = con.prepareStatement(sql);
							
							//pstmt.setString(1, "admin");			추가하기
						
						
					
					
			%>
				<tr>
					<td><%=mb.getId() %></td>
					<td><%=mb.getPw() %></td>
					<td><%=mb.getName() %></td>
					<td><%=mb.getAge() %></td>
					<td><%=mb.getGender() %></td>
					<td><%=mb.getEmail() %></td>
					<td><%=mb.getRegdate() %></td>
				</tr>
			<% } %>
			</table>
			
			<a href="main.jsp">메인페이지</a>
			
			
			
	
	
</body>
</html>