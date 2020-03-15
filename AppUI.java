package com.gsc.boc.brm.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import com.gsc.boc.brm.entity.JenkinsJob;
import com.gsc.boc.brm.util.HttpConnect;
import com.gsc.boc.brm.util.JobBuilder;

public class AppUI {
	public JFrame jFrame;
	
	public AppUI() {
		super();
		getCredentials();
	}

	public void getCredentials(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		jFrame = new JFrame();
		jFrame.setBounds(400, 300, 410, 300);
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
		
		JLabel jobLabel = new JLabel("JOB Name :");
		jobLabel.setBounds(90, 120, 200, 26);
		jFrame.getContentPane().add(jobLabel);
		final JTextField job = new JTextField();
		job.setBounds(177, 120, 133, 26);
		jFrame.getContentPane().add(job);
		url.setColumns(4);
		
		final JLabel fileLabel1 = new JLabel("Select Config file:");
		fileLabel1.setBounds(60, 150, 170, 26);
		jFrame.getContentPane().add(fileLabel1);
		final JTextField fileTextField1 = new JTextField();
		fileTextField1.setBounds(177, 150, 133, 26);
		jFrame.getContentPane().add(fileTextField1);
		fileTextField1.setColumns(4);
		
		
		final JButton button1 = new JButton("open");
		button1.setBounds(320, 150, 63, 26);
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
		final JLabel fileLabel2 = new JLabel("Select Credential file:");
		fileLabel2.setBounds(50, 180, 150, 26);
		jFrame.getContentPane().add(fileLabel2);
		final JTextField fileTextField2 = new JTextField();
		fileTextField2.setBounds(177, 180, 133, 26);
		jFrame.getContentPane().add(fileTextField2);
		fileTextField2.setColumns(4);
		
		final JButton button2 = new JButton("open");
		button2.setBounds(320, 180, 63, 26);
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
		
		
		/*
		 * JButton runButton = new JButton("Create Job"); runButton.setBounds(120, 190,
		 * 100, 30); runButton.setForeground(Color.RED);
		 * jFrame.getContentPane().add(runButton); runButton.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { String alias
		 * = aliasTextField.getText(); String passwd = new
		 * String(passwdTextField.getPassword()); String siteURL = url.getText(); String
		 * configFilePath = fileTextField1.getText(); String uploadCred =
		 * fileTextField2.getText();
		 * 
		 * if(alias.isEmpty() || passwd.equals("")||siteURL.isEmpty()||
		 * configFilePath.isEmpty()){ JOptionPane.showMessageDialog(null,
		 * "Please enter all the required values."); }else{ JenkinsJob job = new
		 * JenkinsJob(alias, passwd, siteURL, configFilePath , uploadCred); JobBuilder
		 * jobBuilder = new JobBuilder(); jobBuilder.createJob(job); } } }); JButton
		 * runButton1 = new JButton("Upload Credential"); runButton1.setBounds(250, 190,
		 * 100, 30); runButton1.setForeground(Color.RED);
		 * jFrame.getContentPane().add(runButton1); runButton1.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { String alias
		 * = aliasTextField.getText(); String passwd = new
		 * String(passwdTextField.getPassword()); String siteURL = url.getText(); String
		 * configFilePath = fileTextField1.getText(); String uploadCred =
		 * fileTextField2.getText();
		 * 
		 * if(uploadCred.isEmpty()){ JOptionPane.showMessageDialog(null,
		 * "Please enter all the required values."); }else{ JenkinsJob job = new
		 * JenkinsJob(alias, passwd, siteURL, configFilePath, uploadCred); JobBuilder
		 * jobBuilder = new JobBuilder(); jobBuilder.uploadCred(job); } } });
		 * 
		 */
		
		//String[] Tasks = new String[] {"Create Job", "Upload Credential"};
		JButton runButton = new JButton("Submit"); 
		runButton.setBounds(200, 230, 100, 30);
runButton.setForeground(Color.RED);
		 final JComboBox<String> Tasks1 = new JComboBox<String>();
		 Tasks1.addItem("Create Job");
		 Tasks1.addItem("Upload Credential");
		Tasks1.setForeground(Color.RED);
		Tasks1.setBounds(90, 230,100, 30);
		jFrame.getContentPane().add(Tasks1);
		jFrame.getContentPane().add(runButton);
	    //Tasks1.addActionListener(new ActionListener() {
			 
	    	runButton.addActionListener(new
	    			 ActionListener() { public void actionPerformed(ActionEvent e) {
		       // JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selected = (String) Tasks1.getSelectedItem();
		 
		        if (selected.equals("Create Job")) {
		        	String alias = aliasTextField.getText();
			    	   String passwd = new String(passwdTextField.getPassword()); 
			    	   String siteURL = url.getText(); 
			    	   String configFilePath = fileTextField1.getText(); 
			    	   String uploadCred = fileTextField2.getText();
			    	   String jobname =job.getText();
			  		 
			  		  if(alias.isEmpty() || passwd.equals("")||siteURL.isEmpty()||
			  		  configFilePath.isEmpty() ||jobname.isEmpty())
			  		  { 
			  			  JOptionPane.showMessageDialog(null,"Please enter all the required values.");
			  		  }
			  		  else
			  		  {
			  			  JenkinsJob job = new
			  		  JenkinsJob(alias, passwd, siteURL, configFilePath , uploadCred, jobname); JobBuilder
			  		  jobBuilder = new JobBuilder(); 
			  		  jobBuilder.createJob(job);
			  		  }
			  		  } 
		         else if (selected.equals("Upload Credential")) {
		        	 String alias = aliasTextField.getText();
			    	   String passwd = new String(passwdTextField.getPassword());
			    	   String siteURL = url.getText(); 
			    	   String configFilePath = fileTextField1.getText();
			    	   String uploadCred = fileTextField2.getText();
			    	   String jobname =job.getText();
				  		 
				  		  if(alias.isEmpty() || passwd.equals("")|| uploadCred.isEmpty()||siteURL.isEmpty())
				  		  { 
				  			  JOptionPane.showMessageDialog(null,"Please enter all the required values.");
				  		  }
				  		  else
				  		  {
				  			  JenkinsJob job = new
				  		  JenkinsJob(alias, passwd, siteURL, configFilePath , uploadCred,jobname); 
				  			 // JobBuilder jobBuilder = new JobBuilder();
				  			  //jobBuilder.uploadCred(job);
				  			//HttpConnect.uploadCred(job);
				  		  }
				  		  } 
		        }
		    });
		

	
	}

	
}