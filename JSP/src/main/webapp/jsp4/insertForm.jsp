<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>insertForm.jsp</h1>
	
	<h2> 정보입력 페이지 </h2>
	
	
	<form action="insertPro2.jsp" method="post">
	
		이름 : <input type="text" name="name"> <br>
		성별 : <input type="text" name="gender"> <br>
		나이 : <input type="text" name="age"> <br>
		주민번호 : <input type="text" name="jumin"><br>  
<!-- 		이 페이지에서는 정보전달만 받을 것이므로 디비 저장을 못함 so 액션페이지 만들어줘야함 -->
		
		<input type="submit" value="디비에 저장하기">
	</form>


</body>
</html>