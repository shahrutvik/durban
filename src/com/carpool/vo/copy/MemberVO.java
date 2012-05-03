package com.carpool.vo.copy;

//Created By : Shrujal
//Date : 04/03/2012
//Purpose : To create getter/setter for Member

public class MemberVO {
	
	private int idMember;
	private String memberFirstName;
	private String memberLastName;
	private String memberPhoneNumber;

	public int getMemberId() {
		return idMember;
	}

	public void setMemberId(int idMember) {
		this.idMember = idMember;
	}

	public String getFirstName() {
		return memberFirstName;
	}

	public void setFirstName(String sFirstName) {
		this.memberFirstName = sFirstName;
	}

	public String getLastName() {
		return memberLastName;
	}

	public void setLastName(String sLastName) {
		this.memberLastName = sLastName;
	}
	
	public String getPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setPhoneNumber(String sPhoneNumber) {
		this.memberPhoneNumber = sPhoneNumber;
	}
}
