import java.util.ArrayList;
// 임포트 된 것 = class ArrayList{  : 객체선언 하지않아도 여기에대한 것 사용할 수 있음

public class Test2 {

	public static void main(String[] args) {
		// 컬렉션 : 자료구조를 자바코드로 구현한 객체
		//			set계열, list계열, map계열
		
		// list계열 : 저장하는 데이터의 순서를 기억(index)
		//			  중복된 데이터를 저장 가능
		
		// http://www.javatpoint.com/collections-in-java
		// ArrayList : 가변길이 배열(컬렉션)
		//			   => 타입 상관없이 모든 타입 저장가능
		// ArrayList<참조형타입> list = new ArrayList<참조형타입>();
		//			   => 참조형 타입만 저장가능한 배열
		
		
				// (1) ArrayList 객체 생성 (java.util 패키지 추가)
				ArrayList list = new ArrayList();
				// => 가변길이 배열 생성
				
				
				// (2) 배열에 데이터 넣기
				// list[0]=10; 원래는 이렇게 하지만
				list.add(1); // [1,2,3]
				list.add(2);
				list.add(3);
				list.add("안녕하세요"); // => 타입 상관없이 모든 타입 저장가능
				list.add(3.14);
				list.add(false);
				list.add('G'); 
				
				
				// (3) 배열에 데이터 사용하기
				System.out.println(list.get(0)); // get(index) : 배열의 위치값
				System.out.println(list.get(1)); 
				System.out.println(list.get(2)); 
												//		1
												//		2
												//		3
				System.out.println("------------------------------------------");
				
				
				// 위 방법 조금 더 편하게 사용하는 방법
				// 반복문 사용해서 출력 - for
				
				for(int i=0; i<list.size(); i++) {
					System.out.println(list.get(i));
				}
				
				// foreach문 (향상된 for문)
				// 문법 : for(배열의 요소를 저장하는 변수 :배열, 컬랙션명) {
				//					변수사용;
				//		  }
				
				for(Object num:list) {     // Object : 모든 객체 타입을 저장할 수 있는 가장 기본적인 타입 
					System.out.println(num);
				}
		
				
				
				
		// 래퍼클래스(wrapper)
		// 기본형 데이터 (객체개념쓸수x) ->  참조형 데이터
		//  (8개)							 (8개)
		/*
		 * boolean							 Boolean
		 * char								 Character
		 * byte								 Byte
		 * short			 =>				 Short
		 * int								 Integer
		 * long								 Long
		 * float							 Float
		 * double							 Double
		 * 
		 */
				
		
		//  JDK 1.6 이후
		//  오토 박싱 : 기본형타입 -> 참조형타입
		//  오토 언박싱 : 참조형타입 -> 기본형타입
			
				
					ArrayList<Integer> list2 = new ArrayList<Integer>(); // int만 저장할 수 있는 배열
					
					list2.add(1);
					list2.add(2);
					list2.add(3);
					
					for(int num:list2) {
						System.out.println(num);
					}

	}

}
