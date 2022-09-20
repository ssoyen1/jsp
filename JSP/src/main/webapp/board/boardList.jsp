<%@page import="java.awt.Taskbar.State"%>
<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>boardList.jsp</h1>
		
		<%
			// DB에 있는 정보 가져와서 화면에 출력
			
			// BoardDAO 객체 생성
			BoardDAO dao = new BoardDAO();
		
		
		
			// 저장된 전체 글의 개수 계산 (계산해야 페이지가 몇개 필요한지 파악할 수 있음 -> 워크밴치에서 갯수 세면됨)
			int count = dao.getBoardCount();
			System.out.println("글 개수 : "+count+"개");
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////
			// 페이징 처리(1) 
			 
			// 한 페이지에 보여줄 글의 개수
			int pageSize = 10;
			
			// http://localhost:8088/JSP/board/boardList.jsp?pageNum=2
					// 2페이지를 출력하는 방법
					// pageNum=2처럼 뒤에 페이지 정보를 넣기. 컨트롤 누른채로 마우스오버하면 실행할 수 있음
			
			//  현 페이지가 몇페이지인지 확인
			String pageNum = request.getParameter("pageNum"); // 원래는 전페이지인 wirtePro에 있어야하는 것 아닌가? 
			if(pageNum==null){
				pageNum = "1"; // 1페이지로 돌아간다. so 우리가 지금 이걸 실행하면 무조건 1페이지로 감. 실행한게 없으니
				
			}
			
			// 시작행 번호 계산하기 1, 11, 21, 31, 41 ... (한페이지에 10개만 보여주므로)를 공식화하기
			int currentPage = Integer.parseInt(pageNum); // 페이지 정보를 안받았으니 위의 pageNum 를 받아옴 .
														 // 근데 왜 String 을 사용햇는가 ?
			int startRow = (currentPage-1)*pageSize+1; //1-1x10+1 = 1		1										 
													   //2-1x10+1 = 11
													   //3-1x10+1 = 21
			
			// 끝행 번호 계산하기 10, 20, 30, 40 ...
			int endRow = currentPage * pageSize;
			
			
			
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////
			// 게시판 정보를 가져오는 메서드 
// 			ArrayList boardList = dao.getBoardList(); // 배열 생김
			
			// 게시판 정보를 가져오는 메서드 (페이징 처리 완료)
			ArrayList boardList = dao.getBoardList(startRow, pageSize);
																	// 그냥 가져오는 것과 밑에 저렇게 가져오는게 어떻게 다른가 공부하기
			
			
																	
			
			// 게시판 정보 출력
		%>
		<h3><a href="writeForm.jsp">글쓰기</a></h3>
		<h3><a href="fWriteForm.jsp">파일 글 쓰기</a></h3>
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일</td>
				<td>ip</td>
			</tr>
			<%for(int i=0; i<boardList.size();i++){
				
				BoardDTO dto = (BoardDTO) boardList.get(i);
			
			%>
			
	
			<tr>
				<td><%=dto.getBno() %></td>
				<td>
				<%if(dto.getRe_lev() > 0) {//이조건에 부합할때 이미지태그를 사용하자%> 
					<img src="level.gif" width="<%=dto.getRe_lev()*10%>"> <!-- 답글의 답글이 더 안쪽으로 들어가도록함 -->
					<img src="re.gif">
				<% } %>
					<a href="boardContent.jsp?bno=<%=dto.getBno()%>&pageNum=<%=pageNum%>"><%=dto.getSubject()%></a>
					<!--  바로 위에있는 bno를 가져오는 것 
					&뒤에 공백을 주면안됨. 뛰어쓰기 내맘대로하면안됨. 주소창에 %20 이 찍혀나옴 (%20=공백을 의미)-->
				</td>
				
				<td><%=dto.getName() %></td>
				<td><%=dto.getReadcount()%></td>
				<td><%=dto.getDate() %></td>
				<td><%=dto.getIp() %></td>
			</tr>
			
			<%} %>
		</table>
		
		
		<%
		//////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리 (2) ( 숫자블럭, 이전, 다음블럭)
		if(count !=0) { //count = 글 전체 갯수를 물어보는것 / 
						//= so '글이 있을 때' 를 의미
			// 총페이지 = 글개수(총량) / 페이지당 출력
			// 55 페이지를 10개씩 했을때 나머지 5는 int로 처리하면 없는 셈이됨. 소수점이 없으니까
			// => so 만약에 나머지가 있을때 페이지 1개 추가해야 함
			
			// 전체 페이지수 (에 추가적으로 더해주는 로직)
			int pageCount = (count/pageSize)+(count%pageSize==0? 0 : 1); // 나머지가 0이다 = 딱떨어지므로 안더해도 됨
																		 //			 아니다 = 페이지 1개 더필요하므로 더하기
																	 
			// 한 화면에 보여줄 페이지 수 (밑에 숫자있는 페이지블럭)
			int pageBlock = 2; // 일단 계산하기 쉽게 지정한 것
			
			// 페이지블럭의 시작번호 1~10 =>(제일 처음 번호는) 1번, 11~20 =>1, 21~30 => 21
			int startPage = ((currentPage-1)/pageBlock)*pageBlock+1; // (1-1)/10*10+1 			 
																	 // (5-1)/10*10+1 => 
			
			// 페이지블럭의 끝번호
			int endPage = startPage+pageBlock-1;
			
			if(endPage > pageCount) {
				endPage = pageCount; // 작을때 비교해서 바꿔라.
				
			}
			
			
			// [이전] 블럭
			// 시작페이지와 페이지블럭 갯수를 비교하는 것.
			if(startPage > pageBlock) {
				%>
					<a href="boardList.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
		
				<%
			}
			
			
			
			// 숫자 (페이지링크) 1,2,3,...
			for(int i=startPage; i<=endPage; i++) { // 작거나 같을때까지 돈다.
				
				%>
					<a href="boardList.jsp?pageNum=<%=i%>"><%=i %></a> 
					<!--  클릭한 곳으로 이동하는데 그때 페이지 넘버는 i안에 있으므로 i 넣어주기
						  누르면 그 다음 페이지로 이동함-->
				<% 
			}
		
			// [다음] 
			if(endPage < pageCount) {
				%>
					<a href="boardList.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
													<!--  1페이지에서 2개씩 보여주기로 했으니  -->	
				<%
				
			}
					
		}
		
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		%>
				
		
</body>
</html>