package org.zerock.domain;

import java.util.ArrayList;

public class BoardVO {
	// 게시판 번호
	private int bno;
	// 제목
	private String title;
	// 내용
	private String content;
	// 작성자
	private String writer;
	// 작성일자
	private String regdate;
	// 수정일자
	private String updatedate;
	// 댓글 건수
	private int replycnt;
	// 첨부파일과 관련이 있는 BoardAttachVO를 클래스를 포함하여 BoardVO를 insert할 때 같이 데이터를 가져가자.
	private ArrayList<BoardAttachVO> attachList;
	
	public ArrayList<BoardAttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(ArrayList<BoardAttachVO> attachList) {
		this.attachList = attachList;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", updatedate=" + updatedate + ", replycnt=" + replycnt + ", attachList=" + attachList
				+ "]";
	}
	
}
