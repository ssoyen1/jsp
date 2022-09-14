
public class Test1 {

	public static void main(String[] args) {
	
		// 변수 : 데이터를 저장하는 메모리 공간
//				  int a;
//				  a=0;
		
		// 배열 : 동일한 타입의 데이터를 연속된 공간에 저장하는 메모리 공간
		// 		  배열을 저장할때는 항상 0번 인덱스(index) 시작	
		
		// int[] 이름 =new int[크기]; // 이 형식으로 사용하기
		// int 이름[] =new int[크기]; // 에러는 안나지만 위 언어로 사용하기 / C언어와 흡사
		
		int[] numbers = new int[5];
		
		numbers[0] = 10;
		numbers[1] = 20;
		numbers[2] = 30;
		numbers[3] = 40;
		numbers[4] = 50;
		
		//	  [10][20][30][40][50]
		//	   0   1   2   3   4
		// => int타입의 데이터를 저장하는 공간 5개 생성
		// => 연속된 공간에 생성됨.
		// => 배열의 이름을 사용하여 접근 numbers[위치] 
		// => 배열의 값을 '요소', 배열의 요소는 이름을 사용하여 변수처럼 접근
		
		
		System.out.println("3번째 요소의 값 : " + numbers[2]);
		System.out.println("배열의 길이 (요소의 개수): "+numbers.length);
		
//		  [10][20][30][40][50]
//		for(int i=0;i<5;i++) { // 최대한 = 기호 안쓰도록 하기
		for(int i=0;i<numbers.length;i++) { // 굳이 숫자 안써도 배열의 길이만큼 출력되도록
//			System.out.println(i); // 배열에 있는 값을 꺼내야함. 배열을 변수처럼 쓰기
//			System.out.println("["1numbers[i]+"]"); // 일렬로 나와야하니까 printf 사용하기
			System.out.printf("["+numbers[i]+"]");
		}
		
		
		
		
	}

}
