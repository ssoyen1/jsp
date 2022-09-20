package com.itwillbs.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	// DAO (Data Access Object) : 데이터(DB) 처리 객체
			
			// 공통변수 선언
			private Connection con = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;
			private String sql = "";
			
			
			// 생성자
			public BoardDAO () {
				System.out.println(" DAO : BoardDAO() 객체 생성 ");
				System.out.println(" DAO : itwill_board 테이블 접근준비 완료 ");
			}
				
			
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
					} // 자원해제
				}
				
				
				
				
				
				
				
				
				// 글쓰기 메서드 - insertBoard()
				
				public void insertBoard(BoardDTO dto) { //BoardDTO dto 에 저장된 것을 가져오는 것
					System.out.println(" DAO : insertBoard() 호출");
					
					int bno = 0; // 최대값 구하기 전 선언해주기
					try {
	
						//1.2 디비연결
						con = getConnection();
						
						//3. sql 작성(글번호 계산) & pstmt 객체
						sql = "select max(bno) from itwill_board";
						pstmt = con.prepareStatement(sql);
						
						//4. sql 실행
						rs = pstmt.executeQuery();
						
						// 5. 데이터처리 (글번호 계산-번호최대값 + 1)
						// * rs.nest() - true/false 구분
						// => 워크벤치에 실행(select)
						// 	  결과 |> (삼각형 커서) 	: true ( 커서 이동 가능, sql null 상관없음) 
						//	  결과 *(점), 커서가 없을때 : false (커서 이동 불가능)
						if(rs.next()) { //최대값은 rs에 저장되어 있음
							// 			  첫번째 컬럼자리에 있는 걸 가져오는 것
							//  (X) bno = rs.getInt("bno")+1;   —> 컬럼명이 bno가 아님!
							//=>(o) bno = rs.getInt("max(bno)")+1; -> 컬럼명
							bno = rs.getInt(1)+1; // -> 컬럼명 인덱스 
							// => rs.getInt() : 리턴데이터의 값이 sql-null인 경우 0 리턴
							// null + 1 인셈 (the column value; if the value is SQL NULL, thevalue returned is 0)
							// null = 0 -> 0+1 = 1
							
							//AI(Auto 인크리먼트) 자동으로 1씩증가하는거를 안하는 형식으로 만들어보는 것 !
							// 차이는 없음!
							
						}
						
	//					else {
	//						bno=1;
	//					}
						
						System.out.println(" DAO : 글번호 = "+bno);
						
						// 3. sql 작성 (insert) & pstmt 객체
						sql = "insert into itwill_board(bno, name, pass, subject, content," 
								+ "readcount,re_ref,re_lev,re_seq,date,ip,file)" //줄바꿈할때 강제로 스페이스 띄어줘야함 꼭!
								+ " values(?,?,?,?,?,?,?,?,?,now(),?,?)"; // now() 시스템시간정보
						pstmt = con.prepareStatement(sql);
						
						
						// ???
						pstmt.setInt(1,bno);
						pstmt.setString(2,dto.getName());
						pstmt.setString(3,dto.getPass());
						pstmt.setString(4,dto.getSubject());
						pstmt.setString(5,dto.getContent());
						
						pstmt.setInt(6,0); 				// 조회수 0 초기화
						pstmt.setInt(7,bno);			// ref 그룹번호 (bno 초기화)
						pstmt.setInt(8,0);				// lev 들여쓰기 (0 초기화)
						pstmt.setInt(9,0);				// seq 순서  	(0 초기화)
						
						pstmt.setString(10, dto.getIp());
						pstmt.setString(11, dto.getFile());
						
						
						// 4. sql 실행
						pstmt.executeUpdate();
						
						System.out.println(" DAO : 게시판 글쓰기 완료! ");
						
					} catch (Exception e) {
						// 예외가 발생 시 처리하는 구문 작성(출력)
						e.printStackTrace();
					} finally {
						// 예외 여부와 상관없이 무조건 한 번 실행하는 구문
						closeDB(); // finally 안에 closeDB 메서드 불러주기. 필수!!!!!!!!!!!!!!!!!!!!!!!!
					}
					
					//3. sql 작성(글번호 계산) & pstmt 객체
					//				
					//4. sql 실행
				}
				// 글쓰기 메서드 - insertBoard()
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				// 글정보 가져오기  메서드 - getBoardList()
				public ArrayList getBoardList() {
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
						BoardDTO dto = new BoardDTO();
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
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				// 전체 글의 개수 - getBoardCount()
				public int getBoardCount() {
					int cnt = 0;
					
					// 1.2. 디비연결
					try {
						con = getConnection();
						
						// 3. sql 작성 & pstmt 객체
						sql = "select count(*) from itwill_board"; // 몇개인가? 
																   // count(*) : 모든 정보의 개수(row 행의 기준 갯수)
//						select count(bno) from itwill_board; // 위와 동일
						pstmt = con.prepareStatement(sql);
						
						
						//4. sql 실행
						rs = pstmt.executeQuery();
						
						// 5. 데이터 처리
						if(rs.next()) {
//							cnt = rs.getInt("count(*)"); // count(*) = 컬럼명
							cnt = rs.getInt(1); // 위와 동일 /첫번째 컬럼 인덱스 ( 컬럼 : 1부터 / 로우,행 : 0부터)
						}
												
						System.out.println("DAO : 전체 글의 개수 "+cnt+"개");
				
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						closeDB();  // finally까지 만드는 것이 기본이므로 공식화해서 외우기!
					}
					
					return cnt;
				}// 전체 글의 개수 - getBoardCount()

				
				
				
				
				
				
				
				
				
				
				
				
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
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				public ArrayList getBoardList(int startRow, int pageSize) { //메서드오버로딩.이름동일
					System.out.println(" DAO : getBoardList() 호출 " );
					// 글정보 모두 저장하는 배열
					ArrayList boardList = new ArrayList();
					
				try {
					// 1.2 디비연결
					con = getConnection();
					
					// 3. sql 작성(select) & pstmt 객체
					sql = "select * from itwill_board " //뛰어쓰기 주의. 안뛰어쓰면 목록 안나옴.
							+ "order by re_ref desc, re_seq asc limit ?, ?;"; // ?, ? 안에 원하는 값을 넣고싶으므로
					pstmt = con.prepareStatement(sql);
					//???
					pstmt.setInt(1, startRow-1); // 시작행-1
					pstmt.setInt(2, pageSize); // 개수
					

					// 4. sql 실행
					rs = pstmt.executeQuery();
					
					// 5. 데이터 처리( DB->DTO->List)
					while(rs.next()) {  // select로 전체를 다 가져오니까 계속 반복하면서 정보 가져오도록 함 if아님! 계속 반복해야하니까.
						
						// DB -> DTO
						BoardDTO dto = new BoardDTO();
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
				
				
				
				
				
				
				
			// 
				
				
}
