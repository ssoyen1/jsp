<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>fmt_formatNumber.jsp</h1>
		
		
		HTML : 100000000 <hr> <!-- 일반적인 수의 형태 출력 
									HTML : 100000000-->
		
		<fmt:formatNumber value="10000000"/><br> <!-- 10,000,000 -->
		<fmt:formatNumber value="10000000" groupingUsed="true"/><br> <!-- 위는 groupingUsed="true"가 생략되어있는것 /10,000,000 -->
		<fmt:formatNumber value="10000000" groupingUsed="false"/><br> <!-- 일반적인 숫자표현하고싶을때./ 10000000 -->
		
		
		<hr>
		<b>3.1456789 => 소수점 2번째 자리까지만 표현</b> <br>
		(원래 반올림) 3.145 -> 314.5로 바꾸기 + 0.5 더하기 -> 마지막자리수가 5보다 크면   - 315.a / 작으면 - 314.a<br>
	
		pattern 속성에 #특수기호를 사용해서 자릿수를 표현
		<fmt:formatNumber value="3.1456789" pattern="#.##"/> <br>
		<fmt:formatNumber value="3.1456789" pattern="#.####"/> <br>
		<fmt:formatNumber value="3.1456789" pattern="#.########"/> <br> <!-- 가지고있는 데이터이상으로 자릿수를 설정해도 원래데이터만출력됨 -->
		
		
		<hr>
		<b>10.5 => 소수점 2번째 자리까지 표현 (10.50)</b><br>	
		pattern 속성에 0을 사용해서 없는 자리수를 표현가능	
		<fmt:formatNumber value="10.5" /><br> 
		<fmt:formatNumber value="10.5" pattern="##.##"/><br> <!--X 10.5까지밖에 출력안됨 -->
<%-- 	<fmt:formatNumber value="10.5" pattern="##.#0"/><br> <!--X 에러생김 --> --%>
		<fmt:formatNumber value="10.5" pattern="##.00"/><br> <!--O 원하는 자릿수만큼 데이터 나옴 -->
		<fmt:formatNumber value="10.5" pattern="##.000000"/><br> <!--O 없는 자릿수를 채워줄 수 있게 만듬 -->
		
		
		<hr>
		<b>0.5 => 50% 치환하는 방법</b><br>
		<fmt:formatNumber value="0.5" type="percent"/><br> <!-- currency 와 percent 뜸 -->
		<fmt:formatNumber value="<%=78/13 %>" type="percent"/><br>
		
		<hr>
		<b>화폐통화변경</b>
		<fmt:formatNumber value="10000000" type="currency"/><br> <!-- ₩10,000,000 -->
		<fmt:formatNumber value="10000000" type="currency" groupingUsed="false"/><br> <!-- ₩10000000 -->
		<fmt:formatNumber value="10000000" type="currency" groupingUsed="false" currencySymbol="@"/><br> <!-- @10000000 -->
		
		
		
		
		
</body>
</html>