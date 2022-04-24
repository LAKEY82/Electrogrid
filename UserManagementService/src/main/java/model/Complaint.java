package model;

import java.sql.*;

import javax.ws.rs.core.Response;

public class Complaint {

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
	
	public String insertComplaint(int accno, String username,int userphone, String complaint,String date) 
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
			 String query = " insert into complaint (`complaintid`,`accountno`,`username`,`userphone`,`complaint`,`date`)"
			 + " values (?, ?, ?, ?, ?,?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setInt(2, accno); 
			 preparedStmt.setString(3, username); 
			 preparedStmt.setInt(4, userphone);  
			 preparedStmt.setString(5, complaint);
			 preparedStmt.setString(6, date);
			
			 
			 // execute the statement
			
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Complaint Details Inserted successfully"; 
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
	
	
	
	
	
	
	
	
	
	
	public String readComplaints() 
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
			 "<th>User Phone</th>" + 
			 "<th>Complaint</th>" +
			 "<th>Date</th>" +
			
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from complaint"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String complaintid = Integer.toString(rs.getInt("complaintid")); 
				 String accountno = Integer.toString(rs.getInt("accountno"));
				 String username = rs.getString("username"); 
				 String userphone = Integer.toString(rs.getInt("userphone"));
				 String complaint = rs.getString("complaint"); 
				 String date = rs.getString("date"); 
			
				 
				 // Add into the html table
				 output += "<tr><td>" + accountno + "</td>"; 
				 output += "<td>" + username + "</td>"; 
				 output += "<td>" + userphone + "</td>"; 
				 output += "<td>" + complaint + "</td>";
				 output += "<td>" + date + "</td>";
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='userid' type='hidden' value='" + complaintid 
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
	

	public String updateComplaint(String complaintid, String accno, String username,String userphone, String complaint,String date) 
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
			 String query = "UPDATE accdetails SET accountno=?,username=?,userphone=?,complaint=?,date=?"
			 		+ " WHERE complaintid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(accno)); 
			 preparedStmt.setString(2, username); 
			 preparedStmt.setInt(3, Integer.parseInt(userphone));
	         preparedStmt.setString(4,complaint); 
	         preparedStmt.setString(5,date);
	         preparedStmt.setInt(6, Integer.parseInt(complaintid));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Complaint Details Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deleteComplaint(String complaintid) 
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
			 String query = "delete from complaint where complaintid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(complaintid)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Complaint Details Deleted Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 
 



