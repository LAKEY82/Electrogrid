package model;

import java.sql.*;






public class Power {



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
	
	public String insertPower(int accno, String period,  int nunits,String unitrate,String totunits) 
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
			 String query = " insert into power (`userid`,`accountno`,`period`,`nunits`,`unitrate`,`totunits`)"
			 + " values (?, ?, ?, ?, ?,?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setInt(2, accno); 
			 preparedStmt.setString(3, period); 
			 preparedStmt.setInt(4, nunits);  
			 preparedStmt.setString(5, unitrate);
			 preparedStmt.setString(6, totunits);
			
			 
			 // execute the statement
			
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Power Monitoring Data Added Successfully"; 
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
	

	 
	
	
	
	public String readPowers() 
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
			 		+ "</th><th>Period</th>" +
			 "<th>Units Consumed</th>" + 
			 "<th>Charge per Unit</th>" +
			 "<th>Unit Total</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from power"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String userid = Integer.toString(rs.getInt("userid")); 
				 String accountno = Integer.toString(rs.getInt("accountno"));
				 String period = rs.getString("period"); 
				 String nunits = rs.getString("nunits"); 
				 String unitrate = Integer.toString(rs.getInt("unitrate")); 
				 String totunits = rs.getString("totunits");
				
				 
				 // Add into the html table
				 output += "<tr><td>" + accountno + "</td>"; 
				 output += "<td>" + period + "</td>"; 
				 output += "<td>" + nunits + "</td>"; 
				 output += "<td>" + unitrate + "</td>";
				 output += "<td>" + totunits + "</td>"; 
				 
				 
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
	

	public String updatePower(String userid, String accno, String period, String nunits, String unitrate,String totunits)
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
			 String query = "UPDATE power SET accountno=?,period=?,nunits=?,unitrate=?,totunits=?"
			 		+ " WHERE userid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(accno)); 
			 preparedStmt.setString(2, period); 
			 preparedStmt.setInt(3, Integer.parseInt(nunits));  
			 preparedStmt.setDouble(4, Double.parseDouble(unitrate)); 
			 preparedStmt.setString(5, totunits); 
		
			 preparedStmt.setInt(6, Integer.parseInt(userid));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Power Monitoring Data Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deletePower(String userid) 
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
			 String query = "delete from power where userid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userid)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Power Monitoring Data Deleted Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 
 
