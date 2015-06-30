package com.bfchuan.downloader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bfchuan.entities.FilePiece;
import com.bfchuan.entities.Task;
import com.bfchuan.util.Global;

/**
 * �ֿ�����ߣ����ڹ�������ķֿ�������Ϣ
 * @author Administrator
 *
 */
public class PieceManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ÿ���ļ�ȱʡʱ����Ϊ20��
	 */
	public static final int PIECE_AMOUNT = Global.PIECES_SUM;  
	/**
	 * ȫ�����
	 */
	public static final int PIECES_COMPLETE = 0;
	/**
	 * �޿��п飬��δ���
	 */
	public static final int PIECES_NO_LEISURE = 1;
	/**
	 * �п���
	 */
	public static final int PIECES_LEISURE = 2;
	
	private int piecesPoolStatus = PIECES_LEISURE;
	

	Task task = null;
	
	List<FilePiece> pieces;
	
	/**
	 * �вι�����������Task�����PieceManager�����ڹ����Task�ķֿ���Ϣ��һ��Task��Ӧһ��PieceManager
	 * @param task
	 */
	public PieceManager(Task task){
		this.task = task;
		if(task.getStatus() == Task.STATE_NEW){ //�������½��ģ����Ȼ����ļ��ֿ�
			pieces = splitFile();
		}else{  //�������½��ģ�֮ǰ�Ѿ����й��ֿ飬����Ҫ�ٷ�
			
		}
	}
	
	/**
	 * ��task���ļ��ֳ�PIECE_AMOUNT��
	 */
	public List<FilePiece> splitFile(){
		List<FilePiece> pieces = new ArrayList<FilePiece>(PIECE_AMOUNT);
		long fileSize = task.getFileSize();  //����ļ�����
		long startPoint = 0;	//
		long endPoint = 0;
		long pieceLength = fileSize/PIECE_AMOUNT;
		for(int i=0;i < PIECE_AMOUNT; i++){
			startPoint = endPoint;       //��ǰ�ݵ���ʼ������һ�ݵĽ�����
			endPoint = startPoint + pieceLength;//��ǰ�ݵĽ����������ʼ��+ÿ�ݳ���
			if(i == PIECE_AMOUNT - 1){ //���һ��
				endPoint = fileSize;   //���һ�ݵĽ���������ļ�����
			}
			FilePiece piece = new FilePiece();
			piece.setStartPoint(startPoint);
			piece.setEndPoint(endPoint);
			piece.setPieceSize(fileSize);
			piece.setStatus(FilePiece.PIECE_LEISURE);   //ÿ���ֿ�ĳ�ʼ״̬��Ϊ�����С�
			piece.setPieceName("��" + i + "��");
			pieces.add(piece);   //��ӵ��ֿ����
		}
		return pieces;
	}
	
	/**
	 * ����ֿ飬�˷������߳�ͬ����
	 * @return
	 */
	public synchronized FilePiece assignPiece(){
		System.out.println("assignPiece");
		int flag_complete = 0;
		int flag_leisure = 0;
		for(Iterator<FilePiece> it = pieces.iterator();it.hasNext();){//ͳ�Ʒֿ��������Ϳ�����
			FilePiece piece = it.next();
			if(piece.getStatus() == FilePiece.PIECE_COMPLETE){//���
				flag_complete ++;
			}else
			if(piece.getStatus() == FilePiece.PIECE_LEISURE){
				flag_leisure ++;
			}
		}
		if(flag_leisure == 0 && flag_complete != PieceManager.PIECE_AMOUNT){//δ�����꣬���޿���
			this.setPiecesPoolStatus(PIECES_NO_LEISURE);
			System.out.println("δ�����꣬���޿���");
		}else
		if(flag_complete == PieceManager.PIECE_AMOUNT){//ȫ��������
			this.setPiecesPoolStatus(PIECES_COMPLETE);
			System.out.println("ȫ��������");
		}else{
			for(Iterator<FilePiece> it = pieces.iterator();it.hasNext();){//ͳ�Ʒֿ��������Ϳ�����
				FilePiece piece = it.next();
				if(piece.getStatus() == FilePiece.PIECE_LEISURE){
					piece.setStatus(FilePiece.PIECE_BUSY);   //�˿鱻�ֳ�����������Ϊæµ״̬
					System.out.println("assignPiece_complete");
					return piece;
				}
			}
		}
		System.out.println("assignPiece_complete_null");
		return null;
	}
	
	
	//----------------get��set-----------------

	/**
	 * ��õ�ǰ�ֿ�ص�״̬
	 */
	public int getPiecesPoolStatus() {
		return piecesPoolStatus;
	}

	/**
	 * ���õ�ǰ�ֿ�ص�״̬
	 * @param piecesPoolStatus
	 */
	public void setPiecesPoolStatus(int piecesPoolStatus) {
		this.piecesPoolStatus = piecesPoolStatus;
	}
	
	/**
	 * ��ù����߹��������
	 * @return
	 */
	public Task getTask() {
		return task;
	}
/**
 * ���ù����߹��������
 * @param task
 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * ��ù����߹���ķֿ�List
	 * @return
	 */
	public List<FilePiece> getPieces() {
		return pieces;
	}
}
