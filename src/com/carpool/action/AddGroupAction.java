package com.carpool.action;

import com.carpool.db.GroupDAO;
import com.carpool.vo.GroupVO;

public class AddGroupAction {
	
	GroupDAO groupDAO;
	
	public AddGroupAction(){
		groupDAO = new GroupDAO();
	}

	public void add(String nickname){
		
		GroupVO groupVO = new GroupVO();
		groupVO.setNickname(nickname);
		add(groupVO);
	}
	
	private void add(GroupVO groupVO){
		groupDAO.insertGroupRecord(groupVO);
	}

	
}
