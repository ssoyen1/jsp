<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>cookieForm.jsp</h1>
<!-- 	메세지 출력해 볼 것. 메세지가 한국어 뜻하면 -> 한국어, 영어면 ->영어 -->
	
	<% 
	    //
		// 쿠키 값에 따라서 출력되는 메세지 변경
		// 한국어 - " 안녕하세요 ! 쿠키연습!"
		// 영어 - "Hello~ Cookie Test~"
		
		// 쿠키값 저장
		
		String lan = "";
	
		// ===> 
		// 1. 쿠키가 있는지 체크
		Cookie cookie[] =  request.getCookies(); // 쿠키여러개이므로 배열로 저장해야함
		
		if(cookie != null){
			// 2. 쿠키들 중에서 내가 원하는 정보(언어)있는지 체크 
			for(int i=0; i<cookie.length;i++){
				if(cookie[i].getName().equals("lang")){
					// 3. 쿠키 값에 따른 메세지 출력
					System.out.println( cookie[i].getValue() );
					
					lan = cookie[i].getValue();
					
					if(cookie[i].getValue().equals("kor")){
						// 쿠키값이 한국어일때
						out.println("안녕하세요! 쿠키연습! <br>");
					}else{
						// 쿠키값이 영어일때
						out.println("Hello~ Cookie Test~ <br>");
						
					}
			}
		}
	}

	%>
	
	<h3>쿠키정보 선택(언어설정)</h3>
	<form action= "cookiePro.jsp" method="post">
		<input type = "radio" name= "language" value="kor" 
			<%if(lan.equals("kor")) {%>
			checked
			<%} %>
			> 한국어
		
		
		<input type = "radio" name= "language" value="eng" 
			<%if(lan.equals("eng")) {%>
			checked
			<%} %>
			> 영어
		<!-- 라디오 경우 같은 이름일 경우 같은 범주에?넣음 -->
		<!--  선택했을 때 , 선택한 것에 계속 표시 되도록 하기 checked 사용
				1. 내가 설정한 쿠기값이 무엇인지 알아야함. (한국어냐?영어?)
					=>   cookie[i].getValue() 통해 알 수 있음-->		
		<br>
		<input type = "submit" value="언어설정">
		
	</form>
	<!-- 쿠키생성 페이지 갔다 올것 -->
	
</body>
</html>