package com.batSSH.service;

import java.util.List;
import java.util.Map;

import com.batSSH.model.User;
/**
 * 
 * @author c0ny1
 * 
 */

//批量多个检查
public class batCheckService {
	public List<User> dobatCheckService(List<User> users){
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
	public User doCheckService(User user){
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
