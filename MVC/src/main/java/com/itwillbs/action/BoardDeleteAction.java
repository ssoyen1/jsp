package com.itwillbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;
import com.itwillbs.db.BoardDTO;

public class BoardDeleteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println("M : @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      System.out.println("M : BoardDeleteAction_execute() 호출");
      
      // 전달된 파라미터 (bno, pass, pageNum) // 페이지에서 넘어오는 것들. 디비에서 오는거 아님.
      int bno = Integer.parseInt(request.getParameter("bno"));
      String pass = request.getParameter("pass");
      String pageNum = request.getParameter("pageNum");
      
      // BoardDAO 객체 생성
      BoardDAO dao = new BoardDAO();
      
      int result = dao.deleteBoard(bno, pass);
      
      // 결과에 따른 페이지 이동(JS)
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if(result == 1) {
         out.print("<script>");
         out.print("alert('게시판 글 삭제');");
         out.print(" location.href='./BoardList.bo?pateNum="+pageNum+"';");
         out.print("<script>");
         out.close();
         
         return null;

      } else if(result == 0) {      // 비밀번호 오류
         out.print("<script>");
         out.print("alert('비밀번호 오류');");
         out.print("history.back();");
         out.print("<script>");
         out.close();
         
         return null;
      } else {
          out.print("<script>");
          out.print("alert('글없음');");
          out.print("history.back();");
          out.print("<script>");
          out.close();
         return null;
      }
      
   }
}