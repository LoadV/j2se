package com.bfchuan.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bfchuan.controller.CentralController;
import com.bfchuan.entities.Task;
import com.bfchuan.util.Global;

/**
 * �½�����Ի���
 * @author Administrator
 *
 */
public class DownloaderDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;
	/**
	 * �˶Ի���������Frame
	 */
	MainFrame frame;
	/**
	 * ���������
	 */
	CentralController centralController;
	
	private DefaultComboBoxModel urlModel;
	private DefaultComboBoxModel locationModel;
	private JComboBox urlBox;
	private JComboBox locationBox;
	private JButton btnSelect;
	private JTextField txtFileName;
	private JTextField threadField;
	private JFileChooser chooser;
	private JButton btnOk;
	private JButton btnCancel;
	
	public DownloaderDialog(MainFrame frame){
		super(frame,"�½����񴰿�");
		this.frame = frame;
		init();
	}
	
	/**
	 * ��ʼ��
	 */
	public void init(){
		
		/*��ȡ�öԻ����panel����*/
		JPanel dialogPanel = (JPanel) this.getContentPane();
		
		/*���öԻ���Ĳ��ֹ����������Ի����Ϊ���ݺͰ�ť������*/
		dialogPanel.setLayout(new BorderLayout(10, 10));// ˮƽ��࣬��ֱ���
		
		/*��������ģ�������*/
		JPanel panelContent = new JPanel(new BorderLayout(10, 10));
		
		/*�����ֲ��Ĳ��ֹ�����*/
		GridLayout gridLayout = new GridLayout(4, 1, 5, 5);// �У��У�ˮƽ��࣬��ֱ���
		
		/*��������ģ������ߣ�����ʾ��ǩ*/
		JPanel contentWest = new JPanel(gridLayout);
		contentWest.add(new JLabel("���ص�ַ�� "));// ���ص�ַ��
		contentWest.add(new JLabel("����·���� "));// ����·��
		contentWest.add(new JLabel("�ļ�����"));
		contentWest.add(new JLabel("�߳����� "));
		
		/*url�ͱ����ַ��ʹ��combox����ʽ���Ƚ�ģ��*/
		urlModel = new DefaultComboBoxModel();
		locationModel = new DefaultComboBoxModel();
		/*Ĭ��Ϊ��*/
		urlModel.addElement("");// ��ָ�������ӵ�������ĩβ
		locationModel.addElement("");
		
		/*����ģ���ؽ�url��*/
		urlBox = new JComboBox(urlModel);// ����url�Ŀ�
		urlBox.setEditable(true);// �ֶοɱ༭
		
		/*��Ϊ�����ַ����Ҫ�С��������ť������Ϊ�˲��ֵ��ٽ�һ��panel�Ͳ��ֹ�����������*/
		JPanel locationPanel = new JPanel(new BorderLayout(5, 5));
	    locationBox = new JComboBox(locationModel);// ����һ�� JComboBox������ȡ�����е�ComboBoxModel ��
		locationBox.setEditable(true);// �ɱ༭
		btnSelect = new JButton("���...");// ���
		locationPanel.add(locationBox, "Center");
		locationPanel.add(btnSelect, "East");
		
		/* �������ֿ���߳�����*/
		txtFileName = new JTextField();
		threadField = new JTextField();// ���߳���
		
		/*ͬ������ǩ��Ӧ�Ŀ�ŵ�panel��*/
		JPanel center = new JPanel(gridLayout);
		center.add(urlBox);
		center.add(locationPanel);
		center.add(txtFileName);
		center.add(threadField);
		
		/*�������ģ�����װ*/
		panelContent.add(contentWest, "West");
		panelContent.add(center, "Center");
		panelContent.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));// ����һ��ռ�ÿռ䵫û�л��ƵĿձ߿�ָ���˶��ߡ����ߡ���߿��ߺ��ұ߿��ߵĿ��
		
		/*�Ի����ϱߵġ�ȷ�����͡�ȡ������ť*/
		JPanel southPanel = new JPanel(new FlowLayout(2));// ������,2Ϊ�Ҷ���
		btnOk = new JButton("ȷ��");// ȷ��
	    btnCancel = new JButton("ȡ��");// ȡ��
		southPanel.add(btnOk);
		southPanel.add(btnCancel);
		southPanel.setBorder(BorderFactory.createEtchedBorder(1));// ����һ�����С����񻯡����Ч���ı߿򣬽�����ĵ�ǰ����ɫ����ͻ����ʾ����Ӱ��ʾ
		
		/*��װdialog�����ݺͰ�ťģ��*/
		dialogPanel.add(panelContent, "Center");
		dialogPanel.add(southPanel, "South");
		
		/*���öԻ������������*/
		this.setSize(500, 220);// ���ô�С
		this.setResizable(false); // ���ɵ�����С
		this.setLocationRelativeTo(this);// ���ô˴��������ָ�������λ��
		
		/*��Ӽ���*/
		this.addListeners();
		
		/*��ʼ��ֵ����ʾ*/
		txtFileName.setText("");
		threadField.setText("5");// Ĭ��Ϊ�߳���
		locationModel.setSelectedItem("");// ����boxĬ��ֵ��Ϊ��
		urlModel.setSelectedItem("");
		this.setVisible(true);
	}
	
	/**
	 * ѡ���ļ�����·��
	 */
	public void selectFolderToStore() {
		if (chooser == null) {
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// ����JFileChooser��ֻѡ��Ŀ¼
		}
		int returnVal = chooser.showSaveDialog(this);// ����һ�� "Save File"
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String dir = chooser.getSelectedFile().getAbsolutePath();// ����ѡ�е��ļ���������ļ��ĸ�Ŀ¼���ǵ�ǰĿ¼���򽫵�ǰĿ¼����Ϊ���ļ��ĸ�Ŀ¼
			locationModel.addElement(dir);// ��ģ�͵�ĩβ�����
			locationModel.setSelectedItem(dir);// ����ѡ�����ֵ
		}
	}
	
	/**
	 * Ϊ��ť��Ӽ���
	 */
	public void addListeners(){
		/*�����ť*/
		this.getBtnSelect().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						selectFolderToStore();
					}
				}
				
		); 
		
		/*ȷ��*/
		this.getBtnOk().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						newTask();	
					}
				}	
		); 
		
		/*ȡ��*/
		this.getBtnCancel().addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						cancel();
					}
				}
				
		); 
	}
	
	/**
	 * �½�����
	 */
	public void newTask(){
			
		/*�ж��߳����Ƿ�������ȷ*/
		int threadSum;
		try{
		   threadSum = Integer.parseInt(threadField.getText().trim());// ����߳���
		   if(threadSum > Global.MAX_THREADS){
			   JOptionPane.showMessageDialog(this, "�����õ�����߳���Ϊ"+Global.MAX_THREADS+"��", "Error",0);
			return;
		   }
		   if(threadSum < 1){
			   JOptionPane.showMessageDialog(this, "�߳�����СΪ1��", "Error",0);
			   return; 
		   }
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "����д��ȷ���߳�����", "Error",0);
			return;
		} 
		/*��ȡ������������Ϣ*/
		String urlString = (String) urlBox.getSelectedItem();
		String location = (String) locationBox.getSelectedItem();// ��ñ���·��
		String fileName = (String) txtFileName.getText();
		
		if("".equals(urlString) || "".equals(location) || "".equals(fileName)){
			JOptionPane.showMessageDialog(this, "����д������Ϣ��", "Error",0);
			return;
		}
		
		/*�½�����*/
		Task task = new Task();
		task.setSourceUrl(urlString);
		task.setSavePath(location);
		task.setFileName(fileName);
		task.setThreadAmount(threadSum);
		task.setStatus(Task.STATE_NEW);// ��������Ϊ�½�
		task.setBeginTime(new Date().getTime());//���õ�ǰʱ��Ϊ����ʼʱ��
		
		/*���ÿ��������½����񷽷�*/
		this.centralController.newTask(task);
		
		/*�Ի�����ʧ*/
		this.dialogDispose();
	}

	/**
	 * ȡ�����Ի�����ʧ
	 */
	public void cancel(){
		this.dialogDispose();
	}
	
	 /**
	  * dialog��ʧ
	  */
	public void dialogDispose(){
		/*�������ڵ�dialog����*/
		this.frame.setDialog(null);
		/*dialog��ʧ*/
		this.dispose();
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
	
	public JButton getBtnSelect() {
		return btnSelect;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
}
