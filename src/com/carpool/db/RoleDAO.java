package com.carpool.db;

import com.carpool.db.retryable.RoleDB;


public class RoleDAO {
	
	RoleDB roleVirtual;

	public RoleDAO(){
		roleVirtual = new RoleDB();
	}
	
	
	public int getRoleId(String roleType) {
		return roleVirtual.getRoleId(roleType);
		
	}


	public String getRoleType(int idRole) {
		return roleVirtual.getRoleType(idRole);
		
	}

}
