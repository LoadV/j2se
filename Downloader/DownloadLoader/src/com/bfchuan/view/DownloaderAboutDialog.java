package com.bfchuan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ����myDownloader�ĶԻ���
 * @author Administrator
 *
 */
public class DownloaderAboutDialog extends JDialog{

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
	public DownloaderAboutDialog(MainFrame frame){
		super(frame,"�汾��Ϣ");
		this.frame = frame;
		init();
	}
	
	/**
	 * ��ʼ��
	 */
	public void init(){
		/*��ȡ�öԻ����panel����*/
		JPanel dialogPanel = (JPanel) this.getContentPane();
		Label labelLogo = new Label();
		labelLogo.setText("LOGO");
		
		JPanel center = new JPanel(new GridLayout(3,1,0,5));
		center.add(new JLabel("�汾��Ϣ ��Ѹ��4.0"));
		center.add(new JLabel("����     ��Leonidas"));
		center.add(new JLabel("ʱ��     ��2010��2��"));
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
		
		dialogPanel.add(labelLogo,BorderLayout.CENTER);
		dialogPanel.add(center,BorderLayout.NORTH);
		dialogPanel.add(south,BorderLayout.SOUTH);
		
		/*���öԻ������������*/
		this.setSize(300, 250);// ���ô�С
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
