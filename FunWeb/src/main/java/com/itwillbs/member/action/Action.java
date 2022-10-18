package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
		// 상수, 추상메서드만 선언가능
		
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception; // 실행할때 에러발생할 수 있으므로 에러처리
	
}
