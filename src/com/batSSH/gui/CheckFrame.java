package com.batSSH.gui;


import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckFrame extends JFrame {
	private String name = "check";
	
	CheckFrame(){
		this.setTitle(name);
		this.setSize(300, 400);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-this.getHeight())/2;
		this.setLocation(x,y);
		
		this.setLayout(new FlowLayout());
		
		Box box_name,box_context,box_btn,box_base;
		box_base = Box.createHorizontalBox();//水平盒式布局
		box_name= Box.createVerticalBox();
		box_context = Box.createVerticalBox();
		box_btn = Box.createVerticalBox();
		
		box_name.add(new JLabel("host:"));
		box_name.add(new JLabel("port:"));
		box_name.add(new JLabel("login_username:"));
		box_name.add(new JLabel("login_password:"));
		box_name.add(new JLabel("root_username:"));
		box_name.add(new JLabel("root_password:"));
		box_name.add(new JLabel("result:"));
		

		box_context.add(new JTextField(10));
		box_context.add(new JTextField(10));
		box_context.add(new JTextField(10));
		box_context.add(new JTextField(10));
		box_context.add(new JTextField(10));
		box_context.add(new JTextField(10));
		
		box_btn.add(new Button("check"));
		
		box_base.add(box_name);
		box_base.add(box_context);
		this.add(box_base);
		this.add(box_btn);
		
	}
	
	public static void main(String[] args){
		CheckFrame frm = new CheckFrame();
		frm.setVisible(true);
	}
}
