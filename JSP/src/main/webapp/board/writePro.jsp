<%@page import="com.itwillbs.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writePro</title>
</head>
<body>

      <h1>writePro.jsp</h1>
      
      <%
         // 한글처리
         request.setCharacterEncoding("UTF-8");
         
      %>
<!--          자바빈 객체 => BoardDTO(Data Transfer Object) (=BoardBean) -->
<!--          전달된 파라메터를 저장(액션태그로) -->
            <jsp:useBean id="dto" class="com.itwillbs.board.BoardDTO"/>

            <jsp:setProperty property="*" name="dto"/>
            <!-- 만들어놓은 dto 객체에 저장할 것! but writeForm에서 받아온 제목, 작성자, 비밀번호, 내용만 저장한 것-->
<%--             <%=dto.toString() %> --%>
<!-- BoardDTO [bno=0, name=라마바, pass=1234, subject=가나다, content=ㄱㄴㄷㄹ, readcount=0, re_ref=0, re_lev=0, seq=0, date=null, ip=null, file=null] -->
            
            <%
               //DTO객체에 IP주소를 추가적으로 저장!
               dto.setIp(request.getRemoteAddr());
            
            %>
            <%=dto.toString() %>
<!--BoardDTO [bno=0, name=라마바, pass=null, subject=가나다, content=ㄱㄴㄷㄹ, readcount=0, re_ref=0, re_lev=0, seq=0, date=null, ip=0:0:0:0:0:0:0:1, file=null] -->
            <!--  ip=0:0:0:0:0:0:0:1 는 ipv6 우리가 아는 아이피 주소는 ipv4 -->
            
            
            <%
               // 저장된 글 정보(DTO)를 DB로 저장하기
               // BoardDAO 객체 생성
               BoardDAO dao = new BoardDAO();
            
               // 글쓰기 메서드 호출
               dao.insertBoard(dto);      // 액션태그로 만들었던 dto를 가져왔음
            
               
               
               // 리스트 페이지로 이동!
               response.sendRedirect("boardList.jsp");
               
            %>
            
            
</body>
</html>