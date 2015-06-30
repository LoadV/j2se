package com.bfchuan.entrance;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;

import com.bfchuan.controller.CentralController;
import com.bfchuan.controller.MainController;
import com.bfchuan.controller.MediaController;
import com.bfchuan.util.Global;
import com.bfchuan.view.MainFrame;

/**
 * myDownloader�����
 * @author Administrator
 *
 */
public class MyDownloader {
	
	static {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(new SubstanceGreenMagicLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	 boolean packFrame = false;
//	 Logger logger;
	
	 /**
	  * ������������һ��myDownloader������
	  */
	public MyDownloader(){

		/*����myDownloader����������Ƿ��������粻�����򴴽�*/
		this.createEnvironment();
		
	//	logger = Logger.getLogger(MyDownloader.class);
		
		/*���������� */ 
		MainFrame frame = MainFrame.getInstance();
		
		/*����������������*/
		MainController mainController = new MainController();
		
		/*�������������*/
		CentralController centralController = new MediaController(mainController,frame);
		
		/*����������������������*/
		frame.addCentralContoller(centralController);
		
		/*��ʼ��������*/
		frame.init();
		
		/*��������ʼ��*/
		centralController.startInit();
		
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        
      //  logger.info(" myDownloader������");
	}
	
	/**
	 * �myDownloader���������л���,�磬�������ļ���
	 */
	public void createEnvironment(){
		
		/*myDownloader��Ŀ¼*/
		File myDownloaderHome = new File(Global.MYDOWNLOADER_PATH);
		if(!myDownloaderHome.exists()){
			myDownloaderHome.mkdir();
		}
		
		/*�������ļ���*/
		File running = new File(Global.RUNNING_TASK_PATH);
		if(!running.exists()){
			running.mkdir();
		}
		
		/*��������ļ���*/
		File complete = new File(Global.COMPLETE_TASK_PATH);
		if(!complete.exists()){
			complete.mkdir();
		}
		
		/*�������ļ���*/
		File garbage = new File(Global.GARBAGE_TASK_PATH);
		if(!garbage.exists()){
			garbage.mkdir();
		}
		
		/*��־�ļ�·��*/
		File log = new File(Global.LOG_PATH);
		if(!log.exists()){
			log.mkdir();
		}
	}
	
	/**
	 * main�������
	 * @param args
	 */
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	               /* try {
	                    UIManager.setLookAndFeel(UIManager.
	                                             getSystemLookAndFeelClassName());
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                }*/

	               new MyDownloader();
	            }
	        });
	}

}
