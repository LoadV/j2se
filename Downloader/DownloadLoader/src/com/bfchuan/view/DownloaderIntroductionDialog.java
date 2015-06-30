package com.bfchuan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.bfchuan.util.TaskUtil;

/**
 * ���ܽ��ܶԻ���
 * @author Administrator
 *
 */
public class DownloaderIntroductionDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �˶Ի���������Frame
	 */
	MainFrame frame;
	
	/**
	 * �������������ǸöԻ������������
	 * @param frame
	 */
	public DownloaderIntroductionDialog(MainFrame frame){
		super(frame,"Ѹ�����ܽ���");
		this.frame = frame;
		init();
	}
	
	/**
	 * ��ʼ��
	 */
	public void init(){
		/*��ȡ�öԻ����panel����*/
		JPanel dialogPanel = (JPanel) this.getContentPane();
		Label labelLogo = new Label("���ܽ���");
		
		//JPanel center = new JPanel();
		JTextArea textArea = new JTextArea();
		textArea.setText(TaskUtil.readFile("introduction.txt"));
		textArea.setEditable(false);
		JScrollPane jsp = new JScrollPane(textArea);
		
	//	center.setPreferredSize(new Dimension(20,20));
		
		JPanel south = new JPanel();
		JButton btn = new JButton("ȷ��");
		btn.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						cancel();
					}
				}
				
		); 
		south.add(btn);
		
		dialogPanel.add(labelLogo,BorderLayout.NORTH);
		dialogPanel.add(jsp,BorderLayout.CENTER);
		dialogPanel.add(south,BorderLayout.SOUTH);
		
		/*���öԻ������������*/
		this.setSize(600, 450);// ���ô�С
		this.setResizable(false); // ���ɵ�����С
		this.setLocationRelativeTo(this);// ���ô˴��������ָ�������λ��
		dialogPanel.setBackground(new Color(203, 223, 245));
		
		this.setVisible(true);
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
		/*dialog��ʧ*/
		this.dispose();
	}
}
