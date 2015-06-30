package com.bfchuan.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.bfchuan.entities.FilePiece;

/**
 * �ֿ���ʾList����Ⱦ�������ݴ�����value״̬�ֱ���ʾ��ͬ����ɫ��
 * @author Administrator
 *
 */
public class PieceListCellRenderer implements ListCellRenderer{

	/**
	 * ��д���෽��������ֵ
	 */
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel();
		Dimension size = new Dimension(20,20);
		
		if (value != null) {
			FilePiece piece = (FilePiece) value;
			if (piece.getStatus() == FilePiece.PIECE_COMPLETE) { //���
				JPanel panelCompleted = new JPanel();
				panelCompleted.setBackground(Color.blue);
				panelCompleted.setPreferredSize(size);
				panel.add(panelCompleted);
			} else if(piece.getStatus() == FilePiece.PIECE_BUSY){//������
				JPanel panelBusy = new JPanel();
				panelBusy.setBackground(Color.green);
				panelBusy.setPreferredSize(size);
				panel.add(panelBusy);
			} else { //����
				JPanel panelLeisure = new JPanel();
				panelLeisure.setBackground(Color.gray);
				panelLeisure.setPreferredSize(size);
				panel.add(panelLeisure);
			}
		}
		return panel;
	}
	
}
