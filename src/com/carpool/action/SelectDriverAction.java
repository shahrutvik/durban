package com.carpool.action;

import com.carpool.db.GroupDAO;
import com.carpool.db.MemberDAO;
import com.carpool.db.MemberToGroupDAO;
import com.carpool.db.MemberTokenDAO;

public class SelectDriverAction {
	MemberToGroupDAO membertoGroupDAO;
	GroupDAO groupDAO;
	MemberTokenDAO memberTokenDAO;
	MemberDAO memberDAO;
	
	public SelectDriverAction(){
		membertoGroupDAO = new MemberToGroupDAO();
		groupDAO = new GroupDAO();
		memberTokenDAO = new MemberTokenDAO();
		memberDAO = new MemberDAO();
			
	}

	public String getDriverName(String nickname) {
		//Find id of the group
		int idGroup=groupDAO.getGroupId(nickname);
		//Find all members of this group
		int[] idMemberGroup=membertoGroupDAO.getMemberGroupIds(idGroup);
		//For this membergroup find the id with lowest token
		int lowestToken=0;
		int localMemberGroup = 0;
		for(int j=0;j<idMemberGroup.length;j++){
			int totalTokens=memberTokenDAO.getTotalTokens(idMemberGroup[j]);
			if(totalTokens<lowestToken){
				localMemberGroup=idMemberGroup[j];
				lowestToken=totalTokens;
			}
			
		}
		
		return memberDAO.getMemberFirstName(idMemberGroup[localMemberGroup]);
		
	}

}
