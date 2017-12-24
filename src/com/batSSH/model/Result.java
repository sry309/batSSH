package com.batSSH.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @Description: TODO(结果公共类) 
* @author lili   
* @date 2017年10月15日 下午6:53:49 
* @version V1.0
 */
public class Result {
	
	private Integer result;
	private String msg;
	private Object data;
	
	/**
	 * 返回结果信息
	 * @param result 1:表示成功，0：表示失败
	 * @param msg  结果信息
	 * @param data 结果数据
	 */
	public Result(Integer result, String msg, Object data) {
		this.result = result;
		this.msg = msg;
		this.data = data;
	}
	public void toshow(){
		System.out.println(result+"==="+msg+"+++"+data);
	}
	/**
	 * 
	 * @return map结果集
	 * map.get("result"):1:表示成功，0：表示失败
	 * map.get("msg"):结果信息
	 * map.get("data"):结果数据
	 */
	public Map<String , Object> get(){
		
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("result", this.result);
		result.put("msg", this.msg);
		result.put("data", this.data);
		
		return result;
	}
	public Integer getResult(){
		return result;
	}
	public String getData(){
		return (String) data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
