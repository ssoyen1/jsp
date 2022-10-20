package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberUpdateProAction_execute() ");	
		
		// 한글처리(필터)
		// 전달된 데이터 저장(수정정보)
		// 아이디,비밀번호,이름,이메일,주소,전화번호,생년월일,성별
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setAddr(request.getParameter("addr"));
		dto.setTel(request.getParameter("tel"));
		dto.setGender(request.getParameter("gender"));
		String birth 
		  = request.getParameterValues("birth")[0]+"-"
				  +request.getParameterValues("birth")[1]+"-"
						  +request.getParameterValues("birth")[2];
		System.out.println(" M : "+birth);
		dto.setBirth(birth);
		
		System.out.println(" M : "+dto);
		
		// DAO - 회원정보 수정
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto);
		
		System.out.println(" M : result : "+result);
		
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
			out.print("<script>");
			out.print(" alert('회원정보 수정완료'); ");
			out.print(" location.href='./MemberInfo.me'; ");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
	}

}


















//package com.itwillbs.member.action;
//
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.itwillbs.member.db.MemberDAO;
//import com.itwillbs.member.db.MemberDTO;
//
//public class MemberUpdateProAction implements Action {
//
//	@Override
//	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		System.out.println(" M : MemberUpdateProAction_execute() ");
//		
//		// 한글처리 (필터사용해서 이미 처리함.매핑)
//		
//		
//		// 데이터부터 받아놓고 시작해야함 어디서든 !!!!!
//		// 전달된 데이터 저장(수정정보). 전달된 데이터들이 많으므로 한번에 저장할 수 있는 객체 생성함
//		// 아이디, 비밀번호, 이름, 이메일, 주소, 전화번호, 생년월일, 성별
//		MemberDTO dto = new MemberDTO();
//		
//		dto.setId(request.getParameter("id"));
//		dto.setPw(request.getParameter("pw"));
//		dto.setName(request.getParameter("name"));
//		dto.setEmail(request.getParameter("email"));
//		dto.setAddr(request.getParameter("addr"));
//		dto.setTel(request.getParameter("tel"));
//		dto.setGender(request.getParameter("gender"));
//
//		String birth = request.getParameterValues("birth")[0]+"-" // 동일한 이름으 ㅣ정보를 저장하는 함수. 스트링타입의 배열로 저장되므로 바로 뒤에 [0]이렇게 표현할 수 있음
//							+request.getParameterValues("birth")[1]+"-"
//									+request.getParameterValues("birth")[2];
//		System.out.println(" M : "+birth);
//		dto.setBirth(birth);
//		
//		
//		System.out.println(" M  : "+dto);
//		
//		
//		// DAO안에 - 회원정보 수정하는 동작 수행
//		MemberDAO dao = new MemberDAO(); // 디비를 사용할 것이니까. 이건 dao만 수행할 수 있음
//		int result = dao.updateMember(dto);
//		
//		System.out.println(" M : result : "+result);
//		
//		
//		// 페이지 이동(자바스크립트를 사용하려면 여기서 전부 수행할 수 있게 만들어줘야함.)
//		response.setContentType("text/html; charset=UTF-8"); //text/html 형태로 동작할 수 있게 됨
//		PrintWriter out = response.getWriter(); 
//		
//		
//		
//		if(result == -1) {
//			out.print("<script>");
//			out.print("alert('회원정보 없음');");
//			out.print("history.back();");
//			out.print("</script>");
//			out.close();
//			
//			return null;
//			
//		}else if(result == 0) {
//			
//		}else { //result == 1
//			out.print("<script>");
//			out.print("alert('회원정보 수정완료');");
//			out.print("location.href='./MemberInfo.me';");
//			out.print("</script>");
//			out.close();
//			
//			return null;
//		}
//		
//		
//		
//	}
//
//}
