package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carpool.util.DatabaseConnection;
import com.carpool.vo.MemberToGroupVO;

public class MemberGroupsDB {
	Connection connection=null;
	String table="`membergroups`";
	String columnIdMemberGroup="idMemberGroup";
	String columnIdMember="idMember";
	String columnIdGroup="idGroup";
	
	public void insert(MemberToGroupVO memberToGroupVO){
		
		
		try{
			
			
			connection=DatabaseConnection.connectToDatabase();
					
			Statement s = connection.createStatement();
			   int count;
			   String insertQuery="INSERT INTO "+table+"("+columnIdMember+","+columnIdGroup+")"
		               + " VALUES"
		               + "('" + memberToGroupVO.getMemberId() + "','" +  memberToGroupVO.getGroupId() + "')";
			   count = s.executeUpdate (insertQuery);              
			             
			   
			   s.close ();
			   
			   System.out.println (count + " rows were inserted");
		}
		catch(SQLException e){
			System.out.println("Failed to insert member in a group record"  + e.getMessage());
		}
		finally{
		DatabaseConnection.closeConnection();
		}
	}
	
	public Connection getConnection(){
		DatabaseConnection conn = new DatabaseConnection();
		connection=conn.connectToDatabase();
		return connection;
	}

	public int getMemberCount(int groupId) {
		int memberCount=-1;
try{
			
			DatabaseConnection conn = new DatabaseConnection();
			connection=conn.connectToDatabase();
			
			Statement s = connection.createStatement();
			System.out.println("SELECT COUNT(idMember) as memberCount FROM memberGroups"
		               + " WHERE"
		               + "idGroup="+groupId);   
			   
			   ResultSet rs = s.executeQuery (
					   "SELECT COUNT(idMember) as memberCount FROM memberGroups"
				               + " WHERE"
				               + "idGroup="+groupId
			              );
			   rs.next();
			   memberCount = rs.getInt("memberCount") ;
			   rs.close() ;
			   System.out.println("Member Count " + memberCount + " returned");
			   s.close ();
			   return memberCount;
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member group record: "  + e.getMessage());
		}
		finally{
			return memberCount;
		}
	}

	public int getMemberGroupId(MemberToGroupVO memberToGroup) {
		int idMemberGroup=-1;
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
					
			Statement s = connection.createStatement();
			   int count;
			   
			   String selectQuery="SELECT "+columnIdMemberGroup+" as idMemberGroup FROM "+table
		               + " WHERE "
		               + columnIdMember+"="+memberToGroup.getMemberId()
		               +" AND "
		               + columnIdGroup+"="+memberToGroup.getGroupId() ;
			   
			   ResultSet rs = s.executeQuery (selectQuery);              
			   rs.next();
			   idMemberGroup = rs.getInt("idMemberGroup") ;
			   rs.close() ;
			   System.out.println("Member Group Id " + idMemberGroup + " returned");          
			   
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member group record"  + e.getMessage());
		}
		finally{
		DatabaseConnection.closeConnection();
		}
		return idMemberGroup;
	}

	public int[] getMemberGroupIds(int groupId) {
		int numOfMembers=getMemberGroupIdCount(groupId);
		
		int[] idMemberGroup = new int[numOfMembers];

try{
			
			
			connection=DatabaseConnection.connectToDatabase();
					
			Statement s = connection.createStatement();
			   
			   
			   String selectQuery="SELECT "+columnIdMemberGroup+" as idMemberGroup FROM "+table
		               + " WHERE "
		               + columnIdGroup+"="+groupId ;
			   //System.out.println(selectQuery);
			   
			   
			   ResultSet rs = s.executeQuery (selectQuery);
			   int i=0;
			   while(rs.next()){
				   
			        
				   		
			        	idMemberGroup[i] = rs.getInt("idMemberGroup") ;
			        	//System.out.println("Member Group Id " + idMemberGroup + " returned");
			        	i++;
			        
			   }
			   rs.close() ;
			             
			   
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member group record"  + e.getMessage());
		}
		finally{
		DatabaseConnection.closeConnection();
		}
		return idMemberGroup;
		
	}

	private int getMemberGroupIdCount(int groupId) {
		int numOfMembersInAGroup=0;

try{
			
			
			connection=DatabaseConnection.connectToDatabase();
					
			Statement s = connection.createStatement();
			   
			   
			   String selectQuery="SELECT COUNT("+columnIdMemberGroup+") as rowcount FROM "+table
		               + " WHERE "
		               + columnIdGroup+"="+groupId ;
			   
			   
			   
			   ResultSet rs = s.executeQuery (selectQuery);
			   rs.next();
			   numOfMembersInAGroup = rs.getInt("rowcount") ;
			   
			   rs.close() ;
			             
			   
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member group record"  + e.getMessage());
		}
		finally{
		DatabaseConnection.closeConnection();
		}
		return numOfMembersInAGroup;
	}


}
