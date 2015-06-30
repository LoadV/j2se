package com.bfchuan.entities;



import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * �ļ�����ʵ����
 * @author Administrator
 *
 */
public class FileAccess implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private RandomAccessFile raf;

	private long position;

	private String name;

	/**
	 * �����ļ�����������洢�ļ�
	 * @param name
	 */
	public FileAccess(String name) {
		this.name = name;
		try {
			raf = new RandomAccessFile(this.name, "rw");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ֽ�д���ļ�
	 * @param b
	 * @param start
	 * @param length
	 */
	public void write(byte[] b, int start, int length) {
		try {
			raf.write(b, start, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ر��ļ�
	 */
	public void close() {
		try {
			this.raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�ļ���
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * ��ȡ��ǰλ��
	 * @return
	 */
	public long getPosition() {
		return position;
	}

	/**
	 * ���õ�ǰλ��
	 * @param position
	 */
	public void setPosition(long position) {
		try {
			this.position = position;
			raf.seek(position);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
