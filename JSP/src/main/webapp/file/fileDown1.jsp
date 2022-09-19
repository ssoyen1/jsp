
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>fileDown1.jsp</h1>
		
				<%
				// 전달할 파일 이름 정보 저장
				String fileName = request.getParameter("file_name");
				
				
				//서버에 업로드된 폴더명
				String savePath = "upload";
				
				
				// 서버의 업로드된 폴더의 실제위치
				ServletContext CTX = getServletContext(); // context = project -> 서블릿프로젝트 /project = 지금 사용하고 있는 로직
														  // 객체 생성
				String downloadPath = CTX.getRealPath(savePath); // 이 이름을 가진 대상의 실제경로를 불러오고 이것을 변수에 저장한다.
				
				System.out.println("downloadPath : "+downloadPath);
				
				
				// 다운로드할 파일의 위치
				String filePath = downloadPath+"\\"+fileName; // 
				System.out.println("filePath : "+filePath); // 이 페이지에서 실행하면 filePath는 null 로 뜸
															// 하지만 form페이지에서 업로드를 해주면 제대로 뜸
															
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				// 파일을 다운로드 준비
				
				// 파일을 한번에 읽고/쓰기를 수행할 배열만들기
				byte[] b = new byte[4096]; // 4 x 1024 = 4KB
				
				
				// 파일 입력 스트립 객체 (파일 열기)
				FileInputStream fis = new FileInputStream(filePath); // String ~ 선택하기
				
				
				// 다운로드할 파일의 MIME타입 확인
				String MIMEType = getServletContext().getMimeType(filePath);
				System.out.println("MIMEType : "+MIMEType);
				
				// MIME 타입의 값이 없을 경우 기본값으로 설정 됨
				if(MIMEType == null) {
					MIMEType = "application/octet-stream";
					
				}
				
				// 응답할 페이지의 형태를 MIME 타입의 형태로 변경
				response.setContentType(MIMEType); // 제일위에있는 contentType="text/html을 코드로 적은 것. "이것을 MIME타입으로 바꾸겠다"
											       // pdf를 보내면 pdf뷰어로, 이미지를 넣으면 이미지뷰어로 바꾸겠다. 
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/// ie (internet exploer) - 에대한 한글처리
				
				// 사용자의 브라우저 정보 확인
				String agent = request.getHeader("User-Agent");
				
				boolean ieBrowser 
				= (agent.indexOf("MSIE")> -1) || (agent.indexOf("Trident") > -1);
															// 문자위치에 내가 원하는게 있는지 없는지 판단해주는 메서드
															// User-Agent중에 MSIE이게 있으면 index가 리턴될 것.
															// ("MSIE")> -1 의 의미! 
															// 문자열.indexOf("문자") : 문자가 포함되어 있을 때 해당 위치 index 리턴,
															//						    	   불포함 되어있을 때 -1 리턴
															// ex) String a = Apple
															// 	   a.indexOf("A");  --> 0리턴 (정보있음)
															// 	   a.indexOf("B");  --> -1리턴 (정보없음)
															// -1보다 크다 = 인덱스가 있다 = 글자를 포함하고 있다.
															// String 은 0부터 시작
															
															// 두 글자중 하나라도 포함되어있으면 ie 이다.
															
				if(ieBrowser){
					// ie 일때
					fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+","%20");//java.net / 우리가 가져온 fileName을 UTF-8로 바꾸겠다.
					
				}else{
					// ie 아닐때
					fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1"); // utf파일로 바꾸고 또다른 인코딩(iso-8859-1)으로 바꿔서 써야함
				}
															
				// => 한글 인코딩처리(파일명)
				
						
						
				// 모든 파일을 다운로드 형태로 처리
				response.setHeader("Content-Disposition", "attachment; filename="+fileName);
				
				
				// 다운로드
				// 다운로드 하기위한 준비 (통로 생성)
				ServletOutputStream out2 = response.getOutputStream();
				
				int data=0;
				while( (data = fis.read(b,0,b.length)) != -1 ){ 
					out2.write(b,0,data);
				}
				
				out2.flush();
				out2.close();
				fis.close();
		%>
</body>
</html>