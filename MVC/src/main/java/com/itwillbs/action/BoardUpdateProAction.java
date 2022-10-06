package com.itwillbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardUpdateProAction implements Action {

	public BoardUpdateProAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateProAction_execute() 호출");
		
		
		// 한글처리(생략)
		// 전달된 데이터 저장(파라미터)
		// DTO 객체생성
		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setPass(request.getParameter("pass"));
		dto.setContent(request.getParameter("content"));
		
		String pageNum = request.getParameter("pageNum");
		
		// DB에 가서 수정
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoard(dto);
		
		System.out.println( " M : 수정완료 " + result);
		
		
		// 페이지 이동 (컨트롤러 X : 컨트롤러에서 움직이지 않게 하겠다 = 티켓생성X)
		// JS 사용 페이지 이동
		
		response.setContentType("text/html; charset=UTF-8"); // JSP페이지 제일위에서 본것들 
		// => 응답페이지의 형태를 html 형태로 사용
		
		PrintWriter out = response.getWriter();
		// => 응답페이지로 출력하는 통로를 준비
		
		if(result == 1) {
			out.print("<script>");
			out.print("alert('수정 완료!);");
			out.print("location.href='./BoardList.bo?pageNum=';");
			out.print("</script>");
			out.close(); // 리소스 해제.리스폰스로 출력하는 통로가 열려있었은데 더이상 안쓸거니까 닫겠다. 난위에것만 ㅎㅏ면되거든
			
			return null; // 여기서 몸움직이도록함. 티켓을 돌려주지않을거양. 함수를 종료시킨다.바로컨트롤러로 보낼거양
						 // 더이상 실행없이 컨트롤러로 전달
						 // 이미 이동했으니 null
		} else if(result == 0) {
			out.print("<script>");
			out.print("alert('비밀번호 오류! 수정X');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
			
		}else { // result == -1
			out.print("<script>");
			out.print("alert('게시판 글없음 ! 수정X');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
			
		}
	}

}
