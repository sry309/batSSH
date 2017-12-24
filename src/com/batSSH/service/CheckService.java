package com.batSSH.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RoleUnresolved;

import com.batSSH.model.User;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;

/**
 * 
* @Description: TODO(检验root登录命令) 
* @author lili   
* @date 2017年12月23日 下午11:01:51 
* @version V1.0
 */
public class CheckService {
	
	ExeCmdService exec=new ExeCmdService();
	
	/**
	 * 
	* @Description: TODO(检验root) 
	* @author lili   
	* @date 2017年12月23日 下午11:03:58 
	* @version V1.0 
	* 
	* @param user 登录信息
	* @return map<>  result:0未检测，1登录失败   2表示登录成功，跳转失败   3表示登录成功，跳转成功    msg 消息信息
	 */
	public Map<String, Object> doCheck(User user){
		
		Map<String, Object> result=new HashMap<>();
		
		Conn conn=new Conn(user);
		ChannelShell channelShell=null;
		try {
			//连接
			channelShell=conn.getShell();
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put("result", 1);
			result.put("msg", "ssh登录失败!");
			return result;
		}
		
		List<String> cmds=new ArrayList<>();
		cmds.add("su "+user.getRoot_username());
		cmds.add(user.getRoot_password());
		cmds.add("id");
		try {
			List<String> msgList=exec.doExec(channelShell, cmds);
			
			for (String msg : msgList) {
				if (msg.contains("(root)")) {
					result.put("result", 3);
					break;
				}
			}
			
			if (result.get("result")==null) {
				//root登录失败！
				result.put("result", 2);
				result.put("msg", "root登录失败！");
			}else{
				//登录成功
				result.put("msg", "跳转成功！");
				user.setCheckResult("1");
				User.userList.add(user);
			}
			
			
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put("result", 2);
			result.put("msg", "执行su root 失败!");
			return result;
		}finally {
			//关闭连接
			channelShell.disconnect();
			conn.close();
		}
		
	}

}
