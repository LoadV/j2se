package com.bfchuan.listener;

/**
 * ��Ӧ����������ļ���
 * @author Administrator
 *
 */
public interface TaskListener {
	
	/**
	 * �߳�+1
	 */
	public void addThread();
	/**
	 * �߳�-1
	 */
	public void subThread();
	/**
	 * �������
	 */
	public void taskComplete();
	/**
	 * ������ͣ
	 */
	public void pauseTask();
	 /**
	  * ��ʼ����
	  */
	public void startTask();
	/**
	 * ���¿�ʼ����
	 */
	public void restartTask();
	/**
	 * ɾ������
	 */
	public void deleteTask();
	/**
	 * ����ʧ��
	 */
	public void taskFailed();
	/**
	 * �ж������Ƿ�ʧ��
	 * @return
	 */
	public boolean isTaskFailure();
	/**
	 * �ж������Ƿ����
	 * @return
	 */
	public boolean isTaskComplete();
	
}
