package com.bfchuan.card;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mp3x.ctl.PlayerControl;

import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;

import com.bfchuan.music.TTMusic;

/**
 * ����������
 * @author root
 *
 */
public class TTMain extends JFrame implements ActionListener{
	
	private static PlayerControl  control =new PlayerControl();// ���ſ�����
	private static final long serialVersionUID = -8046215853787814280L;

	static {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(new SubstanceGreenMagicLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public Container container = null;// ��������
	JMenuItem start, exit, about;// ����˵���ť
	JButton landlord[]=new JButton[2];//��������ť
	JButton publishCard[]=new JButton[2];//���ư�ť
	int dizhuFlag;//������־
	int turn;
	JLabel dizhu; //����ͼ��
	List<TTCard> currentList[] =new Vector[3]; //  ��ǰ�ĳ���
	List<TTCard> playerList[] = new Vector[3]; // ����3����ұ�
	List<TTCard> lordList;//������
	TTCard card[] = new TTCard[56]; // ����54����
	JLabel time[]=new JLabel[3]; //��ʱ��
	TTTime t; //��ʱ�����̣߳�
	boolean nextPlayer=false; //ת����ɫ
	
	public TTMain(){
		new TTMusic().playerCardMusic("music/BackGround.mp3");
		Init();// ��ʼ��
		SetMenu();// �����˵� ��ť(������������,��ʱ��)
		this.setVisible(true);
		CardInit();//����
		getLord(); //�����ƿ�ʼ������
		time[1].setVisible(true);
		//�̰߳�ȫ��,�ѷ����̵߳�UI���Ʒŵ�����
		t=new TTTime(this,10);//��10��ʼ����ʱ
		t.start();
		
	}
	
	
	//music
	public void startMusic(){
		control.openSong("music/BackGround.mp3");
		control.play();
		if(control.getTotalTimeSecond() == control.playedRate()){
			
		}
	}
	
	// ������
	public void getLord(){
		//System.out.println(CardType.c0.toString());
		for(int i=0;i<2;i++)
			landlord[i].setVisible(true);
	}
	//��ʼ����
	// ����ϴ��
	public void CardInit() {
		
		int count = 1;
		//��ʼ����
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2))
					break;
				else {
					card[count] = new TTCard(this, i + "-" + j, false);
					card[count].setLocation(350, 50);
					container.add(card[count]);
					count++;
				}
			}
		}
		//����˳��
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(54)+1;
			int b=random.nextInt(54)+1;
			TTCard k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//��ʼ����
		for(int i=0;i<3;i++)
			playerList[i]=new Vector<TTCard>(); //�����
		lordList=new Vector<TTCard>();//����������
		int t=0;
		for(int i=1;i<=54;i++)
		{
			if(i>=52)//������
			{
				TTCommon.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//������
				TTCommon.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				playerList[0].add(card[i]);
				break;
			case 1:
				//��
				TTCommon.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				playerList[1].add(card[i]);
				card[i].turnFront(); //��ʾ����
				break;
			case 2:
				//�ұ����
				TTCommon.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				playerList[2].add(card[i]);
				break;
			}
			//card[i].turnFront(); //��ʾ����
			container.setComponentZOrder(card[i], 0);
		}
		//���������򣬴Ӵ�С
		for(int i=0;i<3;i++)
		{
			TTCommon.order(playerList[i]);
			TTCommon.rePosition(this,playerList[i],i);//���¶�λ
		}
		dizhu=new JLabel(new ImageIcon("images/dizhu.gif"));
		dizhu.setVisible(false);
		dizhu.setSize(40, 40);
		container.add(dizhu);
	}

	// ��ʼ������
	public void Init() {

		this.setTitle("java������");
		this.setSize(830, 620);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // ��Ļ����
		container = this.getContentPane();
		container.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(0, 112, 26)); // ����Ϊ��ɫ

	}

	// �����˵� ���ܰ�ť
	public void SetMenu() {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu game = new JMenu("��Ϸ");
		JMenu help = new JMenu("����");

		start = new JMenuItem("����Ϸ");
		exit = new JMenuItem("�˳�");
		about = new JMenuItem("����");

		start.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);

		game.add(start);
		game.add(exit);
		help.add(about);

		jMenuBar.add(game);
		jMenuBar.add(help);
		this.setJMenuBar(jMenuBar);
		
		landlord[0]=new JButton("������");
		landlord[1]=new JButton("��     ��");
		publishCard[0]= new JButton("����");
		publishCard[1]= new JButton("��Ҫ");
		for(int i=0;i<2;i++)
		{
			publishCard[i].setBounds(320+i*100, 400, 60, 20);
			landlord[i].setBounds(320+i*100, 400,75,20);
			container.add(landlord[i]);
			landlord[i].addActionListener(this);
			landlord[i].setVisible(false);
			container.add(publishCard[i]);
			publishCard[i].setVisible(false);
			publishCard[i].addActionListener(this);
		}
		for(int i=0;i<3;i++){
			time[i]=new JLabel("����ʱ:");
			time[i].setForeground(Color.red); 
			time[i].setVisible(false);
			container.add(time[i]);
		}
		time[0].setBounds(140, 230, 60, 20);
		time[1].setBounds(374, 360, 60, 20);
		time[2].setBounds(620, 230, 60, 20);
		
		for(int i=0;i<3;i++)
		{
			currentList[i]=new Vector<TTCard>();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == exit) {
			this.dispose();
		}
		if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "piaobomengxiang@gmail.com,Loenidas");
		}
		if (e.getSource() == start) {
			// this.restart();
		}
		if(e.getSource()==landlord[0])
		{
			time[1].setText("������");
			new TTMusic().playerCardMusic("music/������0.wav");
			t.isRun=false; //ʱ���ս�
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("����");
			new TTMusic().playerCardMusic("music/����.wav");
			t.isRun=false; //ʱ���ս�
		}
		//����ǲ�Ҫ
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			new TTMusic().playerCardMusic("music/buyao4.wav");
			time[1].setText("��Ҫ") ;
		}
		//����ǳ��ư�ť
		if(e.getSource()==publishCard[0])
		{
			List<TTCard> c=new Vector<TTCard>();
			//��ѡ����
			for(int i=0;i<playerList[1].size();i++)
			{
				TTCard card=playerList[1].get(i);
				if(card.clicked)
				{
					c.add(card);
				}
			}
			int flag=0;
			
			//�������������
			if(time[0].getText().equals("��Ҫ")&&time[2].getText().equals("��Ҫ"))
			{
				if(TTCommon.jugdeType(c)!=TTCardType.c0)
					flag=1;//��ʾ���Գ���
			}//����Ҹ���
			else{
				flag=TTCommon.checkCards(c,currentList,this);
			}
			//�ж��Ƿ���ϳ���
			if(flag==1)
			{
				currentList[1]=c;
				playerList[1].removeAll(currentList[1]);//�Ƴ��ߵ���
				//��λ����
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					TTCard card=currentList[1].get(i);
					TTCommon.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//�����ƺ�����������
				TTCommon.rePosition(this, playerList[1], 1);
				time[1].setVisible(false);
				this.nextPlayer=true;
			}

		}
	}

	public static void main(String args[]) {
		
			new TTMain();
		
	}

}

