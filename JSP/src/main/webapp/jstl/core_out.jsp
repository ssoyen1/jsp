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
<!-- 0927 (2) -->
			<h1>core_out.jsp</h1>
			
						
			out.print() / JSP표현식 [%= %] => 대신하는 출력형태 (EL표현식처럼)
						  라이브러리안에 tag정보들있어야 가능함!<br>
			JSTL 코드는 HTML 영역에 작성한다. <br>
			
			<c:out value="안녕하세요. ITWILL 입니다."></c:out> <br><!-- 그냥html 태그처럼 되어있음 -->
			<c:out value="안녕하세요. ITWILL 입니다."/>
			
			
			
			
			<hr>
			10 * 10 = 출력하기<br>
			[JSTL] : <c:out value="10 * 10"/>(X)<br>
			[JSTL] : <c:out value="${10 * 10}"/>(O)<br>
			[JSTL] : <c:out value="<%=10 * 20 %>"/>(O)<br> <!-- 스크립틀릿에있는 변수까지 가져다가 출력할 수 있다. -->
			[JSP] : <%=10 * 10 %><br>
			
			<hr>
			* null 값을 출력할 때 공백으로 출력(el표현식)<br>
			  c:out - default 값을 사용하면 null때의 기본값을 사용할 수 있다.<br>
			<c:out value="${member.name }" default="기본값입니다."/><br> <!-- member라는 객체안에 name이라는게 있어서 출력하겠다.
																		하지만 저장한 적 없음 => null => 안나옴.페이지실행은 O
																		default로 지정하면 정한 내용을 출력할 수 있음.-->
<%-- 		<%=member.name %>				 <!-- 컴파일에러 뜸 => 페이지자체 실행X -->	 --%>
			
			* 비교연산자 lt(<)<br>
			  escapeXml : <,> 특수기호를 처리할때 사용 (default - false)<br>
			<c:out value="${10 < 20}" escapeXml="true"/> <br>
<%-- 		<c:out value="${10 &lt; 20}" escapeXml="true"/> <br> --%>
			<c:out value="${10 lt 20}" escapeXml="false"/> <br>
			
			
			<hr>
			<abc>는 abc태그입니다.<br>
			
			&lt;는 abc태그입니다.<br>
			
			<c:out value="<<abc>는 abc태그입니다"/><br>
</body>
</html>