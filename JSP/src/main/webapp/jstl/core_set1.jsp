<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- JSTL은 라이브러리이기 때문에 사용하기전에 core를 header에 추가해주어야 한다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 0927 (1) -->
			<h1>core_set1.jsp</h1>
			<%
				// JSP코드는 set1.jsp페이지에서만 사용가능. (다른페이지에서는 접근불가.)
				int num1 = 100;
				int num2 = 200;
				int sum = num1 + num2;
			%>
				
				[JSP] : <%= sum %> <br>
				[JSTL] : <c:out value="<%=sum %>"></c:out> <br>
<%-- 			[EL] : ${sum }<br> <!-- 출력X. el에서의 데이터는 내장객체(어트리뷰트)에 대한 접근임(X변수에대한접근)
										만들 수 있는 방법????? 밑에!  --> --%>
					
				<h2> 변수 선언(어트리뷰트선언 (변수를 선언한다기보다....))</h2>
	<%-- 		<c:set var="변수명" value="변수값" scope="영역"/>
				 영역 객체.setAttribute("변수명", "변수값")
				 * 영역객체 정보가 생략된 경우 page가 디폴트--%>
				<c:set var="sum0" value="<%=sum %>"/> <!--  영역객체 setAttribue와 같다? -->
													  <!-- crtl+스페이스하면 scope나옴. 페이지 영역에 저장됨
													  		: 접근가능한 영역을 정하겠다 -->
	<%-- 		<%=sum0%> <!-- 출력X --> --%>
				${sum0 } <br>
				page : ${pageScope.sum0 }<br> <!-- page에 저장된게 맞는지 확인해보기 -->
				request : ${requestScope.sum0 }<br> <!-- 혹시나 reqeust에 저장된게 맞는지 확인해보기 -->
				session : ${sessionScope.sum0 }<br> 
				application : ${applicationcope.sum0 }<br> 
				
	
	
	
	
				<hr>
				JSTL 사용해서 100 + 200 = 300 연산결과를 출력 <br>
				<c:set var="num3" value="100"/>
				<c:set var="num4" value="200"/>
				<c:set var="sum5" value="num3+num4"/> <!-- (X) -->
				<c:set var="sum5" value="${num3+num4}"/>
				
<%-- 			<c:out value="num3"/> <!--  출력 : num3가 출력됨 --> --%>
				<c:out value="${num3 }"/> + <c:out value="${num4 }"/> = <c:out value="${sum5 }"/>
				${num3 } + ${num4 } = ${sum5 }<br>
			
				
				
				
				
				<hr>
				영역에 속성값을 만들어서 유지할 수 있도록하는 방법
				
<%-- 			<c:set> --%>
<!-- 				<var></var> -->
<!-- 				<value></value> -->
<%-- 			</c:set> --%>

				<c:set var="model" value="아이폰14" scope="request"/>
				<c:set var="color" value="black" scope="request"/>
<%-- 			<c:set var="price" value="200" scope="request"/> --%>
				<c:set var="price" value="300" scope="session"/> <!-- sessio정보에 담긴것 forward해도 다 넘겨짐. forward는 page를 제외한 모든것 다 넘겨줌 
																	  가격 remove에 이것도 되는지 확인해보기 위해 만듬-->
				
				<h3>core_set2.jsp로 이동</h3>
				액션태그 - forward
				
				<jsp:forward page="core_set2.jsp"/>
			
</body>
</html>