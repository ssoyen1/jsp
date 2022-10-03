package ex;


interface Will {
	public abstract void Hello();
	public void Hello2(); // interface안에서는 abstract
	/*public void*/void Hello3(); //  abstract,public  생략가능
	
}


class Test implements Will { //implements : 구현하다 (인터페이스와의 상속)
							 // extends : 확장하다 (클래스간 상속)

		// alt shift s + v
		@Override
		public void Hello() { }
	
		@Override
		public void Hello2() { }
	
		@Override
		public void Hello3() { }//public 지우면 에러남 ->(이유)Cannot reduce the visibility of the inherited method from Will
		
		
	
}



abstract class Shape{
   public abstract void draw(Shape s); 
      

}

		class Cir extends Shape{ // 원
		   public void draw() {
		      System.out.println("원 그리기");
		   }
		   public void draw(Shape s) {
		      System.out.println(s + " 그리기");
		   }
		}
		
		class Tri extends Shape { // 삼각형
		//   public void paint() {
		//      System.out.println("삼각형 그리기");
		//   }
		   public void draw(Shape s) {
		      System.out.println(s + " 그리기");
		   }
		}
		
		class Rec extends Shape{ // 사각형
		//   public void print() {
		//      System.out.println("사각형 그리기");
		//   }
		   public void draw(Shape s) {
		      System.out.println(s + " 그리기");
		   }
		}
		
		public class Test4 {
		   
		}