package com.bfchuan.listener;

import com.bfchuan.controller.TaskController;

public interface ViewListener {
	/**
	 * ����������ʱ�ĳ�ʼ��
	 */
	public void startInit();
	/**
	 * ��ʾ�����ڡ�������
	 */
	public void showAboatMsg();
	
	/**
	 * ��ʾ�����ܽ��ܡ�������
	 */
	public void showIntroduce();
	
	/**
	 * ��ʾ�����������������������Ϣ
	 * @param taskController
	 */
	public void showTaskMsg(TaskController taskController);
	
}
