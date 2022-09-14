<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>cookiePro.jsp</h1>
	
	
	<%
		String lang = request.getParameter("language");
// 		폼태그로 전달받은 건 파라미터로 받음
	%>
	전달 정보 : <%=lang %><br>
	
	<%
		//(1) 쿠키값 생성
		Cookie cookie = new Cookie("lang", lang);
// 		Cookie cookie = new Cookie("lang", request.getParameter("language"));
// 		변수에 담지않고 바로 담아주는 방법 나중에 이렇게 써야함
// 		javax.servlert

		//(2) 쿠키 유효기간 설정
		cookie.setMaxAge(60*60); //1시간(3600초)
		//int = sec , long = 밀리세터 
		
		//(3) 쿠키값을 클리이어트에 전달
		response.addCookie(cookie);
		
	%>
	<script type= "text/javascript">
		alert('언어 쿠키정보 생성')
		location.href="cookieForm.jsp";
		
	</script>

	
</body>
</html>