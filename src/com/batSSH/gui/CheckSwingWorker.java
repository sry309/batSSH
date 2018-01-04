package com.batSSH.gui;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;
import javax.swing.table.AbstractTableModel;

import com.batSSH.model.User;
import com.batSSH.service.CheckService;
import com.batSSH.service.batCheckService;

/**
 * 
 * @Description: TODO(多线程检查) 
 * @author: c0ny1
 * @date: 2018年1月4日下午5:46:56
 * @version: V1.0
 */

public class CheckSwingWorker extends SwingWorker<List<User>, User> {
	private AbstractTableModel model;
	private List<User> users;
	private int nSucc = 0;
	private int nFail = 0;
	public CheckSwingWorker(AbstractTableModel model,List<User> users){
		this.model = model;
		this.users = users;
	}
	
	@Override
	protected List<User> doInBackground() throws Exception {
	
		CheckService check = new CheckService();
		String code_result;
		Map<String,Object> map;
		for(User user:users){
			MainFrame.statusBar.setLbStatusContext("checking for"+user.toString());
			map = check.doCheck(user);
			code_result = map.get("result").toString();
			sum(code_result);
			user.setCheckResult(code_result);
			publish(user);
		}
		
		return users;
	}

	@Override
	protected void done() {
		model.fireTableDataChanged();
		MainFrame.statusBar.setLbStatusContext("Check Finish!!!");
	}

	@Override
	protected void process(List<User> chunks) {
		for(User user:chunks){
			model.fireTableDataChanged();
			MainFrame.statusBar.setLbSuccessContext(String.valueOf(nSucc));
			MainFrame.statusBar.setLbFailContext(String.valueOf(nFail));
		}
	}
	
	private void sum(String code){
		switch (code) {
		case "0":
			break;
		case "1":
			nFail++;
			break;
		case "2":
			nFail++;
			break;
		case "3":
			nSucc++;
			break;
		default:
			nFail++;
			break;
		}
	}
	
}
