package auth;

import model.Register;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import com.Registers;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Register") 
public class RegisterService 
{ 
	Register userObj = new Register(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return userObj.readUsers(); 
	} 
	
	@GET
	@Path("/getregisterbyid/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Registers getRegisterById(@PathParam("id") int id) {

		return userObj.getRegisterById(id);
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
	public String insertUsers(@FormParam("userName")String userName, 
	 @FormParam("password") String password, 
	 @FormParam("email") String email, 
	 @FormParam("registeredAt") String registeredAt)
	
	{ 
		String output = userObj.insertUsers(userName, password, email, registeredAt); 
	 	return output; 
	}
	

	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUsers(String userData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
		
		 //Read the values from the JSON object
		 String id = userObject.get("id").getAsString(); 
		 String userName = userObject.get("userName").getAsString(); 
		 String password = userObject.get("password").getAsString(); 
		 String email = userObject.get("email").getAsString(); 
		 String registeredAt = userObject.get("registerdAt").getAsString();
		
		 String output = userObj.updateUsers(id, userName, password, email,registeredAt); 
		
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
		 String id = doc.select("id").text(); 
		 String output = userObj.deleteUser(id); 
		
		 return output; 
	}
	
	}

