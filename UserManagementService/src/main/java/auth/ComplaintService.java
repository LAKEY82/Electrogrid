package auth;

import model.Complaint;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import com.Complaints;
import com.Users;
//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Complaints") 
public class ComplaintService 
{ 
	Complaint complaintObj = new Complaint(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return complaintObj.readComplaints(); 
	} 
	
	@GET
	@Path("/getcomplaintbyid/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Complaints getUsersById(@PathParam("id") int id) {

		return complaintObj.getComplaintById(id);
	} 

	
//	@GET
//	@Path("/getuserbyid/{userid}")
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getUserById(@PathParam("userid") Integer userid) {
//		return User.getUserById(userid);
//	}
//	
//	

	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertComplaint(@FormParam("accountno") int accountno, 
	 @FormParam("username") String username, 
	 @FormParam("userphone") int userphone, 
	 @FormParam("complaint") String complaint,
	 @FormParam("date") String date)
	
	{ 
		String output = complaintObj.insertComplaint(accountno, username, userphone, complaint, date); 
	 	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateComplaint(String userData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject complaintObject = new JsonParser().parse(userData).getAsJsonObject(); 
		
		 //Read the values from the JSON object
		 String userid = complaintObject.get("complaintid").getAsString(); 
		 String accountno = complaintObject.get("accountno").getAsString(); 
		 String username = complaintObject.get("username").getAsString(); 
		 String userphone = complaintObject.get("userphone").getAsString(); 
		 String complaint = complaintObject.get("complaint").getAsString();
		 String date = complaintObject.get("date").getAsString(); 
		 String output = complaintObj.updateComplaint(userid, accountno, username,userphone,complaint,date); 
		
		 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteComplaint(String userData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String complaintid = doc.select("complaintid").text(); 
		 String output = complaintObj.deleteComplaint(complaintid); 
		
		 return output; 
	}
	
	}

