package com.bfchuan.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

//import org.apache.log4j.Logger;

import com.bfchuan.entities.Task;
import com.bfchuan.view.MainFrame;

/**
 * �н�����������������г䵱�����ʽ�ɫ�����̳��Գ�����CentralController<br>
 * 			�������������н��е����в��������Ⱦ������н������������<br>
 * 			�����������������ڵ��н���
 * @author Administrator
 *
 */
public class MediaController extends CentralController{
	
	/**
	 * ��������
	 */
	MainController mainController;
	/**
	 * ������
	 */
	MainFrame mainFrame;
	
	/**
	 * ���̣߳����ڶ�ʱ��ء�ˢ��
	 */
	InnerThread innerThread;
	
//	/*��־��¼*/
//	static Logger logger = Logger.getLogger(MediaController.class);
	
	public MediaController(MainController mainController,MainFrame mainFrame){
		this.mainController = mainController;
		this.mainFrame = mainFrame;
	}
	
	/**
	 * ����������ʱ�ĳ�ʼ��
	 */
	public void startInit(){
		try{
			this.refreshAll();
		}catch(Exception e){
			System.out.println("��ʼ������");
		}
	}
	
	/**
	 * ��ʼ�½����񣬵����Ի��򣬻�ȡ������Ϣ
	 */
	public void newTask() {
		System.out.println("newTask()");
		this.mainFrame.newTask();		
	}

	/**
	 * ���ݴ��������񣬽������������½����񣬸�task��������Ϣ���������ļ���С���ļ�����׺
	 */
	public void newTask(Task task) {	
		
		/*У��task���ϸ�*/
		if(!checkTask(task)){
			return;
		}
		
		/*�������������½�����*/
		this.mainController.newTask(task);
		
		/*��������ʾ,ˢ��table*/
		this.showTable();
		
		/*�������̼߳��*/
		this.startInnerThread();
	}
	
	/**
	 * ����½�������������*ͬ������*��ͬurl�����򷵻�true
	 * @param task
	 * @return
	 */
	public boolean checkTask(Task newTask){
		/*�ȼ�������е�*/
		Map<TaskController,Thread> ctrlThdMap = this.mainController.getCtrlThdMap();
		switch (checkTaskInMap(newTask,ctrlThdMap)) {
			case 1 : JOptionPane.showMessageDialog(this.mainFrame, "�Ѵ���ͬURL�����������б��У�", "�½�������Ϣ",0);
					 return false;
			case 2 : JOptionPane.showMessageDialog(this.mainFrame, "�Ѵ���ͬ�ļ����������������б��У�", "�½�������Ϣ",0);
					 return false;
		}
		/*������б�*/
		ctrlThdMap = this.mainController.getCompletedMap();
		switch (checkTaskInMap(newTask,ctrlThdMap)) {
			case 1 : JOptionPane.showMessageDialog(this.mainFrame, "�Ѵ���ͬURL������������б��У�", "�½�������Ϣ",0);
					 return false;
			case 2 : JOptionPane.showMessageDialog(this.mainFrame, "�Ѵ���ͬ�ļ�����������������б��У�", "�½�������Ϣ",0);
					 return false;
		}			 
		/*�������б�*/
		ctrlThdMap = this.mainController.getGarbageBinMap();
		switch (checkTaskInMap(newTask,ctrlThdMap)) {
			case 1 : JOptionPane.showMessageDialog(this.mainFrame, "�Ѵ���ͬURL�������������б��У�", "�½�������Ϣ",0);
					 return false;
			case 2 : JOptionPane.showMessageDialog(this.mainFrame, "�Ѵ���ͬ�ļ������������������б��У�", "�½�������Ϣ",0);
					 return false;
		}
		return true;
	}
	
	/**
	 * ������������ֺ�url�Ƿ�����ڸ�����Map��<br>
	 * ���� 1 ��ʾ������ͬurl
	 * ���� 2 ��ʾ������ͬ�ļ���
	 * ���� 0 ��ʾ������ͬ
	 * @param newTask
	 * @param ctrlThdMap
	 * @return
	 */
	public int checkTaskInMap(Task newTask,Map<TaskController,Thread> ctrlThdMap){
		if(ctrlThdMap != null && ctrlThdMap.size() != 0){
			Set<TaskController> taskControllerSet = ctrlThdMap.keySet();
			for(Iterator<TaskController> it = taskControllerSet.iterator();it.hasNext();){
				TaskController taskController = it.next();
				Task task = taskController.getTask();
				if(newTask.getSourceUrl().equals(task.getSourceUrl())){
					return 1;
				}
				if(newTask.getFileName().equals(task.getFileName())){
					return 2;
				}
			}
		}
		return 0;
	}
	
	/**
	 * �������������
	 */
	public void addTaskToGarbage(TaskController taskController) {
		
	}

	/**
	 * �߳�+1
	 */
	public void addThread(TaskController taskController) {
		this.mainController.addThread(taskController);
		
		this.showTable();
		this.showMsgArea();
	}
	
	/**
	 * �߳�-1
	 */
	public void subThread(TaskController taskController) {
		this.mainController.subThread(taskController);
		
		this.showTable();
		this.showMsgArea();
	}

	/**
	 * ���������
	 */
	public void clearGarbageBin() {
		this.mainController.clearGarbageBin();
		this.showTable();
	}

	/**
	 * ɾ��ָ��������������������
	 */
	public void deleteTask(TaskController taskController) {
		this.mainController.deleteTask(taskController);
		
		this.showTable();
		
	}

	/**
	 * �˳�����
	 */
	public void exit() {	
	  int option = JOptionPane.showConfirmDialog(this.mainFrame, "�Ƿ�Ҫ�˳�Ѹ����������",   
                 "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,   
                 JOptionPane.QUESTION_MESSAGE);   
         if (option == JOptionPane.YES_OPTION) {
         	System.out.println(" STOP�������� ");
         	/*�˳�ǰ��ͣ�����������е�����*/
         	 this.pauseAll();
         	
         	/*��¼��־*/
        // 	logger.info(" Lxlei�˳���");
         	
             System.exit(0);  
         }  
         System.out.println("���ֲ����˳��ˡ���");
	}

	/**
	 * ���ļ�
	 */
	public void openFile(TaskController taskController) {
		try {
			this.mainController.openFile(taskController);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.mainFrame, "���ļ�����", "Error",0);
		}
	}

	/**
	 * ���������ڵ��ļ���
	 * @param taskController
	 */
	public void openFolder(TaskController taskController){
		try {
			this.mainController.openFolder(taskController);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.mainFrame, "���ļ��г���", "Error",0);
		}
	}

	/**
	 * ��ͣȫ������
	 */
	public void pauseAll() {
		this.mainController.pauseAll();
	}

	public void pauseTask(TaskController taskController) {
		this.mainController.pauseTask(taskController);	
	}

	/**
	 * ���¿�ʼ
	 */
	public void restartTask(TaskController taskController) {
		this.mainController.restartTask(taskController);
		
		this.showTable();
		
		this.startInnerThread();
		
	}

	/**
	 * ��������
	 */
	public void reDownload(TaskController taskController){
		/*����������Ϊ�½�*/
		taskController.getTask().setStatus(Task.STATE_NEW);
		
		/*���¿�ʼ����*/
		this.restartTask(taskController);
	}
	
	/**
	 * ���������л�ԭ����
	 */
	public void resumeTaskFromGarbage(TaskController taskController) {
		this.mainController.resumeTaskFromGarbage(taskController);
		this.refreshAll();
	}

	/**
	 * ��ʼ����
	 */
	public void startTask(TaskController taskController) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ��ʾ��������Ϣ��
	 */
	public void showAboatMsg() {
		System.out.println("showAboatMsg");
		this.mainFrame.showAbout();
	}

	/**
	 * ��ʾ�����ܽ��ܡ�
	 */
	public void showIntroduce() {
		this.mainFrame.showIntroduction();
		
	}

	/**
	 * ��ʾ������Ϣ
	 */
	public void showTaskMsg(TaskController taskController) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * table����ѡ�з����˸ı�
	 */
	public void changeTableSelect(TaskController taskController) {
		/*������������*/
		this.mainController.changeTableSelect(taskController);
		
		/*����*/
		this.showButton();
		this.showMsgArea();
		this.mainFrame.setPopupMenuStatus(taskController);
	}

	/**
	 * �趨��ǰ��ͼ���������ء�����ɡ�������
	 */
	public void setViewStatus(int status) {
		this.mainController.setFrameStatus(status);
		
		this.refreshAll();
	}
	
	/**
	 * ���̣߳�����ˢ��table
	 * @author Administrator
	 *
	 */
	private class InnerThread extends Thread implements Serializable {
		private static final long serialVersionUID = -5653497905844621750L;
		@Override
		public void run() {
			while(true){
				try {
					sleep(1000);    //  ÿ1000msˢ��һ������״̬
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("��������ˢ");
					refreshAll();
					
					/*����Ƿ��������������*/
					checkRunningToComplete();
					
					if(!mainController.isHaveRunningTask()){ //���Ѿ�û���������ص������˳�
						refreshAll();
						System.out.println("��������ͣˢ");
						break;
					}
			}
		}
	}
	
	/**
	 * ����Ƿ��������е���������ɣ�����У��򵯳��Ի�����Ϣ����ˢ�½�����ʾ
	 */
	public void checkRunningToComplete(){
		Set<TaskController> completeSet = this.mainController.checkRunningToComplete();
		if(completeSet.size() != 0){  //����ɵ�
			for(Iterator<TaskController> it = completeSet.iterator();it.hasNext(); ){
				TaskController taskController = it.next();
				Task task = taskController.getTask();
				JOptionPane.showMessageDialog(this.mainFrame, task.getFileName() + " �������,��ʱ "
						+ task.getTakesTime()/1000 + " �룡", "���",JOptionPane.INFORMATION_MESSAGE);
			}
			/*��ʾ*/
			this.showTable();
		}
	}
	
	/**
	 * �����߳�δ��������
	 */
	public void startInnerThread(){
		/*�����߳�Ϊ�ջ����Ѿ���ȥ���򴴽���������*/
		if(innerThread == null || !innerThread.isAlive()){ 
			innerThread = new InnerThread();
			//innerThread.setDaemon(true);  //��̨�߳�
			innerThread.start();
		}
	}
	
	/**
	 * ˢ��table
	 */
	public void showTable(){
		Map<TaskController,Thread> ctrlThdMap = this.mainController.getViewMap();
		this.mainFrame.showTable(ctrlThdMap);
	}
	
	/**
	 * ˢ��button
	 */
	public void showButton(){
		this.mainFrame.setButtonStatus(this.mainController.getSelectTaskController());
	}
	
	/**
	 * ˢ����Ϣ����
	 */
	 public void showMsgArea(){
		 this.mainFrame.setMsgAreaStatus(this.mainController.getSelectTaskController());
	 }

	 /**
	  * ˢ��ȫ��
	  */
	 public void refreshAll(){
		 this.showTable();
		 this.showButton();
		 this.showMsgArea();
	 }

	 /**
	  * ��õ�ǰ��ͼ
	  */
	public int getViewStatus() {
		return this.mainController.getFrameStatus();
	}
}
