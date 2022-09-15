<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		// 수정하기 
		function check() {
			alert(" 체크함수 실행! ");

			// 비밀번호(input) 값을 가져오기
			var pass = document.fr.pass.value; // 변수에 가져온 정보를 저장하기
// 			var pass = document.forms[0].pass.value; // fr라는 이름이 없을때
			
			// 비밀번호를 입력 X -> "비밀번호를 입력하세요!" 출력
			if(pass == "") {
				alert("비밀번호를 입력하세요!");
				document.forms[0].pass.focus();
				return false; //submit했을때 
			}
// 			if(pass.length < 0) { // 위와 같은 표현
// 			}
			
			
			// 페이지이동(submit) 금지

		}

</script>

</head>
<body>

		<h1>updateForm.jsp</h1>
<%
		// 기존의 글정보가 화면에 출력  (boardContent 와 비슷함)
		
		// 기존의 글 정보(bno 어떤글을 수정할건지에대해), 페이지정보(pageNum) 가져오기
		// 				전페이지에서 보내줘야만 받을 수 있음.(전페이지: boardContent.jsp) 
		//				so 앞에서 보낼때 적어넣기
		//				onclick="locatuon.href='updateForm.jsp?bno=<%=dto.getBno()>&pageNum=<%=pageNum>
		
		int bno = Integer.parseInt(request.getParameter("bno")); 
		String pageNum = request.getParameter("pageNum");
		
		
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 글 정보를 가져오는 메서드 호출
		BoardDTO dto = dao.getBoard(bno);	
		
		
		
		
%> 
			
		<fieldset>
			<form action="updatePro.jsp?pageNum=<%=pageNum %>" name="fr" method="post" onclick="check();">
					
					<input type="hidden" name="bno" value="<%=dto.getBno() %>"> <!-- 중복제목이 있을 수 있으므로 bno를 가져옴. 굳이 보여줄필요없으므로 hidden-->
					제목 : <input type="text" name="subject" value="<%=dto.getSubject() %>"><br>
					작성자 : <input type="text" name="name"  value="<%=dto.getName() %>"><br>
					비밀번호 : <input type="password" name="pass" placeholder="비밀번호를 입력하세요"><br>
					내용 : 
					<textarea rows="10" cols="20" name="content"><%=dto.getContent() %></textarea> 
					<hr>
					<input type="submit" value="글 수정하기">	
					<!--  submit은 클릭과 서브밋이 같이 있으므로 onclick으로는 제어할 수 없다
					메소드 없에 온클릭으로 제어해야함. -->
						
			</form>
		</fieldset>
		
</body>
</html>