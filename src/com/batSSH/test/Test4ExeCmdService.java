package com.batSSH.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.batSSH.model.User;
import com.batSSH.service.CheckService;
import com.batSSH.service.Conn;
import com.batSSH.service.ExeCmdService1;
import com.jcraft.jsch.ChannelShell;

public class Test4ExeCmdService {
	public static void main(String[] args) {
		List<String> msgList = new ArrayList<>();
		User user=new User();
		user.setHost("192.168.228.135");
		user.setLogin_username("gxv");
		user.setLogin_password("123456");
		user.setPort(22);
		user.setRoot_username("root");
		user.setRoot_password("toor");
		
		Conn conn=new Conn(user);
		ChannelShell channelShell=null;
		try {
			//连接
			channelShell=conn.getShell();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ExeCmdService1 execmd = new ExeCmdService1();
		
		try {
			msgList = execmd.doExec(channelShell);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String msg:msgList){
			System.out.println(msg);
		}
	}
}
