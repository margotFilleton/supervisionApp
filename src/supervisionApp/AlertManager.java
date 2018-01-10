package supervisionApp;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class AlertManager {
	
	private String msgProcessStop = "Alerte : un processus ne tourne plus, processus : ";
	private String msgMemoryFull  = "Alerte : memoire pleine";
	private User user;
	private boolean memoruFullAlertSend;
	private List<Process> lastListProcess;
	/**
	 * @param user
	 */
	public AlertManager(User user) {
		this.user = user;
		memoruFullAlertSend = false;
		lastListProcess = null;
	}
	
	public void SendAlert(String msg) throws MailjetException, MailjetSocketTimeoutException {
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		client = new MailjetClient(ApiKey.ApiKey, ApiKey.ApiSecret);
		request = new MailjetRequest(Email.resource)
		    .property(Email.FROMEMAIL,"margot.filleton@imerir.com")
		    .property(Email.FROMNAME, "Mailjet Pilot")
		    .property(Email.SUBJECT, "Your email flight plan!")
		    .property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
		    .property(Email.HTMLPART, "Dear passenger, welcome to Mailjet!May the delivery force be with you!")
		    .property(Email.RECIPIENTS, new JSONArray()
		    	.put(new JSONObject()
		    	.put("Email",  user.getMail())));
		response = client.post(request);
		System.out.println(response.getData());	    
	}
	
	
	public void CheckIfAlert(Computer computer) {
		if(computer.getPercentageCPU() >= 80.0 && memoruFullAlertSend == false ) {
			try {
				this.SendAlert(msgMemoryFull);
				memoruFullAlertSend = true;
			} catch (MailjetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MailjetSocketTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			memoruFullAlertSend = true;
		}
		List<Process> tempList = computer.getProcessList();
		if( lastListProcess != null ) {					
			for (int i = 0; i < tempList.size(); i++) {
				for (int j = 0; j < lastListProcess.size(); j++) {
					if(tempList.get(i) == lastListProcess.get(i)) {
						try {
							this.SendAlert(msgProcessStop + tempList.get(i).getName());
						} catch (MailjetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MailjetSocketTimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}			
		}
		lastListProcess = tempList;
		
	}

	public static void main(String[] args) {
		AlertManager alert = new AlertManager(new User("margot", "filleton","margot.filleton@gmail.com",true));
		try {
			alert.SendAlert("test");
		} catch (MailjetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailjetSocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


