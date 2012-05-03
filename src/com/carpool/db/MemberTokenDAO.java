package com.carpool.db;

import com.carpool.db.retryable.MemberTokenDB;
import com.carpool.vo.MemberToGroupVO;
import com.carpool.vo.MemberTokenVO;

public class MemberTokenDAO {
	MemberTokenDB memberTokenVirtual;
	
	
	public MemberTokenDAO(){
		memberTokenVirtual = new MemberTokenDB();
	}
	
	/*public void insertMemberTokenRecord(MemberToGroupVO memberToGroupVO){
		memberTokenVirtual.insertMemberTokenRecord(memberToGroupVO);
	}*/
	
	public int getTotalTokens(int idMemberGroup){
		return memberTokenVirtual.getTotalTokens(idMemberGroup);
	}

	public void updateTotalToken(MemberTokenVO memberTokenVO) {
		memberTokenVirtual.updateTotalToken(memberTokenVO);
		
	}

	public void insertMemberTokenRecord(MemberTokenVO memberToken) {
		memberTokenVirtual.insertMemberTokenRecord(memberToken);
		
	}

}
