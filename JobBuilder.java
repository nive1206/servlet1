package com.gsc.boc.brm.util;
//import java.net.Authenticator;



import com.gsc.boc.brm.entity.JenkinsJob;


//import com.sun.net.httpserver.Authenticator;
public class JobBuilder  {
	public void createJob(JenkinsJob job){
		// storing username & password in credentials by calling method readCredentials
		//String credentials = Authenticator.readCredentials("C:\\Users\\nusingh\\Desktop\\Jenkins\\config\\credentials.txt");
		//System.out.println(credentials);
		
		String credentials = Authenticator.getAuthToken(job.getUsername(), job.getToken());
		System.out.println("Credentials read sucessfully from file: " + credentials);
				
		// sending 1st request (get) and storing the crumb value in crumb variable
		String crumb = HttpConnect.sendGetRequest(job.getUrl()+"/crumbIssuer/api/json",credentials, job);
		System.out.println("Get request processed successfully. crumb value obtained");
				        
		// sending 2nd request (post). passing crumb value and xml file with it
		//HttpConnector.sendPostRequest("http://129.146.59.178:8080/createItem?name=Demo", "C:\\Users\\nusingh\\Desktop\\config.xml", credentials);
		//HttpConnect.sendPostRequest(job.getUrl()+"createItem?name="+job.getJobname(), job.getXmlFilePath(), credentials, crumb);
		//System.out.println("Post request processed successfully");
		HttpConnect.uploadCred(job);
		System.out.println("Uploaded Credential");
		System.out.println("DONE !!");
	}

	
}
