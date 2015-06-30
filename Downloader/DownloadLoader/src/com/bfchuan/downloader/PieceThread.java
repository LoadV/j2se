package com.bfchuan.downloader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.bfchuan.entities.FileAccess;
import com.bfchuan.entities.FilePiece;

 /**
  * �ֿ������࣬�ɷֿ�����߷���ֿ�����
  * @author Administrator
  *
  */
public class PieceThread extends Thread implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PieceManager pm;

	public PieceThread(PieceManager pm) {
		this.pm = pm;
	}

	/**
	 * ��ǰ���ؿ�
	 */
	private FilePiece currentPiece;
	
	/**
	 * ���߳��Ƿ�ֹͣ�ˣ�ȱʡֵΪfalse
	 */
	private boolean isStop = false;
	
	/**
	 * �߳��Ƿ�ʧ�ܣ�ȱʡΪfalse
	 */
	private boolean isFailed = false;
	
	/**
	 * �ֿ���������ʼ����
	 */
	@Override
	public void run() {
		
		/*ѭ�����أ�ֱ���ֿ���еķֿ�ȫ���������*/
		while (pm.getPiecesPoolStatus() != PieceManager.PIECES_COMPLETE && !isStop) {
			
			/* ��ȡ���߳�Ҫ���صķֿ� */
			currentPiece = pm.assignPiece();
			
			if(currentPiece == null){//������䵽����pieceΪnull�����˳�ѭ��
				break;
			}
			
			/* ���÷ֿ��״̬����Ϊ��æµ�� */
			currentPiece.setStatus(FilePiece.PIECE_BUSY);
			
			System.out.println(Thread.currentThread() + " -- "+ currentPiece.getPieceName());
		
			try {
				/* ��ȡURL */
				URL url = new URL(pm.getTask().getSourceUrl());
						
				/*��ȡ����洢�ļ�*/
				FileAccess fileAccess = new FileAccess(pm.getTask().getSavePath()+"/"+pm.getTask().getFileName());
				
				/*��ȡhttpЭ�������*/
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				/*ͨ�����������ļ���ʼ��*/
				conn.setRequestProperty("RANGE", "bytes=" + currentPiece.getStartPoint()+ "-" +currentPiece.getEndPoint());
				
				/*��ȡ�ļ���*/
				InputStream is = null;
				BufferedInputStream bis = null;
				is = conn.getInputStream();
				bis = new BufferedInputStream(is);
				
				/*�����ֽڻ�����*/
				byte[] buffer = new byte[1024];
				
				/*���ô����ļ�����ʼ��*/
				fileAccess.setPosition(currentPiece.getStartPoint());
				
				int b = -1; //����ȡ������Դƫ�������
				
				/*���ÿ��Ƿ�����ı��*/
				boolean isPieceComplete = false;
				
				/*ѭ�����÷ֿ��������д���ļ�*/
				while((b = bis.read(buffer)) != -1 && !isPieceComplete && !isStop){
					if(currentPiece.getStartPoint() + b > currentPiece.getEndPoint()){ //��ƫ���������˸÷ֿ�ı߽�
						b = (int)(currentPiece.getEndPoint() - currentPiece.getStartPoint());
					}
					/*���������ļ�ƫ�����ӻ�����д���ļ�*/
					fileAccess.write(buffer, 0, b); 
					
					/*�������øÿ��������ʼ��*/
					currentPiece.setStartPoint(currentPiece.getStartPoint() + b);
					
					if(currentPiece.getStartPoint() == currentPiece.getEndPoint()){//���ÿ����ʼ����ڽ����㣬��ʾ�ÿ��������
						currentPiece.setStatus(FilePiece.PIECE_COMPLETE);
						System.out.println("----���"+currentPiece.getPieceName());
						isPieceComplete = true;
					}
				}
				
				/*�ر���������*/
				is.close();
				bis.close();
				fileAccess.close();
				conn.disconnect();
				
			} catch (MalformedURLException e) {
				//���߳�����ʧ�ܣ����øÿ�Ϊ����
				this.setFailed(true);
				currentPiece.setStatus(FilePiece.PIECE_LEISURE);
				e.printStackTrace();
			} catch (IOException e) {
				this.setFailed(true);
				currentPiece.setStatus(FilePiece.PIECE_LEISURE);
				e.printStackTrace();
			}	
		}
	}

	/**
	 * ��ͣ
	 */
	public void stopMe(){
		/*���õ�ǰ���ؿ�Ϊ����*/
		if(currentPiece != null){
			this.currentPiece.setStatus(FilePiece.PIECE_LEISURE);
		}
		
		/*�����߳�״̬Ϊ ֹͣ*/
		this.setStop(true);
		
		/*�жϸ��߳�*/
		this.interrupt();
	}

	
	//------------------get��set----------------
	/**
	 * ���طֿ��߳��Ƿ�ֹͣ
	 */
	public boolean isStop() {
		return isStop;
	}

	/**
	 * ���÷ֿ��̵߳�ֹͣ״̬
	 * @param isStop
	 */
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	

	/**
	 * ���طֿ��߳��Ƿ�ʧ��
	 * @return
	 */
	public boolean isFailed() {
		return isFailed;
	}

	/**
	 * ���÷ֿ��̵߳�ʧ��״̬
	 * @param isFailed
	 */
	public void setFailed(boolean isFailed) {
		this.isFailed = isFailed;
	}

}
