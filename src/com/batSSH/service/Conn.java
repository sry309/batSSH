package com.batSSH.service;

import java.util.Properties;
import com.batSSH.model.User;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Conn {
	private String host;
	private int port;
	private String login_username;
	private String login_password;
	
	Conn(User user){
		this.host = user.getHost();
		this.port = user.getPort();
		this.login_username = user.getLogin_username();
		this.login_password = user.getRoot_password();
	}
	
	public ChannelShell getShell() throws JSchException{
		JSch jsch = new JSch(); // 创建JSch对象
		Session session = jsch.getSession(login_username, host, port); // 根据用户名，主机ip，端口获取一个Session对象
		session.setPassword(login_password); // 设置密码
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		int timeout = 60000000;
		session.setTimeout(timeout); // 设置timeout时间
		session.connect(); // 通过Session建立链接
		ChannelShell channelShell = (ChannelShell) session.openChannel("shell");
		
		return channelShell;
	}
}
