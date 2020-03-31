package com.gsc.boc.brm.entity;

public class JenkinsJob {
	String username;
	String token;
	String url;
	String xmlFilePath;
	String uploadCred;
	String jobname;
	

	public JenkinsJob(){}
	
	public JenkinsJob(String username, String token, String url,
			String xmlFilePath, String uploadCred ,String jobname) {
		super();
		this.username = username;
		this.token = token;
		this.url = url;
		this.xmlFilePath = xmlFilePath;
		this.uploadCred = uploadCred;
		this.jobname=jobname;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	public String getUploadCred() {
		return uploadCred;
	}

	public void setUploadCred(String uploadCred) {
		this.uploadCred = uploadCred;
	}
	@Override
	public String toString() {
		return "JenkinsJob [username=" + username + ", token=" + token + ", url=" + url + ", xmlFilePath="
				+ xmlFilePath + ", uploadCred=" + uploadCred + ", jobname=" + jobname + "]";
	}
}
