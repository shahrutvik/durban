package com.carpool.action;

import com.carpool.db.MemberTokenDAO;

public class SelectMemberTokenAction {
	MemberTokenDAO memberTokenDAO;
	
	public SelectMemberTokenAction(){
		memberTokenDAO = new MemberTokenDAO();
	}
	
	/*public int getTotalTokens(int idMember){
		return memberTokenDAO.getTotalTokens(idMember);
	}*/

}
