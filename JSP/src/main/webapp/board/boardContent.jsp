<%@page import="com.itwillbs.board.BoardDTO"%>
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
	<h1>boardContent.jsp</h1>
	<%												 //import하는 이유 : 내가 가진게 없으니 추가해서쓰는 것.
				//boardContent.jsp?bno=70&pageNum=1

				// 선택한 글 번호 가져오기
				int bno = Integer.parseInt(request.getParameter("bno"));        
							//DB에 정보를 가져갈때 bno에서 , 사용할 정보까지 만듦.
							// jsp에서의 데이터파일과 DB에서의 데이터파일이 같아야함.
							// 위의 int bno와 String pageNum의 차이점
							// : DTO(워크밴치에 있는 정보들을 한번에 저장할 수 있게하는 자바 클래스 객체?)
							// : bno (DB저장되어있으므로 사용 가능)
							// : String pageNum (저장X 굳이 int로 안한 이유는 고쳐봤자 파라미터로 보낼때 또 String이 됨.
							//		연산을 하는 것도 아니고 그냥 페이지 표시만 하는 것이므로 굳이 int로 안바꾸는 것!)
	
							
				// 페이지 정보 가져오기
				String pageNum = request.getParameter("pageNum");
				
							
				// BoardDAO 객체 생성 (DB 쿼리를 사용해아하므로 만들어줌)
				BoardDAO dao = new BoardDAO(); // 빨간줄 뜰때 DAO는 가져오는 것이므로 무조건 ctrl+space 후 저장해주기
													
				
				// 글 조회수 1증가 동작(조회수가 수정된다. 기존의 값+1)
				dao.updateReadcount(bno);
				
				
				// 게시판 글 정보를 가져와서 출력
				BoardDTO dto = dao.getBoard(bno); // dto를 리턴했으므로 적은 것.!!! (???)
				
			
// 				System.out.println(dto);
				%>
				
				<table border="1">
					<tr>
						<td>글번호</td>
						<td><%=dto.getBno() %></td>
						<td>조회수</td>
						<td><%=dto.getReadcount() %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><%=dto.getReadcount() %></td>
						<td>작성일</td>
						<td><%=dto.getDate() %></td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="3"><%=dto.getSubject() %> </td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="3"><%=dto.getContent() %></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="3">
							<a href="../file/fileDown1.jsp?file_name=<%=dto.getFile()%>"><%=dto.getFile() %></a></td>
							<a href="../upload/<%=dto.getFile()%>"><%=dto.getFile()%></a>
					</tr>
					<tr>
						<td colspan="4">
							<input type="button" value="수정" 
									onclick="location.href='updateForm.jsp?bno=<%=dto.getBno()%>&pageNum=<%=pageNum%>';">
							<input type="button" value="삭제" 
									onclick="location.href='deleteForm.jsp?bno=<%=dto.getBno()%>&pageNum=<%=pageNum%>';">
							<input type="button" value="답글" 
									onclick="location.href='reWriteForm.jsp?bno=<%=dto.getBno()%>&re_ref=<%=dto.getRe_ref()%>&re_lev=<%=dto.getRe_lev()%>&re_seq=<%=dto.getRe_lev()%>';">
							<input type="button" value="목록" 
									onclick="location.href='boardList.jsp?pageNum=<%=pageNum%>';">
						</td>
					</tr>
				</table>
				
	
	
	
	
	
</body>
</html>