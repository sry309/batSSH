package com.batSSH.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.table.AbstractTableModel;

import com.batSSH.model.Result;
import com.batSSH.model.User;
import com.batSSH.service.FileService;
import com.batSSH.service.batCheckService;

public class MainFrame extends JFrame {
	
	private String name = "batSSH";
	private double version = 1.0;
	private User user;
	public static List<User> users = new ArrayList<User>();
	private AbstractTableModel model;
	private JTable table;
	public static JStatusBar statusBar;
	
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//菜单
		JMenu fileMenu = new JMenu("File");
		JMenuItem fmImport = new JMenuItem("Import");
		fileMenu.add(fmImport);
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
    	JMenuItem mStart,mStop,mClean,mCopy,mSave,mAdd,mDel,mModify;
    	mStart = new JMenuItem("开始");
    	mStop = new JMenuItem("停止");
    	mClean = new JMenuItem("清空(C)");
    	mAdd = new JMenuItem("添加(A)");
    	mDel = new JMenuItem("删除(D)");
    	mSave = new JMenuItem("保存(S)");
    	mCopy = new JMenuItem("复制");
    	mModify = new JMenuItem("修改(M)");
    	pppmenu.add(mStart);
    	pppmenu.add(mStop);
		pppmenu.add(mClean);
		pppmenu.add(mSave);
		pppmenu.add(mAdd);
		pppmenu.add(mDel);
		pppmenu.add(mCopy);
		pppmenu.add(mModify);
		
		//表格
		model = new TableModel();
		table = new JTable(model);
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
					CheckDialog checkFrm = new CheckDialog();
					checkFrm.setVisible(true);
				}
			}
		});
		table.setAutoCreateRowSorter(true);//表头排序
		
		// 状态栏		
		statusBar = new JStatusBar();
		
		//布局
		BorderLayout bord = new BorderLayout();
		this.setLayout(bord);
		this.add("North",menubar);
		this.add("Center", scrollPane);
		this.add("South", statusBar);
		
	fmImport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFileChooser chooseFile = new JFileChooser();
				 int returnVal = chooseFile.showOpenDialog(null); 
			     if(returnVal == chooseFile.APPROVE_OPTION) {   
			         File f = chooseFile.getSelectedFile();   
			         //JOptionPane.showConfirmDialog(null, "你选择的文件名是："+chooseFile.getName(f),"确认",JOptionPane.ERROR_MESSAGE);
			         FileService fileService=new FileService();
			 		 Map<String, Object> msg=fileService.doImportText(f, ":");
			 		 
			 		 users.clear();
			 		 int row = users.size();
			 		 users = (List<User>) msg.get("userList");
					 model.fireTableRowsInserted(row,row);
					 statusBar.setLbCountContext(String.valueOf(users.size()));
					 statusBar.setLbSuccessContext("0");
					 statusBar.setLbFailContext("0");
			     } 
			}
		});
		
	fmExport.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser  fileChooser = new JFileChooser();
			int i = fileChooser.showSaveDialog(MainFrame.this);
			if (i == JFileChooser.APPROVE_OPTION) { //点击对话框中打开选项
			    File f = fileChooser.getSelectedFile(); //得到选择的文件
			    try {
			    	FileService fileService=new FileService();
			    	Result result=fileService.doExport(f.getPath(), ":");
			    } catch (Exception ex) {
			    	ex.printStackTrace();  //输出出错信息
			    }
			}
		}
	});
	
	mStart.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//batCheckService.dobatCheckService(users);
			//model.fireTableDataChanged();
			CheckSwingWorker csw = new CheckSwingWorker(model,users);
			csw.execute();
		}
	});
	
	mClean.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			users.clear();
			model.fireTableDataChanged();
			statusBar.setLbCountContext(String.valueOf(users.size()));
			statusBar.setLbSuccessContext("0");
			statusBar.setLbFailContext("0");
		}
	});
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
