package supervisionApp;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class AlertManager {

	private String msgProcessStop = "Alerte : un processus ne tourne plus, processus : ";
	private String msgMemoryFull = "Alerte : memoire pleine";
	private User user;
	private boolean memoruFullAlertSend;
	private List<Process> lastListProcess;
	boolean test = true;
	private List<Process> tempList;

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
		request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, "margot.filleton@imerir.com")
				.property(Email.FROMNAME, "Supervision App").property(Email.SUBJECT, "Problem report")
				.property(Email.TEXTPART, msg).property(Email.HTMLPART, msg)
				.property(Email.RECIPIENTS, new JSONArray().put(new JSONObject().put("Email", user.getMail())));
		response = client.post(request);
		System.out.println(response.getData());
	}

	public void CheckIfAlertCPU(Computer computer) {

		System.out.println("checkif alert");
		System.out.println(computer.getPercentageCPU()); // Check if memory stop
		if(computer.getPercentageCPU() >= 40.0 && memoruFullAlertSend == false ) {
			try { 
				this.SendAlert(msgMemoryFull); memoruFullAlertSend = true;
				System.out.println("send CPU"); 
			} catch (MailjetException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
			} catch(MailjetSocketTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			} 
		} else { memoruFullAlertSend = false; }
			
	}

	public void CheckIfProcessStop(Computer computer) {		
		if(tempList != null) {
			tempList.clear();
		}	
		tempList = computer.getProcessList();
		
		if( lastListProcess != null ) {		
			boolean isInList = false;
			for (int i = 0; i < lastListProcess.size(); i++) {	
				isInList = false;
				for (int j = 0; j < tempList.size(); j++) {

					if(lastListProcess.get(i).getPID().equals( tempList.get(j).getPID())) {
						isInList = true;										
					}									
				}
				if (isInList == false && lastListProcess.get(i).getMemory() > 30.0) {
					System.out.println("send Process end" + lastListProcess.get(i).getMemory());
					try {
						this.SendAlert(msgProcessStop + lastListProcess.get(i).getName() + ", PID : "
								+ lastListProcess.get(i).getPID());
						System.out.println("send");
					} catch (MailjetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MailjetSocketTimeoutException e) {
						e.printStackTrace();
					}			
				}
			}
			copyListByValue(tempList);
		}

		if(lastListProcess != null) {
			lastListProcess.clear();
		}	
		copyListByValue(tempList);	
		System.out.println("end");
	}

	private void copyListByValue(List<Process> destList) {
		lastListProcess = new ArrayList<>();
		for(int i = 0; i < destList.size(); i++ ) {
			lastListProcess.add(destList.get(i));
		}
	}

	public static void main(String[] args) {
		/*
		 * AlertManager alert = new AlertManager(new User("margot",
		 * "filleton","margot.filleton@gmail.com",true)); try { alert.SendAlert("test");
		 * } catch (MailjetException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (MailjetSocketTimeoutException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

}
