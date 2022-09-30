<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>fmt_setLocale.jsp</h1>
		
		<c:set var="today" value="<%=new Date()%>"/> <!-- java.util -->
		<h3> today : ${today }</h3>
		
		<hr>
		<h2>우리나라</h2>
		<fmt:setLocale value="ko_kr"/>
		<h3> today : ${today }</h3>
		<fmt:formatDate value="${today }" dateStyle="full"/><br>
		<fmt:formatNumber value="100000000" type="currency"/><br>
		
		<hr>
		<h2>미국</h2>
		<fmt:setLocale value="en_us"/>
		<fmt:formatDate value="${today }" dateStyle="full"/><br>
		<fmt:formatNumber value="100000000" type="currency"/><br>
		
		<hr>
		<h2>일본</h2>
		<fmt:setLocale value="ja_jp"/>
		<fmt:formatDate value="${today }" dateStyle="full"/><br><!-- 국가데이터에 맞게 포매팅해서 만들어 주는것 -->
		<fmt:formatNumber value="100000000" type="currency"/><br>
		
		
		
		
</body>
</html>