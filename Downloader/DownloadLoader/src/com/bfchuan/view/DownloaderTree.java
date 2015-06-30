package com.bfchuan.view;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.bfchuan.controller.CentralController;
import com.bfchuan.controller.MainController;

 /**
  * ���������ļ����� <br>
  * �������������ء�����ɡ�������
  * @author Administrator
  *
  */
public class DownloaderTree extends JTree{
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * ���������
	 */
	CentralController centralController;
	
	/**
	 * ����������ģ��
	 */
	DefaultTreeModel treeModel;
	
	public DownloaderTree(){
		init();
	}
	
	 /**
	  * ��ʼ��
	  */
	public void init(){
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Ѹ��������");

		
		DefaultMutableTreeNode child5 = new DefaultMutableTreeNode("�ļ�����");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("�Ѿ����");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("������");
		child5.add(child1);
		child5.add(child2);
		child5.add(child3);
		
		DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("�Ʒ�����");
		child4.add(new DefaultMutableTreeNode("��Ϊ����"));
		child4.add(new DefaultMutableTreeNode("��ţ����"));
		child4.add(new DefaultMutableTreeNode("΢������"));
		child4.add(new DefaultMutableTreeNode("�ٶ�����"));
		
		
		DefaultMutableTreeNode child6 = new DefaultMutableTreeNode("������Դ");
		DefaultMutableTreeNode child7 = new DefaultMutableTreeNode("Ӱ����Դ");
		DefaultMutableTreeNode child8 = new DefaultMutableTreeNode("��ѧ��Դ");
		DefaultMutableTreeNode child9 = new DefaultMutableTreeNode("������Դ");
		child6.add(child7);
		child6.add(child8);
		child6.add(child9);
		
		top.add(child5);
		top.add(child4);
		top.add(child6);
		
	    treeModel = new DefaultTreeModel(top);   
		this.setModel(treeModel);
		this.addListeners();
	}
	
	/**
	 * ������ļ���
	 */
	public void addListeners(){
		 this.addTreeSelectionListener(new TreeSelectionListener() {   
	          public void valueChanged(TreeSelectionEvent tse) {   
	            TreePath tp = tse.getNewLeadSelectionPath();   
	            Object o = tp.getLastPathComponent();
	             System.out.println(o);   
	             if("��������".equals(o.toString())){
	            	 centralController.setViewStatus(MainController.RUNNING_VIEW);
	             }else if("�����".equals(o.toString())){
	            	 centralController.setViewStatus(MainController.COMPLETE_VIEW);
	             }else if("������".equals(o.toString())){
	            	 centralController.setViewStatus(MainController.GARBAGEBIN_VIEW);
	             }
	          }   
	        });   

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
}
