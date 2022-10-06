package com.itwillbs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;

public class BoardListAction implements Action {

	   @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      System.out.println("M : BoardListAction_execute() 호출");
	      
	      // 게시판 전체 글 개수 확인....... DB 안에 글이 몇 개 있는지 확인
	      // BoardDAO 객체 생성
	      BoardDAO dao = new BoardDAO();
	      int cnt = dao.getBoardCount();
	      System.out.println("M : 전체 글 개수? " + cnt + "개");
	      
      
      
      
      
      
      
      
		////////////////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리(1) 
		 
		// 한 페이지에 보여줄 글의 개수
		int pageSize = 10;
		
		// http://localhost:8088/JSP/board/boardList.jsp?pageNum=2
				// 2페이지를 출력하는 방법
				// pageNum=2처럼 뒤에 페이지 정보를 넣기. 컨트롤 누른채로 마우스오버하면 실행할 수 있음
		
		//  현 페이지가 몇페이지인지 확인
		String pageNum = request.getParameter("pageNum"); // 원래는 전페이지인 wirtePro에 있어야하는 것 아닌가? 
		if(pageNum==null){
			pageNum = "1"; // 1페이지로 돌아간다. so 우리가 지금 이걸 실행하면 무조건 1페이지로 감. 실행한게 없으니
			
		}
		
		// 시작행 번호 계산하기 1, 11, 21, 31, 41 ... (한페이지에 10개만 보여주므로)를 공식화하기
		int currentPage = Integer.parseInt(pageNum); // 페이지 정보를 안받았으니 위의 pageNum 를 받아옴 .
													 // 근데 왜 String 을 사용햇는가 ?
		int startRow = (currentPage-1)*pageSize+1; //1-1x10+1 = 1		1										 
												   //2-1x10+1 = 11
												   //3-1x10+1 = 21
		
		// 끝행 번호 계산하기 10, 20, 30, 40 ...
		int endRow = currentPage * pageSize;
      
      
      
			
			
			
			
	      
	      
	      // 디비에 있는 전체 글 리스트 가져오기 (startRow, pageSize)
		 ArrayList boardListAll = dao.getBoardList(startRow, pageSize);
	      // System.out.println("M : " + boardListAll );
	      // 실행 해보고 주석처리 해야하는 syso
	      
	      
	      
	      
			//////////////////////////////////////////////////////////////////////////////////
			// 페이징 처리 (2) ( 숫자블럭, 이전, 다음블럭)
//			if(cnt !=0) { //count = 글 전체 갯수를 물어보는것 / 
							//= so '글이 있을 때' 를 의미
				// 총페이지 = 글개수(총량) / 페이지당 출력
				// 55 페이지를 10개씩 했을때 나머지 5는 int로 처리하면 없는 셈이됨. 소수점이 없으니까
				// => so 만약에 나머지가 있을때 페이지 1개 추가해야 함
				
				// 전체 페이지수 (에 추가적으로 더해주는 로직)
				int pageCount = (cnt/pageSize)+(cnt%pageSize==0? 0 : 1); // 나머지가 0이다 = 딱떨어지므로 안더해도 됨
																			 //			 아니다 = 페이지 1개 더필요하므로 더하기
																		 
				// 한 화면에 보여줄 페이지 수 (밑에 숫자있는 페이지블럭)
				int pageBlock = 2; // 일단 계산하기 쉽게 지정한 것
				
				// 페이지블럭의 시작번호 1~10 =>(제일 처음 번호는) 1번, 11~20 =>1, 21~30 => 21
				int startPage = ((currentPage-1)/pageBlock)*pageBlock+1; // (1-1)/10*10+1 			 
																		 // (5-1)/10*10+1 => 
				
				// 페이지블럭의 끝번호
				int endPage = startPage+pageBlock-1;
				
				if(endPage > pageCount) {
					endPage = pageCount; // 작을때 비교해서 바꿔라.
					
				}
				
						
			
			
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      // 직접 출력 -> 위임 (view 한테 대신 출력 시킴.... .jsp 에서 출력!) 
	      // Action -> jsp 페이지로 정보를 전달 (request 영역 객체에 저장)
	      request.setAttribute("boardListAll", boardListAll);         // "boardListAll" 이름으로 boardListAll 저장!
	      
	      
	      // 페이징처리 정보 저장
	      request.setAttribute("pageNum", pageNum);
	      request.setAttribute("totalCnt", cnt);
	      request.setAttribute("pageCount", pageCount);
	      request.setAttribute("pageBlock", pageBlock);
	      request.setAttribute("startPage", startPage);
	      request.setAttribute("endPage", endPage);
	      
	      
	      
	      
	      // ****** 어차피 저장할 거 담아보내는 곳에서 디비 호출을 하는 코드?
	      // 용도가 조금 다름.... 위의 코드는 추가적으로 데이터를 더 담고 싶을 때! 
	      // 아래 코드는 디비에서 받아온 정보를 그대로 토스를 하고 싶을 때 쓰기!
	      // request.setAttribute("boardListAll", dao.getBoardList());
	      
	      
	      // 페이지 이동 준비 (티켓 생성)
	      // ./board/BoardList.jsp로 이동!
	      ActionForward forward = new ActionForward();
	      forward.setPath("./board/boardList.jsp");
	      forward.setRedirect(false);         // request를 담아서 갈 거라서 forward 방식으로 이동할 것
	      
	      return forward;
	   }
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//package com.itwillbs.action;
	//
	//import java.util.ArrayList;
	//
	//import javax.servlet.http.HttpServletRequest;
	//import javax.servlet.http.HttpServletResponse;
	//
	//import com.itwillbs.db.BoardDAO;
	//
	//public class BoardListAction implements Action {
	//
	//	@Override
	//	public ActionForward execute(HttpServletRequest request, 
	//			HttpServletResponse response) throws Exception {//임폴트 안되는 이유 : 같은 패키지안에 있으므로 패키지임폴트 노필요
	//															// 보드리스트 호출 받았을때 
	//		System.out.println(" M : BoardListAction_execute() 호출 ");
	//		
	//		// BoardDAO 객체 생성 (디비사용할거니까)
	//		BoardDAO dao = new BoardDAO(); // 강한결합 ->so Action과같은 인터페이스 사용해야함(프레임워크에서 편한방법 가르쳐줄것)
	//		
	//		
	//		// 게시판 전체 글 개수 확인 
	//		int cnt = dao.getBoardCount();
	//		
	//		System.out.println( " M : 전체 글 개수 :" + cnt +"개");
	//		
	//		
	//		//(1)
	//		// 디비에 전체 글 리스트 가져오기
	//		ArrayList boardListAll = dao.getBoardList(); // arraylist는 외부에 있으므로 사용하려면 임폴트필수
	//		
	////		System.out.println(" M : "+boardListAll); // 한번사용하고 주석처리 해주기..아니면 콘솔에 글 내용이 다찍힘 -> 속도느려짐
	//		
	//		
	//		// 직접출력 -> 위임(대신 출력) view .jsp페이지에서 출력
	//		// Action -> jsp페이지 정보 전달(request 영역객체 저장)
	//		request.setAttribute("boardListAll", boardListAll);
	//		
	//		
	////		//(2)
	////		// 위에것들을 (ArrayList~) 다 담아서 이렇게 표현. 두가지중에 하나 방법 쓰면 됨. so 주석
	////		request.setAttribute("boardListAll", dao.getBoardList());
	//		
	//		
	//		// 페이지 이동 준비(티켓 생성)
	//		// ./boardList.jsp
	//		ActionForward forward = new ActionForward();
	//		forward.setPath("./board/boardList.jsp");
	//		forward.setRedirect(false); // request를 담아가야하므로 담을 수 있는것은 forward(false)밖에 없음
	//		
	// 		
	//		
	//		return forward; // -> 2단계 컨트롤러의 forward = action.execute forward로 이동 
	//						// -> 3단계로 가서 이동시김
	//	} 
	//
	//
	//}
