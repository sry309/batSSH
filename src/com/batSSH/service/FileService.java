package com.batSSH.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.batSSH.gui.MainFrame;
import com.batSSH.model.Result;
import com.batSSH.model.User;
import com.batSSH.utils.StringUtils;

/**
 * 
* @Description: TODO(文件操作service) 
* @author lili   
* @date 2017年12月24日 下午1:17:39 
* @version V1.0
 */
public class FileService {
	
	/**
	 * 
	* @Description: TODO(导入) 
	* @author lili   
	* @date 2017年12月24日 下午1:36:27 
	* @version V1.0 
	* 
	* @param reader:输入流     split:分隔符
	* @return map success_num:成功条数    fail_num：失败条数 count_num:总共条数 userList:用户集合   failList:错误集合   
	* read_flag:读取成功表示  1表示成功读取，2表示读取失败！
	 */
	public Map<String, Object> doImportText(File file,String split){
		
		
		
		//判断是否为空
		if (!StringUtils.isNullAndEmpty(split)) {
			split=":";
		}
		
		Map<String, Object> importMap=new HashMap<>();
		List<User> userList=new ArrayList<>();
		List<String> failList=new ArrayList<>();
		Integer success_num=0;
		Integer fail_num=0;
		Integer count_num=0;
		
		
		InputStreamReader reader=null;
		BufferedReader br=null;
		String line = "";  
		Boolean bool=true;
		try {
			reader = new InputStreamReader(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			importMap.put("read_flag", "0");
		}
		br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
		while (bool) {  
		    
				//读取一行
				try {
					line=br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					break;
				}
			
			try {
				if (line==null) {
					importMap.put("read_flag", "1");
					bool = false;
					break;
				}
				
				String[] temp=line.split(split);
				User user=new User();
				user.setCheckResult("0");
				user.setHost(temp[0]);
				user.setPort(Integer.parseInt(temp[1]));
				user.setLogin_username(temp[2]);
				user.setLogin_password(temp[3]);
				user.setRoot_username(temp[4]);
				user.setRoot_password(temp[5]);
				
				++count_num;
				++success_num;
				userList.add(user);
				System.out.println("第"+count_num+"行数据："+line);
				line="";
			} catch (Exception e) {
				
				e.printStackTrace();
				++count_num;
				++fail_num;
				failList.add("第"+count_num+"行出错！信息为："+line);
			}
			
		}
	
		//封装数据
		importMap.put("success_num", success_num);
		importMap.put("fail_num", fail_num);
		importMap.put("count_num", count_num);
		importMap.put("userList", userList);
		importMap.put("failList", failList);
		//关闭流
		try {
			reader.close();
			br.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return importMap;
	}
	
	/**
	 * 
	* @Description: TODO(导出) 
	* @author lili   
	* @date 2017年12月24日 下午3:36:00 
	* @version V2.0 
	* 
	* @param url 导出路径
	* @param split 分隔符
	* @return result
	 */
	public Result doExport(String url,String split){
		
		//判断是否为空
		if (!StringUtils.isNullAndEmpty(split)) {
			split=":";
		}
		
		//验证url
		if (!StringUtils.isNullAndEmpty(url)) {
			return new Result(0, "目录为空！", null);
		}
		
		File newFile = new File(url); // 相对路径，如果没有则要建立一个新的output.txt文件  
		 
		//判断目标文件所在的目录是否存在  
    	if(!newFile.getParentFile().exists()) {  
    		//如果目标文件所在的目录不存在，则创建父目录  
    		System.out.println("目标文件所在目录不存在，准备创建它！");  
    		if(!newFile.getParentFile().mkdirs()) {  
    			System.out.println("创建目标文件所在目录失败！");
    			return new Result(0, "创建目标文件所在目录失败！", null);
    		}
    	}
		 
        BufferedWriter out = null;
		try {
			newFile.createNewFile(); // 创建新文件  
			out = new BufferedWriter(new FileWriter(newFile));  
			
			for(User user:MainFrame.users){
				
				StringBuffer line=new StringBuffer(user.getHost()+split);
				line.append(user.getPort()+split);
				line.append(user.getLogin_username()+split);
				line.append(user.getLogin_password()+split);
				line.append(user.getRoot_username()+split);
				line.append(user.getRoot_password()+split);
				if (user.getCheckResult().equals("1")) {
					line.append("成功！\r\n");
				}else{
					line.append("失败！\r\n");
				}
				out.write(line.toString()); // \r\n即为换行  
				out.flush(); // 把缓存区内容压入文件  
			}
			
			return new Result(1, "导出成功！", null);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try {
			out.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return new Result(0, "导出失败！", null);
	}

}
