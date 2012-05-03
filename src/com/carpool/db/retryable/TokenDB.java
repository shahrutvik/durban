package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.carpool.util.DatabaseConnection;
import com.carpool.vo.TokenVO;

public class TokenDB {
	
	Connection connection=null;
	String table="`tokens`";
	String columnIdEvent="idEvent";
	String columnIdMemberGroup="idMemberGroup";
	String columnEarnedToken="earnedToken";
	
	public void insert(TokenVO tokenVO){
		
		try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
		Statement s = connection.createStatement();
		   int count;
		   
		   String insertQuery="INSERT INTO "+table+"("+columnIdEvent+","+columnIdMemberGroup+","+columnEarnedToken+")"
	               + " VALUES"
	               + "("+tokenVO.getIdEvent()+","+tokenVO.getIdMemberGroup()+",'"+tokenVO.getEarnedToken()+"')";
		   count = s.executeUpdate (insertQuery);
		               
		              
		   s.close ();
		   System.out.println (count + " token was inserted");
		}
		catch(SQLException e){
			System.out.println("Failed to insert token record"  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	}

}
