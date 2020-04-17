package com.gsc.boc.brm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpResponseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

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
						
			String command = "curl -s -XPOST \"" + job.getUrl() + "/createItem?name="  + job.getJobname()+"\" -u "
					+ job.getUsername() + ":" + job.getToken() + " --data-binary @" + HttpConnect.findConfig(job) + " -H "
					+ "Jenkins-Crumb:"+ finalcrumb + " -H content-Type:text/xml";
			
			System.out.println(command);
			
			Process processjob = Runtime.getRuntime().exec(command);
			InputStream inputStreamjob = processjob.getInputStream();
			String output = IOUtils.toString(inputStreamjob, StandardCharsets.UTF_8);
			System.out.println(output);
			if(output.contains("Jenkins [Jenkins]") ) {
				JOptionPane.showMessageDialog(null, 
                        "Error processing Jobname/Properyfile", 
                        "Jenkins [Jenkins]", 
                        JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, 
                     e1.getMessage(),
                     "Exception Message", 
                      JOptionPane.ERROR_MESSAGE);
			}

		
		return url;

	}
public static String Monitoring(String url, JenkinsJob job) {
		
		
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
						
			String command = "curl -s -XPOST \"" + job.getUrl() + "/createItem?name=KubeMonitor"  +"\" -u "
					+ job.getUsername() + ":" + job.getToken() + " --data-binary @config_kubemonitor.xml" + " -H "
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
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, 
                     e1.getMessage(),
                     "Exception Message", 
                      JOptionPane.ERROR_MESSAGE);
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
			  + " \\ -H \"Jenkins-Crumb:" + finalcrumb + "\" "
			  + "\\ -H  'content-type:application/xml' "
			 + "\\ -d @" + job.getUploadCred()
			+ " \\ \""+job.getUrl()+"credentials/store/system/domain/_/createCredentials\"";
			    
			     
				 

			     System.out.println(command);
			/*
			 * ProcessBuilder process1 = new ProcessBuilder(command); Process p;
			 */
				
				Process processjob = Runtime.getRuntime().exec(command);
				InputStream inputStreamjob = processjob.getInputStream();
				String output = IOUtils.toString(inputStreamjob, StandardCharsets.UTF_8);
				System.out.println(output);
			} catch (IOException e) {
				System.out.print("error");
				e.printStackTrace();
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, 
                         e1.getMessage(),
                         "Exception Message", 
                          JOptionPane.ERROR_MESSAGE);
				}
			return url;

			 
			 
			}
		
		public static String buildJob(String url,JenkinsJob job) {
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
							
				String command ="curl  -XPOST -H" + "Jenkins-Crumb:"+ finalcrumb + " -u "
						+ job.getUsername() + ":" + job.getToken() + " "+job.getUrl() + "/job/"+job.getJobname() +"/build" ;
				
				System.out.println(command);
				
				Process processjob = Runtime.getRuntime().exec(command);
				InputStream inputStreamjob = processjob.getInputStream();
				String output = IOUtils.toString(inputStreamjob, StandardCharsets.UTF_8);
				System.out.println(output);
				if(output.contains("Error 404 Not Found") ) {
					JOptionPane.showMessageDialog(null, 
                            "Problem accessing job", 
                            "HTTP ERROR 404", 
                            JOptionPane.ERROR_MESSAGE);
				}
				
			} catch(FileNotFoundException e1) {
				System.out.println("hello");
				JOptionPane.showMessageDialog(null, 
						e1.getMessage(),
                        "Exception Message", 
                        JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, 
							e.getMessage(),
                            "Exception Message", 
	                        JOptionPane.ERROR_MESSAGE);
			}
			
			return url;
		}
			public static String findConfig(JenkinsJob job) {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        dbf.setValidating(false);
		        DocumentBuilder db;
				try {
					db = dbf.newDocumentBuilder();
					System.out.println(job.getXmlFilePath());
				        Document doc2 = db.parse(new FileInputStream(new File(job.getXmlFilePath())));
				      System.out.println(job.getPropFile());
				       BufferedReader br = new BufferedReader(new FileReader(job.getPropFile()));
				       StringBuilder sb = new StringBuilder(); 
				       String line = br.readLine(); while (line != null) { 
				    	   sb.append(line).append("\n"); line = br.readLine();
				    	   }
				       String fileAsString = sb.toString();

				      //System.out.println(fileAsString);

				        NodeList nodes = doc2.getElementsByTagName("secureGroovyScript");

				        Text a = doc2.createTextNode(fileAsString);
				        Element p = doc2.createElement("propertiesContent"); 
				        p.appendChild(a); 

				        nodes.item(0).getParentNode().insertBefore(p, nodes.item(0));
				        
				        printXmlDocument(doc2,job);
				       // doc2.getElementsByTagName(fileAsString);
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		            
		    
			
			return job.getXmlFilePath() ;
			}
			public static void printXmlDocument(Document doc,JenkinsJob job){
		        try{
		            DOMSource domSource = new DOMSource(doc);
		            StringWriter writer = new StringWriter();
		            StreamResult result = new StreamResult(writer);
		            TransformerFactory tf = TransformerFactory.newInstance();
		            Transformer transformer = tf.newTransformer();
		            transformer.transform(domSource, result);
		            System.out.println("XML IN String format is: \n" + writer.toString());
		            BufferedWriter writer1 = new BufferedWriter(new FileWriter(job.getXmlFilePath()));
		           
		            writer1.write(writer.toString());
		            writer1.close();
		            
		              }catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }
}
