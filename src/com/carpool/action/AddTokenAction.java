package com.carpool.action;

import java.util.Iterator;
import java.util.Map;

import com.carpool.db.EventDAO;
import com.carpool.db.MemberToGroupDAO;
import com.carpool.db.MemberTokenDAO;
import com.carpool.db.RoleDAO;
import com.carpool.db.TokenDAO;
import com.carpool.util.MathUtil;
import com.carpool.vo.MemberTokenVO;
import com.carpool.vo.TokenVO;



public class AddTokenAction {
	
	TokenDAO tokenDAO;
	EventDAO eventDAO;
	MemberTokenDAO memberTokenDAO;
	MemberToGroupDAO memberGroupDAO;
	RoleDAO roleDAO;
	MathUtil mathUtil;
	
	public AddTokenAction(){
		tokenDAO = new TokenDAO();
		eventDAO = new EventDAO();
		memberTokenDAO = new MemberTokenDAO();
		roleDAO = new RoleDAO();
		mathUtil = new MathUtil();
		memberGroupDAO = new MemberToGroupDAO();
	}
	
	public void add(int eventId){
		//calculate earned token for each event for each member
		//int idMemberGroup=eventDAO.getMemberGroup(eventId);
		Map<Integer,Integer> memberGroupRoles=eventDAO.getMemberGroupRoles(eventId);
		
		Iterator iterator = memberGroupRoles.keySet().iterator();
		
		//Logic to calculate earned tokens
		
		int numOfParticipants=memberGroupRoles.size();
		
		int costFactor=mathUtil.getCostFactor(numOfParticipants);
		int driverEarnedToken=mathUtil.calculateDriverToken(costFactor, numOfParticipants);
		int passengerEarnedToken=mathUtil.calculatePassengerToken(costFactor, numOfParticipants);
		TokenVO tokenVO = new TokenVO();
		MemberTokenVO memberTokenVO = new MemberTokenVO();
		tokenVO.setIdEvent(eventId);
		
		
		 while (iterator.hasNext()) {  
		       int idMemberGroup =   (int) iterator.next();  
		       int idRole = memberGroupRoles.get(idMemberGroup);
		       //Can cache the role id/type in memory.will remove need for going to DB everytime.
		       String roleType=roleDAO.getRoleType(idRole);
		       tokenVO.setIdMemberGroup(idMemberGroup);
		       memberTokenVO.setMemberGroupId(idMemberGroup);
		       if(roleType.equals("driver")){
		    	   
		    	   tokenVO.setEarnedToken(driverEarnedToken);
		    	   tokenDAO.insertTokenRecord(tokenVO);
		    	   memberTokenVO.setTotalToken(memberTokenDAO.getTotalTokens(memberTokenVO.getMemberGroupId())+driverEarnedToken);
		    	   memberTokenDAO.updateTotalToken(memberTokenVO);
		       }
		       if(roleType.equals("passenger")){
		    	   tokenVO.setEarnedToken(passengerEarnedToken);
		    	   tokenDAO.insertTokenRecord(tokenVO);
		    	   memberTokenVO.setTotalToken(memberTokenDAO.getTotalTokens(memberTokenVO.getMemberGroupId())+passengerEarnedToken);
		    	   memberTokenDAO.updateTotalToken(memberTokenVO);
		       }
		
		
		
	}
	}

	/*public void add(int eventId){
		int groupId=eventDAO.getMemberGroup(eventId);
		Map<Integer,Integer> memberRoles=eventDAO.getMemberRoles(eventId);
		Iterator iterator = memberRoles.keySet().iterator();
		
		//Logic to calculate earned tokens
		
		int numOfParticipants=memberRoles.size();
		int costFactor=mathUtil.getCostFactor(numOfParticipants);
		int driverEarnedToken=mathUtil.calculateDriverToken(costFactor, numOfParticipants);
		int passengerEarnedToken=mathUtil.calculatePassengerToken(costFactor, numOfParticipants);
		
		TokenVO tokenVO = new TokenVO();
		MemberTokenVO memberTokenVO = new MemberTokenVO();
		tokenVO.setIdMemberGroup(groupId);
		tokenVO.setIdEvent(eventId);
		memberTokenVO.setGroupId(groupId);
		
	    while (iterator.hasNext()) {  
	       int idMember =   (int) iterator.next();  
	       int idRole = memberRoles.get(idMember);
	       //Can cache the role id/type in memory.will remove need for going to DB everytime.
	       String roleType=roleDAO.getRoleType(idRole);
	       tokenVO.setIdMember(idMember);
	       memberTokenVO.setMemberId(idMember);
	       if(roleType.equals("Driver")){
	    	   
	    	   tokenVO.setEarnedToken(driverEarnedToken);
	    	   tokenDAO.insertTokenRecord(tokenVO);
	    	   memberTokenVO.setTotalToken(memberTokenDAO.getTotalTokens(memberTokenVO.getMemberId())+driverEarnedToken);
	    	   memberTokenDAO.updateTotalToken(memberTokenVO);
	       }
	       if(roleType.equals("Passenger")){
	    	   tokenVO.setEarnedToken(passengerEarnedToken);
	    	   tokenDAO.insertTokenRecord(tokenVO);
	    	   memberTokenVO.setTotalToken(memberTokenDAO.getTotalTokens(memberTokenVO.getMemberId())+passengerEarnedToken);
	    	   memberTokenDAO.updateTotalToken(memberTokenVO);
	       }
	       
	       
	              
	    }  


		
	}*/
	
	

	}


