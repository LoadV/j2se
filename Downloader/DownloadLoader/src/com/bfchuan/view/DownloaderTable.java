package com.bfchuan.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.bfchuan.controller.CentralController;
import com.bfchuan.controller.TaskController;
import com.bfchuan.entities.Task;
import com.bfchuan.entities.TaskShowBean;
import com.bfchuan.util.TaskUtil;

/**
 * �������е�����table
 * @author Administrator
 *
 */
public class DownloaderTable extends JTable{
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���������
	 */
	CentralController centralController;
	/**
	 * tableģ��
	 */
    AbstractTableModel tableModel;
    /**
     * �Ҽ��˵�
     */
    DownloaderPopupMenu popupMenu;
	
//	/**
//	 * table������ʾ��task
//	 */
//	Map<TaskController, Thread> ctrlThdMap;
    
    /**
     * table������ʾ�������飬�ŵ�set��
     */
    Set<TaskController> taskControllerSet;

	/**
	 * �вι�������ͨ���ƶ���AbstractTableModel����table
	 * @param tableModel
	 */
	public DownloaderTable(AbstractTableModel tableModel){
		super(tableModel);
		this.tableModel = tableModel;
		init();
	}
	
	/**
	 * ��ʼ��
	 */
	private void init(){
		//this.setBackground(new Color(250, 250, 250));
		this.getTableHeader().setReorderingAllowed(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.addListeners();
		
		/*������*/
		this.getColumn("����").setCellRenderer(new ProgressRenderer());
	}
	
	/**
	 * ��Ӽ���
	 */
	private void addListeners(){
		this.addMouseListener(new MouseAdapter() { // table��������
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 1) { //������
							int index = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
							if (index >= 0&& ((JTable) e.getSource()).isRowSelected(index)){
								tableSelected();
							}
						}
						if (e.getButton() == 3) { //����Ҽ�
							System.out.println("right_table");
							int row = rowAtPoint(e.getPoint());
							requestFocus();
							changeSelection(row, 1, false, false);
							tableSelected();
							if(popupMenu != null){
								popupMenu.show(DownloaderTable.this, e.getX(), e.getY());
							}	
						}
					}

				});
	}
	
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		System.out.println("tableѡ��ı�");
//		this.tableSelected();
//	}

	/**
	 * table��ѡ���ˣ�ִ����Ӧ����
	 */
	public void tableSelected(){
		if(taskControllerSet == null || taskControllerSet.size() == 0){//mapΪ��
			this.centralController.changeTableSelect(null);
			return;
		}
		int selectedRow = this.getSelectedRow();
		if(selectedRow == -1){//û���κ��б�ѡ��
			return;  //�����κβ���
		}else{
			this.centralController.changeTableSelect(matchRowToTaskController(selectedRow));
		}
	}

	/**
	 * ��ѡ����ƥ�䵽��Ӧ��taskController������URL�Ƿ����
	 */
	public TaskController matchRowToTaskController(int selectedRow){
		int urlColumn = 8;//url��Ӧ����
		String selectedRowUrl = (String) this.getValueAt(selectedRow, urlColumn);
		if(selectedRowUrl != null && this.taskControllerSet != null){ //ѡ�еĸ��в�Ϊ����
			for(Iterator<TaskController> it = this.taskControllerSet.iterator();it.hasNext();){
				TaskController taskController = it.next();
				Task task = taskController.getTask();
				if(task.getSourceUrl().equals(selectedRowUrl)){ //�ҵ���ȵ�url
					return taskController;
				}
			}
		}
		return null;//δ�ҵ���ȵ�url����ѡ������ǲ���Ӧ�κ�����Ŀ���
	}
	
	/**
	 * ��ʾtable�����ݴ�����Map,Ϊ��֧�־ɰ汾����ʱ�����÷���
	 * @param ctrlThdMap
	 */
	public void showTable(Map<TaskController, Thread> ctrlThdMap) {
		Set<TaskController> controllerSet =  ctrlThdMap.keySet();
		//this.clearTable();  //���table
		this.showTable(controllerSet);
	}
	
	/**
	 * ���ݴ���������set��ʾ����
	 * @param taskController
	 */
	public void showTable(Set<TaskController> taskControllerSet){
		this.taskControllerSet = taskControllerSet;
		this.clearTable();  //���table
		int row = 0;
		for(Iterator<TaskController> it = taskControllerSet.iterator();it.hasNext();row++){
			TaskController tc = it.next();
			Task task = tc.getTask();
			TaskShowBean tsb = TaskUtil.TaskToShowTask(task);
			tableModel.setValueAt(tsb.getStatus(), row, 0);   //�����ݡ��С��У�
			tableModel.setValueAt(tsb.getFileName(),row, 1);
			tableModel.setValueAt(tsb.getFileSize(), row, 2);
			//tableModel.setValueAt(tsb.getProgressRate(), row, 3);
			tableModel.setValueAt(Math.round(task.getProgressRate()*100), row, 3);
			tableModel.setValueAt(tsb.getSpeed(), row, 4);
			tableModel.setValueAt(tsb.getBeginTime(), row, 5);
			tableModel.setValueAt(tsb.getTakesTime(), row, 6);
			tableModel.setValueAt(tsb.getSavePath(), row, 7);
			tableModel.setValueAt(tsb.getUrl(), row, 8);
			tableModel.setValueAt(tsb.getThreadSum(), row, 9);
		}
	}
	
//	/**
//	 * ��ʾtable���Զ������ͼ�������������Ӧ��ʾ
//	 */
//	public void showTable(){
//		System.out.println("showTable");
//		if(contrller.getFrameStatus() == MainController.RUNNING_VIEW){ //������
//			this.ctrlThdMap = contrller.getCtrlThdMap();
//			this.showTable(ctrlThdMap);
//		}else if(contrller.getFrameStatus() == MainController.COMPLETE_VIEW){  //�����
//			this.ctrlThdMap = contrller.getCompletedMap();
//			this.showTable(ctrlThdMap);
//		}else if(contrller.getFrameStatus() == MainController.GARBAGEBIN_VIEW){//������
//			this.ctrlThdMap = contrller.getGarbageBinMap();
//			this.showTable(ctrlThdMap);
//		}
//		
//	}
	
	/**
	 * ���table
	 */
	public void clearTable(){
		 for(int row = 0;row < tableModel.getRowCount();row++){
			 for(int col = 0; col < tableModel.getColumnCount();col++){
				 tableModel.setValueAt(null, row, col);
			 }
		 }
	}
	
	/**
	 * ������������
	 * @param centralController
	 */
	public void addCentralContoller(CentralController centralController){
		if(this.centralController == null){
			this.centralController = centralController;
		}
	}
	
	/**
	 * ����Ҽ��˵�
	 */
	public void addPopupMenu(DownloaderPopupMenu popupMenu){
		this.popupMenu = popupMenu;
	}
}
