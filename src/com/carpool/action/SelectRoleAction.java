package com.carpool.action;

import com.carpool.db.RoleDAO;


public class SelectRoleAction {
	RoleDAO roleDAO;
	
	public SelectRoleAction(){
		roleDAO = new RoleDAO();
	}
	
	/*public int getRoleId(String roleType) {
		return roleDAO.getRoleId(roleType);
	}*/

}
