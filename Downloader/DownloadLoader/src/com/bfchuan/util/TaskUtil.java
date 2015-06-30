package com.bfchuan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bfchuan.controller.TaskController;
import com.bfchuan.entities.Task;
import com.bfchuan.entities.TaskShowBean;


/**
 * ����Ĺ����࣬�ṩ���־�̬����
 * @author Administrator
 *
 */
public class TaskUtil {
	
	/**
	 * ����url����ļ���С
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	public static long getFileSize(String urlStr) throws IOException{
		/*���URL��*/
		URL  url = new URL(urlStr);
		/*�������*/
		URLConnection urlConnection = url.openConnection();// ������
		/*����*/
		urlConnection.connect();
		System.out.println (urlConnection.getContentLength());
		return urlConnection.getContentLength();
	}
	
	/**
	 * ��ȡ��Դ�ļ���ԭ�ļ���
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	public static String getRealFileName(String urlStr) throws IOException{
		/*���URL��*/
		URL  url = new URL(urlStr);
		/*�������*/
		URLConnection urlConnection = url.openConnection();// ������
		/*����*/
		urlConnection.connect();
		System.out.println(url.getFile());
		return url.getFile();	
	}
	
	/**
	 * ��ô����ļ����ĺ�׺
	 * @param fileName
	 */
	public static String getFilePostfix(String fileName){
		String postfix = fileName.split("\\.")[fileName.split("\\.").length - 1];
		System.out.println(postfix);
		return postfix;
	}
	
	/**
	 * �������������Ӧ�ļ���,�˷����������ж�task��״̬���Ա��ŵ���ͬ���ļ���
	 * @param task
	 * @param filePath
	 */
	public static void saveTasktoFile(Task task){
		try {
			String fileName = task.getFileName() + "." + Global.TASK_OBJECT_POSTFIX;
			String filePath;
			if(task.getStatus() != Task.STATE_COMPLETED ){//δ���
				filePath = Global.RUNNING_TASK_PATH;
			}else{//���
				filePath = Global.COMPLETE_TASK_PATH;
			}
			String fullName = filePath + Global.FILE_SEPARATOR + fileName;
			File fileDat = new File(fullName);
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(fileDat));
			oos.writeObject(task);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �������������������Ӧ�ļ���,�˷����������ж�task��״̬���Ա��ŵ���ͬ���ļ���
	 * @param taskController
	 */
	public static void saveTaskControllerToFile(TaskController taskController){
		System.out.println("saveTaskControllerToFile");
		try {
			String fileName = taskController.getTask().getFileName() + "." + Global.TASK_OBJECT_POSTFIX;
			String filePath;
			if(taskController.getTask().getStatus() != Task.STATE_COMPLETED ){//δ���
				filePath = Global.RUNNING_TASK_PATH;
			}else{//���
				filePath = Global.COMPLETE_TASK_PATH;
			}
			String fullName = filePath + Global.FILE_SEPARATOR + fileName;
			File fileDat = new File(fullName);
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(fileDat));
			oos.writeObject(taskController);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ָ��·���µ��ļ������л�ΪTaskController����ŵ�map��
	 */
	public static Map<TaskController, Thread> loadTaskControllersFromFile(String filePath){
		System.out.println("loadTaskControllersFromFile");
		Map<TaskController,Thread> ctrlThdMap = new HashMap<TaskController, Thread>();
		
		/*��ȡ��·���е������ļ�*/
		File previousFiles = new File(filePath);
		String[] previousFilesArray = previousFiles.list();
		
		/*��������������л�*/
		for(String previousFile : previousFilesArray){
			TaskController taskController;
			try {
				taskController = loadTaskController(filePath + Global.FILE_SEPARATOR + previousFile );
			} catch (IOException e) {
				taskController = null;
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				taskController = null;
				e.printStackTrace();
			}
			if(taskController != null){
				ctrlThdMap.put(taskController, null);
			}
		}
		return ctrlThdMap;
	}
	
	/**
	 * �����ļ������л�ΪTaskController
	 * @param fullPathName
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static TaskController loadTaskController(String fullPathName) throws IOException, ClassNotFoundException{
		System.out.println("loadTaskController");
		TaskController taskController;
		FileInputStream fis = new FileInputStream(fullPathName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		taskController = (TaskController) ois.readObject();
		
		/*�������ļ��е����񲻿�����running��running���п�������Ϊ�Ƿ��ر�����ģ����Խ������´���*/
		if(taskController.getTask().getStatus() == Task.STATE_RUNNING){
			taskController.getTask().setStatus(Task.STATE_PAUSED);
		}
		fis.close();
		ois.close();
		return taskController;
		
	}
	
	/**
	 * ����������Ϣת��Ϊ��table����ʾ����Ϣ����װ��TaskShowBean��
	 * @param task
	 * @return
	 */
	public static TaskShowBean TaskToShowTask(Task task){
		TaskShowBean tsb = new TaskShowBean();
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");          
		String temp = null; //��ʱ�ַ���
		/*����״̬*/
		switch (task.getStatus()){
			case Task.STATE_NEW :
				temp = "�½�";
				break;
			case Task.STATE_RUNNING:
				temp = "�����С�";
				break;
			case Task.STATE_PAUSED:
				temp = "��ͣ";
				break;
			case Task.STATE_COMPLETED:
				temp = "���";
				break;
			case Task.STATE_FAILED:
				temp = "ʧ��";
				break;
		}
		tsb.setStatus(temp);
		
		/*�ļ���*/
		tsb.setFileName(task.getFileName());
		
		/*�ļ���С*/
		String fileSize = fileSizeToStr(task.getFileSize());
		String loadSize = fileSizeToStr(task.getLoadSize());
		tsb.setFileSize(fileSize + "/" + loadSize);
		
		/*����*/
		float progressRate = task.getProgressRate();
		if(progressRate == 1){
			tsb.setProgressRate("100%");
		}else{
			tsb.setProgressRate(fnum.format(progressRate*100) + "%");
		}
		
		/*�ٶ�*/
		tsb.setSpeed(fnum.format(task.getAverageSpeet()) + "KB/s");
		
		/*��ʼʱ��*/
		SimpleDateFormat sf = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss�� ");
		Date sdate = new Date(task.getBeginTime());
		tsb.setBeginTime(sf.format(sdate));
		
		/*����ʱ��*/
		if(task.getStatus() == Task.STATE_COMPLETED){
			if(task.getEndTime() != 0){
				Date edate = new Date(task.getEndTime());
				tsb.setEndTime(sf.format(edate));
			}
		}else{
			tsb.setEndTime("δ���");
		}
		
		/*��ȥʱ��*/
		tsb.setTakesTime(task.getTakesTime()/1000 + "s");
		
		/*����·��*/
		tsb.setSavePath(task.getSavePath());
		
		/*url*/
		tsb.setUrl(task.getSourceUrl());
		
		/*�߳���*/
		tsb.setThreadSum(String.valueOf(task.getThreadAmount()));
		
		return tsb;
	}
	
	/**
	 * �ļ���Сת������Ӧ���ַ�����ʾ��ʽ
	 * @param fileSize
	 * @return
	 */
	public static String fileSizeToStr(long fileSize){
		DecimalFormat fnum = new DecimalFormat("##0.00");   
		String temp = null;
		if(fileSize/1024 < 1){
			temp = fileSize + "B";
		}else if(fileSize/1024 < 1024){
			temp = fileSize/1024 + "KB";
		}else if(fileSize/(1024*1024) < 1024){
			temp = fnum.format((float)fileSize/(1024*1024)) + "M";
		}else{
			temp = fnum.format((float)fileSize/(1024*1024*1024)) + "G";
		}
		return temp;
	}
	
	/**
	 * ɾ���ļ�
	 */
	public static boolean deleteFile(String fullPathName){
		File file = new File(fullPathName);
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
	
	/**
	 * ��Դ�ļ��ƶ���ָ����·����
	 * @param srcFile
	 * @param destPath
	 * @return
	 */
	 public static boolean moveFile(String srcFile, String destPath){
	        // File (or directory) to be moved
	        File file = new File(srcFile);
	        
	        // Destination directory
	        File dir = new File(destPath);
	        
	        // Move file to new directory
	        boolean success = file.renameTo(new File(dir, file.getName()));
	        
	        return success;
	    }
	 
	 /**
	  * �ж��������������������ǲ�����������
	  * @param taskController
	  * @return
	  */
	 public static boolean isTaskGarbage(TaskController taskController){
		 boolean b = false;
		 return b;
	 }
	 
	 /**
	  * ����������е���ʱ�ļ�
	  */
	 public static void deleteAllGarbageFile(){
		 String garbagePath = Global.GARBAGE_TASK_PATH;
		 String[] tempFiles = new File(garbagePath).list();
		 for(String fileName : tempFiles){
			 String fullName = garbagePath + Global.FILE_SEPARATOR + fileName;
			 File file = new File(fullName);
			 file.delete();
		 }
	 }
	 
	 /**
	  * ��ָ��·���µ��ļ�
	  * @param fullPathName
	 * @throws IOException 
	  */
	 public static void openFile(String fullPathName) throws IOException{
		
		 Runtime.getRuntime().exec("cmd /c " + fullPathName);
		// Runtime.getRuntime().exec("start " + fullPathName);
	 }
	 
	 /**
	  * ��ָ�����ļ���
	  * @param filePath
	  * @throws IOException
	  */
	 public static void openFolder(String filePath) throws IOException{
		 Runtime.getRuntime().exec("explorer.exe /n, "+ filePath );
	 }
	 
	 /**
	  * ��ȡ�ı����ݣ������ַ���
	  * @param fileName
	  * @return
	  */
	 public static String readFile(String fileName){
		 StringBuilder sb = new StringBuilder();
			try {
				FileReader fis = new FileReader(new File(fileName));
				BufferedReader br = new BufferedReader(fis);
				String tempStr;
				try {
					while((tempStr = br.readLine()) != null){
						sb.append(tempStr);
						sb.append("\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return sb.toString();
	 }
	 
	 public static void main(String[] args){
		 System.out.println(TaskUtil.readFile("introduction.txt"));
		 try {
			openFolder("F:\\home\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
