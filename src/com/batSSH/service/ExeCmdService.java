package com.batSSH.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.NonWritableChannelException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;

/**
 * 
* @Description: TODO(执行命令service) 
* @author weizhongchuang   
* @date 2017年12月23日 下午11:34:40 
* @version V1.0
 */
public class ExeCmdService {
	
	
	/**
	 * 
	* @Description: TODO(执行命令并返回结果) 
	* @author weizhongchuang   
	* @date 2017年12月23日 下午11:36:57 
	* @version V2.0 
	* 
	* @param channelShell shell
	* @param cmds 执行的命令集合
	* @return 结果集
	 * @throws Exception 
	 */
	public List<String> doExec(ChannelShell channelShell,List<String> cmds) throws Exception{
		
		List<String> msgList=new ArrayList<>();
		
		channelShell.connect();
		InputStream in = channelShell.getInputStream();
		OutputStream out = channelShell.getOutputStream();
		PrintWriter prwout = new PrintWriter(out,true);
	
		
		//执行命令
		for (String cmd : cmds) {
			prwout.println(cmd);
			Thread.sleep(1000);
		}
		prwout.println("end");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		String buf = null;
		
		while ((buf = reader.readLine()) != null) {
			System.out.println(buf);
			msgList.add(buf);
			if (buf.contains("end")) {
				break;
			}
		}
		
		//关闭流
		in.close();
		out.close();
		prwout.close();
		reader.close();
		return msgList;
	}

}
