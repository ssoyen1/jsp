<%@page import="com.itwillbs.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>deletePro.jsp</h1>
	
	
	<%
		// 한글 처리 (post방식으로 보내기떄문에)	
		request.setCharacterEncoding("UTF-8");
	
		// 전달정보 저장 (pageNum, bno, pass)
		String pageNum = request.getParameter("pageNum"); 
			//bno는 직접 받아도되고 액션태그에 넣어서 받아도 됨

		int bno =  Integer.parseInt(request.getParameter("bno"));
		String pass = request.getParameter("pass"); // 지금까지는 이렇게 두개 다 적어서 했찌만 
													// 앞으로는 밑에 주석처럼 dto에 (useBean에) 한번에 담아 올것임
	%>
<%-- 		<jsp:useBean id="dto" class="com.itwillbs.board.BoardDTO"/> --%>
<%-- 		<jsp:setProperty property="*" name="dto"/> --%>
	
	<%
	
		// BoardDAO 객체
		BoardDAO dao = new BoardDAO();
	
	
		// 게시판 글 삭제
		int result = dao.deleteBoard(bno, pass);
		
		// result (-1:  , 0: , 1: 삭제완료)
		if(result == -1) {
			%>
			<script type="text/javascript">
				alert('게시판 글 없음!');
				histroy.back();
			</script>
			<% 
			
		}else if(result == 0){
			%>
			<script type="text/javascript">
				alert('게시판 비밀번호 오류!');
				histroy.back();
			</script>
			<%
		}else{
			//result == 1
			%>
			<script type="text/javascript">
				alert('게시판 삭제 완료!');
				location.href="boardList.jsp?pageNum=<%=pageNum%>";
			</script>
			
			<%		
			
		}
	
	%>
	
	
	
	
</body>
</html>