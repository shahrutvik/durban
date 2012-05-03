package com.carpool.db;

import com.carpool.db.retryable.MemberGroupsDB;
import com.carpool.vo.MemberToGroupVO;

public class MemberToGroupDAO {
	
	MemberGroupsDB memberToGroupVirtual;

	public MemberToGroupDAO(){
		memberToGroupVirtual = new MemberGroupsDB();
	}
	
	public void insertMemberToGroupRecord(MemberToGroupVO memberToGroupVO){
		memberToGroupVirtual.insert(memberToGroupVO);
	}

	public int getMemberCount(int groupId) {
		return memberToGroupVirtual.getMemberCount(groupId);
	}

	public int getMemberGroupId(MemberToGroupVO memberToGroup) {
		
		return memberToGroupVirtual.getMemberGroupId(memberToGroup);
	}
	
	public int[] getMemberGroupIds(int groupId){
		return memberToGroupVirtual.getMemberGroupIds(groupId);
	}

}
