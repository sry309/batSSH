package com.batSSH.test;

import java.util.Map;

import com.batSSH.controller.checkConn;
import com.batSSH.model.User;
import com.batSSH.service.CheckService;

/**
 * 
* @Description: TODO(测试类) 
* @author weizhongchuang   
* @date 2017年12月23日 下午11:54:48 
* @version V2.0
 */
public class Test4check {
	
	
	
	public static void main(String[] args) {
		
		User user=new User();
		user.setHost("192.168.228.135");
		user.setLogin_username("gxv");
		user.setLogin_password("123456");
		user.setPort(22);
		user.setRoot_username("root");
		user.setRoot_password("toor");
		
		CheckService checkService=new CheckService();
		Map<String, Object> result=checkService.doCheck(user);
		if (result.get("result").toString().equals("1")) {
			System.out.println("成功");
		}else{
			System.out.println("失败！:"+result.get("msg"));
		}
	}
}
