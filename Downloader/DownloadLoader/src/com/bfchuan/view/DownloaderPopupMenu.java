package com.bfchuan.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.bfchuan.controller.CentralController;
import com.bfchuan.controller.MainController;
import com.bfchuan.controller.TaskController;
import com.bfchuan.entities.Task;

/**
 * ��̬�˵�
 * @author Administrator
 *
 */
public class DownloaderPopupMenu extends JPopupMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ���������
	 */
	CentralController centralController;
	/**
	 * ���������
	 */
	TaskController taskController;
	
	JPopupMenu popmenu4Table;
	JPopupMenu popmenu4Garbage;
	
	JMenuItem itemNewTask;
	JMenuItem itemPauseTask;
	JMenuItem itemContinueTask;
	JMenuItem itemReDownload; 
	JMenuItem itemDeleteTask;
	JMenuItem itemOpenTask;
	JMenuItem itemOpenFolder;
	JMenuItem itemAddThread;
	JMenuItem itemSubThread;
	
	JMenuItem itemDeleteTask4G;
	JMenuItem itemResumeFromGarbage;
	JMenuItem itemOpenFolder4G;
	JMenuItem itemClearGarbageBin;
	
	/**
	 * ������
	 */
	public DownloaderPopupMenu(){
		init();
	}
	
	/**
	 * ��ʼ��popupMenu
	 */
	public void init(){
		itemNewTask = new JMenuItem("�½�����");

		itemPauseTask = new JMenuItem("��ͣ����");

		itemContinueTask = new JMenuItem("��ʼ����");
		
		itemReDownload = new JMenuItem("��������");
		
		itemOpenTask = new JMenuItem("������");
		
		itemOpenFolder = new JMenuItem("���ļ���");

		itemDeleteTask = new JMenuItem("ɾ������");

		itemAddThread = new JMenuItem("�߳�+");

		itemSubThread = new JMenuItem("�߳�-");

		popmenu4Table = new JPopupMenu();
		
		popmenu4Table.add(itemNewTask);
		popmenu4Table.add(itemPauseTask);
		popmenu4Table.add(itemContinueTask);
		popmenu4Table.add(itemReDownload);
		popmenu4Table.add(itemDeleteTask);
		popmenu4Table.add(itemOpenTask);
		popmenu4Table.add(itemOpenFolder);
		popmenu4Table.add(itemAddThread);
		popmenu4Table.add(itemSubThread);
		
		itemResumeFromGarbage = new JMenuItem("��ԭ����");
		itemDeleteTask4G = new JMenuItem("ɾ������");
		itemOpenFolder4G = new JMenuItem("���ļ���");
		itemClearGarbageBin = new JMenuItem("���������");
		
		popmenu4Garbage = new JPopupMenu();
		
		popmenu4Garbage.add(itemResumeFromGarbage);
		popmenu4Garbage.add(itemDeleteTask4G);
		popmenu4Garbage.add(itemOpenFolder4G);
		popmenu4Garbage.add(itemClearGarbageBin);
		
		/*��Ӽ���*/
		this.addListeners();
	}
	
	/**
	 * Ϊ���˵�����Ӽ���
	 */
	public void addListeners(){
		//�½�����
		this.itemNewTask.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.newTask();
					}
				}
		);
		//��ʼ����
		this.itemContinueTask.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if(taskController.getTask().getStatus() == Task.STATE_PAUSED){ //��������ͣ
							centralController.restartTask(taskController);
						}else{// ����
							centralController.startTask(taskController);
						}
					}
				}
		);
		
		//��������
		this.itemReDownload.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						/*ֻ��ʧ�ܵ�����������������*/
						if(taskController.getTask().getStatus() == Task.STATE_FAILED){ 
							centralController.reDownload(taskController);
						}
					}
				}
		);
		
		//��ͣ����
		this.itemPauseTask.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						//taskController.pauseTask();
						centralController.pauseTask(taskController);
					}
				}
		);
		
		
		//ɾ������
		this.itemDeleteTask.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.deleteTask(taskController);
					}
				}
		);
		
		//ɾ������4G
		this.itemDeleteTask4G.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.deleteTask(taskController);
					}
				}
		);
		
		//���ļ�
		this.itemOpenTask.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if(taskController != null){
							try {
								centralController.openFile(taskController);
							} catch (IOException e) {
								//������ô�����쳣��
								System.out.println("���ļ��쳣��");
							}
						}
					}
				}
		);
		
		//���ļ���
		this.itemOpenFolder.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if(taskController != null){
							try {
								centralController.openFolder(taskController);
							} catch (IOException e) {
								//������ô�����쳣��
								System.out.println("���ļ��쳣��");
							}
						}
					}
				}
		);
		
		//���ļ���4G
		this.itemOpenFolder4G.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if(taskController != null){
							try {
								centralController.openFolder(taskController);
							} catch (IOException e) {
								//������ô�����쳣��
								System.out.println("���ļ��쳣��");
							}
						}
					}
				}
		);
		
		//�߳�+
		this.itemAddThread.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.addThread(taskController);
					}
				}
		);
		
		//�߳�-
		this.itemSubThread.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.subThread(taskController);
					}
				}
		);
		
		//��ԭ����
		this.itemResumeFromGarbage.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.resumeTaskFromGarbage(taskController);
					}
				}
		);
		
		//ɾ������
		this.itemClearGarbageBin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.clearGarbageBin();
					}
				}
		);
	}
	
	
	/**
	 * ��д���෽�������ڶԲ�ͬpopupMenu�ĵ���
	 */
	@Override
	public void show(Component component, int x, int y) {
			this.setItemStatus();
			
			if(this.centralController.getViewStatus() != MainController.GARBAGEBIN_VIEW){
				this.popmenu4Table.show(component, x, y);
			}else{
				this.popmenu4Garbage.show(component, x, y);
			}
	}

	/**
	 * ���ø��˵���״̬
	 */
	public void setItemStatus(){
		if(taskController == null){
			this.itemNewTask.setEnabled(true);
			this.itemPauseTask.setEnabled(false);
			this.itemContinueTask.setEnabled(false);
			this.itemReDownload.setEnabled(false);
			this.itemOpenTask.setEnabled(false);
			this.itemOpenFolder.setEnabled(false);
			this.itemOpenFolder4G.setEnabled(false);
			this.itemDeleteTask.setEnabled(false);
			this.itemDeleteTask4G.setEnabled(false);
			this.itemAddThread.setEnabled(false);
			this.itemSubThread.setEnabled(false);
			this.itemResumeFromGarbage.setEnabled(false);
		}else{
			Task task = taskController.getTask();
			if(task.getStatus() == Task.STATE_NEW || task.getStatus() == Task.STATE_RUNNING){
				this.itemNewTask.setEnabled(true);
				this.itemPauseTask.setEnabled(true);
				this.itemContinueTask.setEnabled(false);
				this.itemReDownload.setEnabled(false);
				this.itemOpenTask.setEnabled(false);
				this.itemOpenFolder.setEnabled(true);
				this.itemOpenFolder4G.setEnabled(true);
				this.itemDeleteTask.setEnabled(true);
				this.itemDeleteTask4G.setEnabled(true);
				this.itemAddThread.setEnabled(true);
				this.itemSubThread.setEnabled(true);
				this.itemResumeFromGarbage.setEnabled(true);
				return;
			}
			if(task.getStatus() == Task.STATE_PAUSED){
				this.itemNewTask.setEnabled(true);
				this.itemPauseTask.setEnabled(false);
				this.itemContinueTask.setEnabled(true);
				this.itemReDownload.setEnabled(false);
				this.itemOpenTask.setEnabled(false);
				this.itemOpenFolder.setEnabled(true);
				this.itemOpenFolder4G.setEnabled(true);
				this.itemDeleteTask.setEnabled(true);
				this.itemDeleteTask4G.setEnabled(true);
				this.itemAddThread.setEnabled(false);
				this.itemSubThread.setEnabled(false);
				this.itemResumeFromGarbage.setEnabled(true);
				return;
			}
			if(task.getStatus() == Task.STATE_COMPLETED){
				this.itemNewTask.setEnabled(true);
				this.itemPauseTask.setEnabled(false);
				this.itemContinueTask.setEnabled(false);
				this.itemReDownload.setEnabled(false);
				this.itemOpenTask.setEnabled(true);
				this.itemOpenFolder.setEnabled(true);
				this.itemOpenFolder4G.setEnabled(true);
				this.itemDeleteTask.setEnabled(true);
				this.itemDeleteTask4G.setEnabled(true);
				this.itemAddThread.setEnabled(false);
				this.itemSubThread.setEnabled(false);
				this.itemResumeFromGarbage.setEnabled(true);
				return;
			}
			if(task.getStatus() == Task.STATE_FAILED){
				this.itemNewTask.setEnabled(true);
				this.itemPauseTask.setEnabled(false);
				this.itemContinueTask.setEnabled(false);
				this.itemReDownload.setEnabled(true);
				this.itemOpenTask.setEnabled(false);
				this.itemOpenFolder.setEnabled(true);
				this.itemOpenFolder4G.setEnabled(true);
				this.itemDeleteTask.setEnabled(false);
				this.itemDeleteTask4G.setEnabled(false);
				this.itemAddThread.setEnabled(false);
				this.itemSubThread.setEnabled(false);
				this.itemResumeFromGarbage.setEnabled(false);
			}
			
		}
		
	}
	
	/**
	 * ��������������popupMenu��һ��ʱ��ֻ�ܶ�Ӧһ��������,���������˿�������ԭ�������ᱻ����
	 * @param taskController
	 */
	public void setTaskController(TaskController taskController){
		this.taskController = taskController;
		/*show*/
		//this.show();
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
}
