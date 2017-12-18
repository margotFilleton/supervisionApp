package supervisionApp;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;

import supervisionApp.ihm.view.ApiKey;

import org.json.JSONArray;
import org.json.JSONObject;


public class AlertManager {
	
	private String msgProcessStop = "Alerte : un processus ne tourne plus";
	private String msgMemoryFull  = "Alerte : memoire pleine";
	private User user;
	
	/**
	 * @param user
	 */
	public AlertManager(User user) {
		this.user = user;
	}
	
	public void SendAlert() throws MailjetException, MailjetSocketTimeoutException {
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		client = new MailjetClient(ApiKey.ApiKey, ApiKey.ApiSecret);
		request = new MailjetRequest(Email.resource)
		    .property(Email.FROMEMAIL, user.getMail())
		    .property(Email.FROMNAME, "Mailjet Pilot")
		    .property(Email.SUBJECT, "Your email flight plan!")
		    .property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
		    .property(Email.HTMLPART, "Dear passenger, welcome to Mailjet!May the delivery force be with you!")
		    .property(Email.RECIPIENTS, new JSONArray()
		    	.put(new JSONObject()
		    	.put("Email", "margot.filleton@gmail.com")));
		response = client.post(request);
		System.out.println(response.getData());	    
	}
	
	
	public void CheckIfAlert() {
		//TODO
	}

}
