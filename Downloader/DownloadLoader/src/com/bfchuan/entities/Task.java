package com.bfchuan.entities;

import java.io.Serializable;

import com.bfchuan.downloader.PieceManager;
import com.bfchuan.util.Global;

/**
 * �����ʵ����
 * @author Administrator
 *
 */
public class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * �½�����
	 */
	public static final int STATE_NEW = 1;
	/**
	 * ������������
	 */
	public static final int STATE_RUNNING = 2;
	/**
	 * �������
	 */
	public static final int STATE_COMPLETED = 3;
	/**
	 * ������ͣ
	 */
	public static final int STATE_PAUSED = 4;
	/**
	 * ����ʧ��
	 */
	public static final int  STATE_FAILED = 5;
	/**
	 * �����ִ��״̬��ȡֵ��Χ��1��2��3��4��5�����ֱ��Ӧ���½������ء���ɡ���ͣ��ʧ�ܣ����״̬
	 */
	private int status;  
	/**
	 * �û�������ļ���
	 */
	private String fileName;
	/**
	 * ��Դ�ļ���ԭʼ�ļ���
	 */
	private String formerFileName;
	/**
	 * ��Դ�ļ����ܴ�С
	 */
	private long fileSize;
	/**
	 * �����صĴ�С
	 */
	private long loadSize;
	/**
	 * �ļ����صĽ���
	 */
	private float progressRate;
	/**
	 * �ļ����صļ�ʱ�ٶ�
	 */
	private float immediateSpeed;
	/**
	 * �ļ����ص�ƽ���ٶ�
	 */
	private float averageSpeet;
	/**
	 * �����ļ��Ŀ�ʼʱ��
	 */
	private long beginTime;
	/**
	 * �����ļ��Ľ���ʱ��
	 */
	private long endTime;
	/**
	 * �����ļ����ѵ�ʱ��
	 */
	private long takesTime;
	/**
	 * �����ļ��ı���·��
	 */
	private String savePath;
	/**
	 * �����ļ�����ԴURL
	 */
	private String sourceUrl;
	/**
	 * �����ļ����߳���
	 */
	private int threadAmount;
	/**
	 * �ļ��ķֿ������
	 */
	private PieceManager pm;
	
	//--------------------��������Ӧ��get��set����---------------------
	/**
	 * ��ȡ����״̬
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * ��������״̬
	 * @param status
	 */
	public void setStatus(int status) {
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
	 * ����������ļ���
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * ��ȡ�����ԭ�ļ���
	 * @return
	 */
	public String getFormerFileName() {
		return formerFileName;
	}
	/**
	 * ���������ԭ�ļ���
	 * @param formerFileName
	 */
	public void setFormerFileName(String formerFileName) {
		this.formerFileName = formerFileName;
	}
	/**
	 * ��ȡ������ļ�����
	 * @return
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * ����������ļ�����
	 * @param fileSize
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 *  ��ȡ�������ؽ���
	 * @return
	 */
	public float getProgressRate() {
		return progressRate;
	}
	/**
	 * �����������ؽ���
	 * @param progressRate
	 */
	public void setProgressRate(float progressRate) {
		this.progressRate = progressRate;
	}
	/**
	 * ��ü�ʱ�����ٶ�
	 * @return
	 */
	public float getImmediateSpeed() {
		return immediateSpeed;
	}
	/**
	 * ���ü�ʱ�����ٶ�
	 * @param immediateSpeed
	 */
	public void setImmediateSpeed(float immediateSpeed) {
		this.immediateSpeed = immediateSpeed;
	}
	/**
	 * ��ȡ����ƽ���ٶ�
	 * @return
	 */
	public float getAverageSpeet() {
		return averageSpeet;
	}
	/**
	 * ��������ƽ�������ٶ�
	 * @param averageSpeet
	 */
	public void setAverageSpeet(float averageSpeet) {
		this.averageSpeet = averageSpeet;
	}
	/**
	 * ��ȡ����ʼʱ��
	 * @return
	 */
	public long getBeginTime() {
		return beginTime;
	}
	/**
	 * ��������ʼʱ��
	 * @param beginTime
	 */
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * ��ȡ�������ʱ��
	 * @return
	 */
	public long getEndTime() {
		return endTime;
	}
	/**
	 * �����������ʱ��
	 * @param endTime
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	/**
	 * ��ȡ����ȥʱ��
	 * @return
	 */
	public long getTakesTime() {
		return takesTime;
	}
	/**
	 * ��������ȥʱ��
	 * @param takesTime
	 */
	public void setTakesTime(long takesTime) {
		this.takesTime = takesTime;
	}
	/**
	 * ��ȡ�����ļ�����·��
	 * @return
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * ���������ļ�����·��
	 * @param savePath
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * ��ȡ�����URL
	 * @return
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}
	/**
	 * ���������URL
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	/**
	 * ��ȡ���ظ�������߳�����
	 * @return
	 */
	public int getThreadAmount() {
		return threadAmount;
	}
	/**
	 * �������ظ�������߳�����
	 * @param threadAmount
	 */
	public void setThreadAmount(int threadAmount) {
		this.threadAmount = threadAmount;
	}
	/**
	 * ��ȡ�ֿ����صĹ�����
	 * @return
	 */
	public PieceManager getPm() {
		return pm;
	}
	/**
	 * ���÷ֿ����صĹ�����
	 * @param pm
	 */
	public void setPm(PieceManager pm) {
		this.pm = pm;
	}
	/**
	 * ��ȡ�������ļ��Ĵ�С
	 * @return
	 */
	public long getLoadSize() {
		return loadSize;
	}
	/**
	 * �����������ļ��Ĵ�С
	 * @param loadSize
	 */
	public void setLoadSize(long loadSize) {
		this.loadSize = loadSize;
	}
	/**
	 * ��������ļ�������·������������
	 * @return
	 */
	public String getFilePathName(){
		return this.savePath + Global.FILE_SEPARATOR + this.fileName;
	}
}
