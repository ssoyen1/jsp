package com.itwillbs.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberAdminAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberAdminAction_execute() ");
		
		// 관리자여부( 관리자만 보게 만들어야함) / 로그인체크(로그인해야하는 상황)
		HttpSession session = request.getSession(); // 로그인할때 세션정보를 아이디에 담아야하므로
		String id = (String)session.getAttribute("id");
		
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		} // 자바스크립트 이용해서 이동해도 되지않는가? 
		  // but 일반유저들은 어드민페이지를 잘 모르므로 그냥 바로 이동시켜주면되는것?
		
		
		
		// DAO - 디비에있는 모든 회원정보 가져오게 함(관리자 제외 가져옴)
		MemberDAO dao = new MemberDAO();
		List memberList = dao.getMemberList();
		
		// request 영역에 저장(view 전달해야하니까 (패턴3) 멤버리스트에 있는 애들을 리퀘스트영역에 저장해줌)
		request.setAttribute("memberList", memberList);
		
		forward.setPath("./member/admin.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
