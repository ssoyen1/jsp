package com.itwillbs.member.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberJoinAction_execute() 호출");
		
		
		
		// 한글처리 (생략 - 필터사용 web.xml에서)
		
		// 전달된 정보 저장 (회원가입 정보)
		//	=> 정보 많을것같으므로 DTO객체 생성해서 저장하기
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setBirth(request.getParameter("birth"));
		dto.setGender(request.getParameter("gender"));
		dto.setEmail(request.getParameter("email"));
		dto.setAddr(request.getParameter("addr"));
		dto.setTel(request.getParameter("tel"));
		
		String[] birthArr = request.getParameterValues("birth"); // 동일한 이름의 파라미터들이 전달되었을때 파라미터 밸류들을 스트링타입으로 만들어 배열에 담아 전달함
		
//		System.out.println( " M : birthArr[0] : "+birthArr[0]);
//		System.out.println( " M : birthArr[1] : "+birthArr[1]);
//		System.out.println( " M : birthArr[2] : "+birthArr[2]);
		
		if(birthArr != null) { //99-1-2 한덩어리로 만들어서 넣음, 컬럼3개를 쓸 수없자너
							   // 이게 된다는 말은 ? 입력창에서 2개이상인 애들도 하나의 칼럼에 넣을 수 있다는 말씀
			dto.setBirth(birthArr[0]+"-"+birthArr[1]+"-"+birthArr[2]);
		}
		
		
		// DAO 객체 생성 - 회원가입 메서드를 호출
		MemberDAO dao = new MemberDAO();
		dao.memberJoin(dto);
		System.out.println(" M : 회원가입 성공! ");
		
		
		// 페이지 이동 (준비)
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me"); //가상주소 // 현재주소와 이동주소를 놓고 비교해서 판단하기 
		forward.setRedirect(false);// 현재주소와 이동주소를 놓고 비교해서 판단하기 , 가상주소에서 가상주소로의 이동을 수행해야함 
								   // so flase -> redirect
		
		return forward;
	}

}
