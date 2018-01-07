package com.batSSH.gui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.batSSH.model.User;
import com.batSSH.service.CheckService;

/**
 * 
 * @Description: TODO(单项检查窗口) 
 * @author: c0ny1
 * @date: 2018年1月7日上午12:20:27
 * @version: V1.0
 */

public class CheckDialog extends JDialog {
	
	private boolean isSaved = false; //是否已经保存
	private CheckSwingWorker csw;
	private String name = "check";
	private User currentUser;
	private User newUser;
	private MainFrame mainfrm;
	
	private JTextField tfHost;
	private JTextField tfPort;
	private JTextField tfLoginUsername;
	private JTextField tfLoginPassword;
	private JTextField tfRootUsername;
	private JTextField tfRootPassword;
	public JLabel lbStatus;

	public JButton btnCheck;
	private JButton btnReset;
	private JButton btnSave;
	private JButton btnShell;
	
	private JTextArea taShell;
	private JScrollPane scrShell;
	private JTextField tfCmd;
	private JButton btnExec;
	
	CheckDialog(User user,MainFrame mainfrm){
		this.setTitle(name);
		this.currentUser = user;
		this.newUser = user;
		this.mainfrm = mainfrm;
		this.setSize(400, 350);
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-this.getHeight())/2;
		this.setLocation(x,y);
		this.setLayout(new GridLayout(1,1));	
		
		JPanel Panel = new JPanel();
		Panel.setLayout(new GridBagLayout());
		//Panel.setBackground(Color.BLACK);
		
		tfHost = new JTextField(2);
		tfHost.setEditable(false);//只读
		tfPort = new JTextField(2);
		tfLoginUsername = new JTextField(2);
		tfLoginPassword = new JTextField(2);
		tfRootUsername = new JTextField(2);
		tfRootPassword = new JTextField(2);
		lbStatus = new JLabel();
		lbStatus.setIcon(new ImageIcon(getClass().getResource("image/0.png")));
		btnCheck = new JButton("Check");
		btnReset = new JButton("Reset");
		btnSave = new JButton("Save");
		btnShell = new JButton("Connection");
		
		Panel.add(new JLabel("Host:"),new GBC(0,0,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(tfHost, new GBC(2,0,3,1).setFill(GBC.BOTH).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Port:"),new GBC(0,1,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(tfPort,new GBC(2,1,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Login Username:"),new GBC(0,2,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(tfLoginUsername,new GBC(2,2,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Login Password:"),new GBC(0,3,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(tfLoginPassword,new GBC(2,3,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Root Username:"),new GBC(0,4,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(tfRootUsername,new GBC(2,4,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
		Panel.add(new JLabel("Root Password:"),new GBC(0,5,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
		Panel.add(tfRootPassword,new GBC(2,5,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));	
		Panel.add(lbStatus,new GBC(0,6,1,1).setInsets(10,15,10,5));
		Panel.add(btnCheck,new GBC(1,6,1,1).setInsets(10,10,10,10));
		Panel.add(btnReset,new GBC(2,6,1,1).setInsets(10,10,10,10));
		Panel.add(btnSave,new GBC(3,6,1,1).setInsets(10,10,10,10));
		Panel.add(btnShell,new GBC(4,6,1,1).setFill(GBC.BOTH).setFill(GBC.BOTH).setInsets(10,10,10,10));
		

		taShell = new JTextArea(5,30);
		taShell.setBackground(new Color(0, 0, 0));
		taShell.setForeground(Color.GREEN);
		taShell.setSelectedTextColor(new Color(255, 255, 255));
		scrShell = new JScrollPane(taShell);
		scrShell.setVisible(false);
		Panel.add(scrShell,new GBC(5,0,12,6).setFill(GBC.BOTH).setInsets(10,0,0,10));
		
		tfCmd = new JTextField(50);
		tfCmd.setVisible(false);
		btnExec = new JButton("exec");
		btnExec.setVisible(false);
		
		Panel.add(tfCmd, new GBC(5,6,11,1).setFill(GBC.BOTH).setWeight(100, 0).setInsets(10,0,10,2));
		Panel.add(btnExec, new GBC(16,6,1,1).setInsets(10,0,10,10));
		this.add(Panel);
		fullValue();
		
		//添加事件
		btnCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(btnCheck.getText() == "Check"){
					btnCheck.setText("Cancel");
					updateUserData();
					csw = new CheckSwingWorker(CheckDialog.this,newUser);
					csw.execute();
				}else if(btnCheck.getText() == "Cancel"){
					if(!csw.isDone()){
						csw.cancel(true);
					}
					btnCheck.setText("Check");
				}
			}
		});
		
		// 打开/关闭Shell
		btnShell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnShell.getText() == "Connection"){
					CheckDialog.this.setSize(750,350);
					taShell.setVisible(true);
					scrShell.setVisible(true);
					tfCmd.setVisible(true);
					btnExec.setVisible(true);
					btnShell.setText("Disconnection");
				}else if(btnShell.getText() == "Disconnection"){
					CheckDialog.this.setSize(400,350);
					taShell.setVisible(false);
					scrShell.setVisible(false);
					tfCmd.setVisible(false);
					btnExec.setVisible(false);
					btnShell.setText("Connection");
				}
			}
		});
	}
	
	private void fullValue(){
		tfHost.setText(currentUser.getHost());
		tfPort.setText(String.valueOf(currentUser.getPort()));
		tfLoginUsername.setText(currentUser.getLogin_username());
		tfLoginPassword.setText(currentUser.getLogin_password());
		tfRootUsername.setText(currentUser.getRoot_username());
		tfRootPassword.setText(currentUser.getRoot_password());
	}
	
	private void updateUserData(){
		newUser.setPort(Integer.valueOf(tfPort.getText()));
		newUser.setLogin_username(tfLoginUsername.getText());
		newUser.setLogin_password(tfLoginPassword.getText());
		newUser.setRoot_username(tfRootUsername.getText());
		newUser.setRoot_password(tfRootPassword.getText());
	}
}
