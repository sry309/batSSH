package com.batSSH.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Map;

import com.batSSH.service.FileService;

/**
 * 
* @Description: TODO(导入测试) 
* @author lili   
* @date 2017年12月24日 下午1:51:53 
* @version V2.0
 */
public class TestImport {
	
	public static void main(String[] args) throws Exception {
		
		File file=new File("D://test.txt");
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		FileService fileService=new FileService();
		Map<String, Object> msg=fileService.doImportText(reader, ":");
		System.err.println("");
	}

}
