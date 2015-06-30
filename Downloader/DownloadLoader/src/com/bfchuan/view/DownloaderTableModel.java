package com.bfchuan.view;

import javax.swing.table.AbstractTableModel;

/**
 * table��ģ��
 * @author Administrator
 *
 */
public class DownloaderTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	final String[] columnNames = {"״̬","�ļ���","��С","����","�ٶ�","��ʼʱ��","��ʱ","����·��","URL","�߳���"};
	final Object[][] data = new Object[40][10];
	
	/**
	 * ���������
	 */
	public int getColumnCount() {	
		return columnNames.length;
	}

	/**
	 * �������
	 */
	public String getColumnName(int columnIndex) {	
		return columnNames[columnIndex];
	}

	/**
	 * �������
	 */
	public int getRowCount() {	
		return data.length;
	}

	/**
	 * ȡ��Ӧ��Ԫ���ֵ
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {	
		return data[rowIndex][columnIndex];
	}

	/**
	 * ���ص�Ԫ���Ƿ�ɱ༭
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Ϊ��Ԫ���趨ֵ
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		//System.out.println("table_setValue");
		data[rowIndex][columnIndex] = value;
		fireTableCellUpdated(rowIndex,columnIndex);
	}
}
