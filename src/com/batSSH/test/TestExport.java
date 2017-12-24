package com.batSSH.test;

import com.batSSH.model.Result;
import com.batSSH.model.User;
import com.batSSH.service.FileService;

/**
 * 
* @Description: TODO(测试导出) 
* @author lili   
* @date 2017年12月24日 下午4:16:28 
* @version V1.0
 */
public class TestExport {

	
	public static void main(String[] args) {
		User user=new User();
		user.setHost("192.168.228.135");
		user.setLogin_username("gxv");
		user.setLogin_password("123456");
		user.setPort(22);
		user.setRoot_username("root");
		user.setRoot_password("toor");
		user.setCheckResult("1");
		User.userList.add(user);
		
		User user1=new User();
		user1.setHost("192.168.228.135");
		user1.setLogin_username("gxv1");
		user1.setLogin_password("123456");
		user1.setPort(22);
		user1.setRoot_username("root");
		user1.setRoot_password("toor");
		user1.setCheckResult("1");
		User.userList.add(user1);
		
		User user2=new User();
		user2.setHost("192.168.228.135");
		user2.setLogin_username("gxv3");
		user2.setLogin_password("123456");
		user2.setPort(22);
		user2.setRoot_username("root");
		user2.setRoot_password("toor");
		user2.setCheckResult("1");
		User.userList.add(user2);
		
		FileService fileService=new FileService();
		Result result=fileService.doExport("D://1.txt", ",");
		System.out.println(result.getMsg());
	}
}
