<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 0926 -->
		<h1>operatorPro.jsp </h1>
		
		<h2> 전달정보 저장</h2>
		[JSP]
		<%
			int num1 = Integer.parseInt(request.getParameter("num1")); // 넘어오는게 다 STring 일거기때문에 Integer.parseInt 통해서 넘어오는걸 숫자로바꿔줌
			int num2 = Integer.parseInt(request.getParameter("num2"));
		%>
		숫자 :<%=num1 %>, 숫자2 :<%=num2 %>
		
		<hr>
		[el] : el표현식으로 전달받은 파라메터는 자동 캐스팅됨(자동으로 알아서 형변환된다) <br>
		숫자1 : ${param.num1 }, 숫자2 : ${pram.num2 }  <br>
		숫자1 : ${param.num1+999999999 }, 숫자2 : ${pram.num2+99999 }  <br>
		
		
		<!--  <차이점>
			  jsp는 2줄. 형변환 해야함
			  el은 1줄,  변환해야하는지 알아보는 것. 숫자를 더해보기
			  			 String 이라면 -> 연결자로 만들어짐 (연산이 안된다)
			  			 int 라면      -> 연산이 된다 (String이 아니다.)-->
	
	
	
	
	
		<hr>
		[el] : num1 + num2 출력 <br>
		${param.num1 + param.num2 } 
		
		
		<br>
		[el] : num1, num2 값이 모두 양수인가? (t/f) <br>
<%-- 	${param.num1 >0 } ${param.num1 >0 } <!-- 각각 독립 시행이 된다. --> --%>
		${ param.num1 >0 && param.num2 >0 } <br> <!-- 혹은 -->
		${ (param.num1 >0) && (param.num2 >0) } <br>
		${ (param.num1 gt 0) && (param.num2 gt 0) } <br> 
		
		
		<br>
		[el] : num1, num2 값이 모두 양수인가? ("양수"/"양수 아님") <br>
		${ ((param.num1 > 0) && (param.num2 > 0)) ? "양수":  "양수아님" }
		<br>
		
		[el] : ${empty param.id } <!-- empty 값(값이 null일 경우 true/ 아닐경우 false)
									true(비어있다) false(파라미터가 존재한다) // = if(id != null)-->
		
		<hr>
		
		
		
		
		
		<h2>EL 연산자 p516~517</h2>
		
		비교연산자						논리연산자
		-----------------------------------------------------------------------------------
		==		eq (equl)				&&		and
		!=		ne(not equl)			||		or
		<		lt(less than)			!		not
		>		gt(great than)
		<=
		>=
		
		
		
		
		<!-- 서블릿 el표현식 같이 사용하기 -->
		
		
		
	
</body>
</html>