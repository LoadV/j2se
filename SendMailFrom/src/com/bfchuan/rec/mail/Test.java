package com.bfchuan.rec.mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;


/** 
* ���Խ����ʼ�
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
public class Test {
	
    public static void main(String args[])  {  
    	try {
    		resWY();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }   
    
    //������������
    public static void resWY() throws Exception{
    	 Properties props = System.getProperties();  
         props.put("mail.smtp.host", "smtp.163.com");  
         props.put("mail.smtp.auth", "true");  
         Session session = Session.getDefaultInstance(props, null);  
         URLName urln = new URLName("pop3", "pop3.163.com", 110, null,  
                 "piaobomengxiang", "");  
         Store store = session.getStore(urln);  
         store.connect();  
         Folder folder = store.getFolder("INBOX");  
         folder.open(Folder.READ_ONLY);  
         Message message[] = folder.getMessages();  
         System.out.println("Messages's length: " + message.length);  
         ReciveMail pmm = null;  
         for (int i = 0; i < message.length; i++) {  
             System.out.println("======================");  
             pmm = new ReciveMail((MimeMessage) message[i]);  
             System.out.println("Message " + i + " subject: " + pmm.getSubject());  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " replysign: "+ pmm.getReplySign());  
             System.out.println("Message " + i + " hasRead: " + pmm.isNew());  
             System.out.println("Message " + i + "  containAttachment: "+ pmm.isContainAttach((Part) message[i]));  
             System.out.println("Message " + i + " form: " + pmm.getFrom());  
             System.out.println("Message " + i + " to: "+ pmm.getMailAddress("to"));  
             System.out.println("Message " + i + " cc: "+ pmm.getMailAddress("cc"));  
             System.out.println("Message " + i + " bcc: "+ pmm.getMailAddress("bcc"));  
             pmm.setDateFormat("yy��MM��dd�� HH:mm");  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " Message-ID: "+ pmm.getMessageId());  
             // ����ʼ�����===============  
             pmm.getMailContent((Part) message[i]);  
             System.out.println("Message " + i + " bodycontent: \r\n"  
                     + pmm.getBodyText());  
             pmm.setAttachPath("c:\\");   
             pmm.saveAttachMent((Part) message[i]);   
         }   
    }
    
    
    //����hotmail����
    public static void resHotmail() throws Exception{
    	 Properties props = System.getProperties();  
         props.put("mail.smtp.host", "pop3.live.com");  
         props.put("mail.smtp.auth", "true");  
         Session session = Session.getDefaultInstance(props, null);  
         URLName urln = new URLName("pop3", "pop3.live.com", 995, null,  
                 "piaobomengxiang", "");  
         Store store = session.getStore(urln);  
         store.connect();  
         Folder folder = store.getFolder("INBOX");  
         folder.open(Folder.READ_ONLY);  
         Message message[] = folder.getMessages();  
         System.out.println("Messages's length: " + message.length);  
         ReciveMail pmm = null;  
         for (int i = 0; i < message.length; i++) {  
             System.out.println("======================");  
             pmm = new ReciveMail((MimeMessage) message[i]);  
             System.out.println("Message " + i + " subject: " + pmm.getSubject());  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " replysign: "+ pmm.getReplySign());  
             System.out.println("Message " + i + " hasRead: " + pmm.isNew());  
             System.out.println("Message " + i + "  containAttachment: "+ pmm.isContainAttach((Part) message[i]));  
             System.out.println("Message " + i + " form: " + pmm.getFrom());  
             System.out.println("Message " + i + " to: "+ pmm.getMailAddress("to"));  
             System.out.println("Message " + i + " cc: "+ pmm.getMailAddress("cc"));  
             System.out.println("Message " + i + " bcc: "+ pmm.getMailAddress("bcc"));  
             pmm.setDateFormat("yy��MM��dd�� HH:mm");  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " Message-ID: "+ pmm.getMessageId());  
             // ����ʼ�����===============  
             pmm.getMailContent((Part) message[i]);  
             System.out.println("Message " + i + " bodycontent: \r\n"  
                     + pmm.getBodyText());  
             pmm.setAttachPath("c:\\");   
             pmm.saveAttachMent((Part) message[i]);   
         }   
    }
    
    //����gmail����
    public static void resGmail() throws Exception{
    	 Properties props = System.getProperties();  
         props.put("mail.smtp.host", "pop.gmail.com");  
         props.put("mail.smtp.auth", "true");  
         Session session = Session.getDefaultInstance(props, null);  
         URLName urln = new URLName("pop3", "pop.gmail.com", 995, null,  
                 "piaobomengxiang", "");  
         Store store = session.getStore(urln);  
         store.connect();  
         Folder folder = store.getFolder("INBOX");  
         folder.open(Folder.READ_ONLY);  
         Message message[] = folder.getMessages();  
         System.out.println("Messages's length: " + message.length);  
         ReciveMail pmm = null;  
         for (int i = 0; i < message.length; i++) {  
             System.out.println("======================");  
             pmm = new ReciveMail((MimeMessage) message[i]);  
             System.out.println("Message " + i + " subject: " + pmm.getSubject());  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " replysign: "+ pmm.getReplySign());  
             System.out.println("Message " + i + " hasRead: " + pmm.isNew());  
             System.out.println("Message " + i + "  containAttachment: "+ pmm.isContainAttach((Part) message[i]));  
             System.out.println("Message " + i + " form: " + pmm.getFrom());  
             System.out.println("Message " + i + " to: "+ pmm.getMailAddress("to"));  
             System.out.println("Message " + i + " cc: "+ pmm.getMailAddress("cc"));  
             System.out.println("Message " + i + " bcc: "+ pmm.getMailAddress("bcc"));  
             pmm.setDateFormat("yy��MM��dd�� HH:mm");  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " Message-ID: "+ pmm.getMessageId());  
             // ����ʼ�����===============  
             pmm.getMailContent((Part) message[i]);  
             System.out.println("Message " + i + " bodycontent: \r\n"  
                     + pmm.getBodyText());  
             pmm.setAttachPath("c:\\");   
             pmm.saveAttachMent((Part) message[i]);   
         }   
    }
    
    
    //����QQ����
    public static void resQQ() throws Exception{
    	 Properties props = System.getProperties();  
         props.put("mail.smtp.host", "pop.qq.com");  
         props.put("mail.smtp.auth", "true");  
         Session session = Session.getDefaultInstance(props, null);  
         URLName urln = new URLName("pop3", "pop.qq.com", 110, null,  
                 "499199747", "wawlplr&bfc");  
         Store store = session.getStore(urln);  
         store.connect();  
         Folder folder = store.getFolder("INBOX");  
         folder.open(Folder.READ_ONLY);  
         Message message[] = folder.getMessages();  
         System.out.println("Messages's length: " + message.length);  
         ReciveMail pmm = null;  
         for (int i = 0; i < message.length; i++) {  
             System.out.println("======================");  
             pmm = new ReciveMail((MimeMessage) message[i]);  
             System.out.println("Message " + i + " subject: " + pmm.getSubject());  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " replysign: "+ pmm.getReplySign());  
             System.out.println("Message " + i + " hasRead: " + pmm.isNew());  
             System.out.println("Message " + i + "  containAttachment: "+ pmm.isContainAttach((Part) message[i]));  
             System.out.println("Message " + i + " form: " + pmm.getFrom());  
             System.out.println("Message " + i + " to: "+ pmm.getMailAddress("to"));  
             System.out.println("Message " + i + " cc: "+ pmm.getMailAddress("cc"));  
             System.out.println("Message " + i + " bcc: "+ pmm.getMailAddress("bcc"));  
             pmm.setDateFormat("yy��MM��dd�� HH:mm");  
             System.out.println("Message " + i + " sentdate: "+ pmm.getSentDate());  
             System.out.println("Message " + i + " Message-ID: "+ pmm.getMessageId());  
             // ����ʼ�����===============  
             pmm.getMailContent((Part) message[i]);  
             System.out.println("Message " + i + " bodycontent: \r\n"  
                     + pmm.getBodyText());  
             pmm.setAttachPath("c:\\");   
             pmm.saveAttachMent((Part) message[i]);   
         }   
    }

}
