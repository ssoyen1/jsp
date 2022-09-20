<%@page import="java.io.File"%>
<%@page import="com.itwillbs.board.BoardDAO"%>
<%@page import="com.itwillbs.board.BoardDTO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>fWritePro.jsp</h1>
		<%
			// 첨부파일이 포함된 글쓰기(일반writePro와 순서 다름!)
			
				// (1) 업로드
				// - uproad 폴더 생성 (만들었으므로 여기서는 따로 만들지 X)
				// - 업로드할 폴더 위치 저장
				String realPath = request.getRealPath("/upload");
				System.out.println("realPath : "+realPath); // 내가 무엇을 출력하는지 데이터와함께 보여주기 콘솔창에
				
				// - 업로드 파일 크기 20MB
				int maxSize = 20 * 1024 * 1024;
				
				// - 업로드 객체 MultipartRequest 생성
				MultipartRequest multi = 
					new MultipartRequest(
								request,
								realPath, 
								maxSize,
								"UTF-8",
								new DefaultFileRenamePolicy()
								);
			
			System.out.println("첨부파일 업로드 성공!");
			
			
			
			// (2) 전달된 정보 처리(파라메터)
				//	(게시판 제목, 글쓴이 ,비밀번호, 내용, 파일명)
				// - 전달정보 한번에 저장객체 BoardDTO 생성
				BoardDTO dto = new BoardDTO();
			
				dto.setSubject(multi.getParameter("subject")); //subject는 multi~안에 객체 안에 있음
				dto.setName(multi.getParameter("name"));
				dto.setPass(multi.getParameter("pass"));
				dto.setContent(multi.getParameter("content"));
				dto.setFile(multi.getFilesystemName("file")); // X 오리지날네임
				
				dto.setIp(request.getRemoteAddr()); //ip는 파라미터로 주는게 아님
				
				//BoardDAO 파일 글쓰기 동작 호출
				BoardDAO dao = new BoardDAO(); // 이 객체가 있어야 DB에대한 모든 수행을 할 수 ㅇ
											   // 파일메서드가dao안에 없음.
				dao.insertBoard(dto);		   // insert 안에 파일까지 불러오게 작성했었음. 그래서 이거 부르면 됨
				
				
				// 페이지 이동(List)
				response.sendRedirect("boardList.jsp");
			
		
			// 
		%>
	
			
			
</body>
</html>