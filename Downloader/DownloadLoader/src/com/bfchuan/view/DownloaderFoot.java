package com.bfchuan.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * ���������²�
 * @author Administrator
 *
 */
public class DownloaderFoot extends JLabel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ������
	 */
	public DownloaderFoot(){
		super("Ѹ��:2010��V1�汾--2012��V2�汾--2013��V3�汾--2014��V4�汾---�׷���");
		init();
	}

	/**
	 * ��ʼ��
	 */
	private void init(){
		
		this.setBorder(BorderFactory.createEtchedBorder(1));// ����һ�����С����񻯡����Ч���ı߿򣬽�����ĵ�ǰ����ɫ����ͻ����ʾ����Ӱ��ʾ
		
	}
}
