package com.gsc.boc.brm.main;



import java.awt.EventQueue;

import com.gsc.boc.brm.ui.AppUI;

public class bootClass {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try{
					AppUI attributesWindow = new  AppUI();
					attributesWindow.jFrame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});

	}

}
