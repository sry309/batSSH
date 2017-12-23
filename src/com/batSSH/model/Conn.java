package com.batSSH.model;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Conn {
	private String host;
	private String username;
	private String password;
	private int port;
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public Conn(String host,String username,String password,int port){
		this.host = host;
		this.username = username;
		this.password = password;
		this.port  = port;
	}
	
	public void getS(){
		JSch jsch = new JSch(); // 创建JSch对象
		String cmd = "su -";// 要运行的命令
		
		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(password); // 设置密码
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config); // 为Session对象设置properties
			int timeout = 60000000;
			session.setTimeout(timeout); // 设置timeout时间
			session.connect(); // 通过Session建立链接
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 根据用户名，主机ip，端口获取一个Session对象
		
	}
}
