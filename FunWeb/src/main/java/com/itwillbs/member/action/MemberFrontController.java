package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" Member - doProcess() ");

		// 1. 가상주소 계산
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : " + requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath : " + ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command :" + command);

		System.out.println(" C : 1. 가상주소 계산 끝 /n");

		
		   // 2. 가상 주소 매핑(패턴 1,2,3)
		   // 패턴1) 컨트롤러에서 바로 페이지이동 (false)
		   // 패턴2) 디비사용-> 페이지이동 후 출력 ('가상주소-> 가상주소'로 이동 후 출력하는 것이므로 true -> else if 를 두번 거쳐야한다
		   //																				여기서 false로 마무리)
		   // 패턴3) 디비사용-> 뷰출력 (바로jsp파일로 출력하기때문에 '가상주소-> jsp'= false)
	       System.out.println(" C : 2. 가상주소 매핑 시작");
	       
	       Action action = null;
	       ActionForward forward = null;
	       
	       if(command.equals("/MemberJoin.me")) {
	         System.out.println(" C : /MemberJoin.me 호출");
	         System.out.println(" C : 패턴1) DB사용 X, view 이동");
	         
	         forward = new ActionForward();
	         forward.setPath("./member/join.jsp");
	         forward.setRedirect(false);       // 가상주소를 실행시키고 있기 때문에 false
	         
	       } 
	       
	       
	       else if(command.equals("/MemberJoinAction.me")) {
	         System.out.println(" C : /MemberJoinAction.me 호출");
	         System.out.println(" C : 패턴2) DB사용 O, 페이지 이동");
	         
	         // MemberJoinAction 객체
	         action = new MemberJoinAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
		
	       }
	       
	       
	       else if(command.equals("/MemberLogin.me")) {
	    	   System.out.println(" C : /MemberLogin.me 호출");
		       System.out.println(" C : 패턴1) DB사용 X, view 이동");
	    	   
		       forward = new ActionForward();
		       forward.setPath("./member/login.jsp");
		       forward.setRedirect(false); // 포워딩   
		         
	       }
	       
	       
	       else if(command.equals("/MemberIdCheck.me")) {
	    	   System.out.println(" C : /MemberIdCheck.me 호출");
		       System.out.println(" C : 패턴1) DB사용 X, view 이동");
	    	   
	    	   forward = new ActionForward();
	    	   forward.setPath("./member/idCheck.jsp");
	    	   forward.setRedirect(false);
	    	   
	    	   
	       }
	       
	       
	       else if(command.equals("/MemberIdCheckAction.me")) {
	    	   System.out.println(" C : /MemberIdCheckAction.me 호출");
		       System.out.println(" C : 패턴3) DB사용 O, view페이지에 바로 출력"); // 2번 : 다른페이지 이동 / 3번 : 그페이지에 바로 출력
	    	   
		       //MemverIdCheckAction() 객체 생성
		       action = new MemberIdCheckAction();
		       
		       try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       
	       }
	       
	       
	       else if(command.equals("/MemberLoginAction.me")) {
	    	   System.out.println(" C : /MemberLoginAction.me 호출");
	    	   System.out.println(" C : 패턴2) DB사용 O, 페이지 이동");
	    	   
	    	   // MemberLoginAction() 객체 
	    	   action = new MemberLoginAction();
	    	   try {
				forward = action.execute(request, response); // 이렇게해야 액션페이지 갔따왔을때 페이지이동을 할 수 있으므로 
			} catch (Exception e) {
				e.printStackTrace();
			}
	       }
	       
	       
	       else if(command.equals("/Main.me")) {
	    	   System.out.println(" C : /Main.me 호출");
	    	   System.out.println(" C : 패턴1) DB사용X, view이동");
	    	   
	    	   forward = new ActionForward();
	    	   forward.setPath("./main/main.jsp");
	    	   forward.setRedirect(false); // 포워딩해야함
	    	   
	       }
	       
	       
	       else if(command.equals("/MemberLogout.me")) {
	    	   System.out.println(" C : /MemberLogout.me 호출");
	    	   System.out.println(" C : 패턴2) 비지니스로직 O, 페이지 이동");
	    	   
	    	   // MemberLogoutAction()
	    	   action = new MemberLoginAction();
	    	   
	    	   try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	       }
	       
	       
	       else if(command.equals("/MemberInfo.me")) {
	    	   System.out.println(" C : /MemberInfo.me 호출");
	    	   System.out.println(" C : 패턴3) DB사용ㅇ, 페이지출력");
	    	   
	    	   // MemberInfoAction() 
	    	   action = new MemberInfoAction();
	    	   
	    	   try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	       
	       
	       else if(command.equals("/MemberUpdate.me")) {
	    	   System.out.println(" C : /MemberUpdate.me 호출");
	    	   System.out.println(" C : 패턴3) DB사용ㅇ, 페이지출력"); // info와 같은 동작임
	    	   
	    	   //MemberUpdate()
	    	   action = new MemberUpdate();
	    	   try {
				forward = action.execute(request, response); // 안에있는 execute메서드 호출하기
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	       
	       
	       
	       else if(command.equals("/MemberUpdatePro.me")) {
	    	   System.out.println(" C : /MemberUpdatePro.me 호출");
	    	   System.out.println(" C : 패턴2) 비지니스로직 O, 페이지 이동");
	    	   
	    	   // MemberUpdateProAction() 객체
	    	   action = new MemberUpdateProAction();
	    	   
	    	   try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	       }
	       
	       
	       
	       else if(command.equals("/MemberDelete.me")) {
	    	   System.out.println(" C : /MemberDelete.me");
	    	   System.out.println(" C : 패턴1) DB사용X, view 페이지이동");
	    	   
	    	   forward = new ActionForward();
	    	   forward.setPath("./member/delete.jsp");
	    	   forward.setRedirect(false);
	       }
	       
	       
	       
	       else if(command.equals("/MemberDeleteAction.me")) {
	    	   System.out.println(" C : /MemberDeleteAction.me");
	    	   System.out.println(" C : 패턴2) 비지니스로직 O, 페이지 이동");
	       
	    	   
	    	   // MemberDeleteAction() 객체 생성
	    	   action = new MemberDeleteAction();
	    	   
	    	   
	    	   try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	       }
	       
	       else if(command.equals("/MemberAdmin.me")) {
	    	   // 디비에있는 모든 정보를 가져와서 화면에 출력할 것.
	    	   // 유저정보 리스트 볼 수 있도록 할 것.
	    	   System.out.println(" C : /MemberAdmin.me 호출");
	    	   System.out.println(" C : 패턴3) DB사용ㅇ, view출력");
	    	   
	    	   // MemberAdminAction() 객체
	    	   action = new MemberAdminAction();
	    	   
	    	   try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	       
	       else if(command.equals("/MemberAdminDeleteAction.me")) {
	    	   System.out.println(" C : /MemberAdminDeleteAction.me 호출");
	    	   System.out.println(" C : 패턴2) DB사용O, 페이지 이동");
	    	   
	    	   action = new MemberAdminDeleteAction();
	    	   
	    	   try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	       }
	       
	       
	       
	       
	       
	       
		
//		
//		// 2. 가상주소 매핑(패턴1,2,3)
//
//		ActionForward forward = null; // 액션포워드의 레퍼렌스 변수를 선언해주기. 밑에 사용할거잖아.
//		Action action = null;
//		
//		
//		if (command.equals("/MemberJoin.me")) {
//			System.out.println(" C : /MemberJoin.me 호출");
//			System.out.println(" C : 패턴1) DB 사용X, view 이동"); // 바로 이동할 수 있도록 객체 하나 만듬
//
//			forward = new ActionForward();
//			forward.setPath("./member/join.jsp");
//			forward.setRedirect(false); // 가상주소를 실행시키고 있기때문에 false
//
//		} 
//		else if(command.equals("/MemberJoinAction.me")) {
//			System.out.println(" C : /MemberJoinAction.me 호출");
//			System.out.println(" C : 패턴2) DB사용ㅇ, 페이지 이동");
//		}
//		
//		// MemberJoinAction() 객체 생성
//		action = new MemberJoinAction();
//		
//		try {
//			forward = action.execute(request, response);// 포워드 통해서 리턴받아주고~ 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		
		
		
		
		
		
		System.out.println(" C : 2. 가상주소 매핑(패턴1,2,3 끝)");

		// 3. 페이지 이동
		if (forward != null) {
			if (forward.isRedirect()) { // true -> send redirect
				System.out.println(" C : sendRedirect() : " + forward.getPath());
				response.sendRedirect(forward.getPath());
			} else { // false -> forward

				System.out.println(" C : forward() : " + forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response); // 보드랑 다 같음.
			}
		}
		System.out.println(" C : 3. 페이지 이동 끝");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" Member - doGet() ");
		doProcess(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" Member - doPost() ");
		doProcess(request, response);
	}

}
