package com.bfchuan.entities;

import java.io.Serializable;

/**
 * ��Դ�ļ��ֿ����ص�ʵ����
 * @author Administrator
 *
 */
public class FilePiece implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *�ֿ鴦�ڿ���״̬
	 */
	public static final int PIECE_LEISURE = 0;
	/**
	 * �ֿ鴦��æµ״̬
	 */
	public static final int PIECE_BUSY = 1;
	/**
	 * �ֿ鴦�����״̬
	 */
	public static final int PIECE_COMPLETE = 2;
	/**
	 * �ֿ��״̬,ȡֵ��Χ��0��1��2�����ֱ��Ӧ�����С�æµ����ɣ�
	 */
	private int status;
	/**
	 * �ֿ������
	 */
	private String pieceName;
	/**
	 * �ֿ���ܴ�С
	 */
	private long pieceSize;
	/**
	 * �ֿ�Ŀ�ʼ��
	 */
	private long startPoint;
	/**
	 * ��ǰ���ص��ĵ�
	 */
	private long nowPoint;
	/**
	 * �ֿ�Ľ�����
	 */
	private long endPoint;
	
	//--------------------��������Ӧ��get��set����-------------------------
	
	/**
	 * ��ȡ��ǰ��״̬
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * ���õ�ǰ���״̬
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * ��ȡ�鳤��
	 * @return
	 */
	public long getPieceSize() {
		return pieceSize;
	}
	/**
	 * ���ÿ鳤��
	 * @param pieceSize
	 */
	public void setPieceSize(long pieceSize) {
		this.pieceSize = pieceSize;
	}
	/**
	 * ��ȡ��ʼ��
	 * @return
	 */
	public long getStartPoint() {
		return startPoint;
	}
	/**
	 * ���ÿ�ʼ��
	 * @param startPoint
	 */
	public void setStartPoint(long startPoint) {
		this.startPoint = startPoint;
	}
	/**
	 * ��õ�ǰ��
	 * @return
	 */
	public long getNowPoint() {
		return nowPoint;
	}
	/**
	 * ���õ�ǰ��
	 * @param nowPoint
	 */
	public void setNowPoint(long nowPoint) {
		this.nowPoint = nowPoint;
	}
	/**
	 * ��ȡ������
	 * @return
	 */
	public long getEndPoint() {
		return endPoint;
	}
	/**
	 * ���ý�����
	 * @param endPoint
	 */
	public void setEndPoint(long endPoint) {
		this.endPoint = endPoint;
	}
	/**
	 * ��ȡ�ÿ������
	 * @return
	 */
	public String getPieceName() {
		return pieceName;
	}
	/**
	 * ���øÿ������
	 * @param pieceName
	 */
	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}
	
}
