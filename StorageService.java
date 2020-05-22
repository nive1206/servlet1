package com.gsc.boc.brm.trial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gsc.boc.brm.entity.JenkinsJob;
import com.gsc.boc.brm.util.HttpConnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
	public class StorageService {
	public JenkinsJob job=new JenkinsJob();
	
	    @Value("${upload.path}")
	    private static  String path;
	    
	    public static  void uploadFile(MultipartFile uploadCred) {
	    	
	        if (uploadCred.isEmpty()) {

	            throw new StorageException("Failed to store empty file");
	        }

	        try {
	        	
	            String fileName = uploadCred.getOriginalFilename();
	            InputStream is = uploadCred.getInputStream();
	           
	            Files.copy(is, Paths.get(path + fileName),
	                    StandardCopyOption.REPLACE_EXISTING);
	            System.out.println(path);
	          uploadCred.transferTo(new File(path+uploadCred.getOriginalFilename()));
	           
	            File xmlFile = new File(path+uploadCred.getOriginalFilename());
	    		
	    		Reader fileReader = new FileReader(xmlFile); 
	    		BufferedReader bufReader = new BufferedReader(fileReader); 
	    		StringBuilder sb = new StringBuilder(); 
	    		String line = bufReader.readLine(); 
	    		while( line != null){ 
	    			sb.append(line).append("\n");
	    			line = bufReader.readLine(); 
	    			} 
	    		String xml2String = sb.toString();
	    		System.out.println("XML to String using BufferedReader : "); 
	    		System.out.println(xml2String);
	    	//	job.setUploadCred(xml2String);
	    		// job.setUploadCred(path+uploadCred.getOriginalFilename());
	    		bufReader.close();
	            
	            
	        } catch (IOException e) {

	            String msg = String.format("Failed to store file %f", uploadCred.getName());

	            throw new StorageException(msg, e);
	        }
	    }
	}

