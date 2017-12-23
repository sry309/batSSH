package com.batSSH.service;

import java.util.Map;

import com.batSSH.controller.checkConn;
import com.batSSH.model.User;

/**
 * 
* @Description: TODO(测试类) 
* @author weizhongchuang   
* @date 2017年12月23日 下午11:54:48 
* @version V2.0
 */
public class Test {
	
	
	
	public static void main(String[] args) {
		
		User user=new User();
		user.setHost("192.168.183.128");
		user.setLogin_username("wei1");
		user.setLogin_password("root");
		user.setPort(22);
		user.setRoot_username("root");
		user.setRoot_password("root");
		
		CheckService checkService=new CheckService();
		Map<String, Object> result=checkService.doCheck(user);
		if (result.get("result").toString().equals("1")) {
			System.out.println("成功");
		}else{
			System.out.println("失败！:"+result.get("msg"));
		}
		
		
	}

}
