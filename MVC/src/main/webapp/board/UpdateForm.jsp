<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>UpdateForm.jsp(MVC)</h1>
		
		
		<fieldset>
			<form action="./BoardUpdateProAction.bo?pageNum=${pageNum }" method="post">
				<input type="hidden" name="bno" value="${dto.bno }"> <!-- dto안에 dno정보를 가져왓음 -->
					
					제목 : <input type="text" name="subject" value="${dto.subject }"><br>
					작성자 : <input type="text" name="name" value=${dto.name }"><br>
					비밀번호 : <input type="password" name="pass"><br>
					내용 : 
					<textarea rows="10" cols="20" name="content">${dto.content }"</textarea> <!-- </textarea>는 엔터를 따로 입력하면 공백이 다 들어감. 조심해야함. 치고싶으면 <textarea>에 치기  -->
					<hr>
					<input type="submit" value="글 수정">	
					
						
			</form>
		</fieldset>
		
</body>
</html>