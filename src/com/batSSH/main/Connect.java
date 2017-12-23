package com.batSSH.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Connect {
	public static void main(String[] args) throws Exception {
		JSch jsch = new JSch(); // ����JSch����
		String userName = "gxv";// �û���
		String password = "123456";// ����
		String host = "192.168.228.135";// ��������ַ
		int port = 22;// �˿ں�
		String cmd = "su -";// Ҫ���е�����
		Session session = jsch.getSession(userName, host, port); // �����û���������ip���˿ڻ�ȡһ��Session����
		session.setPassword(password); // ��������
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // ΪSession��������properties
		int timeout = 60000000;
		session.setTimeout(timeout); // ����timeoutʱ��
		session.connect(); // ͨ��Session��������
		ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
		channelExec.setCommand(cmd);
		channelExec.setInputStream(null);
		channelExec.setErrStream(System.err);
		channelExec.connect();
		InputStream in = channelExec.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		String buf = null;
		StringBuffer sb = new StringBuffer();
		while ((buf = reader.readLine()) != null) {
			sb.append(buf);
			System.out.println(buf);// ��ӡ����̨���
		}
		reader.close();
		channelExec.disconnect();
		if (null != session) {
			session.disconnect();
		}
	}
}