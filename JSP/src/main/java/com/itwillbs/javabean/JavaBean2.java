package com.itwillbs.javabean;

public class JavaBean2 {
		// 변수처럼 클래스도 선언, 초기화, 사용 필요
	

		// itwill_member 테이블의 정보를 한 번에 저장 객체
	
		private int idx;			// 선언!
		private String name;
		private String gender;
		private int age;
		private String jumin;
		
		public JavaBean2() {
			// 생성자 : 클래스를 초기화 하는 메서드
			// 			리턴 타입이 없는 메서드
			//			void가 없는 메서드? 생성자라고 유추할 수 있음
			//			메서드 이름이 클래스 이름과 동일함(클래스 이름 : JavaBean2, 메서드 이름 : JavaBean2 -> 동일)
			// idx = 0; => 기본값으로 변수를 초기화
			// 즉, 별도의 초기화 작업이 필요하지 않음
			System.out.println("이 코드는 객체 생성 시 실행이 됩니다!");
			System.out.println("객체의 변수 정보를 기본값으로 초기화합니다!");
			
		}
		
		
		// set/get 메서드 생성
		// alt shift s + r
		public int getIdx() {
			return idx;
		}
		public void setIdx(int idx) {		// set메서드는 수정, 생성자는 초기화
			this.idx = idx;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getJumin() {
			return jumin;
		}
		public void setJumin(String jumin) {
			this.jumin = jumin;
		}
			
	
		
		
		
	
}
