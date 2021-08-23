package org.zerock.domain;

public class SampleVO {
	private int mno;
	private String firstName;
	private String lastName;
	
	public SampleVO(int mno,String firstName,String lastName) { // 직접 추가
		this.mno = mno;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "SampleVO [mno=" + mno + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
