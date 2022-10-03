package com.itwillbs.action;

public class ActionForward {
		// 컨트롤러에서 페이지 이동할때 필요한
		// 이동주소, 이동방법을 저장하는 객체
		private String path;	// 이동주소
		private boolean isRedirect; // 이동방법
		// 이동방법 2가지밖에 사용못함 - true : redirect방식 이동 - 경로이동만하고
		//					    - false: forward 방식 이동 - request값을 가지고 이동
		
		
		public ActionForward() {
			System.out.println("페이지 이동 준비(티켓준비)"); // 객체생성될때마다 찍힘
		}
		
		
		// private이므로 세터게터 만들기 (alt shift s + r )
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public boolean isRedirect() { // get메서드 (이름이 is로 시작되는 변수는 get메서드가 is로시작)
			return isRedirect;
		}
		public void setRedirect(boolean isRedirect) {
			this.isRedirect = isRedirect;
		}
		
		

}
