package com.itwillbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardFileWriteAction_execute호출");
		
		
		// 1) 파일 업로드
		// 업로드 가상폴더 생성 /upload (이 폴더가 있어야 가상경로로서 접근가능)
		// 첨부파일 크기 지정 / 10MB

		String realPath = request.getRealPath("/upload"); // 쫙쫙하는이유 ? : 함수말고 다른 함수를 사용하라는 의미. 그치만 무시하고 써도됨 .책임안진다 
		System.out.println(" M : realPate : "+realPath);
		int maxSize = 10 * 1024 * 1024;
		
		// 파일 업로드 -> 파일업로드 객체 생성(MultipartRequest)
		MultipartRequest multi 
				= new MultipartRequest(
						request, 
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		
		System.out.println(" M : 첨부파일 업로드 성공! ");
		
		
		
		
		// 2) 정보를 DB에 저장
		BoardDTO dto = new BoardDTO();
		
		dto.setName(multi.getParameter("name"));
		dto.setPass(multi.getParameter("pass"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content")); // 왜 multi로 바꿨지 ..??? 멀티가 도와주는 객체니까 ! , 그리고 다 멀티에 저장했으니까 멀티로적은것
													   // multi는 파일업로드에 관한 수행을 도와주는 객체이므로
		
		
		dto.setFile(multi.getFilesystemName("file"));
		dto.setIp(request.getRemoteAddr());
		
		
																//		 * 객체 생성 == 파일 업로드
																//				 MultipartRequest multi = new MultipartRequest(
																//				          request(폼태그의 정보),
																//				          path(파일 업로드될 경로(서버의 경로)), 
																//				          maxSize(파일 업로드 크기),
																//				          "UTF-8"(파일명 인코딩),
																//				          new DefaultFileRenamePolicy() (파일 이름 중복 처리객체)
																//				         );

																//		
																//		 파일 업로드를 하려면?
																//				 form 태그를 사용!
																//				 전달 방식은 post방식
																//				 enctype="multipart/form-data"
																//				<form emthod="post"  enctype="multipart/form-data">
				 
		
		
		// DAO
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(dto);
		
		
		
		// 페이지 이동(정보 저장)
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true); // 위에서 다 저장했으니 더 보낼게 없다!!!
		
		
		
		return forward;
	}

}
