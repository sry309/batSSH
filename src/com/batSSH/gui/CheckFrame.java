package com.batSSH.gui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class CheckFrame extends JDialog {
	private String name = "check";
	
	CheckFrame(){
		this.setTitle(name);
		this.setSize(750, 350);
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-this.getHeight())/2;
		this.setLocation(x,y);
		this.setLayout(new GridLayout(1,1));	
		
		//右边
		JPanel Panel = new JPanel();
		Panel.setLayout(new GridBagLayout());
		//Panel.setBackground(Color.BLACK);
		
		Panel.add(new JLabel("Host:"),new GBC(0,0,1,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(new JTextField(2), new GBC(1,0,2,1).setFill(GBC.BOTH).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Port:"),new GBC(0,1,1,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(new JTextField(2),new GBC(1,1,2,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Login Username:"),new GBC(0,2,1,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(new JTextField(2),new GBC(1,2,2,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Login Password:"),new GBC(0,3,1,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(new JTextField(2),new GBC(1,3,2,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Root Username:"),new GBC(0,4,1,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(new JTextField(2),new GBC(1,4,2,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Root Password:"),new GBC(0,5,1,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(new JTextField(2),new GBC(1,5,2,1).setFill(GBC.BOTH).setInsets(10,0,2,10));	
		Panel.add(new JButton("Check"),new GBC(1,6,1,1).setInsets(10,10,10,10));
		Panel.add(new JButton("Open shell"),new GBC(2,6,1,1).setFill(GBC.BOTH).setFill(GBC.BOTH).setInsets(10,10,10,10));
		

	
		
		JTextArea taShell = new JTextArea(5,45);
		taShell.setBackground(new Color(0, 0, 0));
		taShell.setForeground(Color.GREEN);
		taShell.setSelectedTextColor(new Color(255, 255, 255));
		JScrollPane scrShell = new JScrollPane(taShell);
		Panel.add(scrShell,new GBC(3,0,12,6).setFill(GBC.BOTH).setInsets(10,0,0,10));
		
		JTextField tfCmd = new JTextField(50);
		JButton btnExec = new JButton("exec");
		Panel.add(tfCmd, new GBC(3,6,11,1).setFill(GBC.BOTH).setWeight(100, 0).setInsets(10,0,10,2));
		Panel.add(btnExec, new GBC(14,6,1,1).setInsets(10,0,10,10));
		this.add(Panel);
	}
	
	public static void main(String[] args){
		//设置皮肤
		try
	    {
			UIManager.put("RootPane.setupButtonVisible",false);
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
		CheckFrame frm = new CheckFrame();
		frm.setVisible(true);
	}
}
