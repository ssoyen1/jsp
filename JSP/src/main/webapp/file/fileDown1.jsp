
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
<!-- 0919 -->		
				<%
				
				
				// 전달할 파일 이름 정보 저장
				String fileName = request.getParameter("file_name");
				
				
				//서버에 업로드된 폴더명
				String savePath = "upload";
				
				
				// 서버의 업로드된 폴더의 실제위치 
				ServletContext CTX = getServletContext(); // context = project -> 서블릿프로젝트 /project = 지금 사용하고 있는 로직
														  // 객체 생성
				String downloadPath = CTX.getRealPath(savePath); // 이 이름을 가진 대상의 실제경로를 불러오고 이것을 변수에 저장한다.
				
				System.out.println("downloadPath : "+downloadPath); //폴더까지만 접근함.
				
				
				// => 두개를 합치면 => 다운로드할 파일의 위치
				String filePath = downloadPath+"\\"+fileName; // 
				System.out.println("filePath : "+filePath); // 사진까지 뜸.
															// 이 페이지에서 실행하면 filePath는 null 로 뜸
															// 하지만 form페이지에서 업로드를 해주면 제대로 뜸
															
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				// 파일을 다운로드 준비
				
				// 파일을 한번에 읽고/쓰기를 수행할 배열만들기
				byte[] b = new byte[4096]; // 4 x 1024 = 4KB
				
				
				
				// 파일 입력 스트립 객체 (원하는 경로에있는 파일 열어주는 객체)
				//						  안의 내용을 그대로 복사해서 원하는 페이지에 가져다놓아야하므로
				//						  파일로부터 바이트로 입력받아서 바이트단위로 출력받을 수 있는 클래스
				FileInputStream fis = new FileInputStream(filePath); // String ~ 선택하기
				//파일의 내용을 byte 단위로 읽어 오는 클래스입니다. String 으로 변환 해야 합니다.
				
				
				
				// 다운로드할 파일의 MIME타입 확인
				String MIMEType = getServletContext().getMimeType(filePath);
				System.out.println("MIMEType : "+MIMEType);
				
				// MIME 타입 
				// : 다운로드 받을때에는 확장자가 무엇인지 고려해야함. 실행하는 동작이 다르므로
				//   ex이미지, 텍스트 각각 을 실행할 도구를 준비할 수 있으므로 거기에대한 정보를 적는 것
				// MIME 타입의 값이 없을 경우 기본값으로 설정 됨
				if(MIMEType == null) { // 이 파일을 다룰 특별한 프로그램이 없다.
					MIMEType = "application/octet-stream"; // 기본값
												//결국 MIME의 개별 타입 중 application에 속하는 타입인데, 8비트 단위의 binary data라는 뜻
												//"특별히 표현할 수 있는 프로그램이 존재하지 않는 데이터의 경우 기본값으로 octet-stream을 사용한다." = 브라우저가 보통 자동으로 실행하지 않거나 실행할지 묻기도 하는 타입이다~
												//Content-Disposition 헤더를 attachment 로 줌으로써 해당 데이터를 수신받은 브라우저가 파일을 저장 또는 다른이름으로 저장 여부를 설정하게 할 수 있다.
					
				}
				
				// 응답할 페이지의 형태를 MIME 타입의 형태로 변경
				response.setContentType(MIMEType); // 제일위에있는 contentType="text/html을 코드로 적은 것. "이것을 MIME타입으로 바꾸겠다"
											       // pdf를 보내면 pdf뷰어로, 이미지를 넣으면 이미지뷰어로 바꾸겠다. 
											       // HTTP 헤더 부분의 Content-Type을 정의
											       // setContentType("text/plain");
			
				
				
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/// ie (internet exploer) - 에대한 한글처리
				
				// 사용자의 브라우저 정보 확인
				String agent = request.getHeader("User-Agent");
				
				boolean ieBrowser 
				= (agent.indexOf("MSIE")> -1) || (agent.indexOf("Trident") > -1);
															// ' 이 문자들이 포함되어있으면 인터넷익스플로어다 '
															
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
															//한글처리			//공백문자 변경	(\\을 공백으로 대체.익스플로어에서는 \\가 입력이안되므로)
				}else{
					// ie 아닐때
					fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1"); // utf파일로 바꾸고 또다른 인코딩(iso-8859-1)으로 바꿔서 써야함
				}
															
				// => 한글 인코딩처리(파일명)
				
						
						
				// 모든 파일을 다운로드 형태로 처리
				response.setHeader("Content-Disposition", "attachment; filename="+fileName);
					// response.setHeader : (내가실행하는 응답정보(실행하는 모습을 결정하는 객체)
					// "Content-Disposition", "attachment; : 모든파일이 다운로드되도록 해주는것
					
				
					
				// 기본 생성되는 내장객체 out 처리	
				out.clear();  // output stream을 닫고 버퍼를 비움	. out이 존재하는데 out이 또 생성됐으므로 걔를 없애줌	 
				out = pageContext.pushBody();
					
				
				
				// 다운로드
				// 다운로드 하기위한 준비 (통로 생성) = 데이터를 보낼 준비 (파일을 다운로드할 목적지에 똑같이보내준다)
				ServletOutputStream out2 = response.getOutputStream();
											//fileinputStream:파일여는것
											// 인풋스트림으로 열려있는데 이걸 보내려면 통로가 필요함. 그 통로 = 아웃풋스트림
											//OutputStream : 출력하는 것
				
				int data=0;
				while( (data = fis.read(b,0,b.length)) != -1 ){  //"파일인풋스트림을 통해 파일을 읽을 것이다."
					out2.write(b,0,data);						 // 배열을 사용하여 0부터 길이까지 적어 가져옴. 
																 // -1의 의미 : 파일의 끝 (end of file) .
																 // -1 이 아닐때 : 파일의 끝이 아닐 떄
																 // out2.write(b,0,data) : 배열의 데이터를 데이터와 함께 출력하겠다.
				}												 //while( (data = fis.read(b,0,b.length)) != -1 ){ 
																 // : 반복문이 돌때마다 데이터1줄씩 가져옴.
				
																 
				
																 
																 
				// 배열을 사용하여 정보 전달										 
				//-> 배열의 빈공간에 공백을 채워서 정보 전달 
				out2.flush();	// 플러싱. -> 배열의 빈공간에 공백을 꽉채워서 정보 전달 //포카칩 질소. 온전히 과자를 그대로 보존하기위해. 
								// 마지막에 공백이 남아서 못넘어오는 데이터가 없도록함. // 데이터를 온전히 보존해서 보내기위해.
				out2.close();   // 데이터 소모 위해 닫아주기.
				fis.close();
		%>
</body>
</html>