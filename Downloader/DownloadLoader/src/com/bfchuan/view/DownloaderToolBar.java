package com.bfchuan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.bfchuan.controller.CentralController;
import com.bfchuan.controller.TaskController;
import com.bfchuan.entities.Task;


/**
 * �������Ĺ������������½����񡢿�ʼ����ͣ����ɾ�����񡢴��ϴ�����Ȱ�ť
 * @author Administrator
 *
 */
public class DownloaderToolBar extends JToolBar{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���������
	 */
	CentralController centralController;
	/**
	 * button��ָʾ������
	 */
	TaskController taskController = null;
	private JButton btnNewTask;
	private JButton btnBeginTask;
	private JButton btnPauseTask;
	private JButton btnDeleteTask;
	private JButton btnOpenPreviousTask;
	private JButton btnSubThread;
	private JButton btnAddThread;
	
	/**
	 * ������toolBar�Ĺ��췽��
	 */
	public DownloaderToolBar(){
		init();
	}
	
	 /**
	  * ��ʼ��
	  */
	public void init(){
		this.buildTaskBtn();
		this.buildThreadBtn();
		this.addListeners();
		
		/*��ʼ��״̬*/
		this.showBtn();
	}
	
	/**
	 * ���ɹ�������İ�ť
	 */
	public void buildTaskBtn(){
		
		//����toolBar������
		this.setLayout(new BorderLayout());
		this.setRollover(true);						// �����������ǣ�buttonͻ�𣬳��ֱ߿�
		this.setBackground(new Color(203, 223, 245));

		//���ڷ�������button��penel���������ڲ���
		JPanel panel4TaskBtn = new JPanel();
		panel4TaskBtn.setLayout(new GridLayout(1, 5, 10, 10));
		panel4TaskBtn.setBackground(new Color(189, 209, 232));

		// �½�����
		ImageIcon iconNewTask = new ImageIcon("images/imgNewTask.jpg");
		btnNewTask = new JButton(iconNewTask);
		btnNewTask.setBorder(BorderFactory.createLineBorder(new Color(90, 160,232)));
		btnNewTask.setToolTipText("����˰�ť��ʼ�½�����");
		
		//��ʼ����
		ImageIcon iconContinueTask = new ImageIcon("images/imgContinueTask.jpg");
		btnBeginTask = new JButton(iconContinueTask);
		btnBeginTask.setBorder(BorderFactory.createLineBorder(new Color(90, 160,232)));
		//btnBeginTask.setEnabled(false);
		btnBeginTask.setToolTipText("����˰�ť��������ͣ�˵�����");
		
		//��ͣ����
		ImageIcon iconPauseTask = new ImageIcon("images/imgPauseTask.jpg");
		btnPauseTask = new JButton(iconPauseTask);
		btnPauseTask.setBorder(BorderFactory.createLineBorder(new Color(90, 160,232)));
		//btnPauseTask.setEnabled(false);
		btnPauseTask.setToolTipText("����˰�ť��ͣ�������ص�����");
	
		//ɾ������
		ImageIcon iconDeleteTask = new ImageIcon("images/imgDeleteTask.JPG");
		btnDeleteTask = new JButton(iconDeleteTask);
		btnDeleteTask.setBorder(BorderFactory.createLineBorder(new Color(90, 160,232)));
		btnDeleteTask.setActionCommand("ɾ������");// ���ð�ť�Ķ�������
		//btnDeleteTask.setEnabled(false);
		btnDeleteTask.setToolTipText("����˰�ťɾ��������Ϣ�������ļ�");

		//��ǰ����
		ImageIcon iconPreviousTask = new ImageIcon("images/imgPreviousTask.jpg");
		btnOpenPreviousTask = new JButton(iconPreviousTask);
		btnOpenPreviousTask.setBorder(BorderFactory.createLineBorder(new Color(90, 160,232)));
		//btnOpenPreviousTask.setEnabled(true);
		btnOpenPreviousTask.setToolTipText("����˰�ť���ļ�");
		
		//����������button���õ�panel��
		panel4TaskBtn.add(btnNewTask);
		panel4TaskBtn.add(btnBeginTask);
		panel4TaskBtn.add(btnPauseTask);
		panel4TaskBtn.add(btnDeleteTask);
		panel4TaskBtn.add(btnOpenPreviousTask);
		
		//��panel���õ�toolBar��
		this.add(panel4TaskBtn, "West");
	}
	
	/**
	 * �����߳�+��-�İ�ť
	 */
	private void buildThreadBtn(){
		JPanel panel4ThreadBtn = new JPanel();
		panel4ThreadBtn.setLayout(new GridLayout(1, 2, 10, 5));
		panel4ThreadBtn.setBackground(new Color(189, 209, 232));

		btnAddThread = new JButton(" �߳�+ ");
		btnAddThread.setBorder(BorderFactory.createEtchedBorder());
		btnAddThread.setActionCommand("�߳�+");// ���ð�ť�Ķ�������
		btnAddThread.setToolTipText("����˰�ťʹ��ǰ�߳���+1");
		//btnAddThread.setEnabled(false);

		btnSubThread = new JButton(" �߳�- ");
		btnSubThread.setBorder(BorderFactory.createEtchedBorder());
		btnSubThread.setActionCommand("�߳�-");// ���ð�ť�Ķ�������
		btnSubThread.setToolTipText("����˰�ťʹ��ǰ�߳���-1");
		//btnSubThread.setEnabled(false);

		panel4ThreadBtn.add(btnAddThread);
		panel4ThreadBtn.add(btnSubThread);
		this.add(panel4ThreadBtn, "East");
	}
	
	/**
	 * ΪtoolBar�и�����ť��Ӽ���
	 */
	public void addListeners(){
		//�½�����
		this.getBtnNewTask().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.newTask();
					}
				}
		);
		//��ʼ����
		this.getBtnBeginTask().addActionListener(
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
		
		//��ͣ����
		this.getBtnPauseTask().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						//taskController.pauseTask();
						centralController.pauseTask(taskController);
					}
				}
		);
		
		
		//ɾ������
		this.getBtnDeleteTask().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.deleteTask(taskController);
					}
				}
		);
		
		//���ļ�
		this.getBtnOpenPreviousTask().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if(taskController != null){
							try {
								centralController.openFile(taskController);
							} catch (IOException e) {
								//������ô�����쳣��
							}
						}
					}
				}
		);
		
		//�߳�+
		this.getBtnAddThread().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.addThread(taskController);
					}
				}
		);
		
		//�߳�-
		this.getBtnSubThread().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.subThread(taskController);
					}
				}
		);
		
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
	 * ��������������toolBar��һ��ʱ��ֻ�ܶ�Ӧһ��������,���������˿�������ԭ�������ᱻ����
	 * @param taskController
	 */
	public void setTaskController(TaskController taskController){
		this.taskController = taskController;
		this.showBtn();
	}
	
	/**
	 * ���ݵ�ǰ�Ŀ���������Ӧ����ʾbutton��״̬
	 */
	public void showBtn(){
		if(taskController == null){ //��������Ϊ��ʱ
			this.getBtnBeginTask().setEnabled(false);
			this.getBtnPauseTask().setEnabled(false);
			this.getBtnDeleteTask().setEnabled(false);
			this.getBtnAddThread().setEnabled(false);
			this.getBtnSubThread().setEnabled(false);
			this.getBtnOpenPreviousTask().setEnabled(false);
			return;
		}else{
			switch (taskController.getTask().getStatus()){
				case Task.STATE_NEW :     
				case Task.STATE_RUNNING :  //�½���������
					this.getBtnBeginTask().setEnabled(false);
					this.getBtnPauseTask().setEnabled(true);
					this.getBtnDeleteTask().setEnabled(true);
					this.getBtnAddThread().setEnabled(true);
					this.getBtnSubThread().setEnabled(true);
					this.getBtnOpenPreviousTask().setEnabled(false);
					break;
				case Task.STATE_PAUSED :   //������ͣ
					this.getBtnBeginTask().setEnabled(true);
					this.getBtnPauseTask().setEnabled(false);
					this.getBtnDeleteTask().setEnabled(true);
					this.getBtnAddThread().setEnabled(false);
					this.getBtnSubThread().setEnabled(false);
					this.getBtnOpenPreviousTask().setEnabled(false);
					break;
				case Task.STATE_FAILED :	//����ʧ��
					this.getBtnBeginTask().setEnabled(false);
					this.getBtnPauseTask().setEnabled(false);
					this.getBtnDeleteTask().setEnabled(true);
					this.getBtnAddThread().setEnabled(false);
					this.getBtnSubThread().setEnabled(false);
					this.getBtnOpenPreviousTask().setEnabled(false);
					break;
				case Task.STATE_COMPLETED :  //�������
					this.getBtnBeginTask().setEnabled(false);
					this.getBtnPauseTask().setEnabled(false);
					this.getBtnDeleteTask().setEnabled(true);
					this.getBtnAddThread().setEnabled(false);
					this.getBtnSubThread().setEnabled(false);
					this.getBtnOpenPreviousTask().setEnabled(true);
					break;
				default: 					 //������
					this.getBtnBeginTask().setEnabled(false);
					this.getBtnPauseTask().setEnabled(false);
					this.getBtnDeleteTask().setEnabled(false);
					this.getBtnAddThread().setEnabled(false);
					this.getBtnSubThread().setEnabled(false);
					this.getBtnOpenPreviousTask().setEnabled(false);
					
			}
			
			/*���⴦���������������������״̬ʱ�����ܿ�ʼ���񣬵��Ȼ�ԭ*/
			if(taskController.getTaskControllerStatus() == TaskController.TASKCONTROLLER_GARBAGE){
				this.getBtnBeginTask().setEnabled(false);
			}
		}
		
	}
	
	//-------------  ��Ӧ��ť��get���� ---------------
	

	public JButton getBtnNewTask() {
		return btnNewTask;
	}
	public JButton getBtnBeginTask() {
		return btnBeginTask;
	}
	public JButton getBtnPauseTask() {
		return btnPauseTask;
	}
	public JButton getBtnDeleteTask() {
		return btnDeleteTask;
	}
	public JButton getBtnOpenPreviousTask() {
		return btnOpenPreviousTask;
	}
	public JButton getBtnAddThread() {
		return btnAddThread;
	}
	public JButton getBtnSubThread() {
		return btnSubThread;
	}
}
