package auth;

import model.User;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import com.Users;
//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Users") 
public class UserService 
{ 
	User userObj = new User(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return userObj.readUsers(); 
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
	@GET
	@Path("/getuserbyid/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Users getUsersById(@PathParam("id") int id) {

		return userObj.getUserById(id);
	} 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("accountno") int accountno, 
	 @FormParam("username") String username, 
	 @FormParam("useremail") String useremail, 
	 @FormParam("userphone") int userphone,
	 @FormParam("useraddress") String useraddress,
	 @FormParam("usernic") String usernic) 
	{ 
		String output = userObj.insertUser(accountno, username, useremail, userphone, useraddress, usernic); 
	 	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
		
		 //Read the values from the JSON object
		 String userid = userObject.get("userid").getAsString(); 
		 String accountno = userObject.get("accountno").getAsString(); 
		 String username = userObject.get("username").getAsString(); 
		 String useremail = userObject.get("useremail").getAsString(); 
		 String userphone = userObject.get("userphone").getAsString();
		 String useraddress = userObject.get("useraddress").getAsString(); 
		 String usernic = userObject.get("usernic").getAsString(); 
		 String output = userObj.updateUser(userid, accountno, username, useremail,userphone,useraddress,usernic); 
		
		 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String userid = doc.select("userid").text(); 
		 String output = userObj.deleteUser(userid); 
		
		 return output; 
	}
	
	}

