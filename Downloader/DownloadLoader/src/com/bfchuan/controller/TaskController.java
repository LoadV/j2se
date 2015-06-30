package com.bfchuan.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bfchuan.downloader.PieceManager;
import com.bfchuan.downloader.PieceThread;
import com.bfchuan.entities.Task;
import com.bfchuan.listener.TaskListener;
import com.bfchuan.util.Global;
import com.bfchuan.util.TaskUtil;

/**
 * ������Ŀ�����
 * @author Administrator
 *
 */
public class TaskController implements Runnable, TaskListener,Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * ������������������ء�
	 */
	public static final int TASKCONTROLLER_RUNNING = 1;
	/**
	 * ���������"�����"
	 */
	public static final int TASKCONTROLLER_COMPLETE = 2;
	/**
	 * ����������������䡱
	 */
	public static final int TASKCONTROLLER_GARBAGE = 3;
	
	/**
	 * �����������״̬����ʼ״̬Ϊrunning
	 */
	private int TaskControllerStatus = TASKCONTROLLER_RUNNING;
	
	/**
	 * �ÿ�����Ҫ���Ƶ�����
	 */
	 Task task;
	 /**
	  * ��������ֿ�ķֿ������
	  */
	 PieceManager pm;
	 /**
	  * �ֿ������߳�
	  */
	 List<PieceThread> pieceThreadList = new ArrayList<PieceThread>();
	 
	 private long startTime;
	 private long takedTime;  // ֮ǰ��ʱ
	 private long takesTime;   //�ܺ�ʱ = ��ǰʱ�� - startTime + ֮ǰ��ʱ
	
	
	/**
	 * �вι������������Ӧ��Task��һ��Task��Ӧһ��TaskController
	 * @param task
	 */
	public TaskController(Task task){
		this.task = task;
	}

	/**
	 * ��дrun��������ʼ����
	 */
	public void run() {
		/*ͳ��ʱ���ʼ��*/
		takedTime = task.getTakesTime();
		startTime = new Date().getTime();
		
		/*��ȡ�ֿ������*/
		if(task.getStatus() == Task.STATE_NEW){ // �����������½��ģ�����Ҫ��ȡ��Ӧ��Ϣ���������ֱ�������߳�
			try {
				/*����ļ���С*/
				long fileSize = TaskUtil.getFileSize(task.getSourceUrl());
				task.setFileSize(fileSize);
				/*����ļ���׺*/
				String fileName = TaskUtil.getRealFileName(task.getSourceUrl());
				String postfix = TaskUtil.getFilePostfix(fileName);
				task.setFileName(task.getFileName() + "." +postfix);
			} catch (IOException e) {
				System.out.println("��ȡ�ļ������ļ���С�����쳣��");
				e.printStackTrace();
			}
			pm = new PieceManager(task);
			//task.setPm(pm);//���ֿ������װ��task�У�������ʱ��ȡ��
		}else{		//�������½��ģ�����֮ǰ�Ѿ����˷ֿ������
			/*��ȡ֮ǰ�ķֿ������*/
			//pm = task.getPm();
			
			/*���֮ǰ�ķֿ��̳߳�*/
			pieceThreadList.clear();
		}
		
		/*�����̣߳���ʼ�ֿ�����*/
		for(int i=0; i < task.getThreadAmount();i++){
			PieceThread pieceThread = new PieceThread(pm);
			pieceThread.start();
			pieceThreadList.add(pieceThread); //�����߳���ӵ��̳߳���
			task.setStatus(Task.STATE_RUNNING);//�������״̬�޸�Ϊ�������С�
		}
		
		//�����費��Ҫ���棿��Ҫ���á���
		/*�����������������*/
		//TaskUtil.saveTaskControllerToFile(this);
		
		/*�������̣߳��������״̬*/
		new InnerThread().start();
	}
	
	/**
	 * ���߳�,���ڼ�ʱͳ��ʱ�䣬���ؽ��ȣ������ٶȵ���Ϣ<br>
	 * ���ж������Ƿ���ɻ�ʧ��
	 * @author Administrator
	 *
	 */
	private class InnerThread extends Thread implements Serializable {

		private static final long serialVersionUID = 1L;
		public void run() {
			while(task.getStatus() == Task.STATE_RUNNING){
				System.out.println(new File(pm.getTask().getFilePathName()).length());
				
				/*����ķѵ�ʱ��*/
				takesTime = System.currentTimeMillis() - startTime + takedTime;
				task.setTakesTime(takesTime);
				
				/*�������ؽ���*/
				long loadSize = new File(pm.getTask().getFilePathName()).length();
				task.setLoadSize(loadSize);
				float progressRate = (float)loadSize/task.getFileSize();
				task.setProgressRate(progressRate);
				
				/*���������ٶ�*/
				float averageSpeet = (float)task.getLoadSize()/task.getTakesTime();
				task.setAverageSpeet(averageSpeet);
				
				/*�ж������Ƿ����*/
				if(isTaskComplete()){
					taskComplete();
					System.out.println("������ɣ�");
				}	
				
				/*�ж������Ƿ�ʧ��*/
				if(isTaskFailure()){
					taskFailed();
				}
				
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * �߳�+1
	 */
	public void addThread() {
		System.out.println("addThead");
		int currentThread = this.task.getThreadAmount();
		/*�߳����������*/
		if(currentThread >= Global.MAX_THREADS){
			return;
		}
		this.task.setThreadAmount(currentThread + 1);
		PieceThread pieceThread = new PieceThread(pm);
		pieceThread.start();
		pieceThreadList.add(pieceThread); //�����߳���ӵ��̳߳���
	}


	/**
	 * �߳�-1
	 */
	public void subThread() {
		System.out.println("subThread");
		int currentThread = this.task.getThreadAmount();
		
		/*�߳����Ѳ����ټ�*/
		if(currentThread <= 1){
			return;
		}
		
		/*��߳���ֻʣ���һ���������ټ�*/
		if(getAliveThreadSum() == 1){
			return;
		}
		
		this.task.setThreadAmount(currentThread - 1);
		
		/*ɾ�����һ��*/
		int size = pieceThreadList.size();
		PieceThread pt = pieceThreadList.get(size-1);
		
		/*ɾ�����������ж�*/
		pt.stopMe();
		
		/*�Ƴ��̳߳�*/
		pieceThreadList.remove(pt);
	}

	/**
	 * ��õ�ǰ���طֿ�Ļ�߳���
	 * @return
	 */
	public int getAliveThreadSum(){
		int count_isAlive = 0;
		for(Iterator<PieceThread> it = pieceThreadList.iterator();it.hasNext();){
			PieceThread pieceThread = it.next();
			if(pieceThread.isAlive()){
				count_isAlive ++;
			}
		}
		return count_isAlive;
	}
	
	public void deleteTask() {
		// TODO Auto-generated method stub

	}

	/**
	 * ��ͣ����
	 */
	public void pauseTask() {
		
		/*����������Ϊ��ͣ״̬*/
		this.getTask().setStatus(Task.STATE_PAUSED);
		
		/*ֹͣ�����������صķֿ��߳�*/
		for(Iterator<PieceThread> it = pieceThreadList.iterator();it.hasNext();){
			PieceThread pieceThread = it.next();
			pieceThread.stopMe(); //�����߳��������ͣ����
		}
		
		/*����ÿ���������*/
		TaskUtil.saveTaskControllerToFile(this);
	}

	 /**
	  * ���¿�ʼ����
	  */
	public void restartTask() {
		// TODO Auto-generated method stub
	}

	/**
	 * ��ʼ����
	 */
	public void startTask() {

	}

	/**
	 * �������
	 */
	public void taskComplete() {
		/*������״̬����Ϊ���*/
		task.setStatus(Task.STATE_COMPLETED);
		
		/*�����������ʱ��*/
		task.setEndTime(new Date().getTime());
		
		/*ɾ���������ڡ��������ء��ļ����е��ļ�*/
		String file = Global.RUNNING_TASK_PATH + Global.FILE_SEPARATOR + task.getFileName() + "." + Global.TASK_OBJECT_POSTFIX;
		boolean b = TaskUtil.deleteFile(file);
		if(b){
			System.out.println("ɾ��" + file +"�ɹ���");
		}else{
			System.out.println("ɾ��" + file +"ʧ�ܣ�");
		}
		
		/*���Լ���״̬תΪ���*/
		this.setTaskControllerStatus(TASKCONTROLLER_COMPLETE);
		
		/*��������ɵĶ�����ļ�*/
		//String fileComplete = Global.COMPLETE_TASK_PATH + "/" + task.getFileName() + "." + Global.TASK_OBJECT_POSTFIX;
		TaskUtil.saveTaskControllerToFile(this);
	}
	
	/**
	 * ����ʧ��
	 */
	public void taskFailed() {
		task.setStatus(Task.STATE_FAILED);
		/*����ʧ�ܶ���*/
		TaskUtil.saveTaskControllerToFile(this);
	}
	
	/**
	 * ��ȡ��ǰ���Ƶ�����
	 * @return
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * �жϸ������Ƿ��������
	 */
	public boolean isTaskComplete() {
		if(pm.getPiecesPoolStatus() == PieceManager.PIECES_COMPLETE){
			for(Iterator<PieceThread> it = pieceThreadList.iterator();it.hasNext();){
				PieceThread pieceThread = it.next();
				if(pieceThread.isAlive()){
					System.out.println("--------------alive");
					return false;
				}
			}
			return true; // ���ֿ��ȫ����ɣ��ҷֿ��߳�ȫ������ʱ���ж������������
		}
		return false;
	}

	/**
	 * �ж������Ƿ�ʧ��
	 */
	public boolean isTaskFailure() { //�ֿ��߳��е������߳�ȫ��ʧ��ʱ���ж�����ʧ��
		int count_failed = 0;
		int count_isAlive = 0;
		for(Iterator<PieceThread> it = pieceThreadList.iterator();it.hasNext();){
			PieceThread pieceThread = it.next();
			if(pieceThread.isFailed()){
				count_failed ++;
			}
			if(pieceThread.isAlive()){
				count_isAlive ++;
			}
		}
		//�������������У���ȫ���ֿ��߳�ʧ�ܻ�ȫ���̶߳��Ѿ����������ж�����ʧ��
		if(this.getTaskControllerStatus() == TASKCONTROLLER_RUNNING 
				&& (count_failed == this.getTask().getThreadAmount() 
						|| count_isAlive == 0)){
			return true;
		}
		return false;
	}
	
	//---------------get��set����-------------------
	
	/**
	 * ���ÿ��������Ƶ�����
	 */
	public void setTask(Task task) {
		this.task = task;
	}
	/**
	 * ��ȡ��ǰ��������״̬
	 * @return
	 */
	public int getTaskControllerStatus() {
		return TaskControllerStatus;
	}
	/**
	 * ���õ�ǰ��������״̬
	 * @param taskControllerStatus
	 */
	public void setTaskControllerStatus(int taskControllerStatus) {
		TaskControllerStatus = taskControllerStatus;
	}
	
	/**
	 * ��ȡ���������Ӧ�ķֿ������
	 * @return
	 */
	public PieceManager getPm() {
		return pm;
	}
}
