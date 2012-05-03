package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carpool.util.DatabaseConnection;
import com.carpool.vo.MemberToGroupVO;
import com.carpool.vo.MemberTokenVO;

public class MemberTokenDB {
	
	Connection connection=null;
	String table="`membertokens`";
	String columnIdMemberGroup="idMemberGroup";
	String columnTotalTokens="totalTokens";
	
/*	public void insertMemberTokenRecord(MemberToGroupVO memberToGroupVO){
try{
			
			DatabaseConnection conn = new DatabaseConnection();
			connection=conn.connectToDatabase();
					
			Statement s = connection.createStatement();
			   int count;
			   System.out.println("INSERT INTO membertokens(idMember, idGroup)"
		               + " VALUES"
		               + "('" + memberToGroupVO.getMemberId() + "','" +  memberToGroupVO.getGroupId() + "')");
			   count = s.executeUpdate (
			               "INSERT INTO membertokens(idMember, idGroup)"
			               + " VALUES"
			               + "('" + memberToGroupVO.getMemberId() + "','" +  memberToGroupVO.getGroupId() + "')"
			              );
			   
			   s.close ();
			   
			   System.out.println (count + " rows were inserted");
		}
		catch(SQLException e){
			System.out.println("Failed to insert member in a member token record"  + e.getMessage());
		}
	}*/
	
	public int getTotalTokens(int idMemberGroup){
		
		int totalTokens=-1;
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
					
			Statement s = connection.createStatement();
			String selectQuery= "SELECT "+columnTotalTokens+" FROM "+table
		               + " WHERE "
		               + columnIdMemberGroup+"="+idMemberGroup;   
			  System.out.println(selectQuery);
			   ResultSet rs = s.executeQuery (selectQuery);
			              
			             
			   rs.next();
			   totalTokens = rs.getInt("totalTokens") ;
			   rs.close() ;
			   System.out.println("Token Total " + totalTokens + " returned");
			   s.close ();
			   return totalTokens;
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member token record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	return totalTokens;
	}

	public void updateTotalToken(MemberTokenVO memberTokenVO) {
		
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			String updateQuery="UPDATE "+table+" SET "+columnTotalTokens+"="
			   		   + memberTokenVO.getTotalToken()
		               + " WHERE "
		               + columnIdMemberGroup+"="+memberTokenVO.getMemberGroupId();   
			   
			    s.executeUpdate (updateQuery);			   
				               
			             
			   
			   
			   
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to update member tokens: "  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
		
		
	}

	public void insertMemberTokenRecord(MemberTokenVO memberToken) {
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
					
			Statement s = connection.createStatement();
			   int count;
			   
			   String insertQuery="INSERT INTO "+table+"("+columnIdMemberGroup+","+columnTotalTokens+")"
		               + " VALUES"
		               + "(" + memberToken.getMemberGroupId() +"," + memberToken.getTotalToken() + ")";
			   count = s.executeUpdate (insertQuery);
			   
			   s.close ();
			   
			   System.out.println (count + " rows were inserted");
		}
		catch(SQLException e){
			System.out.println("Failed to insert in a member token record "  + e.getMessage());
		}
		
	}

}
