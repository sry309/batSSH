package com.batSSH.model;

public class User {
	private String host;
	private int port;
	private String login_username;
	private String login_password;
	private String root_username;
	private String root_password;
	private String checkResult;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getLogin_username() {
		return login_username;
	}
	public void setLogin_username(String login_username) {
		this.login_username = login_username;
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
	public String getRoot_username() {
		return root_username;
	}
	public void setRoot_username(String root_username) {
		this.root_username = root_username;
	}
	public String getRoot_password() {
		return root_password;
	}
	public void setRoot_password(String root_password) {
		this.root_password = root_password;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
}
