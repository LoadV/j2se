package com.bfchuan.entities;

/**
 * ��ʾ��table֮�ϵ�ʵ���࣬�����װ��ʾ��Ϣ
 * @author Administrator
 *
 */
public class TaskShowBean {
	
	private String status;
	private String fileName;
	private String fileSize;
	private String progressRate;
	private String speed;
	private String beginTime;
	private String endTime;
	private String takesTime;
	private String savePath;
	private String url;
	private String threadSum;
	
	 /**
	  * ��ȡ�������ʾ״̬
	  * @return
	  */
	public String getStatus() {
		return status;
	}
	/**
	 * �����������ʾ״̬
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * ��ȡ������ļ���
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 *  ����������ļ���
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * ��ȡ�������ʾ��С
	 * @return
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * �����������ʾ��С
	 * @param fileSize
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * ��ȡ��������ؽ��� ��%��
	 * @return
	 */
	public String getProgressRate() {
		return progressRate;
	}
	/**
	 * ������������ؽ���
	 * @param progressRate
	 */
	public void setProgressRate(String progressRate) {
		this.progressRate = progressRate;
	}
	/**
	 * ��ȡ����������ٶ�
	 * @return
	 */
	public String getSpeed() {
		return speed;
	}
	/**
	 * ��������������ٶ�
	 * @param speed
	 */
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	/**
	 *  ��ȡ����Ŀ�ʼʱ��
	 * @return
	 */
	public String getBeginTime() {
		return beginTime;
	}
	/**
	 * ��������Ŀ�ʼʱ��
	 * @param beginTime
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * ��ȡ��������غ�ʱ
	 * @return
	 */
	public String getTakesTime() {
		return takesTime;
	}
	/**
	 * ������������غ�ʱ
	 * @param takesTime
	 */
	public void setTakesTime(String takesTime) {
		this.takesTime = takesTime;
	}
	/**
	 *  ��ȡ����ı���·��
	 * @return
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * ��������ı���·��
	 * @param savePath
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * ��ȡ�����URL
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * ���������URL
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * ��ȡ����������߳�����
	 * @return
	 */
	public String getThreadSum() {
		return threadSum;
	}
	/**
	 * ��������������߳�����
	 * @param threadSum
	 */
	public void setThreadSum(String threadSum) {
		this.threadSum = threadSum;
	}
	 /**
	  *  �������Ľ���ʱ��
	  * @return
	  */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * ��������Ľ���ʱ��
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
