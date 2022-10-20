package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberDeleteAction_execute() ");
		
		// 한글처리(필터)
		// 전달된 데이터 저장(수정정보)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// DAO - 회원정보 삭제(deleteMember())
		MemberDAO dao = new MemberDAO();
		int result =  dao.deleteMember(id,pw);
		
		
		// 페이지 이동(js)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == -1) {
			out.print("<script>");
			out.print(" alert('회원정보 없음'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;
		}else if(result == 0) {
			out.print("<script>");
			out.print(" alert('비밀번호 오류'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;
		}else { // result == 1
			// 회원정보에 해당하는 세선정보를 초기화 할것.
			HttpSession session = request.getSession(); // 세션사용할거니까 가져와서
			session.invalidate(); //초기화
		
			out.print("<script>");
			out.print(" alert('회원정보 삭제완료'); ");
			out.print(" location.href='./MemberInfo.me'; ");
			out.print("</script>");
			out.close();
			
			return null;
	}

	}
}
