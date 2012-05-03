package com.carpool.action;

import com.carpool.db.MemberDAO;

public class SelectMemberAction {
	MemberDAO memberDAO;
	
	public SelectMemberAction(){
		memberDAO = new MemberDAO();
	}
	
	/*public int getMemberId(String phoneNumber) {
		return memberDAO.getMemberId(phoneNumber);
	}*/

}
