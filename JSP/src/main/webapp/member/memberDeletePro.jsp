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
		<h1>memberDeletePro.jsp</h1>
		
		<%
			// 로그인제어
			String id = (String)session.getAttribute("id");
			if(id==null){
				response.sendRedirect("loginForm.jsp");
			}
			
			// 한글처리
			response.setCharacterEncoding("UTF-8");
			
			// 전달해준 정보(pw) 저장 - 파라미터
			String pw = getInitParameter("pw");
			

			// MemberDAO 객체 생성
			MemberDAO dao = new MemberDAO();
			
	       // 정보 삭제() - memberDelete()
	       int result = dao.memberDelete(id, pw);
		
			// 삭제 결과에 따른 처리 (1,0,-1)
							
			if(result == 1){
				// 삭제 성공
				// 세션정보(로그인정보) 삭제
				session.invalidate();
				// 메인페이지 이동
					%>
					<script type="text/javascript">
						alert("삭제 성공!");
						location.href="main.jsp";
					</script>
					<%
				
			}else if(result == 0){
				// 수정 실패 - 비밀번호오류
				// 페이지 뒤로가기 
					%>
					<script type="text/javascript">
						alert("삭제 실패 - 비밀번호 오류!");
						history.back();
						
					</script>
					<%
				
			}else{ // result == -1
				// 수정 실패 - 회원정보 오류
				// 페이지 뒤로가기
					%>
					<script type="text/javascript">
						alert("삭제 실패 - 회원정보 오류");
						history.back();
					</script>
					<%
			}
		%>			
			

</body>
</html>