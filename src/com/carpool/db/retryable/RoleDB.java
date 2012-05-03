package com.carpool.db.retryable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carpool.util.DatabaseConnection;

public class RoleDB {
	
	Connection connection=null;
	String table="`role`";
	String columnIdRole="idRole";
	String columnRoleType="roleType";
	
	public int getRoleId(String roleType){
		int roleId=-1;
try{
			
			
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			   
		    String selectQuery="SELECT "+columnIdRole+" as roleId FROM "+table
		               + " WHERE "
		               + columnRoleType+"="+"'"+roleType+"'";
		      ResultSet rs = s.executeQuery(selectQuery);
			   rs.next();
			   roleId = rs.getInt("roleId") ;
			   rs.close() ;
			   System.out.println("Role Id " + roleId + " returned");
			   s.close ();
			   return roleId;
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch role record: "  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
	return roleId;
		
	}

	public String getRoleType(int idRole) {
		String roleType=null;
try{
			
			 
			connection=DatabaseConnection.connectToDatabase();
			
			Statement s = connection.createStatement();
			 
			   
			   ResultSet rs = s.executeQuery (
			               "SELECT "+columnRoleType+" as roleType FROM "+table
			               + " WHERE "
			               + columnIdRole+"="+idRole
			              );
			   rs.next();
			   roleType = rs.getString("roleType") ;
			   rs.close() ;
			   System.out.println("Role Type " + roleType + " returned");
			   s.close ();
			   return roleType;
			   
		}
		catch(SQLException e){
			System.out.println("Failed to fetch role record: "  + e.getMessage());
		}
		finally{
			DatabaseConnection.closeConnection();
		}
return roleType;
	}
	

}
