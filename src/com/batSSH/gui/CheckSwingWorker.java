package com.batSSH.gui;

import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import com.batSSH.model.User;
import com.batSSH.service.CheckService;

/**
 * 
 * @Description: TODO(单项检查) 
 * @author: c0ny1
 * @date: 2018年1月6日下午10:48:24
 * @version: V1.0
 */

public class CheckSwingWorker extends SwingWorker<User, User> {
	private CheckDialog checkDlg;
	private String imgPath;
	private User user;
	public CheckSwingWorker(CheckDialog checkDlg,User user){
		this.checkDlg = checkDlg;
		this.user = user;
	}
	
	@Override
	protected User doInBackground() throws Exception {
		checkDlg.lbStatus.setIcon(new ImageIcon(getClass().getResource("image/checking_16.gif")));
		
		CheckService check = new CheckService();
		String code_result;
		Map<String,Object> map;
		map = check.doCheck(user);
		code_result = map.get("result").toString();
		user.setCheckResult(code_result);
		return user;
	}

	@Override
	protected void done() {
		imgPath = String.format("image/%s.png", user.getCheckResult());
		checkDlg.lbStatus.setIcon(new ImageIcon(getClass().getResource(imgPath)));
		checkDlg.btnCheck.setText("Check");
	}
}
