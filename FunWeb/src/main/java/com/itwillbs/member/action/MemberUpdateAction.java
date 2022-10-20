package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberUpdateAction_execute 호출 ");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id"); // 페이지에서 로그인이 된 상태여야하는데 로그인정보는 세션에 저장되어있으므로
		
		ActionForward forward = new ActionForward();
		if(id == null) { //여기서 바로 움직이도록
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		
		
		// DAO - 기존의 회원정보 가져오기(getMember(ID))
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		
		
		// 정보 request 영역 저장 (java -> jsp에 보내기위해서 저장)
		request.setAttribute("dto", dto);
		
		//request.setAttribute("dto", dao.getMember(id));
		
		// 페이지 이동
		forward.setPath("./member/update.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
