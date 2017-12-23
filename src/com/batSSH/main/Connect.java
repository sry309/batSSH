package com.batSSH.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Connect {
	public static void main(String[] args) throws Exception {
		JSch jsch = new JSch(); // 创建JSch对象
		String userName = "gxv";// 用户名
		String password = "123456";// 密码
		String host = "192.168.228.135";// 服务器地址
		int port = 22;// 端口号
		
		Session session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象
		session.setPassword(password); // 设置密码
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		int timeout = 60000000;
		session.setTimeout(timeout); // 设置timeout时间
		session.connect(); // 通过Session建立链接
		ChannelShell channelShell = (ChannelShell) session.openChannel("shell");
		
		channelShell.connect();
		InputStream in = channelShell.getInputStream();
		OutputStream out = channelShell.getOutputStream();
		PrintWriter prwout = new PrintWriter(out,true);
		
		prwout.println("su root");
		Thread.sleep(2000);
		prwout.println("toor");
		Thread.sleep(2000);
		prwout.println("id");
		Thread.sleep(2000);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		String buf = null;
		StringBuffer sb = new StringBuffer();
		while ((buf = reader.readLine()) != null) {
			sb.append(buf);
			int n = buf.indexOf("(root)");
			if(n != -1){
				System.out.println("true:"+buf);
			}else{
				System.out.println("false:"+buf);
			}		
			//System.out.println(buf);// 打印控制台输出
		}
		reader.close();
		
		channelShell.disconnect();
		if (null != session) {
			session.disconnect();
		}
	}
}