package com.itwillbs.action;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



// 컨트롤러를 사용하려면 => 서블릿으로만들기 (상속해서)
//@WebServlet("/board2") //어노테이션
@WebServlet("*.bo") //앞에 주소가 무엇이든지 ~~~.bo 의 형태면 다 가능 
					//
public class BoardFrontController extends HttpServlet {
	
	
	protected void doProcess (HttpServletRequest request, // 컨트롤러 안에있는 매서드
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController - doProcess() 호출");
		System.out.println(" GET / POST 방식 상관없이 한번에 처리");


			//http://localhost:8088/MVC/board
			//////////////////////////// 1. 가상주소 계산단계//////////////////////////////////////
			//https://m.blog.naver.com/suuuk3/221030264413
			//https://all-record.tistory.com/120 //와르파일도 올려줌
			//https://all-record.tistory.com/165 // mvc작동원리
		    // 가상주소 (내가지정한것 (ex).bo)
			// URL => 웹실행 주소
			// URI => URL - (프로토콜, IP, PORT번호)
//			System.out.println(request.getRequestURI());
//			System.out.println(request.getRequestURL());
			String requestURI = request.getRequestURI(); //프로젝트 패스 얻어옴 프로젝트와 파일경로까지 얻어옴 
			System.out.println(" C : (1단계시작) 가상주소 계산 시작-----------------");
			System.out.println(" C : requestURL : "+requestURI); // C : 컨트롤러(가상경로)
			
			String ctxPath = request.getContextPath(); // 프로젝트명(직접경로) 프로젝트 패스 얻어옴 
			System.out.println(" C : ctxPath : " + ctxPath);
			
//			requestURI.substring(0);
//			System.out.println(requestURI.substring(4)); // 아직 안배움
			String command = requestURI.substring(ctxPath.length()); //.substring(시작):  (시작)부터 끝까지 잘라낸 새로운 문자열 리턴
			System.out.println(" C : command : " + command); //폴더?
			
			System.out.println(" C : (1단계끝) 가상주소 계산 완료-------------------");
			
			//////////////////////////// 1. 가상주소 계산단계//////////////////////////////////////
			
			
			
			
			
			
			//////////////////////////// 2. 가상주소매핑///////////////////////////////////////////
			// 실제주소 (ex).jsp/.css/.html)
			System.out.println(" C : (2단계시작) 가상주소 매핑 시작-----------------");
			
			Action action = null; // 미리변수 선언해놓고 밑에선 선언없이 사용
			ActionForward forward = null; //전역변수로 빼놓기 -> if문에 가져다쓰기
										  //Action이 모든 작업을 끝내고서 이동하는 위치을 가상적으로 지정한 것
			
			if(command.equals("/BoardWrite.bo")) { // 같은지 비교
				System.out.println(" C : /BoardWrite.bo 호출");
				System.out.println("[패턴1] DB사용 x, view페이지 이동"); //DB사용없이 바료 뷰페이지로 이동할 수 있게 함
				
				forward = new ActionForward(); //클래스 사용하기위해 객체선언
				forward.setPath("./board/writeForm.jsp"); //목적지 저장하기
				forward.setRedirect(false); //주소에는 jsp나오면안됨! -> false
				
				
		
				
			}  else if (command.equals("/BoardWriteAction.bo")) { // .의 의미는 프로젝트 이름이므로 2단계부터는 .이없는 /부터의 가상주소만?얻어와야햠?뭔소리
								
				// 흰화면이떴는데도 콘솔에 안뜨면? 보통 다 이 주소문제임. .이 이없어야함..?ㅋㅋ
				System.out.println(" C : /BoardWriteAction.bo 호출");
				System.out.println(" C : [패턴2] DB사용 O, 페이지이동(화면전환)"); // 그냥 적었을때 콘솔에서 리로드 뜬다. 서버동작하는방법?. 제일좋은 습관은 작업전 서버 중지 후 작업작성후 서버실행. 결과보는것
																				  // WritheAction -> List
				
				// BoardWriteAction() 객체생성
				// 강한 결함(결합도가 높다 = 이 코드가 잘못되면 다른 코드에 영향을 줄 수 있다)
				//BoardWriteAction bwa = new BoardWriteAction();
				
				// 약한결합 (결합도가 낮다)
				action = new BoardWriteAction(); // 다형성을 구현한것(인터페이스action이므로 BoardWriteAction에 업캐스팅을 한 것이여서 이렇게 불러오는 것)
				
				
				// 명령어에 해당하는 Action 객체를 생성하고 execute( )를 실행한다. 그러면 해당 Action 클래스에서 작업을 처리하고 ActionForward를 반환한다.
				try { //예외발생가능성있는 문장
//				forward = bwa.execute (request, response); // 강한결합 주석하면서 레퍼런스없어졌으므로 밑에처럼바꿔줌
				forward = action.execute (request, response); // BoardWriteAction 인터페이스에서 가져오는..?
															  // 뭔뜻임?
				
				
				} catch (Exception e) {
					e.printStackTrace();
					 //Exception1이 발생했을 경우, 이를 처리하지 위한 문장적는다.
					 //보통 이곳에 예외메세지를 출력하고 로그로 남김.
					
				}
				
				
			}
			
			System.out.println(" C : (2단계끝) 가상주소 매핑 완료-------------------"); // 부산역가서 티켓만끊은 격
			//////////////////////////// 2. 가상주소매핑///////////////////////////////////////////
			
			
			
			
			
			
			
			//////////////////////////// 3. 페이지 이동///////////////////////////////////////////
			System.out.println(" C : (3단계시작) 페이지이동 시작-------------------"); // 
			if(forward != null) { // 이동정보가 있을때(티켓이 있을때)
				if(forward.isRedirect()) { // true (   boolean이라서 (세터게터저장될때 불린으로 저장되었으므로)
										  
					 System.out.println("C : 이동방식 : " + forward.isRedirect() + ", 주소:  " + forward.getPath() );
										   // forward에 어떻게 갈지 저장해놨으므로 불러오기. 
										   // 그자체로 true라는 뜻이기 때문에 굳이 == true라고 적을 필요 없음!
					 					   //ActionForward 객체가 가진 값에 따라 화면 이동을 처리한다. isRedirext가 true 이면 sendRedirect( )를 통해 화면 이동을 처리하고 false라면 forward( )를 통해 화면 이동을 처리한다.시스템(세션, DB 등..)에 변화가 생기는 작업의 경우 sendRedirect( )를 아닐 경우(화면전환, 조회 등..) forward( )를 이용한다.
		
					response.sendRedirect(forward.getPath());  // redirect 하기로 설정되어있다면
															   // 설정한 URL로 이동하기(forward.getPath()로 얻음)
				}else { //false
					 System.out.println("C : 이동방식 : " + forward.isRedirect() + ", 주소:  " + forward.getPath() );
					
					RequestDispatcher dis
						= request.getRequestDispatcher(forward.getPath()); //없으면 저장해라
																		   //  클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
																		   // (차이점)
																		   // (1) response.sendRedirect('jsp경로'); 
																		   //    : 서블릿에서 값을 넘겨주지 않고 페이지만 이동하는 방법
																		   // (2) RequestDispatcher ~ ("경로");
																		   //    : 서블릿에서 값을 넘겨주고 해당 페이지에서 처리할 수 있도록 하는 방법
					
					dis.forward(request, response); //호출 - 설정한 경로로 request, response 객체 다 넘겨줌
					
				}
				
			}
			
			System.out.println(" C : (3단계끝) 페이지이동 완료---------------------"); // 부산역가서 티켓만끊은 격
			
			
			//////////////////////////// 3. 페이지 이동///////////////////////////////////////////
			
			
	
	}// doProcess
	
	
	
	// http://localhost:8088/MVC/board (web.xml 매핑)
	// http://localhost:8088/MVC/board2 (어노테이션)
	
	// alt shift s + v
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n BoardFrontController - doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n BoardFrontController - doPost() 호출");
		doProcess(request, response);
	}

	
		
}
