package com.batSSH.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.batSSH.model.User;

public class MainFrame extends JFrame {
	
	private String name = "batSSH";
	private double version = 1.0;
	private User user;
	private List<User> users = new ArrayList<User>();
	
	MainFrame(){
		this.user = new User();
		
		//主窗口
		this.setTitle(name+" "+version);
		this.setSize(600, 450);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-this.getHeight())/2;
		this.setLocation(x,y);
		
		//菜单
		JMenu fileMenu = new JMenu("File");
		JMenuItem fmExport = new JMenuItem("Export");
		fileMenu.add(fmExport);
		fileMenu.addSeparator();
		JMenuItem fmClose = new JMenuItem("Close");
		fileMenu.add(fmClose);

		JMenu optionMenu = new JMenu("Option");
		JMenuItem omOption = new JMenuItem("Option");
		optionMenu.add(omOption);
		
		JMenu aboutMenu = new JMenu("About");
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(fileMenu);
		menubar.add(optionMenu);
		menubar.add(aboutMenu);
		
		//右键菜单
    	//右键菜单
    	JPopupMenu pppmenu = new JPopupMenu();
    	JMenuItem mClean,mRe,mSave;
    	mClean = new JMenuItem("清空(C)");
    	mRe = new JMenuItem("去重(R)");
    	mSave = new JMenuItem("保存(S)");
		pppmenu.add(mClean);
		pppmenu.add(mRe);
		pppmenu.add(mSave);
		
		//表格
		AbstractTableModel model = new TableModel();
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("11111");
				if(e.getButton() == MouseEvent.BUTTON3){
					pppmenu.show(table,e.getX(),e.getY());//显示菜单
				}
			}
		});
		
		//布局
		BorderLayout bord = new BorderLayout();
		this.setLayout(bord);
		this.add("North",menubar);
		this.add("Center", scrollPane);
		
	}
	
	
// 表格模型类
	class TableModel extends AbstractTableModel{

		@Override
		public int getRowCount() {
			//行数
			return users.size();
		}

		@Override
		public int getColumnCount() {
			//列数
			return 7;
		}
		
	    @Override
	    public String getColumnName(int columnIndex)
	    {
	    	//列名
	        switch (columnIndex)
	        {
				case 0:
					return "host";
				case 1:
					return "port";
				case 2:
					return "login_username";
				case 3:
					return "login_password";
				case 4:
					return "root_username";
				case 5:
					return "root_password";
				case 6:
					return "result";
				default:
					return null;
	        }
	    }
		
	    @Override
	    public Class<?> getColumnClass(int columnIndex)
	    {
	        return String.class;
	    }
	    
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// 每列对应信息实体对象的属性
			User user = users.get(rowIndex);
			
			switch(columnIndex){
			case 0:
				return user.getHost();
			case 1:
				return user.getPort();
			case 2:
				return user.getLogin_username();
			case 3:
				return user.getLogin_password();
			case 4:
				return user.getRoot_username();
			case 5:
				return user.getRoot_password();
			case 6:
				return user.getCheckResult();
			default:
				return null;
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frm = new MainFrame();
		frm.setVisible(true);
	}

}
