package model;

import java.sql.*;






public class Payment {



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
	
	public String insertPayment(int accno, String paymenttype,  String cardtype,String amount) 
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
			 String query = " insert into payments (`transactionid`,`accountno`,`paymenttype`,`cardtype`,`amount`)"
			 + " values (?, ?, ?, ?, ?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setInt(2, accno); 
			 preparedStmt.setString(3, paymenttype); 
			 preparedStmt.setString(4,cardtype);  
			 preparedStmt.setString(5, amount);
	
			
			 
			 // execute the statement
			
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Payment Data Added Successfully"; 
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
	

	 
	
	
	
	public String readPayments() 
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
			 		+ "</th><th>Payment Type</th>" +
			 "<th>Card Type</th>" + 
			 "<th>Amount</th>" +
			 
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from payments"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String transactionid = Integer.toString(rs.getInt("transactionid")); 
				 String accountno = Integer.toString(rs.getInt("accountno"));
				 String paymenttype = rs.getString("paymenttype"); 
				 String cardtype = rs.getString("cardtype"); 
				 String amount = rs.getString("amount"); 
			
				 
				 // Add into the html table
				 output += "<tr><td>" + accountno + "</td>"; 
				 output += "<td>" + paymenttype + "</td>"; 
				 output += "<td>" + cardtype + "</td>"; 
				 output += "<td>" + amount + "</td>";
	
				 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+"</br>"
				 + "<td><form method='post' action='items.jsp'> <input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='userid' type='hidden' value='" + transactionid 
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
	

	public String updatePayment(String transactionid, String accno, String paymenttype, String cardtype, String amount)
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
			 String query = "UPDATE payments SET accountno=?,paymenttype=?,cardtype=?,amount=?"
			 		+ " WHERE transactionid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(accno)); 
			 preparedStmt.setString(2,paymenttype); 
			 preparedStmt.setString(3, cardtype);  
			 preparedStmt.setDouble(4, Double.parseDouble(amount)); 
			
		
			 preparedStmt.setInt(5, Integer.parseInt(transactionid));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Payment Data Updated Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deletePayment(String transactionid) 
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
			 String query = "delete from payments where transactionid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(transactionid)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Payment Data Deleted Successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	} 
 



