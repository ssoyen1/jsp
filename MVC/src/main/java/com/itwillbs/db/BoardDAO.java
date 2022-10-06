package com.itwillbs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// ctrl + shift + o => 해당 파일에 필요없는 패키지 삭제해줌
//import com.itwillbs.board.BoardDTO; - 오류뜨므로 삭제함

public class BoardDAO {

		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql ="";
	
		

		
		
		
//		// 디비 연결정보 메서드(1)
//		private Connection getConnection() throws Exception {
//			
//			// 디비 연결 정보(상수)
//			final String DRIVER = "com.mysql.cj.jdbc.Driver";
//			final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
//			final String DBUSER = "root";
//			final String DBPW = "1234";
//			
//			// DB에서 필요한 정보 가져오기
//			// 1. 드라이버 로드
//				Class.forName(DRIVER);	
//			
//			// 2. 연결
//				con = DriverManager.getConnection(DBURL, DBUSER, DBPW);
//				System.out.println("DAO : 디비 연결 성공");
//				System.out.println("DAO : " + con);
//				
//				return con;
//		}
		
		
		// 디비 연결정보 메서드 (2)
		private Connection getConnection() throws Exception {
				// 1. 드라이버 로드  // 2. 디비연결 
				
				// Context 객체 생성 (JNDI API) : 이름과 폴더에 접근할 수 있게 해주는 객체
				Context initCTX = new InitialContext(); // InitialContext(초기화된 프로젝트) 는 Context를 상속하고 있는 클래스.
														// new 할 수 있으므로. 두 타입이 다르나 저장하는데 아무런 문제가 없으므로 업캐스팅 관계
				
				// 디비연동정보 불러오기 (context.xml안에 파일정보)
				DataSource ds =  (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc"); // 이 영역에있는 디비정보를 가져와라 
																		   // java:comp/env 까지는 고정. 그뒤에 리소스네임만 바뀜
																		   // javax.sql
				
				// 디비연결정보 불러오기
				con = ds.getConnection();
				
				System.out.println(" DAO : 디비연결 성공(커넥션풀) ");
				System.out.println(" DAO : con : "+con);
				
				
				// 
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
				rs = pstmt.executeQuery();
				
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 글 전체 개수 확인 - getBoardCount()
		public int getBoardCount() {
			int cnt = 0;
			
			
			try {
				// 1.2. 디비연걸
				con = getConnection();
				
				
				// 3. sql
				sql = "select count(*) from itwill_board";
				pstmt = con.prepareStatement(sql);
				
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				
				// 5. 데이터처리
				if(rs.next()) {
//					cnt = rs.getInt(1); // 아래와 같은 코드. 인덱스로 접근하는게 더빠름 ㅎㅎ
					cnt = rs.getInt("count(*)");
				}
				
				System.out.println(" DAO : 전체 글 개수 : "+cnt+"개");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			
			return cnt;
		}// 글 전체 개수 확인 - getBoardCount()
		
		
		
		
		
		
		
		
		
		
		
		
		// 글정보 가져오기  메서드 - getBoardList(int startRow, int pageSize))
		public ArrayList getBoardList(int startRow, int pageSize) {
			System.out.println(" DAO : getBoardList() 호출 " );
			// 글정보 모두 저장하는 배열
			ArrayList boardList = new ArrayList();
			
		try {
			// 1.2 디비연결
			con = getConnection();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from itwill_board"; // 기존에 있는 글들을 가져와서 수행하므로.
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리( DB->DTO->List)
			while(rs.next()) {  // select로 전체를 다 가져오니까 계속 반복하면서 정보 가져오도록 함 if아님! 계속 반복해야하니까.
				
				// DB -> DTO
				BoardDTO dto = new BoardDTO(); // dto에 하나의 정보를 다 저장하여 리스트로감
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				dto.setIp(rs.getString("ip"));
				
				// DTO -> List
				
				boardList.add(dto);  // 돌때마다 새로운 dto가 만들어지고 또 while 돌면 새로운 dto 생기도록 함.
									 // ArrayList를 생성한 후 add() 메소드로 엘레멘트를 추가할 수 있음
				
			}//while
			
			System.out.println(" DAO : 게시판 목록 저장완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			return boardList; // 이 메서드가 실행되면 boardList 리턴하기
		}
		// 글정보 가져오기  메서드 - getBoardList()
		
	

		
		
		
		
		
		// 조회수 1증가 - updateReadcount(bno)
		public void updateReadcount(int bno) {//bno라는 변수를 받아와야 실행할 수 있음

			
			
				try {
					// 1.2. 디비연결
					con = getConnection();
					
					// 3. sql 작성(update) & pstmt 객체
					sql = "update itwill_board set readcount=readcount+1"
							+" where bno=?";
					pstmt = con.prepareStatement(sql);
					
					//???
					pstmt.setInt(1, bno); // 위에서 메서드 실행할때 담아왔던 것이므로 바로 bno 적어도됨
					
					// 4. sql 실행
					pstmt.executeUpdate(); //update는 rs에 저장할 수 없음.
					
					System.out.println(" DAO : 조회수 1증가 완료!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeDB();
				}

				
				
		
		
		} // 조회수 1증가 - updateReadcount(bno)
		
		
		
		
		
		
		
		
		
		
		// 게시판 글 1개의 정보 조회 - getBoard(bno)
		public BoardDTO getBoard(int bno) {
			
			BoardDTO dto=null; // try안에있으면 에러가 남. 
							   // -> try 도 지역(= 즉, 메서드) 으로 분류되므로 try밖에서 선언해줘야함
			
			try {
				// 1.2. 디비연결
				con = getConnection();
				
				// 3. sql 작성(select) & pstmt 객체
				sql = "select * from itwill_board where bno = ?";
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setInt(1, bno);
				
				// 4. sql 실행
				rs = pstmt.executeQuery(); // select구문에만 rs 에 정보를 담을 수 있음
				
				// 5. 데이터처리
				if(rs.next()) { //데이터가 있을때( = select실행했을때 결과물이 있다 -> 그때 DTO에 저장한다)
					// 데이터가 있을때만 dto 객체 생성
					dto = new BoardDTO();
					
					//DB정보(rs) -> dto에 저장
					dto.setBno(rs.getInt("bno"));
					dto.setDate(rs.getDate("date"));
					dto.setContent(rs.getString("content"));
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
				
				System.out.println(" DAO : 글 정보 1개 저장완료 ! ");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return dto; // dto에 저장한 후 최종적으로 dto를 리턴해준다.
		}
		
		// 게시판 글 1개의 정보 조회 - getBoard(bno)

		
		
		
		
		
		
		
		
		
		
		
		//게시글 수정 - updateBoard(DTO)
		public int updateBoard(BoardDTO dto) {
			int result = -1; // 임의로 초기화한 값
			
			// 1.2. 디비연결
			try {
				// 1.2. 디비연결
				con = getConnection();
				
				// 3. sql 작성(select) & pstmt 객체
				sql = "select pass from itwill_board where bno=?"; //특정게시판글에 해당하는 비밀번호를 불러오겠다.
				pstmt = con.prepareStatement(sql);
				//???
				pstmt.setInt(1,dto.getBno()); // bno에 빨간줄 뜨는 이유 : bno만 그대로 들고온게 아니므로 
											  // dto에 bno를 담아왔음. 그래서 꺼내오자
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터처리
				if(rs.next()) { // = 게시판에 글이 존재한다 
					// = 수정을 의미함
					if(dto.getPass().equals(rs.getString("pass"))) { 
														//수정할때받은 비밀번호와, 기존디비에 있던 비밀번호와 비교하는 것
							
						// 3. sql 작성(update) & pstmt 객체
						sql = "update itwill_board set subject=?,name=?,content=? where bno=?"; 
														// 바꾸는 것은 정해져있으므로 뒤에 pass=? 굳이 할 필요없음
														// 이미 앞에서 pass는 비교해서 판단했으므로!
						pstmt = con.prepareStatement(sql);
						
						//???
						pstmt.setString(1, dto.getSubject());
						pstmt.setString(2, dto.getName());
						pstmt.setString(3, dto.getContent());
						pstmt.setInt(4, dto.getBno());
						
						// 4. sql 실행
						result = pstmt.executeUpdate();
						
					}else {
							// 비밀번호 오류
							result = 0;
					}
				}else { // = 게시판에 글이 없다.

					result = -1; // 임의로 글이없을때 -1이라고 우리가 지정한 것.
				}
				
				System.out.println(" DAO : 게시판 정보 수정 완료 ("+result+")");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}

			return result;
		}
		//게시글 수정 - updateBoard()
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 게시판 글 삭제 - deleteBoard(bno,pass)
		public int deleteBoard (int bno, String pass) { // 위에서 bno와 따로받았기 때문에 두개 따로 적었지만 ]
												   //
			int result = -1; // 임의로 초기화한 값
			
			// 1.2. 디비연결
			try {
				// 1.2. 디비연결
				con = getConnection();
				
				// 3. sql 작성(select) & pstmt 객체
				sql = "select pass from itwill_board where bno=?"; //특정게시판글에 해당하는 비밀번호를 불러오겠다.
				pstmt = con.prepareStatement(sql);
				//???
				pstmt.setInt(1, bno); // bno에 빨간줄 뜨는 이유 : bno만 그대로 들고온게 아니므로 
											  // dto에 bno를 담아왔음. 그래서 꺼내오자
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터처리
				if(rs.next()) { // = 게시판에 글이 존재한다 
					// = 수정을 의미함
					if(pass.equals(rs.getString("pass"))) { 
														//수정할때받은 비밀번호와, 기존디비에 있던 비밀번호와 비교하는 것
							
						// 3. sql 작성(update) & pstmt 객체
						sql = "delete from itwill_board where bno=?"; 
														// 바꾸는 것은 정해져있으므로 뒤에 pass=? 굳이 할 필요없음
														// 이미 앞에서 pass는 비교해서 판단했으므로!
						pstmt = con.prepareStatement(sql);
						
						//???
						pstmt.setInt(1, bno);
						// 4. sql 실행
						result = pstmt.executeUpdate();
						
					}else {
							// 비밀번호 오류
							result = 0;
					}
				}else { // = 게시판에 글이 없다.

					result = -1; // 임의로 글이없을때 -1이라고 우리가 지정한 것.
				}
				
				System.out.println(" DAO : 게시판 정보 수정 완료 ("+result+")");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}

			return result; 
		}
		// 게시판 글 삭제 - deleteBoard(bno,pass)

		
		/** 
		 * 메서드 설명 작성하기
		 *	*/
		
		
		// 답글쓰기 - reInsertBoard(DTO)
		public void reInsertBoard(BoardDTO dto) { //글쓰고 그대로 리스트 갈 것이므로 void
												  //글쓰고 체크할 것이 있을경우 int
												  // but 보통 insert는 리턴없음
			int bno = 0; 
			

			try {
				
				//////////////////////////////////////////////////////////////////////////////////////
				//1. 글번호 계산하기 (bno)
				//////////////////////////////////////////////////////////////////////////////////////
				
				// 1.2. 디비연결
				con = getConnection();
				
				// 3. sql작성(select) & pstmt 객체
				sql = "select max(bno) from itwill_board"; //글번호중 최대값을 가져와라
				pstmt = con.prepareStatement(sql);
				
				// 4. sql 실행		
				rs = pstmt.executeQuery(); // select하게되면 무조건 데이터가 생기니까 rs 처리를 하는 것.
				
				// 5. 데이터처리
				if(rs.next()) {
					bno = rs.getInt(1)+1; 
					//bno = rs.getInt("max(bno)")+1; : 두개 같은 의미 
				}
				
				System.out.println(" DAO : 답글 번호(bno) "+bno+"@@@@@@@@@@@@@@@@@@@@@@"); // 내가 잘 찾으려면 이렇게 표시를 해놔도 됨 ㅋㅋ

				
				
				
				//////////////////////////////////////////////////////////////////////////////////////
				//2. 답글 순서 재배치
				//////////////////////////////////////////////////////////////////////////////////////

				//3. sql 작성(update) & pstmt 객체
				// => 같은 그룹에 있으면서, 기존의 seq값보다 큰값이 있을때 시퀀스를 1증가해라
				sql = "update itwill_board set re_seq = re_seq+1 " //조회수 할때 썼던 로직
						+ "where re_ref=? and re_seq>?";			// where 조건이 만족할때만 실행하는 것 
				
								// 답글쓰기할때 bno계산, update , insert함. update할때에는 insert가 안되어있으므로 원글밖에 존재하지않는 상태
								// so insert할때 +1 이 걸리는 것. 
								//"where re_ref=? and re_seq>?": 같은 그룹 중에서 기존원글의 시퀀스보다 큰 것이 있느냐 
								// set re_seq = re_seq+1  : 거기에 +1 하는것 (?)
								
								// 딥글의 답글을 달을 경우 lev값으로 판단하기. lev이 0만아니면 답글임.
				
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setInt(1, dto.getRe_ref());
				pstmt.setInt(2, dto.getRe_seq());
				
				//4. sql 실행
				int cnt = pstmt.executeUpdate(); // 쿼리로 업데이트가 몇개 되었는지 count 변수 cnt에 저장해서 알 수 있음
												 // => cnt(update 구문이 적용된 수)
				
				if(cnt > 0) { // 쿼리를 실행했던 구문이 영향을 준게 0보다 클때 = 수정을 한 것이 있을 때
					System.out.println(" DAO : 답글 재정렬 완료! ");
				}
				
				
				//////////////////////////////////////////////////////////////////////////////////////
				//3. 답글 쓰기 (insert) ( 기존의 insert구문과 똑같이 써도 상관없음)
				//////////////////////////////////////////////////////////////////////////////////////
				
				// 3. sql 작성 & pstmt 객체
				sql = "insert into itwill_board(bno,name,pass,subject,content, "
						+ "readcount,re_ref,re_lev,re_seq,date,ip,file) "
						+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
				pstmt = con.prepareStatement(sql);
				
				// ???
				pstmt.setInt(1, bno);
				pstmt.setString(2, dto.getName());
				pstmt.setString(3, dto.getPass());
				pstmt.setString(4, dto.getSubject());
				pstmt.setString(5, dto.getContent());
				
				pstmt.setInt(6, 0); //readcount 조회수는 0이다.
				pstmt.setInt(7, dto.getRe_ref()); // re_ref : 원글의 번호와 동일
				pstmt.setInt(8, dto.getRe_lev()+1); // re_lev : 원글의 lev(dto.getRe_lev)+1
				pstmt.setInt(9, dto.getRe_seq()+1); // re_seq : 원글의 sep+1
				pstmt.setString(10, dto.getIp());
				pstmt.setString(11, dto.getFile());
				
				
				// 4. sql 실행
				pstmt.executeUpdate();
				
				System.out.println(" DAO : 답글 쓰기 완료 ! ");
				
				
				
				
				
				
						
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			

			
			
		}
		
		// 답글쓰기 - reInsertBoard(DTO)
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
	// 
		
		
		
		
		
		
		
		
		
} //BoardDAO
