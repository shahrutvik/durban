package com.carpool.action;


import com.carpool.db.GroupDAO;
import com.carpool.db.MemberDAO;
import com.carpool.db.MemberToGroupDAO;
import com.carpool.db.MemberTokenDAO;
import com.carpool.vo.MemberToGroupVO;
import com.carpool.vo.MemberTokenVO;

public class AddMemberToGroupAction {
	
	MemberToGroupDAO membertoGroupDAO;
	GroupDAO groupDAO;
	MemberDAO memberDAO;
	MemberTokenDAO memberTokenDAO;
	
	public AddMemberToGroupAction(){
		membertoGroupDAO = new MemberToGroupDAO();
		groupDAO = new GroupDAO();
		memberDAO = new MemberDAO();
		memberTokenDAO = new MemberTokenDAO();
	}
	
	public void add(String groupNickName,String memberPhoneNumber){
		//adds the member to the group and initializes the token record.
		MemberToGroupVO memberToGroup = new MemberToGroupVO();
		MemberTokenVO memberToken= new MemberTokenVO();
		int groupId=groupDAO.getGroupId(groupNickName);
		int memberId=memberDAO.getMemberId(memberPhoneNumber);
		if(groupId!=-1 && memberId!=-1){
			memberToGroup.setGroupId(groupId);
			memberToGroup.setMemberId(memberId);
			add(memberToGroup);
			//Also add a initial token record for this member
			int memberGroupId=membertoGroupDAO.getMemberGroupId(memberToGroup);
			if(memberGroupId!=-1){
				memberToken.setMemberGroupId(memberGroupId);
				memberToken.setTotalToken(0);
				memberTokenDAO.insertMemberTokenRecord(memberToken);
			}
			else
			{
				System.out.println("Failed to initialize member token");
			}
			
		}
		else{
			System.out.println("Failed to add member to the group");
		}
		
	}

	/*public void add(int memberId,int groupId){
		
		MemberToGroupVO memberToGroup = new MemberToGroupVO();
		memberToGroup.setMemberId(memberId);
		memberToGroup.setGroupId(groupId);
		add(memberToGroup);
	}*/
	
	private void add(MemberToGroupVO memberToGroup){
		membertoGroupDAO.insertMemberToGroupRecord(memberToGroup);
		//memberTokenDAO.insertMemberTokenRecord(memberToGroup);
	}

	
	
}
