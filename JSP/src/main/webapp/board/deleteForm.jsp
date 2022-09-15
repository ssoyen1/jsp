<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>deleteForm.jsp</h1>
	<%
		// 전달되는 데이터 저장 (bno, pageNum)
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		
		
		
	%>
	<fieldset>
		<form action="deletePro.jsp?pageNum=<%=pageNum %>" method="post">
			<input type="hidden" name="bno" value="<%=bno %>"> <!-- 위에bno에 담겨있으므로 dto.이아니라 그냥 bno쓴것 -->
			비밀번호 : <input type="password" name="pass"> <br>
			<input type="submit" value="삭제하기">
		</form>
	</fieldset>
</body>
</html>