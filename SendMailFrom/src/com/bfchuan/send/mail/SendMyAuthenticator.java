package com.bfchuan.send.mail;
import javax.mail.*;
  


/** 
* �����ʼ���Ҫʹ�õĻ�����Ϣ 
* ����:Leonidas
* ʱ��:2010-2-4
* �汾:1.0
* ����:�����ʼ���Ҫʹ�õĻ�����Ϣ 
* piaobomengxiang@163.com
*/ 
public class SendMyAuthenticator extends Authenticator{
	String userName=null;
	String password=null;
	 
	public SendMyAuthenticator(){
	}
	public SendMyAuthenticator(String username, String password) { 
		this.userName = username; 
		this.password = password; 
	} 
	@Override
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
 
