package model;

import java.sql.*;

import com.Registers;





public class Register{
	
Registers registers=new Registers();

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
	
	public String insertUsers(String userName, String password, String email,String registeredAt) 
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
			 String query = " insert into register (`id`,`userName`,`password`,`email`,`registeredAt`)"
			 + " values (?, ?, ?, ?, ?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 
			 preparedStmt.setString(2, userName); 
			 preparedStmt.setString(3, password); 
			 preparedStmt.setString(4, email);  
			 preparedStmt.setString(5,registeredAt);


			 
			 // execute the statement
			
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Registration Successful"; 
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
	
	
	
  public Registers getRegisterById(int id) {
		  
		  
		  String query = "select * from register where id = " + id;
	    Registers registers = new Registers();
	    
	    
	    try {
	    	 Connection con = connect();
	    	Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			 


	      if (rs.next()) { 
	    	  registers.setId(rs.getInt(1));
	    	  registers.setUserName(rs.getString(2));
	    	  registers.setPassword(rs.getString(3));
	    	  registers.setEmail(rs.getString(4)); 
	    	  registers.setRegisteredAt(rs.getString(5)); 
				
	      }
			 
			
		 } 
		 
	    catch (SQLException e) {
			e.printStackTrace();
		}

		return registers;
	}
	
	
	
	
	
	
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
			 output = "<table border='1'><tr><th>User Name"
			 		+ "</th><th>Password</th>" +
			 "<th> Email</th>" + 
			 "<th>Registered At</th>" +
			
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from register"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 int id = rs.getInt("id");
			        String userName = rs.getString("userName");
			        String password = rs.getString("password");
			        String email = rs.getString("email");
			        String registeredAt = rs.getString("registeredAt");
				 
				 // Add into the html table
				 output += "<tr><td>" + userName + "</td>"; 
				 output += "<td>" + password + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + registeredAt + "</td>";
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+"</br>"
				 + "<td><form method='post' action='items.jsp'> <input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='userid' type='hidden' value='" + id 
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
	

	
	
	public String updateUsers(String id,String userName, String password, String email,String registeredAt)
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
			 String query = "UPDATE register SET userName=?,password=?,email=?,registeredAt=?"
			 		+ " WHERE id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, userName); 
			 preparedStmt.setString(2, password); 
			 preparedStmt.setString(3, email); 
	         preparedStmt.setString(4, registeredAt); 
	         preparedStmt.setInt(5, Integer.parseInt(id));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User profile Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deleteUser(String id) 
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
			 String query = "delete from register where id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User profile Deleted Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 
 



