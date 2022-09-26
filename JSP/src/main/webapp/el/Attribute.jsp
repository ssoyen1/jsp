<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 0926 TestServlet1에서 보낸 파일 -->
		<h1>Attribute.jsp</h1> <!-- 서블릿(자바)에서 다 계산하고보내면 jsp는 출력하는 역할만 할 것 -->
				<%
					int cnt = (Integer)request.getAttribute("cnt"); // 내가 어트리뷰트로 저장했기때문에
																	// 파라미터에는 String만 사용가능
																	// 애트리뷰트에는 다른 String외에 Object, Array 등 다양한 데이터 입력이 가능
					cnt = 2000;
				%>
				
				
				cnt 값 (jsp) : <%= cnt %><br>
				cnt 값 (jsp)  : <%= request.getAttribute("cnt") %> <br>
				
				cnt 값 (el) : ${cnt }<br> <!-- 출력되지만 -->
				cnt 값 (el) : ${requestScope.cnt }<br> <!--  더 정확한 표현 -->
				
				cnt 값 (jsp-session) : <%=session.getAttribute("cnt") %><br>
				cnt 값 (el -session) : ${sessionScope.cnt }<br> 
				<!--  session 을 바로 쓸 수 있는 이유 = 내장객체이기때문에  -->
				
				
				=> el 영역객체의 이름은 생략 가능<br>
				   pageScope -> requestScope -> sessionScope -> applicationScope <br>
				   작														큰
				   
				   
			   * 위의 영역객체를 무시해버리면 영역범위의 크기가 작은 곳에서 큰 곳으로 이동하면서 검색<br>
			   * 동일 이름의 속성값이 존재할 경우, 잘못된 데이터 참조<br>
				   
				
			   
				   
				   
				<h3>
					jsp코드(스크립틀릿) cnt => 변수,<br>
					el 표현식 cnt => 속성(어트리뷰트)라고 부를 것<br>
				</h3>
				
				<!-- cnt jsp값과 cnt el값이 같은지 확인 자바영역에 cnt = 2000; 넣어보기.-->
</body>
</html>