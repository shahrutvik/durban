package com.carpool.action;

import java.util.List;

import com.carpool.db.EventDAO;
import com.carpool.db.GroupDAO;
import com.carpool.db.MemberDAO;
import com.carpool.db.MemberToGroupDAO;
import com.carpool.db.RoleDAO;
import com.carpool.db.TokenDAO;
import com.carpool.vo.EventVO;
import com.carpool.vo.MemberToGroupVO;
import com.carpool.vo.RoleVO;



public class AddEventAction {
	
	EventDAO eventDAO;
	MemberToGroupDAO membertoGroupDAO;
	GroupDAO groupDAO;
	MemberDAO memberDAO;
	RoleDAO roleDAO;
	TokenDAO tokenDAO;
	AddTokenAction addTokenAction;
	
	public AddEventAction(){
		eventDAO = new EventDAO();
		membertoGroupDAO = new MemberToGroupDAO();
		groupDAO = new GroupDAO();
		memberDAO = new MemberDAO();
		roleDAO = new RoleDAO();
		tokenDAO=new TokenDAO();
		addTokenAction = new AddTokenAction();
	}
	
	public EventVO getEvent(String date,String memberPhoneNumber,String groupNickName,String role){
		EventVO event = new EventVO();
		MemberToGroupVO memberToGroup=new MemberToGroupVO();
		int groupId=groupDAO.getGroupId(groupNickName);
		int memberId=memberDAO.getMemberId(memberPhoneNumber);
		memberToGroup.setGroupId(groupId);
		memberToGroup.setMemberId(memberId);
		int idMemberGroup=membertoGroupDAO.getMemberGroupId(memberToGroup);
		RoleVO roleVO=new RoleVO();
		roleVO.setRole(role);
		int roleId=roleDAO.getRoleId(role);
		
		
		event.setEventDate(date);
		event.setIdGroupMember(idMemberGroup);
		event.setRoleId(roleId);
		return event;
		
	}
	
	

	/*public void add(int groupId,int memberId,int roleId,String date){
		
		EventVO event = new EventVO();
		event.setGroupId(groupId);
		event.setMemberId(memberId);
		event.setRoleId(roleId);
		event.setDate(date);
		

		eventDAO.insertEventRecord(event);
	}*/
	
	/*private void add(EventVO eventVO){
				eventDAO.insertEventRecord(eventVO);
	}*/
	
	public void add(List<EventVO> listEventsVO){
		int eventId=1;
		if(eventDAO.getNumberOfEvents()!=0){
			eventId=eventDAO.getNextEventId();
			eventDAO.insertEventsRecord(eventId,listEventsVO);
			addToken(eventId);
			
			
		}
		else{
		eventDAO.insertEventsRecord(eventId,listEventsVO);
		addToken(eventId);
		}
		
	}
	
	private void addToken(int eventId){
		addTokenAction.add(eventId);
	}

}
