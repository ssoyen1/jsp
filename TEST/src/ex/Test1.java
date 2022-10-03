package ex;
//0920

				// 상속 필요조건  : 부모객체가 존재해야함, 부모객체의 성격(형태)과 유사한 형태를 구현

				// 기존의 클래스로 새로운 클래스를 작성하는 것. (코드재사용)
				class SuperClass {
					
				}
				
				class SubClass extends SuperClass { // extends SuperClass : 둘 사이에 상속관계 맺어짐 (extends: 확장하다.)
												   // 서브클래스에 수퍼클래스를 합쳐서 확장함. 상속을받았기에 부모의 모든 것을 사용 ㅇ
					
					
				}

		
				// iPhone13 객체 - 속성 : color, price
				class IPhone13 {
					String color;
					int price;
				}
				
				 
				
//				// iPhone14 객체 - 속성 : color, price, tel 	// 위와 2개가 중복됨.
//				class IPhone14  {				
//					String color;
//					int price;
//					String tel;
//				}
				

				// iPhone14 객체 - 속성 : color, price, tel 	// 상속
				class IPhone14 extends IPhone13 {				// 
//					String color; 								// 이 두가지를 적지 않아도 메인에서 color와 price를 사용할 수 ㅇ
//					int price;
					String tel;
				}

				

				class Parent { 
					public void pprn() {
						System.out.println(" P : pprn() 호출 !!! ");
					}
				}
				
				
				
				class Child extends Parent {
					// @Override : 해당 메서드가 오버라이딩 하고있다. 표시(생략가능. 왼쪽 초록색삼각형)
					@Override
					public void pprn() {
						System.out.println(" C : pprn() 호출 !!! (오버라이딩)");  // 위에 parent에서 받은 pprn과 
																	  // Child에 있는 pprn 이 중복되어 충돌함.
																	  // 이때, 오버라이딩이 되어 중복이 안됨! - 에러X
					}
					
					public void cprn() {
						System.out.println(" C : cprn() 호출 !!! ");

					}
				}
				
				
				
				class Point2D {
//					private int x;    // Q.  private 접근지정자를 걸었을때 상속이되는가? A. X
					protected int x;  // Q.  protected 접근지정자를 걸었을때 상속이되는가? A. O
					private int y;  // 
					
				}
				
				
				
				class Point3D extends Point2D {
//					int x;
//					int y;
					int z;
				}
				
				
				
				
				
				
				
				
				
				
public class Test1 {

	public static void main(String[] args) {
				// 객체 : 클래스를 구체화 (메모리에 올림) 형태
				// 상속 : 자식이 부모가 가지고 있는 재산, 권력을 물려받는 것
				// => 자식클래스가 부모클래스의 모든 요소를 사용가능하게 하는 것.
				
				// 자식클래스 ( 서브클래스 - SubClass, 하위클래스 )
				// 부모클래스 ( 슈퍼클래스 - SuperClass, 상위클래스 )
				
				
				//			[생물]     			[ 상위 객체 (부모클래스) ]
				//		 /			\			  : 공통 속성을 보유, 속성 간단
				//	  [동물]	  [식물]
				//	  /    \
				//	[새]  [호랑이]				[ 하위 객체 (서브클래스)]
												//: 개별 속성 보유 , 속성 복잡
				
				
				// * 프로그래밍에서의 상속 : 이미 정의되어 있는 객체(속성)을 사용해서 새로운 객체를 만드는 것
				//												(객체사용 = 관계를 맺는다)
				
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
			
				// 아이폰 13,14 객체 생성 - 정보출력
				IPhone13 i13 = new IPhone13();
				i13.color = "white";
				i13.price = 199;
				
				
				
				IPhone14 i14 = new IPhone14();
				i14.color = "black";
				i14.price = 299;
				i14.tel = "010-1234-1234";
				
				
				
				Parent p1 = new Parent();
				p1.pprn(); 				// 객체안에 메서드 만들었으므로 출력됨당연
										//  P : pprn() 호출 !!! 
				//p1.cprn();            // (x) : 상속관계 X - 자식것을 사용할 수는 없음 
				
				System.out.println("----------------------------------------------------------");
				
				Child c1 = new Child();
				c1.cprn();              //  		   C : cprn() 호출 !!! 
				c1.pprn();				// 상속관계 O  C : pprn() 호출 !!! 
				
				
				
				
				
				// 접근지정자 ( 제어자)
				// public >> protected >> default(생략된) >> private
				
				// private   : 자신 클래스에서만 사용 가능
				// 생략      : 자신 클래스 ,같은 패키지
				// protected : 자신 클래스 ,같은 패키지, 하위 클래스(상속관계에서의 자식, 상속받으면 사용할 수 있다)
				// public	 : 자신 클래스 ,같은 패키지, 하위 클래스, 다른패키지

				
				// protected => 상속관계 일때는 public, 아닐때는 private.
				
				
		
				 Point2D p2 = new Point2D();
//				 p2.x = 100; // private를 지정했기때문에 p2에서 사용할 수 없음. protected (O)
//				 p2.y = 200;
				 
				 
				 
				 Point3D p3 = new Point3D();
				 p3.x = 100;
//				 p3.y = 200;
				 p3.z = 300; // 접근지정자가 없는 변수이므로 사용할 수 있음.
				 
				 
				 
				 
				 
				 
				 
				 // * 메서드 오버라이딩 (면접에서 많이 물어봄 오버라이딩, 오버로딩 두개 비교)
				 // => 오버라이딩 : 무조건 '상속' 관계에서만 사용할 수 있음 ( 상속이 필수조건)
				 // 			  : 서브클래스에서 슈퍼클래스의 메서드를 재정의 하는 것. = 상속
				 // 				=> 기존의 부모의 메서드를 같은 형태로 사용하는것 (동작은 다름)
				 //					   원래있는 것을 가져다가 자신에 맞게 내용만 바꿔서 사용하기.
				 //					=> 기존의 메서드명, 전달인자의 타입, 전달인자의 개수가 모두 동일 (그냥 복사붙혀넣기 하면됨)
				 //						=> 부모의 메서드를 '은닉'시켜서 항상 재정의된 메서드가 호출
				 
				 //    Q. 똑같이 이름을 사용하지못하니까 오버로딩을 한 것인데,어떻게 오버라이딩에서 똑같이 사용할 수 있는가?
				 
				 
				 // 메서드 시그니처 = (메서드명, 메서드 전달인자의 타입, 전달인자의 개수)
				 //					  : 메서드를 구별할 수 있는 고유한 값
				 
				 
				 
				 // < 메서드 오버로딩 > : 기존에 없는 새로운 메서드를 정의하는 것(new)
				 // 1) 메서드명 동일
				 // 2) 메서드의 전달인자 타입 다름
				 // 3) 메서드의 전달인자 개수 다름
				 
				 
				 
				 // < 메서드 오버라이딩 사용 방법, 조건 > : 상속받은 메서드의 내용을 변경하는 것 (change, modify)
				 // 1) 선언부가 조상클래스 메서드와 일치 해야한다.
				 // 2) 메서드의 전달인자 타입 동일
				 // 3) 메서드의 전달인자 개수 동일 (2,3번 없으면 오버로딩)
				 // 4) 접근지정자의 범위가 더 좁아질 수 없음
				 // 5) 예외는 조상클래스의 메서드보다 많이 선언할 수 없다.
				 
				 
//				 public void method() {}
//				 private void method() {}// (X) 사용하고싶은 경우 public을 써야함.
//				 public void method() {} // (O)
				 
				 
				 //< 부모의 메서드 >
				 // void method() {} // 접근지정자 default가 사용됨 -> public, protected, default 접근 가능
				 // void method(); // 다른개념/ 추상메서드(중괄호(메서드바디)가없음 = 실행을 못함)
				 				   // => public 으로 인식됨 -> public에만 접근 가능
				 
				 
				 
				 //< 오버라이딩 된 메서드 >
				 // public void method() {}		- O
				 // protected void method() {}  - O
				 // void method() {} 			- O
				 // private void method() {}	- X
				 
				 
				 
	}									

}
