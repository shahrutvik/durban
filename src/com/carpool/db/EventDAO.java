package com.carpool.db;

import java.util.List;
import java.util.Map;

import com.carpool.db.retryable.EventDB;
import com.carpool.vo.EventVO;


public class EventDAO {

	EventDB eventVirtual;

	public EventDAO(){
		eventVirtual = new EventDB();
	}
	
	public void insertEventRecord(int eventId,EventVO eventVO){
		eventVirtual.insert(eventId,eventVO);
	}
	
	public void insertEventsRecord(int eventId,List<EventVO> listEventsVO){
		for(EventVO eventVO:listEventsVO){
			insertEventRecord(eventId,eventVO);
		}
	}

	public int getNextEventId() {
		
		return eventVirtual.getNextEventId();
	}

	public int getNumberOfEvents() {
		
		return eventVirtual.getNumberOfEvents();
	}
	
	public int getMemberGroup(int eventId){
		return eventVirtual.getMemberGroup(eventId);
	}
	
	public Map<Integer,Integer> getMemberGroupRoles(int eventId){
		return eventVirtual.getMemberRoles(eventId);
	}
	

}
