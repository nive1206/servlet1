package com.gsc.boc.brm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.gsc.boc.brm.entity.JenkinsJob;

public class HttpConnect {
	

	public static String sendGetRequest(String url, JenkinsJob job) {
		
		
		try {
			
			String crumbkey = "curl -s -XPOST \"" + job.getUrl() + "/crumbIssuer/api/xml"+"?"+"xpath=//crumb" + "\" -u "+ job.getUsername() + ":" + job.getToken();
			
			//String crumbkey = "curl -s -X POST \"http://129.146.59.178:8080/crumbIssuer/api/xml?xpath=//crumb\" -u devops:11dc8f66300e9e3550f66f0bb22f1fbf38";
			System.out.println(crumbkey);
			
			Process process = Runtime.getRuntime().exec(crumbkey);
			InputStream inputStream = process.getInputStream();
			String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			String result1 = result.substring(result.indexOf(">") + 1);
			String finalcrumb = result1.substring(0, result1.indexOf("<"));
			System.out.println(result+"-------"+finalcrumb);
						
			String command = "curl -s -XPOST \"" + job.getUrl() + "/createItem?name=" + job.getJobname() + "\" -u "
					+ job.getUsername() + ":" + job.getToken() + " --data-binary @" + job.getXmlFilePath() + " -H "
					+ "Jenkins-Crumb:"+ finalcrumb + " -H content-Type:text/xml";
			
			System.out.println(command);
			
			Process processjob = Runtime.getRuntime().exec(command);
			InputStream inputStreamjob = processjob.getInputStream();
			String output = IOUtils.toString(inputStreamjob, StandardCharsets.UTF_8);
			System.out.println(output);
		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}

		
		return url;

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



		public static String uploadCred(String url,JenkinsJob job) {
			try {
String crumbkey = "curl -s -XPOST \"" + job.getUrl() + "/crumbIssuer/api/xml"+"?"+"xpath=//crumb" + "\" -u "+ job.getUsername() + ":" + job.getToken();
			
			//String crumbkey = "curl -s -X POST \"http://129.146.59.178:8080/crumbIssuer/api/xml?xpath=//crumb\" -u devops:11dc8f66300e9e3550f66f0bb22f1fbf38";
			System.out.println(crumbkey);
			
			Process process = Runtime.getRuntime().exec(crumbkey);
			InputStream inputStream = process.getInputStream();
			String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			String result1 = result.substring(result.indexOf(">") + 1);
			String finalcrumb = result1.substring(0, result1.indexOf("<"));
			System.out.println(result+"-------"+finalcrumb);
				 
			String command = "curl -XPOST \\ -u " + job.getUsername()+":"+ job.getToken() 
			  + " \\ -H \"Jenkins-Crumb:{" + finalcrumb + "}\" "
			  + "\\ -H  'content-type:application/xml' "
			 + "\\ -d @" + job.getUploadCred()
			+ " \\ \""+job.getUrl()+"credentials/store/system/domain/_/createCredentials\"";
			    
			     
				 

			     System.out.println(command);
				ProcessBuilder process1 = new ProcessBuilder(command);
				Process p;
				
				Process processjob = Runtime.getRuntime().exec(command);
				InputStream inputStreamjob = processjob.getInputStream();
				String output = IOUtils.toString(inputStreamjob, StandardCharsets.UTF_8);
				System.out.println(output);
			} catch (IOException e) {
				System.out.print("error");
				e.printStackTrace();
			}
			return url;

			 
			 
			}
}
