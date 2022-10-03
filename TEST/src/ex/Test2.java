package ex;
//0922





		class Parent1 {
			public void pprn() {
				System.out.println(" P : pprn() ");{
			
				}
			}
		}
		
		
		
		
		
		// Alt + shift +s -> 오버라이딩 단축기
		class Child1 extends Parent1 {
			
			
		@Override
			public void pprn() {
			System.out.println(" C : pprn() ");
				super.pprn(); // 부모의 은닉된 메서드를 호출하겠다.
			}
		
			
		//	public void pprn() {
		//		System.out.println(" C : pprn() ");{
	
		
			public void cprn() {
				System.out.println(" C : cprn() ");
			}
			
		}

		
		
		
		
		
		class Itwill {
			// 클래스 구성요소
			// 1) 변수(데이터저장하기위해)
			
			// 2) 생성자 
			// 3) 메서드
			
			// Q. 생성자를 지웠을때 super가 생성자를 불러올 수 있는가?
			// A. (O) 기본생성자는 컴파일러가 자동으로 생성해줌
			//		  => 오버로딩된 생성자가 없을 경우
			
			
			
			public Itwill() {
				System.out.println("Itwill 생성자");
			}
			
			
			public Itwill(int a) {
				System.out.println("Itwill 생성자 a ");
			}
			
		  //기본생성자는 주석처리하고 public Itwill(int a) 만하면 에러생김.
		  // Q. 에러를 없앨 수 있는가?
		  // A. 있음 .class ItwillBusan extends Itwill {
		  //	public ItwillBusan() { 에서 super에 인트 아무거나 주면 됨
			
		}
		
		
		
		class ItwillBusan extends Itwill {
			public ItwillBusan() {
				// super(); 생략 //"Itwill 생성자" 출력해줌
				super(0); // public Itwill(int a) 생성자를 가져오는 법.
				System.out.println("ItwillBusan 생성자"); // ItwillBusan 생성자
			}
			
		}
		// * 생성자는 상속 불가능!! => 상속되지않는 유일한 멤버메서드
		//	 어떻게 호출하는가?
		// * 개발자가 별도의 명시적 호출 없이 자동으로 서브클래스의 생성자는
		//	 슈퍼클래스의 디폴트 생성자를 호출
		
		
		
		
		
		
		
		
		// => main() 포함하는 메서드 (public 붙이는이유)
		public class Test2 {
		
			public static void main(String[] args) {
				Parent1 p1 = new Parent1();
				p1.pprn();
				
				System.out.println("--------------------------------");
				
				Child1 c1 = new Child1();
				c1.cprn();
				c1.pprn(); //parent1 상속했기때문에
				
				
				// this : 나 자신의 객체 레퍼런스(레퍼런스 = 참조변수 : 객체를 참조할 수 있게 정보를 저장한 변수)
				// super : 상속하고있는 부모의 객체 레퍼런스 
				// => 처음 클래스 생성시 컴파일러가 1개씩 생성		
				System.out.println("---------------------------------");
				
				Itwill will = new Itwill();
				System.out.println("---------------------------------");
				ItwillBusan willbs = new ItwillBusan();
				
				
				
			}
		
		}
