package com.itwillbs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {

		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql ="";
	
		

		// 디비 연결정보 메서드
		private Connection getConnection() throws Exception {
			
			// 디비 연결 정보(상수)
			final String DRIVER = "com.mysql.cj.jdbc.Driver";
			final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
			final String DBUSER = "root";
			final String DBPW = "1234";
			
			// DB에서 필요한 정보 가져오기
			// 1. 드라이버 로드
				Class.forName(DRIVER);	
			
			// 2. 연결
				con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
				System.out.println("DAO : 디비 연결 성공");
				System.out.println("DAO : " + con);
				
				return con;
		}
	
		
		// 자원해제 메서드-closeDB();
		//해제시 만들었던 역순으로 종료하는 것이 안정적인 종료 방법
		public void closeDB() {
			System.out.println("DAO : 디비연결자원 해제");
			
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 자원해제메서드 
		}
	
		
		
		
		
		// 글쓰기 메서드 - insertBoard(DTO)
		public void insertBoard(BoardDTO dto) {
			int bno = 0; //글번호 계산해야하니까
			
			try {
				// 1.2 디비연결
				con = getConnection();
				
				// 3. sql 작성 & pstmt 객체
				sql = "select max(bno) from itwill_board";
				pstmt = con.prepareStatement(sql);
				
				// 4. sql 실행
				rs = pstmt.executeQuery(); //결과값을 rs에 넘겨줌
				
				// 5. 데이터처리
				if(rs.next()) {
					bno = rs.getInt(1)+1; // 셀렉트에 나온 결과값(1)에 1을 더하겠다.
				}
				
				System.out.println(" DAO : bno : " + bno);
				
				
				// insert (3.부터 수행)

				
                sql = "INSERT INTO itwill_board(bno, name, pass, subject, content, readcount, re_ref, re_lev, re_seq, date, ip, file) "
                        + "values(?,?,?,?,?,?,?,?,?,now(),?,?)";               // now() : 현재 디비 접속 시간

				
				pstmt = con.prepareStatement(sql);
				
				
				// ???
				pstmt.setInt(1, bno);
				pstmt.setString(2, dto.getName()); // 네임은 디티오에서꺼내올거니까
				pstmt.setString(3, dto.getPass());
				pstmt.setString(4, dto.getSubject());
				pstmt.setString(5,  dto.getContent());
				pstmt.setInt(6,0); // 조회수0
				pstmt.setInt(7, bno); // ref == bno
				pstmt.setInt(8, 0); // lev 0
				pstmt.setInt(9, 0); // req 0
				pstmt.setString(10, dto.getIp());
				pstmt.setString(11, dto.getFile());
				
				
				// 실행(4.)
				pstmt.executeUpdate();
				
				System.out.println(" DAO : 글쓰기 완료");
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}// 글쓰기 메서드 - insertBoard(DTO)
		
		
		
		
		
		
		
		
		
		
}
