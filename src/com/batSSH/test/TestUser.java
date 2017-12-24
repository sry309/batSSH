package com.batSSH.test;

import java.util.List;

import com.batSSH.model.User;

public class TestUser {
	
	public static void main(String[] args) {
		
		User user1=new User("0");
		user1.setHost("192.168.228.135");
		user1.setLogin_username("gxv");
		user1.setLogin_password("123456");
		user1.setPort(22);
		user1.setRoot_username("root");
		user1.setRoot_password("toor");
		user1.setCheckResult("1");
		user1.userList.add(user1);
		User.userList.add(user1);
		
		User user2=new User("1");
		user2.setLogin_username("1");
		User.userList.add(user2);
		
		User user3=new User("2");
		User.userList.add(user3);
		
		
		
		System.out.println("========================");
		System.out.println("user1:"+user1.userList.get(2).getLogin_username());
		System.out.println("user2:"+user2.userList.size());
		System.out.println("user3:"+user3.userList.size());
		System.out.println("user:"+User.userList.size());
		
	}

}
