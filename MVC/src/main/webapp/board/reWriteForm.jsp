<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC ReWrite</title>
</head>
<body>
		<h1>reWriteForm.jsp(MVC)</h1>
		
		<!-- 전달된 정보 : bno, pageNum, re_ref, re_lev, re_seq 가져옴 -->
		
		
		${param.bno }
		${param.re_ref }
		${param.re_lev }
		${param.re_seq }
		
		<fieldset>
			<form action="./BoardReWriteAction.bo?pageNum=${param.pageNum }" method="post"> <!--  페이지넘버만 주소줄에 넘겨주고 나머지는 다 디비에있는애들이라서 넘거쥼 -->
					<input type="hidden" name="bno" value="${param.bno }">	<!-- 다른것들은 직전에서 dto를 만들어줫기때문에 dto.으로 적었고 -->
					<input type="hidden" name="re_ref" value="${param.re_ref }"> <!--  얘네들은 직전 주소줄에서 정보를 저장했기때문에 걔네를 받아오는 것. -->
					<input type="hidden" name="re_lev" value="${param.re_lev }">
					<input type="hidden" name="re_seq" value="${param.re_seq }">
					제목 : <input type="text" name="subject" value="[답글]"><br>
					작성자 : <input type="text" name="name"><br>
					비밀번호 : <input type="password" name="pass"><br>
					내용 : 
					<textarea rows="10" cols="20" name="content"></textarea> <!-- </textarea>는 엔터를 따로 입력하면 공백이 다 들어감. 조심해야함. 치고싶으면 <textarea>에 치기  -->
					<hr>
					<input type="submit" value="글쓰기">	
					
						
			</form>
		</fieldset>
		
</body>
</html>