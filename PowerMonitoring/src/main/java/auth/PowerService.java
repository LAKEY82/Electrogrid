package auth;

import model.Power;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/Powers") 
public class PowerService 
{ 
	Power userObj = new Power(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return userObj.readPowers(); 
	} 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("accountno") int accountno, 
	 @FormParam("period") String period, 
	 @FormParam("nunits") int nunits, 
	 @FormParam("unitrate") String unitrate,
	 @FormParam("totunits") String totunits)
	 
	{ 
		String output = userObj.insertPower(accountno, period, nunits, unitrate, totunits); 
	 	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePower(String userData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
		
		 //Read the values from the JSON object
		 String userid = userObject.get("userid").getAsString(); 
		 String accountno = userObject.get("accountno").getAsString(); 
		 String period = userObject.get("period").getAsString(); 
		 String nunits = userObject.get("nunits").getAsString();
		 String unitrate = userObject.get("unitrate").getAsString(); 
		 String totunits = userObject.get("totunits").getAsString();
		 String output = userObj.updatePower(userid, accountno, period, nunits,unitrate,totunits); 
		
		 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePower(String userData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String userid = doc.select("userid").text(); 
		 String output = userObj.deletePower(userid); 
		
		 return output; 
	}

	
	}

