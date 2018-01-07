package com.batSSH.gui;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDlg extends JDialog {
	private String appName;
	private double version;
	
	public AboutDlg(String appName,double version){
		this.appName = appName;
		this.version = version;
		this.setTitle("About");
		this.setSize(350,150);
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-this.getHeight())/2;
		this.setLocation(x,y);
		this.setLayout(new FlowLayout());
		//设置为模态对话框，但不起作用
		//this.setModalExclusionType(JDialog.ModalExclusionType.TOOLKIT_EXCLUDE);
		
		JLabel app = new JLabel(appName+" "+version);
		JLabel lbAuthor = new JLabel("Author:");
		JLabel lbAuthorContext = new JLabel("c0ny1,lili");
		JLabel lbBlog = new JLabel("Blog:");
		JLabel lbBlogContext = new JLabel("http://gv7.me");
		JLabel lbGithub = new JLabel("Github:");
		JLabel lbGithubContext = new JLabel("http://github/c0ny1/batSSH");
		
		Box item,context,base;
		item = Box.createVerticalBox();
		context = Box.createVerticalBox();
		base = Box.createHorizontalBox();
		
		item.add(app);
		item.add(lbAuthor);
		item.add(lbBlog);
		item.add(lbGithub);
		
		context.add(Box.createVerticalStrut(8));
		context.add(lbAuthorContext);
		context.add(lbBlogContext);
		context.add(lbGithubContext);
		
		base.add(item);
		base.add(context);
		this.add(base);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AboutDlg a = new AboutDlg("batSSH",1.0);
		a.setVisible(true);
	}

}
