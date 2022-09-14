<%@page import="com.itwillbs.member.MemberBean"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		// js 입력된 데이터 체크
		//alert(window.document.fr); //window(=열려있는 브라우저(객체)안에있는 /document(=html)안에있는 /fr(폼태그)
		alert(document.fr);
		
		
	</script>
</head>
<body>
		<h1>memberUpdate.jsp</h1>
		<h2> 회원정보 수정페이지 </h2>
		
		<%
			// 로그인 여부 체크
			String id = (String)session.getAttribute("id");
			if(id==null){
				System.out.println("memberUpdate.jsp - 로그인 X"); 
				response.sendRedirect("loginForm.jsp");
			}
		
			
			// DB에서 기존의 회원정보를 가져오기
			
			// MemberDAO 객체 생성
			MemberDAO dao = new MemberDAO(); // DB관련 동작 위임해주기위해
			
			// dao 객체 안에 회원정보 전부를 조회하는 메서드 호출
			
			
			// 회원정보 조회 메서드 호출
			MemberBean mb = dao.getMember(id);
			
			if(mb !=null ){
				
				
			%>
			  <fieldset>
				<form action="memberUpdatePro.jsp" method="post" name="fr" onsubmit="return check();">
				아이디 : <input type="text" name="id" value="<%=mb.getId()%>"readonly><br> <!-- readonly, disabled 로그인 못하도록 하는것 -->
				비밀번호 : <input type="password" name="pw" value="" placeholder="비밀번호를 입력하세요"><br>
				이름 : <input type="text" name="name" value="<%=mb.getName()%>"><br>
				나이 : <input type="text" name="age" value="<%=mb.getAge()%>"><br>
				 성별 : <input type="radio" name="gender" value="남" 
        		<%if(mb.getGender().equals("남")){ %>
        		   checked
        		<%} %>
               >남<br>
               <input type="radio" name="gender" value="여" 
               <%if(mb.getGender().equals("여")){ %>
        		   checked
        		<%} %>
               >여<br>
    
				이메일 : <input type="text" name="email" value="<%=mb.getEmail() %>"><br>
						<!--  날짜정보는 따로 안받음 -->
						
				<hr><hr>
				
				<input type="submit" value="수정하기" >
				</form>
			  </fieldset>
		 	<% }%>
		 	
		 	
		 	
		 	<script type="text/javascript">
		 	 // 맨 위에 말고 끝날때쯤 적어주기. 실행순서때문에
				// js 입력된 데이터 체크
				//alert(window.document.fr); //window(=열려있는 브라우저(객체)안에있는 /document(=html)안에있는 /fr(폼태그)
				//alert(document.fr);
				//console.log(window.document.fr.pw); //페이지실행후+f12 -> 콘솔탭 : 내부적인 것 볼 수 있음 (alert은 내부적인것까지 정확하게 볼 수 X)
				//console.log(window.document.fr.pw.value); // "  아무것도 안뜸
				
				function check(){
					if(window.document.fr.pw.value == ""){ //비밀번호 있는지없는지 실행
						alert("비밀번호를 입력하세요!"); // 수정누르기 전에 먼저 뜸 -> function 안에 넣기 (혼자 작동되지않도록)	
						document.fr.pw.focus(); // 어디가 잘못됐는지 알 수있게 커서 깜빡거리게함
						return false; //함수의 종료 /break(X)  			   -> form 에서 onsubmit 에서 function내용 넣어서 사용하기

					}								

					alert("check() 끝");
				}
			</script>
			
			
</body>
</html>