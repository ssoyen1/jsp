<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<!-- <div id="login"><a href="../member/login.html">login</a> | <a href="../member/join.html">join</a></div> -->
	<div id="login">

		<c:if test="${id == null }"><!-- 로그인했을때 session에 로그인정보가 담겨졌기때문에, 따로 dto(X) session~ 적을 필요없이 바로 id 적을 수 있음 -->
			<a href="./MemberLogin.me">login</a> | <a href="./MemberJoin.me">join</a>	
			<!--  <a href="./MemberJoin.me">join</a></div> -->
		</c:if>
		
		<c:if test="${id != null }">
			<a href="./MemberInfo.me">${id }님의 info</a> | <a href="./MemberLogout.me">logout</a>
		</c:if>
	</div>

	<div class="clear"></div>

	<!-- 로고들어가는 곳 -->
	<!-- <div id="logo"><img src="../images/logo.gif" width="265" height="62" alt="Fun Web"></div> -->
	<div id="logo">
		<img src="./images/bday.png" width="265" height="62" alt="Fun Web">
	</div>
	<!-- 로고들어가는 곳 -->
	<nav id="top_menu">
		<ul>
			<li><a href="./main/main.me">HOME</a></li>
			<li><a href="./company/welcome.jsp">COMPANY</a></li>
			<li><a href="#">SOLUTIONS</a></li>
			<li><a href="./center/notice.jsp">CUSTOMER CENTER</a></li>
			<li><a href="#">CONTACT US</a></li>
		</ul>
	</nav>
</header>