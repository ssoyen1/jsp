package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberInfoAction_execute() 호출");
		
		// (1) 세션 제어 (로그인을 했다 = 이미 비밀번호를 알고있다는 뜻) 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id"); //다운캐스팅?????????? 왜 String으로 변환하는가
		
		
		ActionForward forward = new ActionForward();
		if(id == null) {				// 액션페이지 왔을 때 세션에 정보가 없으면  여기서 바로 컨트롤러로 이동하고 아래쪽에는 실행되지 않는다.
			forward.setPath(id);
			forward.setRedirect(false);
			return forward; //return 동작이 execute를 종료시킴
		}
		
		
		// (2) DAO - 회원정보 가져오는 메서드 (getMember(ID))
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);	
		System.out.println(dto);
		
		
		
		// (3) 정보를 request 영역에 저장( -> 그래야 view(.jsp)로 전달할 수 있으므로)
		request.setAttribute("dto",dto );
		
		
		
		// (4) ./member/info.jsp 페이지 만들어 이동시킨 후 거기서 정보 출력하도록 하기
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
