package com.batSSH.utils;

/**
 * 
* @Description: TODO(字符串工具类) 
* @author lili   
* @date 2017年12月24日 下午3:25:43 
* @version V1.0
 */
public class StringUtils {
	
	/**
	 * 
	* @Description: TODO(判断字符串是否是空值) 
	* @author weizhongchuang   
	* @date 2017年12月24日 下午3:28:10 
	* @version V2.0 
	* 
	* @param obj
	* @return  true不等于空   false等于空
	 */
	public static Boolean isNullAndEmpty(Object obj){
		
		if (obj==null) {
			return false;
		}
		
		if (obj.toString().isEmpty()) {
			return false;
		}
		
		return true;
		
	}

}
