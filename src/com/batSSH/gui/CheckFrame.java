package com.batSSH.gui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CheckFrame extends JFrame {
	private String name = "check";
	
	CheckFrame(){
		this.setTitle(name);
		this.setSize(800, 250);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-this.getHeight())/2;
		this.setLocation(x,y);
		
		this.setLayout(new BorderLayout());
		
		
		
		
		
		//右边
		JPanel LPanel = new JPanel();
		Box box_host,box_port,box_login_user,box_login_pwd,box_root_user,box_root_pwd,box_btn,box_base;
		box_base = Box.createHorizontalBox();//水平盒式布局
		
		box_host = Box.createHorizontalBox();
		box_port = Box.createHorizontalBox();
		box_login_user = Box.createHorizontalBox();
		box_login_pwd = Box.createHorizontalBox();
		box_root_user = Box.createHorizontalBox();
		box_root_pwd = Box.createHorizontalBox();
		box_btn = Box.createHorizontalBox();
		box_base = Box.createVerticalBox();
		
		box_host.add(new JLabel("Host:"));
		box_host.add(new JTextField(10));
		box_port.add(new JLabel("Port:"));
		box_port.add(new JTextField(10));
		box_login_user.add(new JLabel("Login Username:"));
		box_login_user.add(new JTextField(10));
		box_login_pwd.add(new JLabel("Login Password:"));
		box_login_pwd.add(new JTextField(10));
		box_root_user.add(new JLabel("Root Username:"));
		box_root_user.add(new JTextField(10));
		box_root_pwd.add(new JLabel("Root Password:"));
		box_root_pwd.add(new JTextField(10));	
		box_btn.add(new JButton("Check"));
		box_btn.add(new JButton("Open shell"));
		
		box_base.add(box_host);
		box_base.add(box_port);
		box_base.add(box_login_user);
		box_base.add(box_login_pwd);
		box_base.add(box_root_user);
		box_base.add(box_root_pwd);
		box_base.add(box_btn);
		
		LPanel.add(box_base);
		
		
		//右边
		JPanel RPanel = new JPanel();
		Box box_1,box_2,box_3;
		
		box_1 = Box.createHorizontalBox();
		box_2 = Box.createHorizontalBox();
		box_3 = Box.createVerticalBox();
		
		JTextArea taShell = new JTextArea(10,40);
		taShell.setBackground(new Color(0, 0, 0));
		taShell.setSelectedTextColor(new Color(255, 255, 255));
		JScrollPane scrShell = new JScrollPane(taShell);
		box_1.add(scrShell);
		
		JTextField tfCmd = new JTextField(5);
		JButton btnExec = new JButton("exec");
		box_2.add(tfCmd);
		box_2.add(Box.createHorizontalStrut(5));
		box_2.add(btnExec);
		
		box_3.add(box_1);
		box_3.add(Box.createVerticalStrut(10));
		box_3.add(box_2);
		
		RPanel.add(box_3);
		
		this.add(LPanel,BorderLayout.WEST);
		this.add(RPanel,BorderLayout.EAST);

		
	}
	
	public static void main(String[] args){
		CheckFrame frm = new CheckFrame();
		frm.setVisible(true);
	}
}
