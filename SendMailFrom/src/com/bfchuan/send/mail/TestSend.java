package com.bfchuan.send.mail;


/** 
* ���Է����ʼ�
* ����:Leonidas
* piaobomengxiang@163.com
* ʱ��:2010-2-4
* �汾:1.0
* ����:���Է����ʼ� 
* �����ʹ�õķ����������������䰲ȫ��֤,��ȸ�����
* ����Ҫ��
* �������䰲ȫ��֤,��ȸ����䣬
* ����Ҫ��SendMailSenderInfo.java������
* p.put("mail.smtp.starttls.enable","true");
* ����ᱨ��
* com.sun.mail.smtp.SMTPSendFailedException: 
* 530 5.7.0 Must issue a STARTTLS command first. h2sm9729708pbj.38 - gsmtp
* 
*/ 
public class TestSend {
	
	
	public static void main(String[] args){
        //�������Ҫ�������ʼ�
		sendGmail();
	}

	//����QQ����
	public static void sendQQmail(){
		 SendMailSenderInfo mailInfo = new SendMailSenderInfo(); 
		  mailInfo.setMailServerHost("smtp.qq.com"); 
		  mailInfo.setMailServerPort("25"); 
		  mailInfo.setValidate(true); 
		  mailInfo.setUserName(""); //����
		  mailInfo.setPassword("");//������������ 
		  
		  mailInfo.setFromAddress(""); //�ĸ����䷢
		  mailInfo.setToAddress(""); //�����Ķ�
		  mailInfo.setSubject("�����������"); 
		  mailInfo.setContent("������������"); 
	        //�������Ҫ�������ʼ�
		  SendSimpleMailSender sms = new SendSimpleMailSender();
	         sms.sendTextMail(mailInfo);//���������ʽ 
	         sms.sendHtmlMail(mailInfo);//����html��ʽ
	}
	
	//������������
	public static void sendWy(){
		 SendMailSenderInfo mailInfo = new SendMailSenderInfo(); 
		  mailInfo.setMailServerHost("smtp.163.com"); 
		  mailInfo.setMailServerPort("25"); 
		  mailInfo.setValidate(true); 
		  mailInfo.setUserName(""); 
		  mailInfo.setPassword("");//������������ 
		  mailInfo.setFromAddress(""); 
		  
		  
		  mailInfo.setToAddress(""); 
		  mailInfo.setSubject("�����������"); 
		  mailInfo.setContent("������������"); 
	        //�������Ҫ�������ʼ�
		  SendSimpleMailSender sms = new SendSimpleMailSender();
	         sms.sendTextMail(mailInfo);//���������ʽ 
	         sms.sendHtmlMail(mailInfo);//����html��ʽ
	}

	
	//����HOTMAIL����
	public static void hotmail(){
		 SendMailSenderInfo mailInfo = new SendMailSenderInfo(); 
		  mailInfo.setMailServerHost("smtp.live.com"); 
		  mailInfo.setMailServerPort("25"); 
		  mailInfo.setValidate(true); 
		  mailInfo.setUserName(""); 
		  mailInfo.setPassword("");//������������ 
		  mailInfo.setFromAddress(""); 
		  
		  
		  mailInfo.setToAddress(""); 
		  mailInfo.setSubject("�����������"); 
		  mailInfo.setContent("������������"); 
	        //�������Ҫ�������ʼ�
		  SendSimpleMailSender sms = new SendSimpleMailSender();
	         sms.sendTextMail(mailInfo);//���������ʽ 
	         sms.sendHtmlMail(mailInfo);//����html��ʽ
	}
	
	//����GMAIL����
	public static void sendGmail(){
		 SendMailSenderInfo mailInfo = new SendMailSenderInfo(); 
		  mailInfo.setMailServerHost("smtp.gmail.com"); 
		  mailInfo.setMailServerPort("25"); 
		  mailInfo.setValidate(true); 
		  mailInfo.setUserName(""); 
		  mailInfo.setPassword("");//������������ 
		  mailInfo.setFromAddress(""); 
		  
		  
		  mailInfo.setToAddress(""); 
		  mailInfo.setSubject("�����������"); 
		  mailInfo.setContent("������������"); 
	        //�������Ҫ�������ʼ�
		  SendSimpleMailSender sms = new SendSimpleMailSender();
	         sms.sendTextMail(mailInfo);//���������ʽ 
	         sms.sendHtmlMail(mailInfo);//����html��ʽ
	}
}
