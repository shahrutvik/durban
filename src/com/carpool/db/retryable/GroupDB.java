package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carpool.util.DatabaseConnection;
import com.carpool.vo.GroupVO;

public class GroupDB {
	
	Connection connection=null;
	String table="`group`";
	String columnIdGroup="idGroup";
	String columnNickName="nickname";

	/*
	 * Add a new group to db
	 */
	public void insert(GroupVO carpoolVO) {
		
		try{
			connection=DatabaseConnection.connectToDatabase();
			Statement s = connection.createStatement();
			int count;
			String insertQuery="INSERT INTO "+table+"("+columnNickName+")"
		               + " VALUES"
		               + "('"+carpoolVO.getNickname()+"')";
			
		   	count = s.executeUpdate (insertQuery);
		               
		   	s.close ();
		   	System.out.println (count + " group was inserted");
		}
		catch(SQLException e){
			System.out.println("Failed to insert group record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
		
	}
	
	/*public boolean isGroupInDB(int groupId) {
		boolean isGroupInDB=false;
		try{
						
			connection=DatabaseConnection.connectToDatabase();
			Statement s = connection.createStatement();
			   
			   
			   ResultSet rs = s.executeQuery (
			               "SELECT COUNT(idGroup) as rowCount FROM group"
			               + " WHERE"
			               + "idGroup="+groupId
			              );
			   rs.next();
			   int count = rs.getInt("rowcount") ;
			   rs.close() ;
			   System.out.println("MyTable has " + count + " row(s).");

			   s.close ();
			   System.out.println (count + " rows were fetched");
			   if(count==1){
				   isGroupInDB= true;
			   }
			   else{
				   isGroupInDB= false;
			   }
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member record"  + e.getMessage());
		}
		finally{
			isGroupInDB= false;
		}
		return isGroupInDB;
	}*/
	
	public int getIdForNickname(String nickname){
		int groupId=-1;
try{
			
			connection=DatabaseConnection.connectToDatabase();
			Statement s = connection.createStatement();
			String selectQuery="SELECT "+columnIdGroup+" as groupId FROM "+table
		               + " WHERE "
		               + columnNickName+"="+"'"+nickname+"'";   
			   
			   ResultSet rs = s.executeQuery (selectQuery);               
			              
			   rs.next();
			   groupId = rs.getInt("groupId") ;
			   rs.close() ;
			  // System.out.println("Group Id " + groupId + " returned");
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch group record: "  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
		return groupId;
		
	}
	
	/*
	 * Checks if a group nickname exists in database
	 */

	public boolean isGroupNickNameInDB(GroupVO groupVO) {
		boolean isGroupInDB=false;
		try{
						
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			
			String selectQuery="SELECT COUNT(*) as groupCount FROM "+table
		               + " WHERE "
		               + columnNickName+"='"+groupVO.getNickname()+"'";   
			System.out.println(selectQuery);  
			   ResultSet rs = s.executeQuery (selectQuery);
			   rs.next();
			   int count = rs.getInt("groupCount") ;
			   rs.close() ;
			   System.out.println("group table has " + count + " row(s).");
			   s.close ();
			   System.out.println (count + " rows were fetched");
			   if(count==1){
				   isGroupInDB= true;
			   }
			   else{
				   isGroupInDB= false;
			   }
		}
		catch(SQLException e){
			System.out.println("Failed to fetch group record"  + e.getMessage());
		}
		
		finally{
			DatabaseConnection.closeConnection();
		}
		
		return isGroupInDB;
	}
	
	
	


}
