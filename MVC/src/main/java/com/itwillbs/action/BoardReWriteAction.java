package com.itwillbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardReWriteAction implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(" M : BoardReWriteAction_excute() 호출");
		
		// 한글처리 (form-post방식) 생략
		
		// 전달 데이터 저장 (pageNum, re_ref, re_lev, re_seq 
		//					 subject, name, pass, content)   앞에서 보낸건 다 가져오기
		
		String pageNum = request.getParameter("pageNum");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBno(Integer.parseInt(request.getParameter("bno"))); //파라미터로 전달하므로 ㅇ인트로 바꾸기
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		dto.setSubject(request.getParameter("subject"));
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setContent(request.getParameter("content"));;
		
		dto.setIp(request.getRemoteAddr());
		System.out.println("DTO 출력"+dto);
		
		//DAO 객체 생성
		BoardDAO dao = new BoardDAO();
		dao.reInsertBoard(dto);
		
		//페이지 이동(정보 저장)
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo?pageNum="+pageNum);
		forward.setRedirect(true); // 위에서 다 저장했기때문에 , 들고나를 정보가 없어서 true
		
		return forward;
	}

}
