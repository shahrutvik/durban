package com.carpool.vo.copy;





public class EventVO {
	private int idEvent;
	private String eventDate;
	private int idMemberGroup;
	private int idRole;
	
	public int getIdGroupMember() {
		return idMemberGroup;
	}
	public void setIdGroupMember(int groupId) {
		this.idMemberGroup = groupId;
	}
	
	public int getRoleId() {
		return idRole;
	}
	public void setRoleId(int roleId) {
		this.idRole = roleId;
	}
	
	public int getEventId() {
		return idEvent;
	}
	public void setEventid(int id) {
		this.idEvent = id;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String date) {
		this.eventDate = date;
	}
	
	

}
