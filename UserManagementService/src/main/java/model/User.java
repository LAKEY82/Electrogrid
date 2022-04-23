package model;

import java.sql.*;





public class User {

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
	
	public String insertUser(int accno, String username, String email, int phone,String address,String nic) 
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
			 String query = " insert into cusdetails (`userid`,`accountno`,`username`,`useremail`,`userphone`,`useraddress`,`usernic`)"
			 + " values (?, ?, ?, ?, ?,?,?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setInt(2, accno); 
			 preparedStmt.setString(3, username); 
			 preparedStmt.setString(4, email);  
			 preparedStmt.setInt(5, phone);
			 preparedStmt.setString(6, address);
			 preparedStmt.setString(7, nic);
			 
			 // execute the statement
			
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User Data Added Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	}
	
	
	
	
//	//Get Funding Body By ID
//
//	public Response getUserById(int id) {
//	    User user = null;
//		  
//	    
//	    try {
//	    	 Connection con = connect();
//	         if (con == null) return Response
//	           .status(Response.Status.INTERNAL_SERVER_ERROR)
//	           .entity("Database connectivity Error")
//	           .build();
//		
//
//	      String query = "select * from cusdetails where userid = " + id;
//	      Statement stmt = con.createStatement();
//	      ResultSet rs = stmt.executeQuery(query);
//
//	      { 
//				 String userid = Integer.toString(rs.getInt("userid")); 
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
	
	
	 //Get Funding Body By ID
//
//	  public Response getUserById(int id) {
//	    User user = null;
//	    
//	    try {
//	    	 Connection con = connect();
//	      if (con == null) return Response
//	        .status(Response.Status.INTERNAL_SERVER_ERROR)
//	        .entity("Database connectivity Error")
//	        .build();
//
//	      String query = "select * from cusdetails where userid = " + id;
//	      Statement stmt = con.createStatement();
//	      ResultSet rs = stmt.executeQuery(query);
//
//	      while (rs.next()) {
//	    	  int userid =rs.getInt("userid"); 
//				 int accountno = rs.getInt("accountno");
//				 String username = rs.getString("username"); 
//				 String useremail = rs.getString("useremail"); 
//				int userphone = rs.getInt("userphone"); 
//				 String useraddress = rs.getString("useraddress");
//				 String usernic = rs.getString("usernic");
//	             user = new User(accountno, username, useremail,userphone,useraddress,usernic);
//	            
//	           
//	       
//	      }
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
	
	
	
	
	
	
	
	
	
	public String readUsers() 
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
			 		+ "</th><th>User Name</th>" +
			 "<th>User Email</th>" + 
			 "<th>Contact Number</th>" +
			 "<th>User Address</th>" +
			 "<th>User NIC</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from cusdetails"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String userid = Integer.toString(rs.getInt("userid")); 
				 String accountno = Integer.toString(rs.getInt("accountno"));
				 String username = rs.getString("username"); 
				 String useremail = rs.getString("useremail"); 
				 String userphone = Integer.toString(rs.getInt("userphone")); 
				 String useraddress = rs.getString("useraddress");
				 String usernic = rs.getString("usernic");
				 
				 // Add into the html table
				 output += "<tr><td>" + accountno + "</td>"; 
				 output += "<td>" + username + "</td>"; 
				 output += "<td>" + useremail + "</td>"; 
				 output += "<td>" + userphone + "</td>";
				 output += "<td>" + useraddress + "</td>"; 
				 output += "<td>" + usernic + "</td>"; 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+"</br>"
				 + "<td><form method='post' action='items.jsp'> <input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='userid' type='hidden' value='" + userid 
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
	

	public String updateUser(String userid, String accno, String username, String email, String phone,String address,String nic)
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
			 String query = "UPDATE cusdetails SET accountno=?,username=?,useremail=?,userphone=?,useraddress=?,usernic=?"
			 		+ " WHERE userid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(accno)); 
			 preparedStmt.setString(2, username); 
			 preparedStmt.setString(3, email); 
			 preparedStmt.setInt(4, Integer.parseInt(phone)); 
			 preparedStmt.setString(5, address); 
			 preparedStmt.setString(6, nic); 
			 preparedStmt.setInt(7, Integer.parseInt(userid));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User Data Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deleteUser(String userid) 
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
			 String query = "delete from cusdetails where userid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userid)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User Data Deleted Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 
 



