package com.bfchuan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.table.AbstractTableModel;

import com.bfchuan.controller.CentralController;
import com.bfchuan.controller.TaskController;
import com.bfchuan.util.Global;

/**
 * ��������
 * @author Administrator
 *
 */

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static MainFrame mainFrame = null;
//	/*��־��¼*/
//	static Logger logger = Logger.getLogger(MainFrame.class);
	/**
	 * ���������
	 */
	 CentralController centralController;
	 DownloaderMenuBar menuBar;
	 DownloaderToolBar toolBar;
	 DownloaderTree tree;
	 AbstractTableModel tablemodel;
	 DownloaderTable table;
	 DownloaderPopupMenu popupMenu;
	 DownloaderMsgArea msgArea;
	 DownloaderFoot foot;
	 DownloaderDialog dialog;
	 DownloaderAboutDialog aboutDialog;
	 DownloaderIntroductionDialog introductionDialog;
	
	 /**
	  * ��ȡMainFrame��ʵ��������ģʽ
	  * @return
	  */
	 public static MainFrame getInstance(){
		 if(mainFrame == null){
			 mainFrame = new MainFrame();
		 }
		 return mainFrame;
	 }
	 
	/**
	 * ������������mainFrame��ͬʱ�ø�������һ��mainController
	 */
	private MainFrame(){
		super(Global.VERSION_NAME);
	}
	
	/**
	 * ��ʼ��
	 */
	public void init(){
		
		 /*����������Ĳ���*/
		this.setLayout(new BorderLayout());
		
		 /*���menuBar*/
		 menuBar = new DownloaderMenuBar();
		 menuBar.addCentralContoller(centralController);
		 this.setJMenuBar(menuBar);
		 
		 /*���toolBar*/ 
		 toolBar = new DownloaderToolBar();
		 toolBar.addCentralContoller(centralController);
		 // toolBar.addTaskListener(controller);
		 this.add(toolBar,"North");
		
		 /*�������*/
		JSplitPane center = buildCenterPane();
		this.add(center,"Center");
		
	     /*���foot*/
		foot = new DownloaderFoot();
		this.add(foot,"South");
		
		/*���������ڵĹر����ԣ��ر�ǰ�Ķ���*/
		//this.setDefaultCloseOperation(3);// �����û��ڴ˴����Ϸ��� "close" ʱĬ��ִ�еĲ�����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
		this.addWindowListener(new WindowAdapter() {   
		            public void windowClosing(WindowEvent e) {   
		               centralController.exit();
		            }   
		        });  
		 /*������������������*/
		this.setSize(1000, 700);
		this.setLocation(120, 100);// ������Ƶ���λ��
		this.setVisible(true);
		
//		/*��ʾ��ǰ������*/
//		this.showTable();
	}
	
	/**
	 *  ��������
	 * @return
	 */
	private JSplitPane buildCenterPane() {
		// ����һ������Ϊָ�����������������ֵ��� JSplitPane,newOrientation -
		// JSplitPane.HORIZONTAL_SPLIT �� JSplitPane.VERTICAL_SPLIT
		JSplitPane centerPane = new JSplitPane(1);
		centerPane.setResizeWeight(0.2);
		
		/*������ߣ��ļ���*/
		centerPane.setRightComponent(getRightPane());
		
		/*�����ұߣ�table����Ϣ��*/
		centerPane.setLeftComponent(getLeftPane());
		
		/*����������������*/
		centerPane.setContinuousLayout(false);// ����ָ����ı�λ��ʱ��������ػ棬��Ϊ true
		centerPane.setDividerSize(8);// ���÷ָ����Ĵ�С��
		centerPane.setOneTouchExpandable(true);// �ڷָ������ṩһ�� UI С����������չ��/�۵��ָ���
		return centerPane;
	}
	
	/**
	 * �����������ߣ��ļ���
	 * @return
	 */
	private JScrollPane getLeftPane() {
		this.tree = new DownloaderTree();
		JScrollPane treeView = new JScrollPane(tree);
		tree.addCentralContoller(centralController);
		return treeView;
	}
	
	/**
	 * ���������ұ�
	 * @return
	 */
	private JPanel getRightPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 245, 250));
		
		/*�����ұ߰���table����Ϣ��Ҳ����JSplitPane����*/
		JSplitPane rightPane = new JSplitPane(0);
		rightPane.setResizeWeight(0.2);
		
		/*table*/
		rightPane.setLeftComponent(getUpPane());
		
		/*��Ϣ��*/
		rightPane.setRightComponent(getDownPane());
		rightPane.getRightComponent().setBackground(new Color(248,248,248));
		
		panel.add(rightPane,BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * ��ȡ������Ϣ��
	 * @return
	 */
	private JPanel getDownPane() {
		msgArea = new DownloaderMsgArea();
		return msgArea;
	}
	
	/**
	 * ��ȡ����table
	 * @return
	 */
	private JPanel getUpPane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(248,248,248));
		
		/*tableģ��*/
		tablemodel = new DownloaderTableModel();
		
		/*��ģ�ͽ���table*/
	    table = new DownloaderTable(tablemodel);
	    table.addCentralContoller(centralController);
	    
	    /*Ϊtable����Ҽ��˵�*/
	    popupMenu = new DownloaderPopupMenu();
	    popupMenu.addCentralContoller(centralController);
	    table.addPopupMenu(popupMenu);
	    
	    /*װ��table*/
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBackground(new Color(248,248,248));
		
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * ��ʾtable�����ݴ���������
	 * @param ctrlThdMap
	 */
	public void showTable(Map<TaskController,Thread> ctrlThdMap){
		this.table.showTable(ctrlThdMap);
	}
	
//	/**
//	 * �˷�������table��showTable���������Զ�ʶ��ǰ��ͼ��������Ӧ��ʾ
//	 */
//	public void showTable(){
//		this.table.showTable();
//	}
	
	/**
	 * �½�����
	 */
	public void newTask(){
		 if(dialog == null || dialog.isVisible() == false){
			 dialog = new DownloaderDialog(this);
			 dialog.addCentralContoller(centralController);
		 }
	}
	
	/**
	 * ���ݴ����������������ʾ��ť��״̬
	 * @param taskController
	 */
	public void setButtonStatus(TaskController taskController){
		this.toolBar.setTaskController(taskController);
	}
	
	/**
	 * ��ʾ�����ڡ�
	 */
	public void showAbout(){
		if(this.aboutDialog == null || this.aboutDialog.isVisible() == false){
			aboutDialog = new DownloaderAboutDialog(this);
		}
	}
	
	/**
	 * ��ʾ���ܽ���
	 */
	public void showIntroduction(){
		if(this.introductionDialog == null || this.introductionDialog.isVisible() == false){
			introductionDialog = new DownloaderIntroductionDialog(this);
		}
	}
	
	/**
	 * ���ݴ����������������ʾ��Ϣ�����״̬
	 * @param taskController
	 */
	public void setMsgAreaStatus(TaskController taskController){
		this.msgArea.setTaskController(taskController);
	}
	
	/**
	 * ���ݴ�����������������ø�popupMenu
	 * @param taskController
	 */
	public void setPopupMenuStatus(TaskController taskController){
		this.popupMenu.setTaskController(taskController);
	}
	
	/**
	 * ������������
	 * @param centralController
	 */
	public void addCentralContoller(CentralController centralController){
		//if(this.centralController == null){
			this.centralController = centralController;
		//}
	}
	
	//-------------------get��set---------------------
	
	/**
	 * ���õ����Ի���
	 */
	public void setDialog(DownloaderDialog dialog) {
		this.dialog = dialog;
	}
	
	/**
	 * ������Ϣ��ʾ����
	 * @return
	 */
	public DownloaderMsgArea getMsgArea() {
			return msgArea;
	}
}
