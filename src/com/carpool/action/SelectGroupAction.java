package com.carpool.action;

import com.carpool.db.GroupDAO;

public class SelectGroupAction {
	
	GroupDAO groupDAO;
	
	public SelectGroupAction(){
		groupDAO = new GroupDAO();
	}
	
	/*public int getId(String nickname){
		return groupDAO.getGroupId(nickname);
	}*/

}
