<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>core_set2.jsp</h1>
	
				<h2> 전달된 정보 출력하기 </h2>
				모델명 : ${model }, ${requestScope.model } <br>
				색상   : ${color }, ${requestScope.color}<br>
				가격   : ${price }, ${requestScope.price}<br>
				
				
				
				
				<hr>
				<h2> 영역개체의 정보 삭제()</h2>
				(영역객체 정보가 없을 경우 모든 영역에 값을 삭제)
				(특정 데이터만 삭제하고 싶을 때는 해당 영역을 명시)
<%-- 			<c:remove var="price"/> <!-- 어트리뷰트를 지우는 것 --> --%>
				<c:remove var="price" scope="request"/> 
				모델명 : ${model }, ${requestScope.model } <br>
				색상   : ${color }, ${requestScope.color}<br>
				가격   : ${requestScope.price}<br>
				가격   : ${sessionScope.price}<br>
				가격   : ${price }<br>
				
		
		
		
		
				<hr>
				<h3> 서블릿에서 전달된 회원정보 출력하기 </h3>
				<h4>1. </h4>
				아이디 : ${requestSope.memberBean.id }<br>
				비밀번호 : ${requestSope.memberBean.pw }<br>
				이름 : ${requestSope.memberBean.name }<br>
				나이 :${requestSope.memberBean.age }<br>
				성별 : ${memberBean.id }<br> <!-- scope 생략도가능 -->
				이메일 : ${memberBean.email }
<%-- 			회원가입일 : ${memberBean.regdate } } --%>





				<h4>2. jstl-set 사용해서 출력하기 (이름길고 복잡할 경우) </h4>
				<c:set var="m" value="${requestScope.memberBean }"/>
				request.setAttribute("m",memberBean);
				성별 : ${m.gender }<br>
				이메일 : ${m.email }<br>
<%-- 			회원가입일 : ${m.regdate }<br> --%>



				<h4> 연습) ArrayList 전달한 회원정보 출력하기</h4>
				
				마지막 회원정보<br>
				아이디 :${requestScope.memberList[2].id }<br> <!-- 2번인덱스 안에있는 id정보필요 -->
				비밀번호 : ${requestScope.memberList[2].pw }<br>
				이름 :  ${requestScope.memberList[2].name }<br>
				
				
				[jstl-set]<br>
				<c:set value="${memberList }" var="list"/><br>
				나이 :  ${list[2].age }<br>
				<c:set value="${memberList[2] }" var="l2"/><br>
				성별 :  ${l2.gender }<br>
				이메일 :  ${l2.email }<br>
<%-- 			회원가입일 :  ${memberList[2].regdate } --%>
<!-- 				<??????????????????????????????? -->

				
				
		
		
		
		
		
		
		
		
</body>
</html>