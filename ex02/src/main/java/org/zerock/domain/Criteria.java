package org.zerock.domain;

public class Criteria {
	// 페이지 번호(pageNum)
	private int pageNum;
	// 한 페이지당 데이터 갯수(amount)
	private int amount;
	// type
	private String type;
	// keyword
	private String keyword;
	
	public Criteria() { // 기본 생성자
		this(1,10); // 첫 번째 페이지에 10개를 보여줘라.
	}
	public Criteria(int pageNum, int amount) { // 매개변수가 2개인 생성자
		this.pageNum=pageNum; // ex) 2번 째 페이지
		this.amount=amount; // 10개를 보여줘라
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
	
	
	
	
	
}
