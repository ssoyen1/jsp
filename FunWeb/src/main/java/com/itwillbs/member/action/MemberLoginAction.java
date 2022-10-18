package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
			System.out.println(" M : MemberLoginAction_execute() 호출");
			
			
			// 한글처리(생략)
			// 전달정보 저장(id,pw)
			String id = (request.getParameter("id"));
			String pw = (request.getParameter("pw"));
			System.out.println(" id, pw 값 전달 성공");
			
			// DAO 객체 생성 - 로그인 여부 체크 
			MemberDAO dao = new MemberDAO(); // 로그인체크 메서드 불러오기
			
			int result = dao.memberLogin(id, pw); //메서드에서 리턴을 result값으로 받았으므로 int result 적기.
			
			
			
			// 체크 결과에 따른 페이지 이동(JS)
			// 자바스크립트 에서 사용하기위해서 변경해야함.
			
			if(result == 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('비밀번호 오류!')");
				out.print("history.back();");
				out.print("</script>");
				out.close();
				return null; //null이란 Controller에서 페이지 이동하지않겠다는  의미
			}
			
			if(result == -1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원 정보 없음!')");
				out.print("history.back();");
				out.print("</script>");
				out.close();
				return null; 
			}
			
//			if(result == 1) {
			// 로그인 성공 -> 아이디 세션영역에 저장 // 왜?
			//jsp페이지가 아닌 java페이지기때문에 java에는 session내장객체가 없다.
			//session.setAttribute("id", id); 한줄로는 실행이 안되니까 HttpSession session = req.getSession(); 로 불러와준다
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.me");
			forward.setRedirect(true); // 리다이렉트 ( 로그인엑션.me에서 main.me로 가므로 가상주소->가상주소 = true!!)
			return forward;
			
			
	}

}
