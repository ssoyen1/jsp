<%@page import="java.util.Date"%>
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
		<h1>fmt_formatDate.jsp</h1>
		
		
		<c:set var="today" value="<%=new Date() %>"/>
		
		
		<hr>
		<h2>기본값</h2>
		${today }
		
		
		<hr>
		<fmt:formatDate value="${today }"/><br> <!-- 날짜정보출력 -->
		<fmt:formatDate value="${today }" type="time"/><br> <!-- 시간정보만출력 -->
		<fmt:formatDate value="${today }" type="both"/><br> <!-- 둘다출력 -->
		
		
		<hr>
		<b>날짜 다양하게 출력</b><br>
		<fmt:formatDate value="${today }" dateStyle="full"/><br><!-- 2022년 9월 29일 목요일 -->
		<fmt:formatDate value="${today }" dateStyle="long"/><br><!-- 2022년 9월 29일 -->
		<fmt:formatDate value="${today }" dateStyle="medium"/><br><!-- 2022. 9. 29./ 기본값 -->
		<fmt:formatDate value="${today }" dateStyle="short"/><br><!-- 22. 9. 29. -->
		
		
		<hr>
		<b>시간 다양하게 출력</b><br>
		<fmt:formatDate value="${today }" type="time" timeStyle="full"/><br><!-- 오후 3시 9분 11초 대한민국 표준시 -->
		<fmt:formatDate value="${today }" type="time" timeStyle="long"/><br><!-- 오후 3시 9분 11초 KST -->
		<fmt:formatDate value="${today }" type="time" timeStyle="medium"/><br><!-- 오후 3:09:11 -->
		<fmt:formatDate value="${today }" type="time" timeStyle="short"/><br><!-- 오후 3:09 -->
		
		
		<hr>
		<b>2022년 9월 29일 목요일 오후 3:00:00 형태로 출력하기</b><br>
		<fmt:formatDate value="${today }" type="both" dateStyle="full" timeStyle="medium" /> <!-- timeStyle은 medium이 기본값이므로 생략가능 -->


		<hr>
		<b>2022/9/29(목)</b><br>
		<fmt:formatDate value="${today }" pattern="yyyy/MM/dd(E)"/><br> <!-- 월 대문자M 사용(소문자m = 분) / 2022/09/29(목) -->
		<fmt:formatDate value="${today }" pattern="yy-MM-dd(E)"/><br> <!-- 월 대문자M 사용(소문자m = 분) / 2022/09/29(목) -->
		<fmt:formatDate value="${today }" pattern="yy-MM-dd(E)" /><br> <!-- 월 대문자M 사용(소문자m = 분) / 2022/09/29(목) -->
	
	
		<hr>
		<b>(오후) 3:00:00</b><br>
		<fmt:formatDate value="${today }" type="time" pattern="(a)hh:mm:ss"/><br> <!-- (오후)03:20:18 -->
		<fmt:formatDate value="${today }" type="time" pattern="[a h:mm:ss]"/><br> <!-- [오후 3:20:18] -->
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
</body>
</html>