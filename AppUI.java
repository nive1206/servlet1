package com.gsc.boc.brm.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

//import entity.JenkinsJob;
//import entity.JobBuilder;
import com.gsc.boc.brm.entity.JenkinsJob;
import com.gsc.boc.brm.entity.TaskSelector;
import com.gsc.boc.brm.util.Authenticator;
import com.gsc.boc.brm.util.HttpConnect;
import com.gsc.boc.brm.util.JobBuilder;
import com.gsc.boc.brm.util.ReadXml;
public class AppUI {
	public JFrame jFrame;
	public JFrame jFrame1;
	TaskSelector select= new TaskSelector();
	public AppUI() {
		super();
		getCredentials();
	}

	public void getCredentials(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		 
		
		jFrame = new JFrame();
		jFrame.setBounds(400, 400, 410, 370);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);

		JLabel aliasLabel = new JLabel("User name : ");
		aliasLabel.setBounds(100, 18, 86, 26);
		jFrame.getContentPane().add(aliasLabel);
		final JTextField aliasTextField = new JTextField();
		aliasTextField.setBounds(177, 18, 133, 26);
		jFrame.getContentPane().add(aliasTextField);
		aliasTextField.setColumns(4);
		
		JLabel passwdLabel = new JLabel("Token : ");
		passwdLabel.setBounds(100, 55, 200, 26);
		jFrame.getContentPane().add(passwdLabel);
		final JPasswordField passwdTextField = new JPasswordField();
		passwdTextField.setBounds(177, 55, 133, 26);
		jFrame.getContentPane().add(passwdTextField);
		passwdTextField.setColumns(4);
		
		JLabel urlLabel = new JLabel("URL : ");
		urlLabel.setBounds(130, 90, 200, 26);
		jFrame.getContentPane().add(urlLabel);
		final JTextField url = new JTextField();
		url.setBounds(177, 90, 133, 26);
		jFrame.getContentPane().add(url);
		url.setColumns(4);
		
		final JLabel jobLabel = new JLabel("JOB Name :");
		jobLabel.setBounds(90, 170, 200, 26);
		jFrame.getContentPane().add(jobLabel);
		final JTextField job = new JTextField();
		job.setBounds(177, 170, 133, 26);
		jFrame.getContentPane().add(job);
		url.setColumns(4);
		
		final JLabel fileLabel1 = new JLabel("Select Config file:");
		fileLabel1.setBounds(60, 210, 170, 26);
		jFrame.getContentPane().add(fileLabel1);
		final JTextField fileTextField1 = new JTextField();
		fileTextField1.setBounds(177, 210, 133, 26);
		jFrame.getContentPane().add(fileTextField1);
		fileTextField1.setColumns(4);
		final JButton button1 = new JButton("open");
		button1.setBounds(320, 210, 63, 26);
		jFrame.getContentPane().add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jchooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int opt = jchooser.showOpenDialog(null); 
				if (opt == JFileChooser.APPROVE_OPTION){
					fileTextField1.setText(jchooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		//250
		final JLabel fileLabel2 = new JLabel("Select Credential file:");
		fileLabel2.setBounds(40, 170, 150, 26);
		jFrame.getContentPane().add(fileLabel2);
		final JTextField fileTextField2 = new JTextField();
		fileTextField2.setBounds(177, 170, 133, 26);
		jFrame.getContentPane().add(fileTextField2);
		fileTextField2.setColumns(4);
		final JButton button2 = new JButton("open");
		button2.setBounds(320, 170, 63, 26);
		jFrame.getContentPane().add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jchooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int opt = jchooser.showOpenDialog(null); 
				if (opt == JFileChooser.APPROVE_OPTION){
					fileTextField2.setText(jchooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		fileLabel1.setVisible(false);
		fileTextField1.setVisible(false);
		button1.setVisible(false);
		jobLabel.setVisible(false);
		job.setVisible(false);
		fileLabel2.setVisible(false);
		fileTextField2.setVisible(false);
		button2.setVisible(false);
		
		JLabel taskLabel = new JLabel("select Task : ");
		taskLabel.setBounds(90, 130, 200, 26);
		jFrame.getContentPane().add(taskLabel);
		
		final JComboBox<String> Tasks1 = new JComboBox<String>();
		Tasks1.addItem("-- Select --");
		Tasks1.addItem("Create Job");
		Tasks1.addItem("Upload Credential");
		Tasks1.setForeground(Color.RED);
		Tasks1.setBounds(175, 130, 150, 26);
		jFrame.getContentPane().add(Tasks1);
		Tasks1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if((String) Tasks1.getSelectedItem()=="Create Job"){
					
					fileLabel2.setVisible(false);
					fileTextField2.setVisible(false);
					button2.setVisible(false);
					
					/*
					 * fileLabel1.setVisible(true); fileTextField1.setVisible(true);
					 * button1.setVisible(true); jobLabel.setVisible(true); job.setVisible(true);
					 */
					AppUI2 attributesWindow = new  AppUI2();
					//attributesWindow.jFrame.setVisible(true);
					 select= attributesWindow.getCredentials();
				System.out.println(select.toString());
					 
					  
				}else if((String) Tasks1.getSelectedItem()=="Upload Credential"){
					//aliasTextField.setVisible(true);
					fileLabel2.setVisible(true);
					fileTextField2.setVisible(true);
					button2.setVisible(true);
					
					fileLabel1.setVisible(false);
					fileTextField1.setVisible(false);
					button1.setVisible(false);
					jobLabel.setVisible(false);
					job.setVisible(false);
				}else{
					fileLabel1.setVisible(false);
					fileTextField1.setVisible(false);
					button1.setVisible(false);
					jobLabel.setVisible(false);
					job.setVisible(false);
					
					fileLabel2.setVisible(false);
					fileTextField2.setVisible(false);
					button2.setVisible(false);
				}
			}
		});
		
		JButton runButton = new JButton("Submit"); 
		runButton.setBounds(180, 250, 100, 30);
		runButton.setForeground(Color.RED);
		jFrame.getContentPane().add(runButton);
		runButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String alias = aliasTextField.getText();
				String passwd = new String(passwdTextField.getPassword()); 
		    	String siteURL = url.getText(); 
		    	String configFilePath = fileTextField1.getText(); 
		    	String uploadCred = fileTextField2.getText();
		    	String jobname =job.getText();
		    	 
				String selected = (String) Tasks1.getSelectedItem();
				if (selected.equals("Create Job")){
					System.out.println(selected);
					JenkinsJob job = new JenkinsJob(alias, passwd, siteURL, configFilePath, uploadCred, jobname);
					
					job.setXmlFilePath(select.getConfigfilepath());
					job.setToken(passwd);
					job.setUsername(alias);
					job.setUrl(siteURL);
					System.out.println("hello");
					HttpConnect.sendGetRequest(url.getText(), job);
					System.out.println("done: " + selected);
					/*
					 * AppUI2 attributesWindow = new AppUI2();
					 * //attributesWindow.jFrame.setVisible(true); TaskSelector select=
					 * attributesWindow.getCredentials(); System.out.println(select.toString());
					 */
					System.out.println(select.toString());
					
				}else if (selected.equals("Upload Credential")){
					System.out.println("Selected: " + selected);
					
					if(alias.isEmpty() || passwd.equals("")||siteURL.isEmpty()|| uploadCred.isEmpty()){
						 JOptionPane.showMessageDialog(null,"Please enter all the required values.");
					}else{
						JenkinsJob job = new JenkinsJob(alias, passwd, siteURL, configFilePath, uploadCred, jobname);
						JobBuilder jobBuilder = new JobBuilder();
						String credentials = Authenticator.getAuthToken(job.getUsername(), job.getToken());
						HttpConnect.uploadCred(credentials, job);
						System.out.println("done: " + selected);
					}
				}
			}
		});	 
	    
		
	}
}
