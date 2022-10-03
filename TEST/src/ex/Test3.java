package ex;
//0922
	
					class SuperClass1 {
						public void pprn() {
							System.out.println(" P : SuperClass1 ");
						}
					}
					
					
					class SubClass1 extends SuperClass1 {
						public void cprn() {
							System.out.println(" C : SubClass1 ");
						}
					}
					



public class Test3 {

	public static void main(String[] args) {
					
					// 기본형타입 (boolean(1) char(2) byte(1) short(2) int(4) long(8) float(4) double(8) )
					// 형변환	1) 묵시적 형변환 (자동) : 메모리 크기 작은곳 -> 큰곳 갈 때 발생
					//									 => 컴파일러가 자동으로 형변호나 실행(에러 없음)
					// 			2) 명시적 형변환 (강제) : 메모리 크기 큰곳 -> 작은곳 갈 때 발생
					//									 => 컴파일러가 자동으로 형변환 X (에러 생김. 데이터손실)
					//									 => 강제 형변환 (개발자가 에러를 확인하고 책임진다는 의미)
					
					int a = 100;
					long a1;
					
					a1 = a; // 자동형변환 (int가 long에 들어간 것)
					
					int b;
					long b1 = 1000L;
			//		b = b1; // 에러발생!  long 을 int에 넣을 수 없음
					b=(int)b1; // 강제형변환
					
					
		
		
		
		
					
					// * 반드시 '상속' 관계에서만 가능 (융통성. 비슷한 객체끼리 바꿀 수 있다.)
					//참조형 타입 (기본형 타입 8개 제외한 나머지)
					
					
					
					// 형변환		1) 업캐스팅  (자동 형변환) : 
					//			- 슈퍼클래스의 레퍼런스가 서브클래스의 인스턴스(객체)를 가르키는 것								 
					//			- 자식타입이 부모타입으로 형변환 (위로 형변환)								 
					//			- 서브클래스의 레퍼런스 값이 슈퍼클래스의 레퍼런스 변수에 저장
					
					
					
					
					
					//			2) 다운캐스팅(강제 형변환)
					//			- 서브클래스의 레퍼런스가 슈퍼클래스의인스턴스를 가르키는것
					//			- 자식타입으로 형변환
					//			- 슈퍼클래스의 레퍼런스 값이 서브클래스의 레퍼런스 변수에 저장
					
					
					
					
					
					
					
					
					// 슈퍼클래스의 레퍼런스가 서브클래스의 인스턴스를 가르키는것
					
//					SuperClass1 s =  new SubClass1(); // 업캐스팅 맞음. 자바에서는 기본적으로 타입이 다른 것을 저장할 수 없음.
													  // 타입이 다른데 저장했을때 에러가 안남 = 자동으로 바뀌었다 = 업캐스팅이다.
													  // = 두 관계는 상속관계이다. (업캐스팅은 상속관계에서만 가능하므로)
//					SuperClass1 s;
//					SubClass1 sc1 = new SubClass1();
//					
//					s = sc1;
					
					SuperClass1 s1 = new SuperClass1();
					s1.pprn();
					
					SubClass1 sc1 = new SubClass1();
					sc1.cprn();
					sc1.pprn(); // 상속받았으므로 사용ㅇ
					
					
					// 업캐스팅 (형변환됨)
					// s 는 superClass타입의 형변환
					SuperClass1 s = new SuperClass1();
					s.pprn();
//					s.cprn(); // (사용X) 업캐스팅에서는 상속받은것만 쓸 수 있음.
							  //        상속받은게아니므로 내꺼를 쓸 수는 없음(숨어있다. 은닉되어있다.)
							  // => 상속받은 부분만 사용 가능하다.
					
					
					
					// * 업캐스팅
					// : 슈퍼클래스 타입으로 형변환
					//	참조 가능한 영역의 축소 (상속받은 부분만 사용가능)
					//	컴파일러가 자동으로 형변환(에러없음)
					//	상속받은 부분만 사용하고, 나머지는 안쓴다(포기)
					
					
					
					
					// * 다운캐스팅
					// : 서브클래스의 레퍼런스가 슈퍼클래스의인스턴스를 가리키는 것
					
					
					
					
					
//					SubClass1 sc2;
//					SuperClass1 s2 = new SuperClass1();
//					sc2 = s2;
					
					
					
					System.out.println("-----------------------------------");
//					SubClass1 sc2 = new SuperClass1(); // 컴파일 에러 
					SubClass1 sc2 = (SubClass1) new SuperClass1(); // (해결) 다운캐스팅 (강제형변환)
					sc2.pprn();
//					sc2.cprn(); // 출력안됨 에러발생(실행할때 에러발생 = 런타임에러)
								// -> 처음부터 에러발견했으나 우리가 무시함(강제형변환하면서)
					
								// => 실행시 (runtime 에러) 없는 영역에 참조가능성 있기 때문에 예외 발생
					
					
					
					System.out.println("-----------------------------------");
					
					// 안전한 다운캐스팅 : 업캐스팅된 데이터를 -> 다운캐스팅 하는 것
					//					   부모타입이면서 자식부모 둘 다 가지고 있는것? = 업캐스팅된 데이터
					
					SuperClass1 s3 = new SubClass1();
					
//					SubClass1 sc2 = s3; // 에러발생
					SubClass1 sc3 = (SubClass1)s3;
					
					sc3.pprn();
					sc3.cprn();
					
					
					
					
					
					// * 다운 캐스팅
					//   - 서브클래스 형태로 형변환
					//   - 참조 가능한 영역이 확대됨
					//   - 컴파일러가 자동으로 형변환 X (에러발생)
					// 	 -> 존재하지 않는 영역 참조 가능성 있음 (컴파일에러.빨간줄)
					//   => 개발자가 강제 형변환
					//   - 업캐스팅된 객체를 다운캐스팅 해야함. (안전한 다운 캐스팅)
					
					

					
					// * 참조형 형변환 실행시
					//	 1) 컴파일 단계, 
					// 	 - 총 2번 에러 체크
					
					
					
					
					
//					session.setAttribute("이름", "값");
//					String name = (String) session.getAttribute("이름");
//											-------------------
//												오브젝트 (상속된것)
					
					
					
					
					// 형변환보다 오버라이딩이 무조건 먼저 호출됨
					
					
					
	}
}

