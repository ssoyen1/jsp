package ex;

class Alpha{
	public void foo() { System.out.println("Afoo ");}
}





public class Beta extends Alpha {
	public void foo() { System.out.println("Bfoo"); }
	public static void main(String[] args) {
		//*형변환 여부와 상관없이 항상 오버라이딩 된 메서드만 호출됨
		Beta a = new Beta();
		
		Beta b = (Beta) a;
		a.foo();
		b.foo();
	}

	
	
	
}
