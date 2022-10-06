package com.itwillbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardDeleteAction implements Action {

	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardDeleteAction_execute() 호출");
		
		
		// (1) 페이지 전달정보 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		// BoardDAO 객체생성
		BoardDAO dao = new BoardDAO();
		
		// (2) DB에 저장된 삭제할 글번호를 가져와서 view출력
		BoardDTO dto = dao.getBoard(bno);
		
		// request 영역에 저장
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		// (3) view출력
		ActionForward forward = new ActionForward();
		forward.setPath("./board/deleteForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
