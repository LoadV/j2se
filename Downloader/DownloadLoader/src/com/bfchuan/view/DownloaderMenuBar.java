package com.bfchuan.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.bfchuan.controller.CentralController;


/**
 *  ��������menuBar��
 * @author Administrator
 *
 */
public class DownloaderMenuBar extends JMenuBar{
	
	private static final long serialVersionUID = 1L; 
	/**
	 * ���������
	 */
	CentralController centralController;
	private JMenu menuTask;
	private JMenu menuHelp;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemIntoduce;
	private JMenuItem menuItemNewTask;
	/**
	 * ������menuBar�Ĺ��췽��
	 */
	public DownloaderMenuBar(){
		init();
	}
	
	/**
	 * ��ʼ��
	 */
	private void init(){

		/* ��һ���˵��� */
		menuTask = new JMenu("�� ��");
		menuItemExit = new JMenuItem("�˳�����");
		menuItemNewTask = new JMenuItem("�½�����");
		menuTask.add(menuItemNewTask);
		menuTask.add(menuItemExit);
		this.add(menuTask);
		
		/* �ڶ����˵��� */
		menuHelp = new JMenu("�� ��");
		menuItemAbout =  new JMenuItem("����");
		menuItemIntoduce = new JMenuItem("���ܽ���");
		menuHelp.add(menuItemAbout);
		menuHelp.add(menuItemIntoduce);
		this.add(menuHelp);
		
		/*ΪԪ����Ӽ���*/
		this.addListeners();
		
		//���ñ߿���ʽ
		this.setBorder(BorderFactory.createEtchedBorder(0));
	}
	
		
	/**
	 * ������������
	 * @param centralController
	 */
	public void addCentralContoller(CentralController centralController){
		if(this.centralController == null){
			this.centralController = centralController;
		}
	}
	
	/**
	 * ΪmenuBar�еĸ���Ԫ�������Ӧ����
	 */
	private void addListeners(){
		
		/*�½�����Ĳ˵���*/
		this.getMenuItemNewTask().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.newTask();
					}	
				}
		);	
		
		/*�˳�����Ĳ˵���*/
		this.getMenuItemExit().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.exit();
					}	
				}
		);	
		
		/*���ڲ˵���*/
		this.getMenuItemAbout().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.showAboatMsg();
					}	
				}
		);	
		
		/*���ܽ��ܲ˵���*/
		this.getMenuItemIntoduce().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						centralController.showIntroduce();
					}	
				}
		);
	}


	//-----------��������Ӧ��get����-------------------
	
	public JMenuItem getMenuItemExit() {
		return menuItemExit;
	}

	public JMenuItem getMenuItemAbout() {
		return menuItemAbout;
	}


	public JMenuItem getMenuItemIntoduce() {
		return menuItemIntoduce;
	}

	public JMenuItem getMenuItemNewTask() {
		return menuItemNewTask;
	}

}
