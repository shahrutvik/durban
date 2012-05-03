package com.carpool.db;


import com.carpool.db.retryable.MemberDB;
import com.carpool.vo.MemberVO;

public class MemberDAO {
	
	MemberDB memberVirtual;

	public MemberDAO(){
		memberVirtual = new MemberDB();
	}
	
	public void insertMemberRecord(MemberVO memberVO){
		if(!memberExistInDB(memberVO)){
		memberVirtual.insert(memberVO);
		}
		else{
			System.out.println("Member already exists");
		}
	}
	
	private boolean memberExistInDB(int memberId){
		return memberVirtual.isMemberInDB(memberId);
	}
	
	private boolean memberExistInDB(MemberVO memberVO){
		return memberVirtual.memberExistInDB(memberVO);
	}

	public int getMemberId(String phoneNumber) {
		
		return memberVirtual.getMemberId(phoneNumber);
	}

	public String getMemberFirstName(int memberGroupId) {
		return memberVirtual.getMemberFirstName(memberGroupId);
		
	}
}
