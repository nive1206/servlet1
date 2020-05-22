package com.gsc.boc.brm.trial;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;

import com.gsc.boc.brm.entity.JenkinsJob;
import com.gsc.boc.brm.entity.TaskSelector;
import com.gsc.boc.brm.util.HttpConnect;

@Controller
public class IndexController {
@Autowired
JenkinsJob job;
@Autowired
TaskSelector select;
@Autowired
private static StorageService storageService;



@GetMapping("getForm")
public String getForm() {
    return "next";
}
@PostMapping("/saveDetails")                     
public  String saveDetails(@RequestParam("username") String username, @RequestParam("token") String token, @RequestParam("url") String url,
		@RequestParam("task") String task,@RequestParam("jobname") String jobname,@RequestParam("uploadCred") MultipartFile uploadCred,
                          ModelMap modelMap) {
    // write your code to save details
    modelMap.put("username", username);
    modelMap.put("token", token);
    modelMap.put("url", url);
  modelMap.put("task", task);
    
   
  System.out.println("hello");
    JenkinsJob job =  new JenkinsJob();

	job.setToken(token);
	job.setUsername(username);
	job.setUrl(url);
	//job.setJobname(jobname);
	
    if (task.equals("BRM_pipeline"))
    {
    	return "Brm_pipeline";
    }
    else if(task.equals("Upload Credential")) {
    	System.out.println("into upload");
    	
    	
    	
    	IndexController.upload(uploadCred);
		System.out.println("into upload method");
		
		
		
		HttpConnect.uploadCred(url, job);
		

  	
	    	return "success";
    	
    }
    
	
    else if (task.equals("Build Job"))
	{
    	modelMap.put("jobname", jobname);
    	job.setJobname(jobname);
		System.out.println("into build");
		HttpConnect.buildJob(url, job);
		return "viewDetails"; 
	}
	else if (task.equals("Brm_monitoring"))
	{
		System.out.println("intomonitor");
		
		HttpConnect.Monitoring(url, job);
	}
	
    return "index";          
}
@PostMapping("/pipeline")
public static String pipeline(@RequestParam("jobname") String jobname,@RequestParam("propFile")String propFile,@RequestParam("ticketTool") String ticketTool, @RequestParam("scmTool") String scmTool, @RequestParam("buildTool") String buildTool,@RequestParam("ciTool") String ciTool,@RequestParam("testTool") String testTool,@RequestParam("dockerTool") String dockerTool,
		@RequestParam("codescanTool") String codescanTool,@RequestParam("repoTool") String repoTool,@RequestParam("monitorTool") String monitorTool,@RequestParam("username") String username,@RequestParam("token") String token,@RequestParam("url") String url,ModelMap modelMap) throws IOException{
			
	//IndexController.saveDetails("username", "token", "url", "task", "jobname", "uploadCred", modelMap);
	
	JenkinsJob job = new JenkinsJob();
	TaskSelector select=new TaskSelector();
	
	//job.setXmlFilePath(select.getConfigfilepath());
	
	 modelMap.put("jobname", jobname);
	 modelMap.put("propFile", propFile);
	 modelMap.put("ticketTool", ticketTool);
	 modelMap.put("scmTool", scmTool);
	 modelMap.put("buildTool", buildTool);
	 modelMap.put("ciTool", ciTool);
	 modelMap.put("testTool", testTool);
	 modelMap.put("dockerTool", dockerTool);
	 modelMap.put("codescanTool", codescanTool);
	 modelMap.put("repoTool", repoTool);
	 modelMap.put("monitorTool", monitorTool);
	 modelMap.put("username", username);
	 modelMap.put("token", token);
	 modelMap.put("url", url);
	 select.getTicketTool();
	 select.getBuildTool();
	 select.getCiTool();
	 select.getCodescanTool();
	 select.getDockerTool();
	 select.getMonitorTool();
	 select.getRepoTool();
	 select.getScmTool();
	 select.getTestTool();
	 
	 
	 select.setBuildTool(buildTool);
	 select.setCiTool(ciTool);
	 select.setCodescanTool(codescanTool);
	 select.setDockerTool(dockerTool);
	 select.setMonitorTool(monitorTool);
	 select.setRepoTool(repoTool);
	 select.setScmTool(scmTool);
	 select.setTestTool(testTool);
	 select.setTicketTool(ticketTool);
	 
	 System.out.println(select.toString());
	 job.getPropFile();
	 job.setPropFile(propFile);
	 
	
		
		 job.getToken();
		job.getUrl();
		 job.getUsername();
		job.setToken(token);
		job.setUsername(username);
		job.setUrl(url);
		
		System.out.println(token);
		System.out.println(username);
		System.out.println(url);
		System.out.println("into pipeline");
		
		job.setJobname(jobname);
		//job.setXmlFilePath(select.getConfigfilepath());
		
	 if( ticketTool.equals("Jira")) {
		 
		 select.setConfigfilepath("config_Jira.xml");
		 job.setXmlFilePath(select.getConfigfilepath());
	System.out.println("into if condition");	 
		 try {
			BufferedReader br = new BufferedReader(new FileReader(job.getPropFile()));
		

			Path path = Paths.get(job.getPropFile());
			java.util.List<String> lines;
			try{
				lines = Files.readAllLines(path,StandardCharsets.UTF_8);
				//int lineNumber = -1;
				for(int i=0; i<lines.size(); i++){
					if(lines.get(i).contains("pipeline_template=pipeline_template.groovy")){
						//lineNumber = i;
						//break;	

						String temp = lines.get(i);
						temp = temp.replace("pipeline_template=pipeline_template.groovy","pipeline_template=pipeline_template_jira.groovy");
						System.out.println("new line: " + temp);
						lines.set(i,temp);
					}
				}

				//lines.set(lineNumber, "pipeline_template=pipeline_template_jira.groovy");
				Files.write(path, lines,StandardCharsets.UTF_8);
			}catch(IOException e1){
				e1.printStackTrace();
			}
							    
              			HttpConnect.sendGetRequest(jobname, job);
			System.out.println("done: ");
	;


	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
		 
		 else {
			 job.setXmlFilePath(select.getConfigfilepath());
			 select.setConfigfilepath("config.xml");
		//System.out.println("hello");
		HttpConnect.sendGetRequest(jobname, job);
		System.out.println("done: ");
	}
	 
 
	
	
	return "index";
	
 
}
@RequestMapping(value = "/upload", method = RequestMethod.POST,
consumes = {"multipart/form-data"})
public static MultipartFile upload(@RequestParam MultipartFile uploadCred) {
	System.out.println("into uploads method");
	//storageService.store(uploadCred);
	StorageService.uploadFile(uploadCred);
System.out.println("service done");
return uploadCred;



}





@ExceptionHandler(StorageException.class)
public String handleStorageFileNotFound(StorageException e) {

    return "redirect:/failure.html";
}

}
	