package com.bfchuan.listener;

import java.io.IOException;

import com.bfchuan.controller.TaskController;
import com.bfchuan.entities.Task;

/**
 * �������������ܼ���
 * @author Administrator
 *
 */
public interface DownloaderListener {
	
	/**
	 * ��ʼ�½�����׼����ȡ������Ϣ
	 */
	public void newTask();
	
	/**
	 * ���ݴ����������½�����
	 */
	public void newTask(Task task);
	
	/**
	 * ��ͣ��������������������
	 * @param taskController
	 */
	public void pauseTask(TaskController taskController);
	
	/**
	 * ���¿�ʼ��������������������
	 * @param taskController
	 */
	public void restartTask(TaskController taskController);
	
	/**
	 * �������ظ�������������������
	 * @param taskController
	 */
	public void reDownload(TaskController taskController);
	
	/**
	 * ��ʼ�������������Ķ�Ӧ����
	 * @param taskController
	 */
	public void startTask(TaskController taskController);
	
	/**
	 * ɾ������
	 * @param taskController
	 */
	public void deleteTask(TaskController taskController);
	
	/**
	 * ������ɵ��ļ�
	 */
	public void openFile(TaskController taskController) throws IOException;
	
	/**
	 * ���������ڵ��ļ���
	 * @param taskController
	 */
	public void openFolder(TaskController taskController) throws IOException;
	
	/**
	 * ���������������������ƶ���������
	 */
	public void addTaskToGarbage(TaskController taskController);
	
	/**
	 * ������������仹ԭ����
	 * @param taskController
	 */
	public void resumeTaskFromGarbage(TaskController taskController);
	
	/**
	 * ���������
	 */
	public void clearGarbageBin();
	
	/**
	 * Ϊ�����������������������߳�
	 * @param taskController
	 */
	public void addThread(TaskController taskController);
	
	/**
	 * Ϊ�������������������ȥ�߳�
	 * @param taskController
	 */
	public void subThread(TaskController taskController);
	
	
	/**
	 * ��ͣȫ���������ص�����
	 */
	public void pauseAll();
	
	/**
	 * table��ѡ��ı�
	 * @param taskController
	 */
	public void changeTableSelect(TaskController taskController);
	
	/**
	 * �л���ͼ����Ϊ�������ء�����ɡ�������
	 * @param status
	 */
	public void setViewStatus(int status);
	
	/**
	 * ��õ�ǰ��ͼ
	 */
	public int getViewStatus();
	
	/**
	 * �˳�������
	 */
	public void exit();
	
}
