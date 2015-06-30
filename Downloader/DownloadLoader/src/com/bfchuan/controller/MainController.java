package com.bfchuan.controller;

import java.io.IOException;
import java.util.*;


import com.bfchuan.entities.Task;
import com.bfchuan.listener.DownloaderListener;
import com.bfchuan.util.Global;
import com.bfchuan.util.TaskUtil;

/**
 * ���������ܿ�����<br>
 * �����������ء�����ɡ��������е����������´�ָ��ִ����Ӧ�����ز�����<br>
 * ���������Ĺ��������ǣ�MainController����TaskController��Ȼ��TaskController����Task
 *  @author Administrator
 *
 */
public class MainController implements DownloaderListener{
	
	/**
	 * ����������ͼ����
	 */
	public static final int RUNNING_VIEW = 1;
	/**
	 * �������ͼ����
	 */
	public static final int COMPLETE_VIEW = 2;
	/**
	 * ��������ͼ����
	 */
	public static final int GARBAGEBIN_VIEW = 3;
	/**
	 * ��������ǰ���Ƶ���ͼ��ȱʡΪ���������ء�
	 */
	private int frameStatus = 1;
	
	/**
	 * �ܿ�������Ҫ�����ȫ������
	 */
	Set<TaskController> taskControllerSet = new HashSet<TaskController>();
	/**
	 * һ�������������Ӧһ�������̣߳���Map����,����δ��������map
	 */
	Map<TaskController,Thread> ctrlThdMap = new HashMap<TaskController,Thread>();
	
	/**
	 * ����ɵ������map
	 */
	Map<TaskController,Thread> completedMap = new HashMap<TaskController,Thread>();
	
	/**
	 * �������еĶ���map
	 */
	Map<TaskController,Thread> garbageBinMap = new HashMap<TaskController,Thread>();
	
	/**
	 * ��ѡ����������������ǰ֧��ÿ��ѡһ������
	 */
    TaskController selectTaskController;
	
	public MainController(){
		this.init();
	}
	
	/**
	 * ��ʼ��֮ǰ������
	 */
	public void init(){
		/*�������ص�����*/
		ctrlThdMap = TaskUtil.loadTaskControllersFromFile(Global.RUNNING_TASK_PATH);
		/*�Ѿ���ɵ�����*/
		completedMap =  TaskUtil.loadTaskControllersFromFile(Global.COMPLETE_TASK_PATH);
		/*������*/
		garbageBinMap = TaskUtil.loadTaskControllersFromFile(Global.GARBAGE_TASK_PATH);
		
	}
	
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * �½����񣬵����Ի�������Ϣ
	 */
	public void newTask() {
		//this.frame.newTask();
	}

	/**
	 * ���ݴ�����������Ϣ�½�����
	 * @param task
	 */
	public void newTask(Task task){
		
		/*���������ȡ���������*/
		TaskController taskController = new TaskController(task);
		
		/*��������������ĳ�ʼ״̬*/
		taskController.setTaskControllerStatus(TaskController.TASKCONTROLLER_RUNNING);
		
		/*��������������뵽�ܿ��������������������*/
		this.taskControllerSet.add(taskController);
		
		/*��ʼ����*/
		this.startTask(taskController);
		
		/*�����������*/
		//TaskUtil.saveTasktoFile(task);
		/*�����������������*/
		//TaskUtil.saveTaskControllerToFile(tc);
	}
	
	/**
	 * ���ݴ��������������������Ӧ����
	 */
	public void startTask(TaskController taskController) {
		Thread thread = new Thread(taskController);
		
		thread.start();
		
		/*����map��*/
		ctrlThdMap.put(taskController,thread);
		
		System.out.println("---------------"+ctrlThdMap.get(taskController));
		
//		/*��table����ʾ*/
//		this.frame.showTable(ctrlThdMap);
		
//		/* �������߳� */
//		this.startInnerThread();
	}

	
	
	/**
	 * ���������ص������м���Ƿ��������������,������ɵ�����Set
	 */
	public Set<TaskController> checkRunningToComplete() {
		Set<TaskController> completeSet = new HashSet<TaskController>();
		Set<TaskController> taskControllerSet = ctrlThdMap.keySet();
		for(Iterator<TaskController> it = taskControllerSet.iterator();it.hasNext();){
			TaskController taskController = it.next();
			Task task = taskController.getTask();
			if(task.getStatus() == Task.STATE_COMPLETED){
//				JOptionPane.showMessageDialog(this.frame, task.getFileName() + " �������,��ʱ "
//						+ task.getTakesTime()/1000 + " �룡", "���",
//						JOptionPane.INFORMATION_MESSAGE);
				/*���뵽�����*/
				this.completedMap.put(taskController, this.ctrlThdMap.get(taskController));
				
				/*�Ƴ�������*/
				this.ctrlThdMap.remove(taskController);
				
				/*��ӵ����set�У�������*/
				completeSet.add(taskController);
			}
		}	
		return completeSet;
	}
	
	/**
	 * �ж��Ƿ����������е�����
	 * @return
	 */
	public boolean isHaveRunningTask(){
		Set<TaskController> taskController = ctrlThdMap.keySet();
		for(Iterator<TaskController> it = taskController.iterator();it.hasNext();){
			Task task = it.next().getTask();
			if(task.getStatus() == Task.STATE_RUNNING || task.getStatus() == Task.STATE_NEW){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * table��ѡ��ı���
	 */
	public void changeTableSelect(TaskController taskController) {
		this.setSelectTaskController(taskController);
	}
	
	/**
	 * ��ͣ������
	 */
	public void pauseTask(TaskController taskController) {
		/*��������������״̬�򷵻�*/
		if(taskController.getTask().getStatus() != Task.STATE_RUNNING){
			return;
		}
		
	    /*��������������Լ�����ͣ������*/
	   taskController.pauseTask();
	   
	   /*ֹͣ������Ĵ����߳�*/
	   Thread thread =  this.ctrlThdMap.get(taskController);
	   System.out.println("--------2-------"+ctrlThdMap.get(taskController));
	   thread.interrupt();
	}

	 /**
	  * ���¿�ʼ������
	  */
	public void restartTask(TaskController taskController) {
		System.out.println("restartTask");
		
		Thread thread = new Thread(taskController);
		
		thread.start();
		
		this.ctrlThdMap.put(taskController,thread);
		
	}
	
	/**
	 * ������������
	 */
	public void reDownload(TaskController taskController){
		/*����������Ϊ�½�*/
		taskController.getTask().setStatus(Task.STATE_NEW);
		
		/*���¿�ʼ����*/
		this.restartTask(taskController);
	}
	
	/**
	 * ɾ�����������䣬����������������򳹵�ɾ��
	 */
	public void deleteTask(TaskController taskController) {
		
		System.out.println("deleteTask");
		/*�����ļ����ļ���*/
		String tempFileName = taskController.getTask().getFileName() + "." + Global.TASK_OBJECT_POSTFIX;
		
		if(this.frameStatus == GARBAGEBIN_VIEW){// ��ǰ��ͼ�������䣬�����񳹵�ɾ��
			/*��������map���Ƴ�*/
			this.garbageBinMap.remove(taskController);
			/*ɾ���ļ�*/
			TaskUtil.deleteFile(Global.GARBAGE_TASK_PATH + Global.FILE_SEPARATOR + tempFileName);
		}else if(this.frameStatus == RUNNING_VIEW){  //���򣬽������ļ��ƶ���������
			/*���������У�������ͣ����*/
			if(taskController.getTask().getStatus() == Task.STATE_RUNNING){
				pauseTask(taskController);
			}
			
			this.ctrlThdMap.remove(taskController);
			TaskUtil.moveFile(Global.RUNNING_TASK_PATH + Global.FILE_SEPARATOR + tempFileName, Global.GARBAGE_TASK_PATH);
			
			/*��ӵ�������map��*/
			this.garbageBinMap.put(taskController, null);
			
			/*�����������״̬�޸�Ϊ����*/
			taskController.setTaskControllerStatus(TaskController.TASKCONTROLLER_GARBAGE);
		}else if(this.frameStatus == COMPLETE_VIEW){
			this.completedMap.remove(taskController);
			TaskUtil.moveFile(Global.COMPLETE_TASK_PATH + Global.FILE_SEPARATOR + tempFileName, Global.GARBAGE_TASK_PATH);
			
			/*��ӵ�������map��*/
			this.garbageBinMap.put(taskController, null);
			
			/*�����������״̬�޸�Ϊ����*/
			taskController.setTaskControllerStatus(TaskController.TASKCONTROLLER_GARBAGE);
		}
		
//		/*ˢ��table*/
//		this.frame.showTable();
	}
	
	/**
	 * ������ɵ�����
	 * @throws IOException 
	 */
	public void openFile(TaskController taskController) throws IOException {
		System.out.println("openFile");
		Task task = taskController.getTask();
		if(task.getStatus() == Task.STATE_COMPLETED){ //ֻ������ɵ�������Դ�		
				TaskUtil.openFile(task.getFilePathName());
		}		
		
	}
	

	/**
	 * ���������ڵ��ļ���
	 * @param taskController
	 */
	public void openFolder(TaskController taskController) throws IOException{
		Task task = taskController.getTask();
		System.out.println(task.getSavePath());
		TaskUtil.openFolder(task.getSavePath());
	}
	
	/**
	 * ������������仹ԭ����
	 * @param taskController
	 */
	public void resumeTaskFromGarbage(TaskController taskController) {
		System.out.println("resumeTaskFromGarbage");
		Task task = taskController.getTask();
		String tcFilePath = Global.GARBAGE_TASK_PATH + Global.FILE_SEPARATOR + task.getFileName() + "." + Global.TASK_OBJECT_POSTFIX;
		int taskStatus = task.getStatus();
		if( taskStatus == Task.STATE_RUNNING || taskStatus == Task.STATE_NEW || taskStatus == Task.STATE_PAUSED){//�����к��½�״̬
			/*�ƶ��ļ�*/
			TaskUtil.moveFile(tcFilePath, Global.RUNNING_TASK_PATH);
			
			/*��ӵ���������map*/
			this.ctrlThdMap.put(taskController, null);
			
			/*�Ƴ�������map*/
			this.garbageBinMap.remove(taskController);
			
			/*�������������״̬*/
			taskController.setTaskControllerStatus(TaskController.TASKCONTROLLER_RUNNING);
			
		}else if(taskStatus == Task.STATE_COMPLETED){//�����
			TaskUtil.moveFile(tcFilePath, Global.COMPLETE_TASK_PATH);
			
			/*��ӵ���������map*/
			this.completedMap.put(taskController, null);
			
			/*�Ƴ�������map*/
			this.garbageBinMap.remove(taskController);
			
			/*�������������״̬*/
			taskController.setTaskControllerStatus(TaskController.TASKCONTROLLER_COMPLETE);
		}
		
	}
	
	/**
	 * Ϊ�����������������������߳�
	 * @param taskController
	 */
	public void addThread(TaskController taskController) {
		Task task = taskController.getTask();
		int currentThread = task.getThreadAmount();
		if(currentThread >= Global.MAX_THREADS){
			
		}else{
			taskController.addThread();
		}	
//		/*ˢ*/
//		this.frame.showTable();
	}

	/**
	 * Ϊ�������������������ȥ�߳�
	 * @param taskController
	 */
	public void subThread(TaskController taskController) {
		Task task = taskController.getTask();
		int currentThread = task.getThreadAmount();
		if(currentThread <= 1){
			
		}else{
			taskController.subThread();
		}	
//		/*ˢ*/
//		this.frame.showTable();
	}
	
	/**
	 * ��ͣ�����������ص�����
	 */
	public void pauseAll() {
		Set<TaskController> taskControllerSet = this.ctrlThdMap.keySet();
		/*�����ͣ����*/
		for(Iterator<TaskController> it = taskControllerSet.iterator();it.hasNext();){
			TaskController taskController = it.next();
			this.pauseTask(taskController);
		}
	}
	
	public void addTaskToGarbage(TaskController taskController) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ���������
	 */
	public void clearGarbageBin() {
		/*�����ʱ�ļ�*/
		TaskUtil.deleteAllGarbageFile();
		
		/*���������map*/
		this.garbageBinMap.clear();
		
	}
	
	/**
	 * ��ȡ������ʾ������map
	 * @return
	 */
	public Map<TaskController,Thread> getViewMap(){
		if(this.frameStatus == MainController.RUNNING_VIEW){
			return ctrlThdMap;
		}else if(this.frameStatus == MainController.COMPLETE_VIEW){
			return completedMap;
		}else {
			return garbageBinMap;
		}
	}
	
	//-------------get��set����-------------
	
	/**
	 * ��ȡ��ǰ��ͼ״̬
	 */
	public int getFrameStatus() {
		return frameStatus;
	}
	
	/**
	 * ���õ�ǰ��ͼ״̬
	 * @param frameStatus
	 */
	public void setFrameStatus(int frameStatus) {
		this.frameStatus = frameStatus;
		//this.frame.showTable();
	}
	
	/**
	 * ��ȡ��ǰѡ������������
	 * @return
	 */
	public TaskController getSelectTaskController() {
		return selectTaskController;
	}

	/**
	 * ���õ�ǰѡ������������
	 * @param selectTaskController
	 */
	public void setSelectTaskController(TaskController selectTaskController) {
		this.selectTaskController = selectTaskController;
	}
	/**
	 * ��ȡȫ���������ص�����
	 * @return
	 */
	public Map<TaskController, Thread> getCtrlThdMap() {
		return ctrlThdMap;
	}
	/**
	 * ��ȡȫ������ɵ�����
	 * @return
	 */
	public Map<TaskController, Thread> getCompletedMap() {
		return completedMap;
	}
	/**
	 * ��ȡȫ�������������
	 * @return
	 */
	public Map<TaskController, Thread> getGarbageBinMap() {
		return garbageBinMap;
	}

	/**
	 *  ���õ�ǰ��ͼ
	 */
	public void setViewStatus(int status) {
		this.frameStatus = status;	
	}

	/**
	 * ��ȡ��ǰ��ͼ
	 */
	public int getViewStatus() {
		return frameStatus;
	}

	
}
