package com.bfchuan.ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;


/**
 * ��¼����
 * ���ߣ�Leonidas 
 * piaobomengxiang@163.com
 * ʱ�䣺2013-9-13
 * �汾��1.0
 * ��������¼�����棬�õ�¼����ΪTT��ֲ����
 * ����Ϊ��ϰ����δ���޸�
 * ��Ϊ��Ϊ���ԡ�
 * ��¼�û�����������@�Լ�����Ķ���
 */
public class LoginFrame extends JFrame implements ActionListener{
	
	private static LoginFrame loginFrame = null;
	private static final long serialVersionUID = -2302380191880949014L;
	
	static {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(new SubstanceGreenMagicLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public JComboBox<Object> iconList = new JComboBox<Object>(
			new Integer[]{1, 2, 3, 4, 5 , 6, 7, 8 ,9 ,10});
	
	
	private FindChatSimThread activity;
	private Container contentPane;
	private JProgressBar jpb;
	private JPanel paneTop = new JPanel();
	private JPanel paneMid = new JPanel();
	private JPanel paneBut = new JPanel();
	private JLabel lblName = new JLabel();
	private JLabel lblPwd = new JLabel();
	private JLabel lblApply = new JLabel();
	private JLabel lblForget = new JLabel();
	private JLabel lblModel = new JLabel();
	private Timer activityMonitor;
	private JTextField txtName = new JTextField(15);
	private JPasswordField txtPwd = new JPasswordField(15);

	private JComboBox<String> cmb = new JComboBox<String> ();
	private JCheckBox chk = new JCheckBox();

	private JButton btnKill = new JButton("��ɱľ��");
	private JButton btnSet = new JButton("����");
	private JButton btnLogin = new JButton("��¼");

	
	public static LoginFrame  getInstance(){
		if(loginFrame == null){
			return new LoginFrame();
		}else{
			return loginFrame;
		}
	}
	
	
	
	public LoginFrame() {
		//�޸�LOGO
		Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("/home/java/Work/Communication/icon/icon.gif");
		this.setIconImage(image);
		
		btnKill.addActionListener(this);
		btnLogin.addActionListener(this);
		btnSet.addActionListener(this);
		
		//�ڴ�ִ�м�飬���ݺ�׺�����߽���
		//����׺��Ϊ�걾�ϴ�������������MD5�����
		//�����Ʋ�ɱ
		//�ڴ�Ϊ�˼�
		//ʡ��
		activityMonitor = new Timer(100, new ActionListener() {// ÿ0.5��ִ��һ��
					public void actionPerformed(ActionEvent e) {// ���¶��������¼������߳������У�ʮ�ְ�ȫ
						int current = activity.getCurrent();// �õ��̵߳ĵ�ǰ����

						jpb.setValue(current);// ���½�������ֵ

						if (current == activity.getTarget()) {// �������Ŀ��ֵ
							JOptionPane.showMessageDialog(null,"����ϵͳ�ܰ�ȫ");
							activityMonitor.stop();// ��ֹ��ʱ��
							btnKill.setEnabled(true);// ���ť
						}
					}
				});

		jpb = new JProgressBar();
		jpb.setStringPainted(true);
		lblName.setText("�����ʺ�:");
		lblApply.setText("��������");
		lblPwd.setText("��������:");
		lblForget.setText("�������� ?");
		lblModel.setText("״̬:");

		String[] s1 = { "����", "����", "æµ" };
		cmb.addItem(s1[0]);
		cmb.addItem(s1[1]);
		cmb.addItem(s1[2]);

		chk.setText("�Զ���¼");
		paneMid.add(lblName);
		paneMid.add(txtName);
		paneMid.add(lblApply);
		paneMid.add(lblPwd);
		paneMid.add(txtPwd);
		paneMid.add(lblForget);
		paneMid.add(chk);
		paneMid.add(lblModel);
		paneMid.add(cmb);
		paneMid.add(jpb);

		paneBut.add(btnKill);
		paneBut.add(btnSet);
		paneBut.add(btnLogin);

		contentPane = this.getContentPane();

		contentPane.add(paneTop, BorderLayout.NORTH);
		contentPane.add(paneMid, BorderLayout.CENTER);
		contentPane.add(paneBut, BorderLayout.SOUTH);

		setTitle("TT����2013��¼");
		setSize(330, 200);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		LoginFrame.getInstance();
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnKill){
				jpb.setVisible(true);
				jpb.setMaximum(1000);// ���ý����������ֵ
				activity = new FindChatSimThread(1000);
				activity.start();// �����߳�
				activityMonitor.start();// ������ʱ��
				btnKill.setEnabled(false);// ��ֹ��ť
		}
		
		if(obj == btnSet){
			
		}
		if(obj == btnLogin){
			MainFrame.tbUser.setName(txtName.getText().trim());
			MainFrame.tbUser.setPass(txtPwd.getText().trim());
			this.dispose();
			new MainFrame().getMail();;
		}
	}
}
