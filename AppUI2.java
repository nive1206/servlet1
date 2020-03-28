package com.gsc.boc.brm.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.gsc.boc.brm.entity.TaskSelector;


public class AppUI2 {
	public JFrame jFrame;
	TaskSelector selector= new  TaskSelector();
	
	
	/*
	 * public AppUI2() { super(); getCredentials(); }
	 */

	public TaskSelector getCredentials(){
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		jFrame = new JFrame();
		jFrame.setBounds(400, 400, 410, 370);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);
		jFrame.setVisible(true);
		
	//Ticketing	
		JLabel taskLabel = new JLabel("Select Ticket tool : ");
		taskLabel.setBounds(30, 18, 133, 26);
		jFrame.getContentPane().add(taskLabel);
		final JComboBox<String> Tasks1 = new JComboBox<String>();
		Tasks1.addItem("-- Select --");
		Tasks1.addItem("Jira");
		Tasks1.addItem("Redmine");
		Tasks1.setForeground(Color.RED);
		Tasks1.setBounds(177, 18, 133, 26);
		jFrame.getContentPane().add(Tasks1);
		Tasks1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks1.getSelectedItem()==("Jira")){
					selector.setConfigfilepath("config_Jira.xml");
				}else if((String) Tasks1.getSelectedItem()=="Redmine"){
					selector.setConfigfilepath("config.xml");
					
				}else{
					
				}
			}
		});
		//Scm tool
		JLabel taskLabel1 = new JLabel("Select SCM tool : ");
		taskLabel1.setBounds(30, 55, 200, 26);
		jFrame.getContentPane().add(taskLabel1);
		final JComboBox<String> Tasks2 = new JComboBox<String>();
		Tasks2.addItem("-- Select --");
		Tasks2.addItem("Git");
		Tasks2.addItem("Bitbucket");
		Tasks2.addItem("Subversion");
		Tasks2.setForeground(Color.RED);
		Tasks2.setBounds(177, 55, 133, 26);
		jFrame.getContentPane().add(Tasks2);
		Tasks2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks2.getSelectedItem()=="Git"){
					
				}else if((String) Tasks2.getSelectedItem()=="Bitbucket"){
					
					
				}else if((String) Tasks2.getSelectedItem()=="Subversion"){
					
				}else {
				
				}
			}
		});
		
		//Build App
		JLabel taskLabel2 = new JLabel("Select Build tool : ");
		taskLabel2.setBounds(30, 90, 200, 26);
		jFrame.getContentPane().add(taskLabel2);
		final JComboBox<String> Tasks3 = new JComboBox<String>();
		Tasks3.addItem("-- Select --");
		Tasks3.addItem("maven");
		Tasks3.addItem("gradle");
		Tasks3.setForeground(Color.RED);
		Tasks3.setBounds(177, 90, 133, 26);
		jFrame.getContentPane().add(Tasks3);
		Tasks3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks3.getSelectedItem()=="maven"){
					
				}else if((String) Tasks3.getSelectedItem()=="gradle"){
					
				}else {
				
				}
			}
		});
		//CI tool
		
		JLabel taskLabel3 = new JLabel("Select CI tool : ");
		taskLabel3.setBounds(30, 130, 200, 26);
		jFrame.getContentPane().add(taskLabel3);
		final JComboBox<String> Tasks4 = new JComboBox<String>();
		Tasks4.addItem("-- Select --");
		Tasks4.addItem("Jenkins");
		Tasks4.addItem("Bamboo");
		Tasks4.addItem("CircleCI");
		Tasks4.setForeground(Color.RED);
		Tasks4.setBounds(177, 130, 133, 26);
		jFrame.getContentPane().add(Tasks4);
		Tasks3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks4.getSelectedItem()=="Jenkins"){
					
				}else if((String) Tasks4.getSelectedItem()=="Bamboo"){
					
				}else if((String) Tasks4.getSelectedItem()=="CircleCI"){
				
				}else {
				
				}
			}
		});
		//Test Tool
		JLabel taskLabel4 = new JLabel("Select Test tool : ");
		taskLabel4.setBounds(30, 160, 200, 26);
		jFrame.getContentPane().add(taskLabel4);
		final JComboBox<String> Tasks5 = new JComboBox<String>();
		Tasks5.addItem("-- Select --");
		Tasks5.addItem("TestNg");
		Tasks5.addItem("Junit");
		Tasks5.addItem("Selenium");
		Tasks5.setForeground(Color.RED);
		Tasks5.setBounds(177, 160, 133, 26);
		jFrame.getContentPane().add(Tasks5);
		Tasks3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks5.getSelectedItem()=="TestNg"){
					
				}else if((String) Tasks5.getSelectedItem()=="Junit"){
					
				}else if((String) Tasks5.getSelectedItem()=="Selenium"){
				
				}else {
				
				}
			}
		});
		
		//Docker Tool
		JLabel taskLabel5 = new JLabel("Select Docker tool : ");
		taskLabel5.setBounds(30, 190, 200, 26);
		jFrame.getContentPane().add(taskLabel5);
		final JComboBox<String> Tasks6 = new JComboBox<String>();
		Tasks6.addItem("-- Select --");
		Tasks6.addItem("Helm");
		Tasks6.addItem("K8 yaml");
		Tasks6.addItem("Ansible");
		Tasks6.addItem("Chef");
		Tasks6.setForeground(Color.RED);
		Tasks6.setBounds(177, 190, 133, 26);
		jFrame.getContentPane().add(Tasks6);
		Tasks6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks6.getSelectedItem()=="Helm"){
					
				}else if((String) Tasks6.getSelectedItem()=="K8 yaml"){
					
				}else if((String) Tasks6.getSelectedItem()=="Ansible"){
				
				}else if((String) Tasks6.getSelectedItem()=="Chef"){
				
				}else {
				}
			}
		});
		
		//CodeScan Tool
		JLabel taskLabel6 = new JLabel("Select CodeScan tool : ");
		taskLabel6.setBounds(30, 220, 170, 26);
		jFrame.getContentPane().add(taskLabel6);
		final JComboBox<String> Tasks7 = new JComboBox<String>();
		Tasks7.addItem("-- Select --");
		Tasks7.addItem("SonarQube");
		Tasks7.addItem("Parfait");
		Tasks7.setForeground(Color.RED);
		Tasks7.setBounds(177, 220, 133, 26);
		jFrame.getContentPane().add(Tasks7);
		Tasks7.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks7.getSelectedItem()=="SonarQube"){
					
				}else if((String) Tasks7.getSelectedItem()=="Parfait"){
				
				}else {
				}
			}
		});
		
		//Repository Tool
		JLabel taskLabel7 = new JLabel("Select Repository tool : ");
		taskLabel7.setBounds(30, 250, 170, 26);
		jFrame.getContentPane().add(taskLabel7);
		final JComboBox<String> Tasks8 = new JComboBox<String>();
		Tasks8.addItem("-- Select --");
		Tasks8.addItem("Rubygem");
		Tasks8.addItem("Artifactory");
		Tasks8.addItem("Proget");
		Tasks8.setForeground(Color.RED);
		Tasks8.setBounds(177, 250, 133, 26);
		jFrame.getContentPane().add(Tasks8);
		Tasks8.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if((String) Tasks8.getSelectedItem()=="Rubygem"){
					
				}else if((String) Tasks8.getSelectedItem()=="Artifactory"){
				}else if((String) Tasks8.getSelectedItem()=="Proget"){
				}else {
				}
			}
		});
		
		JButton runButton = new JButton("Submit"); 
		runButton.setBounds(160, 290, 100, 30);
		runButton.setForeground(Color.RED);
		jFrame.getContentPane().add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		     	System.out.println((String)Tasks1.getSelectedItem());
				/*
				 * if ((String)Tasks2.getSelectedItem()==("Git")) {
				 * selector.setConfigfilepath("config.xml"); }else if
				 * ((String)Tasks2.getSelectedItem()==("Subversion")) {
				 * selector.setConfigfilepath("config_Svn.xml"); }
				 */
				selector.setTicketTool((String)Tasks1.getSelectedItem());
				selector.setScmTool((String)Tasks2.getSelectedItem());
				selector.setBuildTool((String)Tasks3.getSelectedItem());
				selector.setCiTool((String)Tasks4.getSelectedItem());
				selector.setTestTool((String)Tasks5.getSelectedItem());
				selector.setDockerTool((String)Tasks6.getSelectedItem());
				selector.setCodescanTool((String)Tasks7.getSelectedItem());
				selector.setRepoTool((String)Tasks8.getSelectedItem());
				System.out.println(selector);
				
				
				;
			}
		});
		return selector;
		}
	
	}

