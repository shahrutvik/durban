package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carpool.util.DatabaseConnection;
import com.carpool.vo.GroupVO;
import com.carpool.vo.MemberVO;

public class MemberDB {
	
	Connection connection=null;
	String table="`member`";
	String membergrouptable="`membergroups`";
	String columnIdMember="idMember";
	String columnFirstName="memberfirstname";
	String columnLastName="memberlastname";
	String columnPhoneNumber="memberphonenumber";
	String columnIdMemberGroup="idMemberGroup";
	
	public void insert(MemberVO memberVO){
		
		try{
						
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			   int count;
			   String insertQuery="INSERT INTO "+table+"("+columnFirstName+","+columnLastName+","+columnPhoneNumber+")"
		               + " VALUES"
		               + "('" + memberVO.getFirstName() + "','" +  memberVO.getLastName() + "','" + memberVO.getPhoneNumber() +"')";
			   count = s.executeUpdate (insertQuery);        
			       
			   
			   s.close ();
			   
			   System.out.println (count + " members was inserted ");
		}
		catch(SQLException e){
			System.out.println("Failed to insert member record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	}
	
	
	public boolean isMemberInDB(int memberId){
		boolean isMemberInDB=false;
		try{
			
			DatabaseConnection conn = new DatabaseConnection();
			connection=conn.connectToDatabase();
			
			Statement s = connection.createStatement();
			   
			   
			   ResultSet rs = s.executeQuery (
			               "SELECT COUNT(idMember) as rowCount FROM member"
			               + " WHERE"
			               + " idMember="+memberId
			              );
			   rs.next();
			   int count = rs.getInt("rowcount") ;
			   rs.close() ;
			   System.out.println("MyTable has " + count + " row(s).");

			   s.close ();
			   System.out.println (count + " rows were fetched");
			   if(count==1){
				   isMemberInDB= true;
			   }
			   else{
				   isMemberInDB= false;
			   }
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member record"  + e.getMessage());
		}
		finally{
			isMemberInDB= false;
		}
		return isMemberInDB;
	}
	
	public Connection getConnection(){
		DatabaseConnection conn = new DatabaseConnection();
		connection=conn.connectToDatabase();
		return connection;
	}


	public int getMemberId(String phoneNumber) {
		int memberId=-1;
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			String selectQuery="SELECT "+columnIdMember+" as memberId FROM "+table
		               + " WHERE "
		               + columnPhoneNumber+"="+"'"+phoneNumber+"'";   
			   
			   ResultSet rs = s.executeQuery (selectQuery);		               
			              
			   rs.next();
			   memberId = rs.getInt("memberId") ;
			   rs.close() ;
			   System.out.println("Member Id " + memberId + " returned");
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	return memberId;
		
	}


	public boolean memberExistInDB(MemberVO memberVO) {
		boolean isMemberInDB=false;
		try{
						
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			
			String selectQuery="SELECT COUNT(*) as memberCount FROM "+table
		               + " WHERE "
		               + columnPhoneNumber+"='"+memberVO.getPhoneNumber()+"'";   
			System.out.println(selectQuery);  
			   ResultSet rs = s.executeQuery (selectQuery);
			   rs.next();
			   int count = rs.getInt("memberCount") ;
			   rs.close() ;
			   System.out.println("group table has " + count + " row(s).");
			   s.close ();
			   System.out.println (count + " rows were fetched");
			   if(count==1){
				   isMemberInDB= true;
			   }
			   else{
				   isMemberInDB= false;
			   }
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member record "  + e.getMessage());
		}
		
		finally{
			DatabaseConnection.closeConnection();
		}
		
		return isMemberInDB;
	}


	public String getMemberFirstName(int memberGroupId) {
		String firstName=null;
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			String selectQuery="SELECT m."+columnFirstName+" as firstName from "+membergrouptable+" mg join "+table+" m"
						+" where m."+columnIdMember+"=mg."+columnIdMember+" AND "
						+"mg."+columnIdMemberGroup+"="+memberGroupId;   
			   
			   ResultSet rs = s.executeQuery (selectQuery);		               
			              
			   rs.next();
			   firstName = rs.getString("firstName") ;
			   rs.close() ;
			   System.out.println("Member name " + firstName + " returned");
			   s.close ();
			   
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch member record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	return firstName;
		
	}

}
