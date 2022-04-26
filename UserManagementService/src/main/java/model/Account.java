package model;

import java.sql.*;

import javax.ws.rs.core.Response;

import com.Accounts;

public class Account {

	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		
		try
		{ 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", 
					 "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		 
		return con; 
	 } 
	
	public String insertAccount(int accno, String premisesid, String areaoffice,String tarifftype) 
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect();
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for inserting."; 
			 } 
			 // create a prepared statement
			 String query = " insert into accdetails (`accountid`,`accountno`,`premisesid`,`areaoffice`,`tarifftype`)"
			 + " values (?, ?, ?, ?, ?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setInt(2, accno); 
			 preparedStmt.setString(3,  premisesid); 
			 preparedStmt.setString(4, areaoffice);  
			 preparedStmt.setString(5, tarifftype);
			
			 
			 // execute the statement
			
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Electricity Account Details Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	}
	
	
	
	
	//Get Funding Body By ID

//	public Response getAccountById(int accountid) {
//	    User user = null;
//		  
//	    
//	    try {
//	    	 Connection con = connect();;
//	         if (con == null) return Response
//	           .status(Response.Status.INTERNAL_SERVER_ERROR)
//	           .entity("Database connectivity Error")
//	           .build();
//		
//
//	      String query = "select * from accdetails where accountid = " + accountid;
//	      Statement stmt = con.createStatement();
//	      ResultSet rs = stmt.executeQuery(query);
//
//	      { 
//				 String userid1 = Integer.toString(rs.getInt("userid")); 
//				 String accountno = Integer.toString(rs.getInt("accountno"));
//				 String username = rs.getString("username"); 
//				 String useremail = rs.getString("useremail"); 
//				 String userphone = Integer.toString(rs.getInt("userphone")); 
//				 String useraddress = rs.getString("useraddress");
//				 String usernic = rs.getString("usernic");
//			
//			 }
//	      con.close();
//
//	    } catch (Exception e) {
//	      return Response
//	        .status(Response.Status.INTERNAL_SERVER_ERROR)
//	        .entity(e)
//	        .build();
//	    }
//
//	    return Response
//	      .status(Response.Status.OK)
//	      .entity(user)
//	      .build();
//
//	  }
//	
	
	
	
	
	
	  public Accounts getAccountById(int id) {
		  
		  
		  String query = "select * from accdetails where accountid = " + id;
	    Accounts accounts = new Accounts();
	    
	    
	    try {
	    	 Connection con = connect();
	    	Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			 


	      if (rs.next()) { 
	    	  accounts.setAccountid(rs.getInt(1));
	    	  accounts.setAccountno(rs.getInt(2));
	    	  accounts.setPremisesid(rs.getString(3)); 
	    	  accounts.setAreaoffice(rs.getString(4)); 
	    	  accounts.setTarifftype(rs.getString(5)); 
				
	      }
			 
			
		 } 
		 
	    catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}
	
	  
	  
	  
	
	
	
	
	public String readAccounts() 
	 { 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Electricity Account No"
			 		+ "</th><th>Premises ID</th>" +
			 "<th>Area Office</th>" + 
			 "<th>Tariff Type</th>" +
			
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from accdetails"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String accountid = Integer.toString(rs.getInt("accountid")); 
				 String accountno = Integer.toString(rs.getInt("accountno"));
				 String premisesid = rs.getString("premisesid"); 
				 String areaoffice = rs.getString("areaoffice"); 
				 String tarifftype = rs.getString("tarifftype"); 
			
				 
				 // Add into the html table
				 output += "<tr><td>" + accountno + "</td>"; 
				 output += "<td>" + premisesid + "</td>"; 
				 output += "<td>" + areaoffice + "</td>"; 
				 output += "<td>" + tarifftype + "</td>";
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='userid' type='hidden' value='" + accountid 
				 + "'>" + "</form></td></tr>"; 
			 }
			 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		 }
		 
		 return output; 
	 } 
	

	public String updateAccount(String accountid, String accno, String premisesid, String areaoffice, String tarifftype)
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect();
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating."; 
			 } 
			 
			 // create a prepared statement
			 String query = "UPDATE accdetails SET accountno=?,premisesid=?,areaoffice=?,tarifftype=?"
			 		+ " WHERE accountid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(accno)); 
			 preparedStmt.setString(2, premisesid); 
			 preparedStmt.setString(3, areaoffice); 
	         preparedStmt.setString(4, tarifftype); 
	         preparedStmt.setInt(5, Integer.parseInt(accountid));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Electricity Account Details Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deleteAccount(String accountid) 
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 // create a prepared statement
			 String query = "delete from accdetails where accountid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(accountid)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Electricity Account Details Deleted Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 
 



