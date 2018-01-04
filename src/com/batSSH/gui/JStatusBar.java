package com.batSSH.gui;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JStatusBar extends JPanel {
	private JLabel lbStatusContext; //状态内容
	private JLabel lbCountContext; // 总数
	private JLabel lbSuccessContext; //成功数
	private JLabel lbFailContext; //失败数
	
	public JStatusBar(){
		
		JLabel lbStatus=new JLabel("Status:");
		
		lbStatusContext=new JLabel("start check...");
		JLabel lbCount=new JLabel("Conut:");
		lbCountContext=new JLabel("0");
		lbCountContext.setForeground(Color.BLUE);//数量为蓝色
		JLabel lbSuccess=new JLabel("Success:");
		lbSuccessContext=new JLabel("0");
		lbSuccessContext.setForeground(Color.GREEN);//成功为绿色
		JLabel lbFail=new JLabel("Fail:");
		lbFailContext=new JLabel("0");
		lbFailContext.setForeground(Color.RED);//失败为红色
		
		this.setLayout(new GridBagLayout());
		this.add(lbStatus,new GBC(0,0,1,1).setAnchor(GBC.WEST).setInsets(0,10,0,0));
		this.add(lbStatusContext,new GBC(1,0,5,1).setAnchor(GBC.WEST).setIpad(200,0).setWeight(100, 0).setInsets(2));
		this.add(lbCount,new GBC(6,0,1,1));
		this.add(lbCountContext,new GBC(7,0,1,1).setInsets(0,0,0,10));
		this.add(lbSuccess,new GBC(8,0,1,1));
		this.add(lbSuccessContext,new GBC(9,0,1,1).setInsets(2).setInsets(0,0,0,10));
		this.add(lbFail,new GBC(10,0,1,1));
		this.add(lbFailContext,new GBC(11,0,1,1).setInsets(0,0,0,10));
	}

	public String getLbStatusContext() {
		return lbStatusContext.getText();
	}

	public void setLbStatusContext(String lbStatusContext) {
		this.lbStatusContext.setText(lbStatusContext);;
	}

	public String getLbCountContext() {
		return lbCountContext.getText();
	}

	public void setLbCountContext(String lbCountContext) {
		this.lbCountContext.setText(lbCountContext);
	}

	public String getLbSuccessContext() {
		return lbSuccessContext.getText();
	}

	public void setLbSuccessContext(String lbSuccessContext) {
		this.lbSuccessContext.setText(lbSuccessContext);
	}

	public String getLbFailContext() {
		return lbFailContext.getText();
	}

	public void setLbFailContext(String lbFailContext) {
		this.lbFailContext.setText(lbFailContext);
	}
}
