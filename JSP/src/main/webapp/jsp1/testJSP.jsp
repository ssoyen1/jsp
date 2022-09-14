<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

     <h1>JSP파일2</h1>
<!--      HTML + JAVA코드 -->   
<script type="text/javascript">
	// javascript 주석문
</script>

     <%
     	// 스크립틀릿 - Java코드 작성     // F12했을 때 html 주석은 나옴, java 주석은 안나옴
     	
     	// 한줄짜리 주석
     	/*
     	  여러줄짜리 주석
     	  1
     	  2
     	  3     	 
     	*/
     	
     	//ctrl + alt + 방향키 아래 => 코드라인 복사
     	//ctrl + d => 코드라인 삭제
     	//ctrl + shift + c => 코드라인 주석 설정/해제
    
//      	System.out.println("1");
     	System.out.println("2");
//      	System.out.println("3");
     	System.out.println("4");
//      	System.out.println("5");  //화면이 아니라 console 창에 출력됨 

		// 화면(view)에 출력 => HTML코드로 출력하는 것과 같다
        out.println("아이티윌<br>");
        out.println("아이티윌<br>");
        out.println("아이티윌<br>");
        out.println("아이티윌<br>");
        out.println("아이티윌<br>");
        out.println("아이티윌"+"<br>");
        
        
		// bit : 0 또는 1을 표현하는 공간 
        // 1 byte <=> 8 bit
        // 1 KB   <=> 1024 byte
        // 1 MB   <=> 1024*1024 byte
        

		// 기본형 타입
		// 논리형
		// boolean - 1byte
		// 문자형 
		// char - 2byte
		// 정수형
		// byte - 1byte
		// short - 2byte
		// int - 4byte
		// long - 8byte
		// 실수형
		// float - 4byte
		// double - 8byte
		
		// 강제형변환 : 컴파일(문제가 있다)는 것을 인지하고도 처리하겠다.
    
		
		// < 반복문 - for >
//         for(초기식;조건식;증감식){
//         	반복할 코드;
//         }
        
		for(int i=0;i<20;i++){   //i=1;i<=5 보다 i=0 에 익숙해지도록 하기 (배열배울 때 대비)
			out.println("울산"+(i+1)+"<br>");
		}
		
// 		out.println("울산i<br>"); // 울산i
// 		out.println("울산"+i+1+"<br>"); // 울산01 / 울산11 / 울산 21
// 		out.println("울산"+(i+1)+"<br>");// 울산1 / 울산2 / 울산3
		
		
		%>
		부산 부산 부산 부산

	<%
	 // JSP
	 for(int i=0;i<5;i++){
	 %>
	 	<!-- HTML // html은 반복할수x but 자바로 인식됨, jsp라서 html도 반복할수ㅇ --> 
	 	<h1>@@@</h1> 
	 <%
	 // JSP
	  }
	 %>
</body>
</html>