package com.bfchuan.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {
	
	private static Properties properties = new Properties();

	/**
	 * �����ļ���·��(Ĭ��Ϊ��ǰĿ¼�µ� myDownloader.ini�ļ�)
	 */
	private static String CONFIG_FILE = "myDownloader.ini";
	
	/**
	 * �������汾����
	 */
	public static final String VERSION_NAME;
	
	/**
	 * ����ʱ���ļ��ֿ���<br>
	 * ȱʡֵΪ:20
	 */
	public static final int PIECES_SUM;
	
	/**
	 * �����õ�����߳���<br>
	 * ȱʡֵΪ:20
	 */
	public static final int MAX_THREADS;
	
	/**
	 * Ĭ�ϵ��������ļ�·��<br>
	 * ȱʡΪ:F:/myDownloader
	 */
	public static final String MYDOWNLOADER_PATH;
	
	/**
	 * ������������󱣴�·��
	 * ȱʡΪ:MYDOWNLOADER_PATH + "/running_task";
	 */
	public static final String RUNNING_TASK_PATH;
	
	/**
	 * �����������󱣴�·��<br>
	 * ȱʡΪ:MYDOWNLOADER_PATH + "/complete_task"
	 */
	public static final String COMPLETE_TASK_PATH;
	
	/**
	 * ������·��<br>
	 * ȱʡֵΪ��MYDOWNLOADER_PATH + "/garbage_bin"
	 */
	public static final String GARBAGE_TASK_PATH;
	
	/**
	 * ��־�ļ���·��<br>
	 * ȱʡֵ��MYDOWNLOADER_PATH + "/log"
	 */
	public static final String LOG_PATH;
	
	/**
	 * ������󱣴�ĺ�׺<br>
	 * ȱʡֵΪ��data
	 */
	public static final String TASK_OBJECT_POSTFIX;
	
	/**
	 * ����ϵͳ���ļ��ָ���<br>
	 * ȱʡΪ��/
	 */
	public static final String FILE_SEPARATOR;
	
	/**
	 * ֧�ֶ�ȡ�����ļ��Ĺ���,��̬�飬������ʱ��ʼ��
	 */
	static{
		
		InputStream inputStream = null;
		try {
			inputStream = Global.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("û�������ļ�");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Integer temp = null;
		String tempStr = null;
		/*�������汾����*/
		VERSION_NAME = (tempStr = getValue("version_name")) != null ? tempStr : "Ѹ��";
		/*����ϵͳ�ļ��ָ���*/
		FILE_SEPARATOR = (tempStr = System.getProperty("file.separator")) != null ? tempStr : "/";
		/*�ֿ���*/
		PIECES_SUM = (temp = getIntValue("pieces_sum")) != null && temp <= 100 && temp > 1 ? temp : 20;
		/*����߳���*/
		MAX_THREADS = (temp = getIntValue("max_threads")) != null && temp <= 100 && temp > 1 ? temp : 20;
		/*myDownloader����Home*/
		MYDOWNLOADER_PATH = (tempStr = getValue("myDownloader_path")) != null ? tempStr : "/opt";
		/*�������ļ���*/
		String runnning = (tempStr = getValue("running_task_fileName")) != null ? tempStr : "running_task";
		RUNNING_TASK_PATH = MYDOWNLOADER_PATH + Global.FILE_SEPARATOR + runnning;
		/*������ļ���*/
		String complete = (tempStr = getValue("complete_task_fileName")) != null ? tempStr : "complete_task";
		COMPLETE_TASK_PATH = MYDOWNLOADER_PATH + Global.FILE_SEPARATOR + complete;
		/*������*/
		String garbage = (tempStr = getValue("garbage_task_fileName")) != null ? tempStr : "garbage_bin";
		GARBAGE_TASK_PATH = MYDOWNLOADER_PATH + Global.FILE_SEPARATOR + garbage;
		/*��־�ļ���*/
		String log = (tempStr = getValue("log_path")) != null ? tempStr : "log";
		LOG_PATH = MYDOWNLOADER_PATH + Global.FILE_SEPARATOR + log;
		/*��ʱ�ļ��ĺ�׺*/
		TASK_OBJECT_POSTFIX = (tempStr = getValue("task_object_postfix")) != null ? tempStr : "data";
		
	}
	
	/**
	 * Ҫ���Ƕ������<BR>
	 * 1. û�����key��value<BR>
	 * 2. ��key û�� value
	 */
	private static Integer getIntValue(String key){
		if (key == null)
			throw new RuntimeException("key ����Ϊ��");
		try {
			return new Integer(properties.getProperty(key));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * ��ȡkey��Ӧ��ֵ���������쳣�򷵻�null
	 * @param key
	 * @return
	 */
	private static String getValue(String key) {
		try {
			return new String(properties.getProperty(key).getBytes("utf-8"));
		} catch (Exception e) {
			return null;
		}
	}	
	
}
