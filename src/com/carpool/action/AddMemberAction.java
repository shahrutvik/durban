package com.carpool.action;

import com.carpool.db.MemberDAO;
import com.carpool.vo.MemberVO;

public class AddMemberAction {
	
	MemberDAO memberDAO;
	
	public AddMemberAction(){
		memberDAO = new MemberDAO();
	}

	public void add(String sFirstName, String sLastName, String sPhoneNumber){
		
		MemberVO mb = new MemberVO();
		
		//cp.setNickname("group1");
		//cp.setNickname(request.ge)
		mb.setFirstName(sFirstName);
		mb.setLastName(sLastName);
		mb.setPhoneNumber(sPhoneNumber);

		add(mb);
	}
	
	private void add(MemberVO memberVO){
		memberDAO.insertMemberRecord(memberVO);
	}

	
	public static void main(String[] args) {
		AddMemberAction mb = new AddMemberAction();
		mb.add("Rutvik", "Shah", "408-506-6064");
	}


}
