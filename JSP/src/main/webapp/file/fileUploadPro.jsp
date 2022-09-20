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
		<h1>fileUploadPro.jsp</h1>
		
		<%	
			// (1) 파일 업로드 장소 -> upload폴더 (가상경로!  이클립스 upload폴더)
			// 실제 파일이 저장되는 곳은(서버안에 upload 폴더)
			
			String path = request.getRealPath("/upload"); //업로드라는 걸 실제주소를 가져와서 저장하게 폴더만들기
														  // 서버에 저장되는 공간
			System.out.println("path : "+path);
			//path : D:\workspace_jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSP
			// D:\workspace_jsp\JSP\src\main\webapp와 다름
		
			
			
			
			// (2) 업로드 파일의 크기 제한 : 10MB 
			int maxSize = 10 * 1024 * 1024; //bit(10). 비트는 2진 계산이므로 2진수로 표현할 수 있는 1000에 가까운 숫자가 1024임.
		
			
			
			// (3) 파일 업로드
			MultipartRequest multi// lib에 cos.jar을 추가했기때문에 사용할 수 있는 것. 
				= new MultipartRequest(
						request,//내장객체. 폼태그의 정보를 받아야함.
						path, // 파일 업로드될 경로(서버의 경로)
						maxSize,//파일 업로드 크기
						"UTF-8", //파일명 인코드
						new DefaultFileRenamePolicy() //파일이름 중복처리 객체
						
						);
			
			System.out.println("파일 업로드 성공!");
			
			// (4) 전달받은 정보를 저장 (이름, 메세지 출력)
			
// 			String name = request.getParameter("name");
			String name = multi.getParameter("name");
// 			String msg = request.getParameter("msg");
			String msg = multi.getParameter("msg");
			
			System.out.println("이름 : "+name+", 메세지 : "+msg);
			
// 			String file = multi.getParameter("file");// 안나옴. 파라미터형태이긴 하지만 파라미터로 받을 수 없는 정보임
			String file = multi.getFilesystemName("file");  // => 서버에 저장되는 파일명 (중복파일 일 경우 이름 변경됨)
																			// 이름 : 작성자4, 메세지 : 123
																			// 파일명 : tea.jpg
			String o_file = multi.getOriginalFileName("file");// => 원래 파일명
			System.out.println("파일명 : "+file); 
			System.out.println("파일명_o : "+o_file); 
			
			
			
			
		%>
		
			<a href="fileDown1.jsp?file_name=<%=o_file%>"> 다운로드 페이지 이동1</a> <!-- 다운로드 하려면 업로드한 대상중 다운로드할 대상의 정보가 필요함 -->
			<hr>																     <!-- 파일네임이있어야 다운로드 할 수 있으니 보낼 파일네임 적어주기 
																					 주소표시줄에 정보를 전달하면 뜸.  
																					 fileDown1파일만 있다면 다운로드 할 수 있따: "다운로드 모듈"-->
			<a href="../upload/<%=o_file%>"> 다운로드 페이지 이동2</a>
		
		
</body>
</html>