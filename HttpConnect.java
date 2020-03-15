package com.gsc.boc.brm.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.JSONParser;

import com.gsc.boc.brm.entity.JenkinsJob;

import org.apache.james.mime4j.message.SingleBody;


public class HttpConnect  {
	//private static String crumb;

	public static String sendGetRequest(String url, String authorization,JenkinsJob job) {
		URLConnection urlConn = null;
		 String crumb="";
		String inputLine	="";
		String Jenkinscrumb="";
		try {
			//JenkinsJob job=new JenkinsJob();
		JSONParser parser = new JSONParser();
		
			/*
			 * HttpPost request = new HttpPost(url); String authHeader = "Basic " +
			 * authorization; request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			 */
			
			  URL url1 = new URL(url);
			  
			  urlConn = url1.openConnection(); urlConn.setRequestProperty("Authorization",
			  "Basic " + authorization);
			 
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				urlConn.getInputStream()));
		
		   while ((inputLine = buff.readLine()) != null) 
			// System.out.println(inputLine);
		   Jenkinscrumb=inputLine.toString();
		   System.out.println(Jenkinscrumb);
       
		   String[] arrOfStr = Jenkinscrumb.split(",", -2); 
		   System.out.println(arrOfStr[1]);  
		   String crumb1 = arrOfStr[1].substring(1);
          crumb1 = crumb1.split(":")[1];
          System.out.println(crumb1);
         crumb=crumb1.replace("\"","");
         System.out.println(crumb);
		   //return crumb;
		   
		//curl -s -XPOST 'http://example.com/createItem?name=yourJobName' -u username:API_TOKEN --data-binary @mylocalconfig.xml -H "Content-Type:text/xml"
		
         
         //String[] command = {"curl -s -XPOST " +job.getUrl()+"/createItem?name="+job.getJobname()+"-u"+job.getUsername()+":"+ job.getToken() + "--data-binary" + job.getXmlFilePath()+ crumb+  "'content-Type:text/xml'"}; 
		//curl -s -XPOST 'http://129.146.59.178:8080/createItem?name=yourdemo' -u devops:11dc8f66300e9e3550f66f0bb22f1fbf38 --data-binary @mylocalconfig.xml -H a915c770d8bad738c5ce8118d4b2384c8d78bfe52e76b4024ddf2398b256f86a -H "Content-Type:text/xml"
 		String command = "curl -s -XPOST " +job.getUrl()+"/createItem?name="+job.getJobname()+ "-u" +job.getUsername()+":"+ job.getToken() +  "--data-binary"  + job.getXmlFilePath()  +  crumb +   "'content-Type:text/xml'"; 

         System.out.println(command);
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		
			p = process.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			String result = builder.toString();
			System.out.print(result);

		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}
	
		
		return crumb;

	
}
	/*
	 * public static void sendPostRequest (String url, String xmlFilePath, String
	 * authorization, String crumb) {
	 * 
	 * HttpPost request = new HttpPost(url); String authHeader = "Basic " +
	 * authorization; request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
	 * 
	 * //System.out.println("into post"); //HttpPost post = new HttpPost(url); File
	 * file = new File(xmlFilePath); FileBody fileBody = new FileBody(file);
	 * 
	 * MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	 * builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
	 * builder.addPart("Config", fileBody); HttpEntity entity = builder.build();
	 * //request.setRequestProperty("Jenkins-Crumb",crumb);
	 * request.setEntity(entity); //request.setHeader("Jenkins-Crumb",crumb);
	 * //request.setHeader("Content-Type","text/xml");
	 * //request.setContentType("text/html;charset=UTF-8"); //CloseableHttpClient
	 * client = HttpClients.createDefault();
	 * 
	 * //Unirest.setTimeouts(0, 0); // HttpResponse<String> response =
	 * Unirest.post("http://129.146.59.178:8080/createItem?name=d2")
	 * request.addHeader("Content-Type", "text/xml");
	 * request.addHeader("Jenkins-Crumb", crumb); request.addHeader("Authorization",
	 * authorization ); request.addHeader("Content-Type", "application/xml");
	 * request.addHeader("Cookie", "");
	 * 
	 * HttpClient client = HttpClientBuilder.create().build(); try { HttpResponse
	 * response = client.execute(request);
	 * 
	 * System.out.println(response.toString()); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */
	
	
	
	
	/*public void test(){
		
		String auth = DEFAULT_USER + ":" + DEFAULT_PASS;
		byte[] encodedAuth = Base64.encodeBase64(
		  auth.getBytes(StandardCharsets.ISO_8859_1));
		String authHeader = "Basic " + new String(encodedAuth);
		//request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
		 
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);
		 
		int statusCode = response.getStatusLine().getStatusCode();
		assertThat(statusCode, equalTo(HttpStatus.SC_OK));
	}*/


  public static void uploadCred(JenkinsJob job) {
  
  try { 
	  String credentials = Authenticator.getAuthToken(job.getUsername(),job.getToken()); 
  
 String crumb = HttpConnect.sendGetRequest(job.getUrl()+"/crumbIssuer/api/json",credentials, job);
 
  String[] command = {"curl -X POST " + " \\ -U"+ job.getUsername()+":"+
  job.getToken() + "\\ -H" + crumb+ "\\ -H"+
  "'content-type:application/xml' \\ -d" +job.getUploadCred()+job.getUrl()+
  "/job/top-folder/job/sub-folder/credentials/store/folder/domain/_/createCredentials"
  };
  
  
  //curl -X POST \ -u $JENKINS_USER:$JENKINS_PASSWORD_OR_API_TOKEN \ -H "Jenkins-Crumb:${JENKINS_CRUMB}" \ -H 'content-type:application/xml' \ -d @credential.xml \"$JENKINS_URL/job/top-folder/job/sub-folder/credentials/store/folder/domain/_/createCredentials"
  ProcessBuilder process = new ProcessBuilder(command); Process p;
  
  p = process.start(); BufferedReader reader = new BufferedReader(new
  InputStreamReader(p.getInputStream())); StringBuilder builder = new
  StringBuilder(); String line = null; while ((line = reader.readLine()) !=
  null) { builder.append(line);
  builder.append(System.getProperty("line.separator")); } String result =
  builder.toString(); System.out.print(result);
  
  } catch (IOException e) { System.out.print("error"); e.printStackTrace(); }
 
	}
}

