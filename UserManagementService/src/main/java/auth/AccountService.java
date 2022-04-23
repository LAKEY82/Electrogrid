package auth;

import model.Account;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Accounts") 
public class AccountService 
{ 
	Account accountObj = new Account(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return accountObj.readAccounts(); 
	} 
	
	

//	
//	@GET
//	@Path("/getaccountbyid/{accountid}")
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAccountById(@PathParam("accountid") Integer accountid) {
//		return accountObj.getAccountById(accountid);
//	}
//	
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("accountno") int accountno, 
	 @FormParam("premisesid") String premisesid, 
	 @FormParam("areaoffice") String areaoffice, 
	 @FormParam("tarifftype") String tarifftype
	) 
	{ 
		String output = accountObj.insertAccount(accountno, premisesid, areaoffice, tarifftype); 
	 	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAccount(String accountData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject accountObject = new JsonParser().parse(accountData).getAsJsonObject(); 
		
		 //Read the values from the JSON object
		 String accountid = accountObject.get("accountid").getAsString(); 
		 String accountno = accountObject.get("accountno").getAsString(); 
		 String premisesid = accountObject.get("premisesid").getAsString(); 
		 String areaoffice = accountObject.get("areaoffice").getAsString(); 
		 String tarifftype = accountObject.get("tarifftype").getAsString();
		
		 String output = accountObj.updateAccount(accountid, accountno, premisesid, areaoffice,tarifftype); 
		
		 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAccount(String accountData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(accountData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String accountid = doc.select("accountid").text(); 
		 String output = accountObj.deleteAccount(accountid); 
		
		 return output; 
	}
	
	}

