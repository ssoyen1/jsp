<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionLoginPro.jsp</h1>

<!-- 		sessionLoginPro.jsp -->
<!-- 		3. 아이디, 비밀번호 일치여부 체크  -->
<!-- 		3-1. 아이디 O -->
<!-- 			  비밀번호 O => 본인 로그인 -->
<!-- 			  비밀번호 X => 비밀번호 오류 -->
<!-- 		3-2. 아이디 X => 비회원 -->
	<%  //3.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		// attribute 경우 (String)붙였음 . 파라미터는 가져오는거 다 string 이므로 안붙임
		
		String userID = "admin";
		String userPW = "1234";
		
			// == : 기본형 타입의 값을 비교할 때 사용 (사물함어딘지, 주소비교)
			// equals() : String 타입의 값을 비교할때 사용 (안에 값을 꺼내서 비교.==보다 정확하게 비교 가능)
			
			
		if(userID.equals(id)){ // userID 값이랑 내가입력한 id 값이랑 같은가 ? 
			   // 실행ㅇ (true)  -> 아이디ㅇ
				if(userPW.equals(pw)){
				// 아이디.비밀번호 ㅇ => 본인
				out.println("로그인 성공!");
					// 로그인 성공한 ID 정보를 유지(세션영역에 저장)
					session.setAttribute("id", id);
					
					// sessionMain.jsp 이동
					response.sendRedirect("sessionMain.jsp");
				}else{
					// 아이디ㅇ, 비밀번호X
				out.println("비밀번호 오류!");
				}

		}else{ 
                //-> 아이디 X
			out.println(" 비회원 !!");
		}
	
	%>
	
	
	
	
	


</body>
</html>