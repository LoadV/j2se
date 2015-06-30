package com.bfchuan.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.bfchuan.controller.TaskController;
import com.bfchuan.downloader.PieceManager;
import com.bfchuan.entities.FilePiece;
import com.bfchuan.entities.Task;
import com.bfchuan.entities.TaskShowBean;
import com.bfchuan.util.TaskUtil;

/**
 * ��Ϣ��ʾ����
 * @author Administrator
 *
 */
public class DownloaderMsgArea extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JTextArea msgArea;
	/**
	 * ��ģ����ʾList��ģ��
	 */
	private DefaultListModel pieceListModel;
	private JList pieceList;
	/**
	 * ��������������ʾ������
	 */
    TaskController taskController;
	
	public DownloaderMsgArea(){
		init();
	}
	
	/**
	 * ��ʼ��
	 */
	public void init(){
		tabbedPane = new JTabbedPane();
		
		/*��Ϣ�ı���*/
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(msgArea);
		
		/*�ֿ���ʾ��*/
		pieceListModel = new DefaultListModel();
		pieceList = new JList(pieceListModel);
		pieceList.setCellRenderer(new PieceListCellRenderer());
		//jsp2.getViewport().setView(list);
		pieceList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		pieceList.setVisibleRowCount(1);
		JScrollPane scrollPane2 = new JScrollPane(pieceList);
		
		tabbedPane.add(scrollPane,"������Ϣ");
		tabbedPane.add(scrollPane2,"�ֿ�������Ϣ");
		
		this.setLayout(new BorderLayout());
		this.add(tabbedPane,"Center");
	}
	
	/**
	 * ��ʾ������Ϣ
	 */
	public void showMsg(){
		StringBuilder sb = new StringBuilder();
		if(taskController == null){
			sb.append("��ӭʹ��Ѹ��������");
		}else{
			Task task = taskController.getTask();
			TaskShowBean tsb = TaskUtil.TaskToShowTask(task);
			sb.append("�ļ�����" + tsb.getFileName());
			sb.append("\n");
			sb.append("״̬��" + tsb.getStatus());
			sb.append("\n");
			sb.append("�ļ���С��" + tsb.getFileSize());
			sb.append("\n");
			sb.append("���ȣ�" + tsb.getProgressRate());
			sb.append("\n");
			sb.append("�ٶȣ�" + tsb.getSpeed());
			sb.append("\n");
			sb.append("��ʼʱ�䣺" + tsb.getBeginTime());
			sb.append("\n");
			sb.append("���ʱ�䣺" + tsb.getEndTime());
			sb.append("\n");
			sb.append("��ʱ��" + tsb.getTakesTime());
			sb.append("\n");
			sb.append("����·����" + tsb.getSavePath());
			sb.append("\n");
			sb.append("��ԴURL��" + tsb.getUrl());
			sb.append("\n");
			sb.append("�����߳�����" + tsb.getThreadSum());
			sb.append("\n");
		}	
		this.msgArea.setText(sb.toString());
	}
	
	/**
	 * ��ʾ�ֿ�������Ϣ
	 */
	public void showPieceList(){
		
		if(taskController == null){
			pieceListModel.clear();
			return;
		}
		/*��ȡ����*/
		Task task = taskController.getTask();
		if(task.getStatus() == Task.STATE_COMPLETED || task.getStatus() == Task.STATE_FAILED){
			pieceListModel.clear();
			return;
		}
		/*��ȡ�ֿ������*/
		PieceManager pm =  taskController.getPm();
		if(pm == null){
			pieceListModel.clear();
			return;
		}
		/* ��ȡ�ֿ���Ϣ */
		List<FilePiece> pieces = pm.getPieces();
		if(pieces == null){
			pieceListModel.clear();
			return;
		}
		int len = pieces.size();
		for(int i=0;i<len;i++){//ѭ�����зֿ�
			if(pieceListModel.getSize() < len){//�ֿ���ʾ�ĳ�ʼ����δ���
				pieceListModel.addElement(pieces.get(i));//��ӵ�list��
			}else {//�����ѹ�������ɵ�һ�εĳ�ʼ��
				/*�Ա�list���ж�Ӧ��piece��״̬�Ƿ�ı�*/
				//if(pieces.get(i) != (FilePiece)pieceListModel.get(i)){
					pieceListModel.set(i, pieces.get(i));
				//}
				
			}
			
		}
		
	}
	
	/**
	 * �������������
	 * @param taskController
	 */
	public void setTaskController(TaskController taskController){
		this.taskController = taskController;
		this.showMsg();
		this.showPieceList();
	}
}
