package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class BoardDAO2 {
	
	
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";
		
		public BoardDAO2() {
			System.out.println("DAO : 객체생성완료!");
			System.out.println("DAO : iwill_board 테이블 접근 준비 완료!");
		}
		
		
		
		//(1) 연결정보 메서드
			public Connection getConnection() throws Exception {
				
				// 디비 연결 정보(상수)
				final String DRIVER = "com.mysql.cj.jdbc.Driver";
				final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
				final String DBUSER = "root";
				final String DBPW = "1234";
				
				//1. 드라이브 로드
				Class.forName(DRIVER);
				
				//2. 디비연결
				con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
				System.out.println("DAO : 디비 연결 성공");
				System.out.println("DAO : " + con);
				
				return con;
				
			}
			
		
		//(2) 글쓰기 메서드
			
			public void insertBoard(BoardDTO2 dto) {
				
				try {
					//0.~2.
					con = getConnection();
					//3.
					sql = "select max(bno) from itwill_board";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					
					int bno = 0;
					if(rs.next()) {
						bno = rs.getInt(1)+1;
					}
					
					sql = "insert into itwill_board(bno, name, pass, subject, content,"
						 +"readcount, re_ref, re_lev, re_seq, date, ip,file)"
						 +" values (?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, bno);
					pstmt.setString(2, dto.getName());
					pstmt.setString(3, dto.getPass());
					pstmt.setString(4, dto.getSubject());
					pstmt.setString(5, dto.getSubject());
					
					pstmt.setInt(6, 0);
					pstmt.setInt(7,bno);
					pstmt.setInt(8, 0);
					pstmt.setInt(9, 0);
					
					pstmt.setString(10, dto.getIp());
					pstmt.setString(11, dto.getFile());
					
					pstmt.executeUpdate();
					

					System.out.println(" DAO : 게시판 글쓰기 완료! ");
					
					
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		
		
		
		
}
