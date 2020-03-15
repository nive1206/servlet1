package com.gsc.boc.brm.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.apache.http.entity.mime.MultipartEntity;



public class Authenticator  {
	 public String readCredentials(String fileName)
	    {
		 String authorization = "";
			try{
				FileReader reader=new FileReader("C:\\Users\\nusingh\\Desktop\\Jenkins\\config\\credentials.txt");
				Scanner in= new Scanner(reader);
		        
		        while (in.hasNextLine()) {
		            String line=in.nextLine();
		            authorization = Base64.getEncoder().encodeToString(line.getBytes());
		            System.out.println(line  + " = " + authorization);
		        }
		        in.close();
		        
		    }catch (IOException exception){
		        System.out.println("Error processing file:" +exception);
		        exception.printStackTrace();
		    }
			return authorization;
	    }
		
		public static String getAuthToken(String username, String passwd){
			String authorization = "";
			if(username != null && passwd != null && username!="" && passwd!=""){
				authorization = username+":"+passwd;
				System.out.println(authorization);
				authorization = Base64.getEncoder().encodeToString(authorization.getBytes());
			}
			return authorization;
		    }

	    }
	
	

