package com.itwillbs.board;

import java.sql.Date;

public class BoardDTO {
	// DTO (Data Transfer Object) 데이터(DB 테이블) 전송 객체  (자바빈과 별 차이 없음)
				
				private int bno;			// 게시판 글번호(PK)
				private String name;		// 글쓴이
				private String pass;		// 글 비밀번호
				private String subject;		// 글 제목
				private String content;		// 글 내용
				
				private int readcount;		// 조회수
				private int re_ref;			// 답글 - 글 그룹번호
				private int re_lev;			// 답글 - 글 들여쓰기
				private int re_seq;			// 답글 - 글 순서
				
				private Date date;			// 글 작성일 
				private String ip;			// 글 작성자 IP주소
				private String file;		// 첨부파일 (이미지파일을 여기 저장할 수 없음. 다른 공간에 저장해야함/ 파일 이름만 저장가능 필요할때마다 이름을 가져와서 파일저장한 곳에 이름으로 찾도록 함. 전당포느낌)
				
				
				// alt shift s + r
				public int getBno() {
					return bno;
				}
				public void setBno(int bno) {
					this.bno = bno;
				}
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
				public String getPass() {
					return pass;
				}
				public void setPass(String pass) {
					this.pass = pass;
				}
				public String getSubject() {
					return subject;
				}
				public void setSubject(String subject) {
					this.subject = subject;
				}
				public String getContent() {
					return content;
				}
				public void setContent(String content) {
					this.content = content;
				}
				public int getReadcount() {
					return readcount;
				}
				public void setReadcount(int readcount) {
					this.readcount = readcount;
				}
				public int getRe_ref() {
					return re_ref;
				}
				public void setRe_ref(int re_ref) {
					this.re_ref = re_ref;
				}
				public int getRe_lev() {
					return re_lev;
				}
				public void setRe_lev(int re_lev) {
					this.re_lev = re_lev;
				}
				public int getRe_seq() {
					return re_seq;
				}
				public void setRe_seq(int re_seq) {
					this.re_seq = re_seq;
				}
				public Date getDate() {
					return date;
				}
				public void setDate(Date date) {
					this.date = date;
				}
				public String getIp() {
					return ip;
				}
				
				// alt shift s + s  -> Generate
				/**
				 *  한번에 BoardDTO 객체에 저장된 정보 확인용
				 */
				@Override
				public String toString() {
					 // 한번에 해당 객체의 정보를 출력
				
					return "BoardDTO [bno=" + bno + ", name=" + name + ", pass=" + pass + ", subject=" + subject
							+ ", content=" + content + ", readcount=" + readcount + ", re_ref=" + re_ref + ", re_lev="
							+ re_lev + ", re_seq=" + re_seq + ", date=" + date + ", ip=" + ip + ", file=" + file + "]";
				}
				public void setIp(String ip) {
					this.ip = ip;
				}
				public String getFile() {
					return file;
				}
				public void setFile(String file) {
					this.file = file;
				}
				
		
		
		
			


	

} // class


