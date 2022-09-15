package com.itwillbs.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
			// DAO(Data Access Object) : 데이터(=DB) 처리 객체
			// => DB에 관한 모든 동작을 수행하게 될 것!
	


	// 공통으로 사용할 변수를 선언
				private Connection con = null;
				private PreparedStatement pstmt = null;
				private ResultSet rs = null;
				private String sql = "";
				
				
				// 생성자 - 새로운 객체를 생성하기 위해 초기화해주는 메서드
				//			생성자의 이름은 반드시 클래스명과 일치해야 함
				//			객체 생성 시 자동으로 호출됨!
				//			단, 클래서 내에 한 개 이상의 생성자가 정의되여 있다면 컴파일러는 생성자를 자동으로 추가하지 않는다.
				public BoardDAO() {
					System.out.println("DAO : 생성자 호출");
					System.out.println("DAO : itwill_board 테이블 접근 준비 완");
				}
				
				
				
				// 디비를 연결해주는 메서드 
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
			
				// 자원해제 메서드 - closeDB()
				public void closeDB() {
					System.out.println("DAO : 디비 연결자원 해제");
					
					try {
						if(rs != null) rs.close();  		// rs.close() :rs안에 정보가 있으면 연결을 끊어준다는 뜻
															// sql쿼리를 실행하면 rs가 not null
						if(pstmt != null) pstmt.close();
						if(con != null) con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} // 자원해제 메서드 끝
				
				
				
	
				// 글쓰기 메서드 - insertBoard();
				public void insertBoard(BoardDTO dto) {
					System.out.println("DAO : insertBoard() 메서드 호출");
					
					
					int bno = 0;
					try {
						// 1.2. 디비 연결
						con = getConnection();
						
						// 3. sql 작성(글 번호 계산) & pstmt 생성
						sql = "SELECT max(bno) FROM itwill_board";		// 최댓값 + 1 하면 글번호!
						pstmt = con.prepareStatement(sql);
						
						// 4. 실행
						rs = pstmt.executeQuery();
						
						// 5. 데이터 처리하기 (글 번호 계산하기 - 번호 최댓값 + 1)
						// * rs.next() - true / false 구분하는 방법
						//		=> 워크벤치에 해당 쿼리를 실행(select)
						//		   결과 |> (삼각형 커서) : true (커서 이동 가능, sql null 상관 없음!)
						//		   결과 . , 커서X : false (커서 이동 불가능)
						
						// * rs.getInt() : 리턴데이터의 값이 sql-null인 경우 0을 리턴!
						
														 
						if(rs.next()) {
							// 데이터가 있을 때 번호 최댓값 + 1!
							// bno = rs.getInt("max(bno)") + 1;		// 작성하면 컬럼명이 max(bno)가 되기 때문에 그냥 bno로 쓰면 안 된다! 테이블 항상 확인하기
							bno = rs.getInt(1) + 1; 
							// (1) => 실행했던 결과의 첫 번째 자리를 의미함! = max(bno)를 하면 결국 최댓값이 나오고, 그 최댓값이 첫 번째 자리에 있을 거니까 위치를 통한 접근방식임
							
						} 
//						else {
//							bno = 1;			// 글이 아무것도 없을 때 bno를 1로 주면 될듯? 근데 없어도 됨....ㅎ
//						}						// rs.next()는 값이 null이던 아니던 커서가 있다면 커서가 움직일 수 있다는 뜻! 즉 rs.next()가 true라는 뜻
												// rs.getInt("max(bno)") -> the column value; if the value is SQL NULL, the value returned is 0 
												// 즉, 굳이 else를 쓸 필요가 없이 rs.getInt(1) + 1는 0 + 1 이 됨!
						
						System.out.println("DAO : 글번호 계산! 글 번호 = " + bno);
						
						
						// 3. sql 작성 (insert) & pstmt
						
						sql = "INSERT INTO itwill_board(bno,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,date,ip,file) "
								+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";		// now() : 현재 디비 접속 시간
						
						pstmt = con.prepareStatement(sql);
						

						// ???
						pstmt.setInt(1, bno);
						pstmt.setString(2, dto.getName());
						pstmt.setString(3, dto.getPass());
						pstmt.setString(4, dto.getSubject());
						pstmt.setString(5, dto.getContent());
						
						pstmt.setInt(6, 0); 		// 조회수 0으로 초기화
						pstmt.setInt(7, bno);		// ref 그룹번호 (bno초기화)
						pstmt.setInt(8, 0);			// lev 들여쓰기 (0 초기화)
						pstmt.setInt(9, 0);			// seq 순서 (0 초기화)
						
						pstmt.setString(10, dto.getIp());
						pstmt.setString(11, dto.getFile());
						
						
						// 4. sql 실행
						pstmt.executeUpdate();
						
						System.out.println("DAO : 게시판 글쓰기 완료");
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						closeDB();	// 예외가 발생하든 하지 않든 무조건 실행
					}
				
					
				}  // 글쓰기 메서드 종료
	
				
				
				
				
	
			// 글정보 가져오기 메서드 - getBoardList();
				
			public ArrayList getBoardList() {
				
				System.out.println("DAO : getBoardList() 메서드 호출 ");
				
				// 글 정보를 모두 저장하는 배열 먼저 만들기!
				ArrayList boardList = new ArrayList();
				
				
				try {
					// 1.2.
					con = getConnection();
					
					
					// 3. sql작성(select) & pstmt
					sql = "SELECT * FROM itwill_board";
					
					pstmt = con.prepareStatement(sql);

					
					// 4. sql 실행
					rs = pstmt.executeQuery();
					
					
					// 5. 데이터 처리(db->dto->list)
					while(rs.next()) {
						// 데이터가 있을 때!
							BoardDTO dto = new BoardDTO();
						
						// DB -> DTO
						dto.setBno(rs.getInt("bno"));
						dto.setContent(rs.getString("content"));
						dto.setDate(rs.getDate("date"));
						dto.setFile(rs.getString("file"));
						dto.setIp(rs.getString("ip"));
						dto.setName(rs.getString("name"));
						dto.setPass(rs.getString("pass"));
						dto.setRe_lev(rs.getInt("re_lev"));
						dto.setRe_ref(rs.getInt("re_ref"));
						dto.setReadcount(rs.getInt("readcount"));
						dto.setRe_seq(rs.getInt("re_seq"));
						dto.setSubject(rs.getString("subject"));
						
						// BoareBean -> ArrayList
						// DTO -> List
						// 글 정보 저장 완
						boardList.add(dto);
					}
					
					System.out.println("DAO : 게시판 목록 저장 완료");
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeDB();
					// try with 구문으로 자원해제 시키게 만들었음!
				}
				
				return boardList; 		// 메서드가 실행되면 boardList가 리턴되어야 한다!
				
			} //글정보 가져오기 메서드 종료
			
			
			
			
			
			// 글정보 가져오기 메서드 - getBoardList(int startRow, int pageSize);
			public ArrayList getBoardList(int startRow, int pageSize) {				// 메서드 오버로딩! 동일한 이름의 메서드를 여러 개 쓰기 위함
				
				System.out.println("DAO : getBoardList() 메서드 호출 ");
				
				// 글 정보를 모두 저장하는 배열 먼저 만들기!
				ArrayList boardList = new ArrayList();
				
				
				try {
					// 1.2.
					con = getConnection();
					
					// 3. sql작성(select) & pstmt
					sql = "select * from itwill_board "
							+ "order by re_ref desc, re_seq asc limit ?,?";
					
					pstmt = con.prepareStatement(sql);
					
					// ???
					pstmt.setInt(1, startRow-1);		// 시작행-1
					pstmt.setInt(2, pageSize);			// 개수 (pageSize가 한 페이지에 몇 개씩 보여주겠다는 뜻이므로)
					
					// 4. sql 실행
					rs = pstmt.executeQuery();
					
					// 5. 데이터 처리(db->dto->list)
					while(rs.next()) {
						// 데이터가 있을 때!
							BoardDTO dto = new BoardDTO();
						
						// DB -> DTO
						dto.setBno(rs.getInt("bno"));
						dto.setContent(rs.getString("content"));
						dto.setDate(rs.getDate("date"));
						dto.setFile(rs.getString("file"));
						dto.setIp(rs.getString("ip"));
						dto.setName(rs.getString("name"));
						dto.setPass(rs.getString("pass"));
						dto.setRe_lev(rs.getInt("re_lev"));
						dto.setRe_ref(rs.getInt("re_ref"));
						dto.setReadcount(rs.getInt("readcount"));
						dto.setRe_seq(rs.getInt("re_seq"));
						dto.setSubject(rs.getString("subject"));
						
						// BoareBean -> ArrayList
						// DTO -> List
						// 글 정보 저장 완
						boardList.add(dto);
					}
					
					System.out.println("DAO : 게시판 목록 저장 완료");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeDB();
					// try with 구문으로 자원해제 시키게 만들었음!
				}
				
				return boardList; 		// 메서드가 실행되면 boardList가 리턴되어야 한다!
				
			} //글정보 가져오기 메서드 - getBoardList(int startRow, int pageSize) 종료
	
			
			
			
	
		// 전체 글의 개수 - getBoardCount()
			public int getBoardCount() {
				int cnt = 0;
				
				try {
					// 1. 2.
					con = getConnection();
					
					// 3. sql 작성 & pstmt
					sql = "select count(*) from itwill_board";		// itwill_board 테이블의 모든 정보의 개수가 몇 개인지 
					pstmt = con.prepareStatement(sql);
					
					// 4. 실행
					rs = pstmt.executeQuery();
					
					// 5. 데이터 처리
					if(rs.next()) {		// 데이터 있을 때
						// cnt = rs.getInt("count(*)");			// 안에 컬럼명 넣기
						cnt = rs.getInt(1);						// 똑같음! 첫 번째 인덱스...?
						
					}
					
					System.out.println("DAO : 전체 글의 개수 " + cnt + "개!");
				
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				
				return cnt;
			} // getBoardCount() 메서드 끝
			
			
			
			
			
			
			
			
			
			// 조회수 1 증가 - updateReadcount(bno);
			public void updateReadcount(int bno) {

				try {
					// 1.2.					
					con = getConnection();
					// 3. sql작성 & pstmt
					sql = "update itwill_board set readcount=readcount+1 where bno=?";
					pstmt = con.prepareStatement(sql);
					// ??
					pstmt.setInt(1, bno);
					// 4. sql실행
					pstmt.executeUpdate();
					
					System.out.println("DAO : 조회수 1증가 완료");
				} catch (Exception e) {
					e.printStackTrace();
				}

				
			} // updateReadcount(bno) 메서드 끝
			
			
			
			
			
			
			
			
			// 게시판 글 1개의 정보를 조회 - getBoard(bno);
			public BoardDTO getBoard(int bno) {
				BoardDTO dto = null;
				try {
					// 1.2.
					con = getConnection();
					// 3. sql작성 & pstmt
					sql = "select * from itwill_board where bno = ?";
					pstmt = con.prepareStatement(sql);
					// ??
					pstmt.setInt(1, bno);
					// 4. sql 실행
					rs = pstmt.executeQuery();
					// 5. 처리
					if(rs.next()) {
						// 데이터가 있을 때? 즉, select구문을 썼을 때 결과물이 있음! 
						// => 이 결과물을 dto에 저장!
						
						// 데이터가 있을 때만 dto 객체를 생성할 것!
						dto = new BoardDTO();
						// DB정보(rs에 있는) -> dto 저장
						dto.setBno(rs.getInt("bno"));
						dto.setContent(rs.getString("content"));
						dto.setDate(rs.getDate("date"));
						dto.setFile(rs.getString("file"));
						dto.setIp(rs.getString("ip"));
						dto.setName(rs.getString("name"));
						dto.setPass(rs.getString("pass"));
						dto.setRe_lev(rs.getInt("re_lev"));
						dto.setRe_ref(rs.getInt("re_ref"));
						dto.setRe_seq(rs.getInt("re_seq"));
						dto.setReadcount(rs.getInt("readcount"));
						dto.setSubject(rs.getString("subject"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				return dto;
			} // getBoard(bno) 메서드 끝
			
			
			
			
			
			
			
			
			
			
			
		// 게시글을 수정하는 메서드 - updateBoard(DTO)
			public int updateBoard(BoardDTO dto) {
				int result = -1;
				
				try {
					// 1.2.
					con = getConnection();
					// 3. sql 작성 & pstmt
					sql = "SELECT pass from itwill_board where bno=?";
					pstmt = con.prepareStatement(sql);
					// ??
					pstmt.setInt(1, dto.getBno());
					// 4. sql 실행
					rs = pstmt.executeQuery();
					// 5. 처리
					if(rs.next()) { 				// 게시판에 글이 존재함
						if(dto.getPass().equals(rs.getString("pass"))) {		// 비밀번호가 같음
							// 3. sql 작성 & pstmt
							sql="update itwill_board set subject=?, name=?, content=? where bno=?";
							pstmt=con.prepareStatement(sql);
							// ???
							pstmt.setString(1, dto.getSubject());
							pstmt.setString(2, dto.getName());
							pstmt.setString(3, dto.getContent());
							pstmt.setInt(4, dto.getBno());
							// 4. 실행
							result = pstmt.executeUpdate();			// 결과를 result 변수에 담음
										//수정된 행 갯수가 1이므로 자동1반환
							
						} else {						// 비밀번호가 다름
							result = 0;
						} 
					} else {						// 게시판에 글이 없음
							result = -1;
					}
					
					System.out.println("DAO : 게시판 정보 수정 완료 (" + result + ")");
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				return result;
			} // updateBoard(DTO) 메서드 끝
			
			
			
			
			
			
			
			
			
		// 게시판 글 삭제 메서드 - deleteBoard(bno, pass);	
			public int deleteBoard(int bno, String pass) {
				int result = -1;
				
				try {
					// 1.2.
					con = getConnection();
					// 3. sql 작성 & pstmt
					sql = "SELECT pass from itwill_board where bno=?";
					pstmt = con.prepareStatement(sql);
					// ??
					pstmt.setInt(1, bno);
					// 4. sql 실행
					rs = pstmt.executeQuery();
					// 5. 처리
					if(rs.next()) { 				// 게시판에 글이 존재함
						if(pass.equals(rs.getString("pass"))) {		// 비밀번호가 같음
							// 3. sql 작성 & pstmt
							sql="delete from itwill_board where bno=?";
							pstmt=con.prepareStatement(sql);
							// ???
							pstmt.setInt(1, bno);

							// 4. 실행
							result = pstmt.executeUpdate();			// 결과를 result 변수에 담음
							
						} else {						// 비밀번호가 다름
							result = 0;
						} 
					} else {						// 게시판에 글이 없음
							result = -1;
					}
					
					System.out.println("DAO : 글 삭제 완료 (" + result + ")");
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				return result;
			}	// 글 삭제 메서드 - deleteBoard(bno, pass);	끝
			
		// 답글 쓰기 메서드 - reInsertBoard(DTO);
			public void reInsertBoard(BoardDTO dto) {
				
				int bno = 0;
				try {
					/////////////////////////////////////////////////////////
					// 1. 글 번호 계산하기(bno)
					//		1.2. 디비 연결
							con = getConnection();
					//		3. sql(Select) & pstmt
							sql = "select max(bno) from itwill_board";
							pstmt = con.prepareStatement(sql);
					//		4. 실행
							rs = pstmt.executeQuery();
					//		5. 처리
							if(rs.next()) {	// 데이터가 있을 때
								bno = rs.getInt(1) + 1;
								// ("max(bno)")+1 과 같은 의미
							}
							System.out.println("DAO : 답글 번호는? " + bno + "@@@@@@@@@@@@@@@@@@@");
					/////////////////////////////////////////////////////////
					// 2. 답글 순서를 재배치! (insert와의 차이점)
							//https://dlgkstjq623.tistory.com/124
					//		3. sql(update) & pstmt		
							sql = "UPDATE itwill_board set re_seq = re_seq+1 where re_ref=? and re_seq>?";
							// 이게 머선 말이람ㅎ
							// ref의 값이 같으면서(즉, 같은 그룹에 있으면서), 기존의 seq 값보다 큰값이 있을 때
							pstmt = con.prepareStatement(sql);
					//		?????
							pstmt.setInt(1, dto.getRe_ref());
							pstmt.setInt(2, dto.getRe_seq());
							
					//		4. sql 실행		
							int cnt = pstmt.executeUpdate();
							// => cnt (update 구문이 적용된 수)
							if(cnt > 0) {		// = 수정된 구문이 하나라도 있다면
							System.out.println("DAO : 답글 재정렬 완료!");
							}
					/////////////////////////////////////////////////////////
					// 3. 답글 쓰기(insert)
					//		3. sql(insert) & pstmt
							sql = "INSERT INTO itwill_board(bno, name, pass, subject, content, "
									+ "readcount, re_ref, re_lev, re_seq, date, ip, file) "
									+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
							pstmt = con.prepareStatement(sql);
					//		??		
							pstmt.setInt(1, bno);
							pstmt.setString(2, dto.getName());
							pstmt.setString(3, dto.getPass());
							pstmt.setString(4, dto.getSubject());
							pstmt.setString(5, dto.getContent());
							
							pstmt.setInt(6, 0);		// 조회수 0
							pstmt.setInt(7, dto.getRe_ref());	// re_ref : 원글의 번호와 동일하기 때문에 dto에서 꺼내옴
							pstmt.setInt(8, dto.getRe_lev()+1);	// re_lev : 원글의 lev + 1
							pstmt.setInt(9, dto.getRe_seq()+1);	// re_seq : 원글의 seq + 1
							
							pstmt.setString(10, dto.getIp());
							pstmt.setString(11, dto.getFile());
					//		4. sql 실행
							pstmt.executeUpdate();
							
							System.out.println("DAO : 답글 쓰기 완료!");
					/////////////////////////////////////////////////////////
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					closeDB();
				}
				
				
				
				
			}
			
			
} // class
