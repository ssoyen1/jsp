package com.itwillbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardUpdateAction implements Action {

	public BoardUpdateAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : ################################");
		System.out.println(" M : BoardUpdateAction_execute() 호출");
		
		//BoardUpdate.bo?bno=1&pageNum=1
		
		//(1) 페이지 전달정보 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		//(2) DB에 저장된 수정할 글번호를 가져와서 view 출력
		BoardDTO dto = dao.getBoard(bno); // bno를 주면 그 글번호에 해당하는 데이터를 디비에서 모두저장해서 가져다줌
						   // getBoard가 리턴하는 타입은 dto임
		// request 영역에 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum); //2개정보 넘겨줌
		
		//(3) view출력 (./board/updateFrom.jsp)
		//페이지 이동(티켓)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/UpdateForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
