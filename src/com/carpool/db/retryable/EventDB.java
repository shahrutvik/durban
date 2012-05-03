package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.carpool.util.DatabaseConnection;
import com.carpool.vo.EventVO;


public class EventDB {
	
	Connection connection=null;
	String table="`event`";
	String columnIdEvent="idEvent";
	String columnIdMemberGroup="idMemberGroup";
	String columnIdRole="idRole";
	String columnEventDate="eventDate";
	
	public void insert(int eventId,EventVO eventVO){
		
		try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
		Statement s = connection.createStatement();
		   int count;
		   String insertQuery="INSERT INTO "+table+"("+columnIdEvent+","+columnIdMemberGroup+","+columnIdRole+","+columnEventDate+")"
	               + " VALUES"
	               + "("+eventId+","+eventVO.getIdGroupMember()+","+eventVO.getRoleId()+",'"+eventVO.getEventDate()+"')";
		   
		   count = s.executeUpdate (insertQuery);
		              
		              
		   s.close ();
		   System.out.println (count + " event was inserted");
		}
		catch(SQLException e){
			System.out.println("Failed to insert event record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	}

	public int getNextEventId() {
	int maxEventId=0;
	
	try{
			
			
		   connection=DatabaseConnection.connectToDatabase();
			
		   Statement s = connection.createStatement();
		   
		   String selectQuery="SELECT MAX("+columnIdEvent+") AS idEvent FROM "+table;
	       ResultSet rs = s.executeQuery(selectQuery);
		   rs.next();
		   maxEventId = rs.getInt("idEvent") ;
		   rs.close() ;
		   System.out.println("Max Event Id " + maxEventId + " returned");
		   s.close ();
		              
		              
		   
		   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch max event id"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
		return (maxEventId+1);
	}
	
	public int getNumberOfEvents(){
		int countEvents=-1;	
		try{
				
				
			   connection=DatabaseConnection.connectToDatabase();
				
			   Statement s = connection.createStatement();
			   
			   String selectQuery="SELECT COUNT("+columnIdEvent +") AS countEvents FROM "+table;
		       ResultSet rs = s.executeQuery(selectQuery);
			   rs.next();
			   countEvents = rs.getInt("countEvents") ;
			   rs.close() ;
			   System.out.println("Num of events: " + countEvents + " returned");
			   s.close ();
			              
			              
			   
			   
			}
			catch(SQLException e){
				System.out.println("Failed to fetch event count"  + e.getMessage());
			}
			finally{
				DatabaseConnection.closeConnection();
			}
			return countEvents;
		
	}
	
	
	public int getMemberGroup(int eventId){
		int idMemberGroup=-1;
		
		try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			   
			String selectQuery="SELECT "+columnIdMemberGroup+" as idMemberGroup FROM "+table
		               + " WHERE "
		               +columnIdEvent+"="+"'"+eventId+"'";   
			ResultSet rs = s.executeQuery (selectQuery);
			               
			             
			   rs.next();
			   idMemberGroup = rs.getInt("idMemberGroup") ;
			   rs.close() ;
			   System.out.println("Group Id " + idMemberGroup + " returned");
			   s.close ();
			   
		}
		catch(SQLException e){
			System.out.println("Failed to insert event record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
		return idMemberGroup;
	}
	
	public Map<Integer,Integer> getMemberRoles(int eventId){
		Map<Integer,Integer> memberRoles = new HashMap<Integer,Integer>();
		int idMemberGroup;
		int roleId;
		try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			String selectQuery= "SELECT "+columnIdMemberGroup+","+columnIdRole+" FROM "+table
		               + " WHERE "
		               + columnIdEvent+"="+"'"+eventId+"'";  
			   
			   ResultSet rs = s.executeQuery (selectQuery);
			               
			              
			   while(rs.next())
			   {
			   idMemberGroup = rs.getInt("idMemberGroup");
			   roleId = rs.getInt("idRole");
			   //System.out.println("Member Id " + memberId + " returned");
			   //System.out.println("Role Id " + roleId + " returned");
			   memberRoles.put(idMemberGroup,roleId);
			   }
			   
			   
			   rs.close() ;
			   
			   s.close ();
			   return memberRoles;
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member+role record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
		return memberRoles;
		
	}
	

}
