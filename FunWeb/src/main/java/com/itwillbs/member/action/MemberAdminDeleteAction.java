package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberAdminDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println(" M : MemberAdminDeleteAction_execute() 호출! ");
		
		
		// 세션제어(admin)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		ActionForward forward = new ActionForward();
		
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true); // 
			return forward;
		}
		
		// 전달정보 저장(삭제할 사용자)
		String delID = request.getParameter("id"); // 아이디라는 변수가 어드민인지 확인하는 것과 충돌나므로 delID로 바꾸기
		
		// DAO에 가서 adminMemberDelete(ID) 메서드 가져와서 삭제하기
		MemberDAO dao = new MemberDAO();
		dao.adminMemberDelete(delID);
		
		
		// 페이지이동(MemberList)
		forward.setPath("./MemberAdmin.me");
		forward.setRedirect(true);
		return forward;
	}

}
