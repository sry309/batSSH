package com.batSSH.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
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
		this.setSize(750,500);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		ImageIcon icon = new ImageIcon(getClass().getResource("image/bat3.png"));
		this.setIconImage(icon.getImage());
		
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton() == MouseEvent.BUTTON3){
					pppmenu.show(table,e.getX(),e.getY());//显示菜单
				}
				
				if(e.getClickCount() == 2){
					CheckFrame checkFrm = new CheckFrame();
					checkFrm.setVisible(true);
				}
			}
		});
		table.setAutoCreateRowSorter(true);//表头排序
		
		// 状态栏
		JPanel plStatus = new JPanel();
		JLabel lbStatus=new JLabel("Status:");
		
		JLabel lbStatusContext=new JLabel("start check...");
		JLabel lbCount=new JLabel("Conut:");
		JLabel lbCountContext=new JLabel("0");
		lbCountContext.setForeground(Color.BLUE);//数量为蓝色
		JLabel lbSuccess=new JLabel("Success:");
		JLabel lbSuccessContext=new JLabel("0");
		lbSuccessContext.setForeground(Color.GREEN);//成功为绿色
		JLabel lbFail=new JLabel("Fail:");
		JLabel lbFailContext=new JLabel("0");
		lbFailContext.setForeground(Color.RED);//失败为红色
		
		plStatus.setLayout(new GridBagLayout());
		plStatus.add(lbStatus,new GBC(0,0,1,1).setAnchor(GBC.WEST).setInsets(0,10,0,0));
		plStatus.add(lbStatusContext,new GBC(1,0,5,1).setAnchor(GBC.WEST).setIpad(200,0).setWeight(100, 0).setInsets(2));
		plStatus.add(lbCount,new GBC(6,0,1,1));
		plStatus.add(lbCountContext,new GBC(7,0,1,1).setInsets(0,0,0,10));
		plStatus.add(lbSuccess,new GBC(8,0,1,1));
		plStatus.add(lbSuccessContext,new GBC(9,0,1,1).setInsets(2).setInsets(0,0,0,10));
		plStatus.add(lbFail,new GBC(10,0,1,1));
		plStatus.add(lbFailContext,new GBC(11,0,1,1).setInsets(0,0,0,10));

		//布局
		BorderLayout bord = new BorderLayout();
		this.setLayout(bord);
		this.add("North",menubar);
		this.add("Center", scrollPane);
		this.add("South", plStatus);
		
		//
		//测试数据
		//
		
		user.setHost("127.0.0.1");
		user.setPort(22);
		user.setLogin_username("gxv");
		user.setLogin_password("123456");
		user.setRoot_username("root");
		user.setRoot_password("toor");
		user.setCheckResult("1");
		
		int row = users.size();
		users.add(user);
		users.add(user);
		users.add(user);
		users.add(user);
		users.add(user);
		users.add(user);
		User user1 = new User();
		user1.setHost("127.0.0.1");
		user1.setPort(22);
		user1.setLogin_username("gxv");
		user1.setLogin_password("123456");
		user1.setRoot_username("root");
		user1.setRoot_password("toor");
		user1.setCheckResult("2");
		users.add(user1);
		users.add(user1);
		users.add(user1);
		users.add(user1);
		users.add(user1);
		User user2 = new User();
		user2.setHost("127.0.0.1");
		user2.setPort(22);
		user2.setLogin_username("gxv");
		user2.setLogin_password("123456");
		user2.setRoot_username("root");
		user2.setRoot_password("toor");
		user2.setCheckResult("0");
		users.add(user2);
		users.add(user2);
		users.add(user2);
		users.add(user2);
		model.fireTableRowsInserted(row,row);
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
	    	if(columnIndex == 6){
	    		return Icon.class;
	    	}
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
				//return user.getCheckResult();
				String path = String.format("image/%s.png", user.getCheckResult());
				//System.out.println(path);
				return new ImageIcon(getClass().getResource(path));
			default:
				return null;
			}
		}
	}
	
	
	public static void main(String[] args) {
		//设置皮肤
		try
	    {
			UIManager.put("RootPane.setupButtonVisible",false);
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
		
		MainFrame frm = new MainFrame();
		frm.setVisible(true);
	}

}
