package com.batSSH.service;

import java.util.List;
import java.util.Map;

import com.batSSH.model.User;

/**
 * 
 * @Description: TODO(检查) 
 * @author: c0ny1
 * @date: 2018年1月4日上午10:35:36
 * @version: V1.0
 */

public class batCheckService {
	/**
	 * 
	 * @param users 用户列表
	 * @return 检查后的用户列表
	 */
	public static List<User> dobatCheckService(List<User> users){
		List<User> currentUser = users;
		CheckService check = new CheckService();
		String code_result;
		Map<String,Object> map;
		for(User user:currentUser){
			map = check.doCheck(user);
			code_result = map.get("result").toString();
			user.setCheckResult(code_result);
		}
		return currentUser;
	}
	
	// 单个检查
	public static User doCheckService(User user){
		User currentUser = user;
		CheckService check = new CheckService();
		String code_result;
		Map<String,Object> map;
		
		map = check.doCheck(currentUser);
		code_result = map.get("result").toString();
		currentUser.setCheckResult(code_result);
		
		return currentUser;
	}
}
