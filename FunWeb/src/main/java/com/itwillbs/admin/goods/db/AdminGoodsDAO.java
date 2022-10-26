package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class AdminGoodsDAO {
	 // 두개 이름이 다른 이유?
	 // GoodsDTO : 상품의 정보는 굳이 admin으로 만들 필요가 없음. 
	 // AdminGoodsDAO : 처리는 관리자가 해야하므로 
	
	 // (반복되는 동작들을 계속 줄여나가는 것이 중요하다)
	
			private Connection con = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;
			private String sql = "";
			
			// 디비를 연결해주는 메서드 (커넥션 풀)
			private Connection getConnection() throws Exception {
				
				// 1. 드라이버 로드 + 2. 디비 연결
				// => 하나로 합침! 
				// Context 객체를 생성 (JavaNamingDirectoryInterface API)
				Context initCTX = new InitialContext();			// 인터페이스! 추상메서드 가지고 있기 때문에 객체 생성 불가
									// = 초기화된 프로젝트		// 둘 사이는 업캐스팅 된 관계이기 때문에 둘은 상속관계인 걸 알 수 있음
				// 디비 연동 정보를 불러오기 (context.xml 정보)
				DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/funweb");		
				// 그래서 이게 뭔데
				// java:comp/env/ 까지는 고정이고 리소스의 네이밍만 바꾸면 다른 정보를 불러서 쓸 수 있다
				
				// 디비 정보(연결) 불러오기
				con = ds.getConnection();
				System.out.println("DAO : 디비연결 성공(커넥션 풀)");
				System.out.println("DAO : con : " + con);
				
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
					e.printStackTrace();
				}
				
			} // 자원해제 메서드 끝
			
			
			
			// 상품등록 메서드 - insertGoods(DTO)
			
			public void insertGoods(GoodsDTO dto) {
				

					int gno = 0;
					try {
					// 1. 상품번호 계산
						//1.2
						con = getConnection();
						
						//3. sql&pstmt
						sql = "select max(gno) from itwill_goods";
						pstmt = con.prepareStatement(sql);
						
						//4. 실행
						rs = pstmt.executeQuery();
						
						//5. 데이터처리
						if(rs.next()) {
							gno = rs.getInt(1)+1;
						}
						
						System.out.println(" DAO : gno :"+ gno);
						
						
					// 2. 상품 등록
						//3. sql&pstmt
						sql = "insert into itwill_goods(gno,category,name,content,size,color,amount,price,image,best) "
								+ "values(?,?,?,?,?,?,?,?,?,?)"; // 데이터정보는 담지않는다.
						pstmt = con.prepareStatement(sql);
						
						pstmt.setInt(1, gno);
						pstmt.setString(2, dto.getCategory());
						pstmt.setString(3, dto.getName());
						pstmt.setString(4, dto.getContent());
						pstmt.setString(5, dto.getSize());
						pstmt.setString(6, dto.getColor());
						pstmt.setInt(7, dto.getAmount());
						pstmt.setInt(8, dto.getPrice());
						pstmt.setString(9, dto.getImage());
						pstmt.setInt(10, dto.getBest());
						
						pstmt.executeUpdate();
						
						System.out.println(" DAO : 상품 등록 완료! ");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			// 상품등록 메서드 - insertGoods(DTO)
			
			
//			
//			// 회원리스트 메서드 - getGoodsList
//			
//			public ArrayList getGoodsList() {
//				
//				
//				return goodslist;
////			}
			
			
		
			}
