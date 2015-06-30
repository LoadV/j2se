package com.bfchuan.card;
import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.bfchuan.music.TTMusic;
public class TTCommon {

	// �ж�����
	public static TTCardType jugdeType(List<TTCard> list) {
		// ��Ϊ֮ǰ��������ԱȽϺ��ж�
		int len = list.size();
		// ����,���ӣ�3������4��һ��ը��
		if (len <= 4) { // �����һ����������ͬ��˵��ȫ����ͬ
			if (list.size() > 0
					&& TTCommon.getValue(list.get(0)) == TTCommon.getValue(list
							.get(len - 1))) {
				switch (len) {
				case 1:
					return TTCardType.c1;
				case 2:
				{
					new TTMusic().playerCardMusic("music/liandui.wav");
					return TTCardType.c2;
				}
					
				case 3:
				{
					new TTMusic().playerCardMusic("music/sandaiyidui.wav");
					return TTCardType.c3;
				}
					
				case 4:
					return TTCardType.c4;
				}
			}
			// ˫��,��Ϊ���ӷ���
			if (len == 2 && TTCommon.getColor(list.get(1)) == 5)
				return TTCardType.c2;
			// ����һ����������ͬʱ,3��1
			if (len == 4
					&& ((TTCommon.getValue(list.get(0)) == TTCommon.getValue(list
							.get(len - 2))) || TTCommon.getValue(list.get(1)) == TTCommon
							.getValue(list.get(len - 1))))
				return TTCardType.c31;
			else {
				return TTCardType.c0;
			}
		}
		// ��5������ʱ�����֣�3��2���ɻ���2˳��4��2�ȵ�
		if (len >= 5) {// ���ڰ���ͬ���������ִ���
			Card_index card_index = new Card_index();
			for (int i = 0; i < 4; i++)
				card_index.a[i] = new Vector<Integer>();
			// ����������ֳ���Ƶ��
			TTCommon.getMax(card_index, list); // a[0,1,2,3]�ֱ��ʾ�ظ�1,2,3,4�ε���
			// 3��2 -----�غ��ظ�3�ε���
			if (card_index.a[2].size() == 1 && card_index.a[1].size() == 1
					&& len == 5)
				return TTCardType.c32;
			// 4��2(��,˫)
			if (card_index.a[3].size() == 1 && len == 6)
				return TTCardType.c411;
			if (card_index.a[3].size() == 1 && card_index.a[1].size() == 2
					&& len == 8)
				return TTCardType.c422;
			// ����,��֤��������
			if ((TTCommon.getColor(list.get(0)) != 5)
					&& (card_index.a[0].size() == len)
					&& (TTCommon.getValue(list.get(0))
							- TTCommon.getValue(list.get(len - 1)) == len - 1))
				return TTCardType.c123;
			// ����
			if (card_index.a[1].size() == len / 2
					&& len % 2 == 0
					&& len / 2 >= 3
					&& (TTCommon.getValue(list.get(0))
							- TTCommon.getValue(list.get(len - 1)) == (len / 2 - 1)))
			{
				new TTMusic().playerCardMusic("music/liandui.wav");
				return TTCardType.c1122;
			}
			// �ɻ�
			if (card_index.a[2].size() == len / 3
					&& (len % 3 == 0)
					&& (TTCommon.getValue(list.get(0))
							- TTCommon.getValue(list.get(len - 1)) == (len / 3 - 1)))
			{
				new TTMusic().playerCardMusic("music/feiji.wav");
				return TTCardType.c111222;
			}
			// �ɻ���n��,n/2��
			if (card_index.a[2].size() == len / 4
					&& ((Integer) (card_index.a[2].get(len / 4 - 1))
							- (Integer) (card_index.a[2].get(0)) == len / 4 - 1))
				return TTCardType.c11122234;

			// �ɻ���n˫
			if (card_index.a[2].size() == len / 5
					&& card_index.a[2].size() == len / 5
					&& ((Integer) (card_index.a[2].get(len / 5 - 1))
							- (Integer) (card_index.a[2].get(0)) == len / 5 - 1))
				return TTCardType.c1112223344;

		}
		return TTCardType.c0;
	}

	// �ƶ�Ч���ĺ���,���ڷ���
	public static void move(TTCard card, Point from, Point to) {
		if (to.x != from.x) {
			double k = (1.0) * (to.y - from.y) / (to.x - from.x);
			double b = to.y - to.x * k;
			int flag = 0;// �ж������������ƶ�����
			if (from.x < to.x)
				flag = 20;
			else {
				flag = -20;
			}
			for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
				double y = k * i + b;// ������Ҫ�õ���ѧ�е����Ժ���

				card.setLocation(i, (int) y);
				try {
					Thread.sleep(5); // �ӳ٣����Լ�����
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// λ��У׼
		card.setLocation(to);
	}

	// ��list����
	public static void order(List<TTCard> list) {
		Collections.sort(list, new Comparator<TTCard>() {
			@Override
			public int compare(TTCard o1, TTCard o2) {
				// TODO Auto-generated method stub
				int a1 = Integer.parseInt(o1.name.substring(0, 1));// ��ɫ
				int a2 = Integer.parseInt(o2.name.substring(0, 1));
				int b1 = Integer.parseInt(o1.name.substring(2, o1.name.length()));// ��ֵ
				int b2 = Integer.parseInt(o2.name.substring(2, o2.name.length()));
				int flag = 0;
				// ��������Ļ�
				if (a1 == 5)
					b1 += 100;
				if (a1 == 5 && b1 == 1)
					b1 += 50;
				if (a2 == 5)
					b2 += 100;
				if (a2 == 5 && b2 == 1)
					b2 += 50;
				// �����A����2
				if (b1 == 1)
					b1 += 20;
				if (b2 == 1)
					b2 += 20;
				if (b1 == 2)
					b1 += 30;
				if (b2 == 2)
					b2 += 30;
				flag = b2 - b1;
				if (flag == 0)
					return a2 - a1;
				else {
					return flag;
				}
			}
		});
	}

	// ���¶�λ flag��������1 ,2 ��������
	public static void rePosition(TTMain m, List<TTCard> list, int flag) {
		Point p = new Point();
		if (flag == 0) {
			p.x = 50;
			p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
		}
		if (flag == 1) {// �ҵ����� _y=450 width=830
			p.x = (800 / 2) - (list.size() + 1) * 21 / 2;
			p.y = 450;
		}
		if (flag == 2) {
			p.x = 700;
			p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
		}
		int len = list.size();
		for (int i = 0; i < len; i++) {
			TTCard card = list.get(i);
			TTCommon.move(card, card.getLocation(), p);
			m.container.setComponentZOrder(card, 0);
			if (flag == 1)
				p.x += 21;
			else
				p.y += 15;

		}
	}

	// ������Ȩֵ�����Ƿ�������
	public static int getScore(List<TTCard> list) {
		int count = 0;
		for (int i = 0, len = list.size(); i < len; i++) {
			TTCard card = list.get(i);
			if (card.name.substring(0, 1).equals("5")) {
				// System.out.println(card.name.substring(0, 1));
				count += 5;
			}
			if (card.name.substring(2, card.name.length()).equals("2")) {
				// System.out.println(2);
				count += 2;
			}
		}
		return count;

	}

	// ���ػ�ɫ
	public static int getColor(TTCard card) {
		return Integer.parseInt(card.name.substring(0, 1));
	}

	// ����ֵ
	public static int getValue(TTCard card) {
		int i = Integer.parseInt(card.name.substring(2, card.name.length()));
		if (card.name.substring(2, card.name.length()).equals("2"))
			i += 13;
		if (card.name.substring(2, card.name.length()).equals("1"))
			i += 13;
		if (TTCommon.getColor(card) == 5)
			i += 2;// ����
		return i;
	}

	// �õ������ͬ��
	public static void getMax(Card_index card_index, List<TTCard> list) {
		int count[] = new int[14];// 1-13����һ��,�����14��
		for (int i = 0; i < 14; i++)
			count[i] = 0;
		for (int i = 0, len = list.size(); i < len; i++) {
			if (TTCommon.getColor(list.get(i)) == 5)
				count[13]++;
			else
				count[TTCommon.getValue(list.get(i)) - 1]++;
		}
		for (int i = 0; i < 14; i++) {
			switch (count[i]) {
			case 1:
				card_index.a[0].add(i + 1);
				break;
			case 2:
				card_index.a[1].add(i + 1);
				break;
			case 3:
				card_index.a[2].add(i + 1);
				break;
			case 4:
				card_index.a[3].add(i + 1);
				break;
			}
		}
	}

	// ����
	public static TTModel getModel(List<TTCard> list,int[] orders) {
		// �ȸ���һ��list
		List<TTCard> list2 = new Vector<TTCard>(list);
		TTModel model = new TTModel();
		for(int i=0;i<orders.length;i++)
			showOrders(orders[i], list2, model);
		return model;
	}

	// ������
	public static void get123(List<TTCard> list, TTModel model) {
		List<TTCard> del = new Vector<TTCard>();// Ҫɾ����Cards
		if (list.size() > 0
				&& (TTCommon.getValue(list.get(0)) < 7 || TTCommon.getValue(list
						.get(list.size() - 1)) > 10))
			return;
		if (list.size() < 5)
			return;
		// ��Ҫ�����в��ظ����ƹ�Ϊһ�࣬��ֹ3��������Ӱ��
		List<TTCard> list2 = new Vector<TTCard>();
		List<TTCard> temp = new Vector<TTCard>();
		List<Integer> integers = new Vector<Integer>();
		for (TTCard card : list2) {
			if (integers.indexOf(TTCommon.getValue(card)) < 0) {
				integers.add(TTCommon.getValue(card));
				temp.add(card);
			}
		}
		TTCommon.order(temp);
		for (int i = 0, len = temp.size(); i < len; i++) {
			int k = i;
			for (int j = i; j < len; j++) {
				if (TTCommon.getValue(temp.get(i)) - TTCommon.getValue(temp.get(j)) == j
						- i) {
					k = j;
				}
			}
			if (k - i >= 4) {
				String s = "";
				for (int j = i; j < k; j++) {
					s += temp.get(j).name + ",";
					del.add(temp.get(j));
				}
				s += temp.get(k).name;
				del.add(temp.get(k));
				model.a123.add(s);
				i = k;
			}
		}
		list.removeAll(del);
	}

	// ��˫˳
	public static void getTwoTwo(List<TTCard> list, TTModel model) {
		List<String> del = new Vector<String>();// Ҫɾ����Cards
		// ��model����Ķ�����
		List<String> l = model.a2;
		if (l.size() < 3)
			return;
		Integer s[] = new Integer[l.size()];
		for (int i = 0, len = l.size(); i < len; i++) {
			String[] name = l.get(i).split(",");
			s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
		}
		// s0,1,2,3,4 13,9,8,7,6
		for (int i = 0, len = l.size(); i < len; i++) {
			int k = i;
			for (int j = i; j < len; j++) {
				if (s[i] - s[j] == j - i)
					k = j;
			}
			if (k - i >= 2)// k=4 i=1
			{// ˵����i��k������
				String ss = "";
				for (int j = i; j < k; j++) {
					ss += l.get(j) + ",";
					del.add(l.get(j));
				}
				ss += l.get(k);
				model.a112233.add(ss);
				del.add(l.get(k));
				i = k;
			}
		}
		l.removeAll(del);
	}

	// ��ɻ�
	public static void getPlane(List<TTCard> list, TTModel model) {
		List<String> del = new Vector<String>();// Ҫɾ����Cards
		// ��model�����3����
		List<String> l = model.a3;
		if (l.size() < 2)
			return;
		Integer s[] = new Integer[l.size()];
		for (int i = 0, len = l.size(); i < len; i++) {
			String[] name = l.get(i).split(",");
			s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
		}
		for (int i = 0, len = l.size(); i < len; i++) {
			int k = i;
			for (int j = i; j < len; j++) {
				if (s[i] - s[j] == j - i)
					k = j;
			}
			if (k != i) {// ˵����i��k�Ƿɻ�
				String ss = "";
				for (int j = i; j < k; j++) {
					ss += l.get(j) + ",";
					del.add(l.get(j));
				}
				ss += l.get(k);
				model.a111222.add(ss);
				del.add(l.get(k));
				i = k;
			}
		}
		l.removeAll(del);
	}

	// ��ը��
	public static void getBoomb(List<TTCard> list, TTModel model) {
		List<TTCard> del = new Vector<TTCard>();// Ҫɾ����Cards
		if(list.size()<1)
			return;
		// ��ը
		if (list.size() >= 2 && TTCommon.getColor(list.get(0)) == 5
				&& TTCommon.getColor(list.get(1)) == 5) {
			model.a4.add(list.get(0).name + "," + list.get(1).name); // �����ּ���
			del.add(list.get(0));
			del.add(list.get(1));
		}
		// �����������ը��զ�Ȳ�
		if (TTCommon.getColor(list.get(0)) == 5
				&& TTCommon.getColor(list.get(1)) != 5) {
			del.add(list.get(0));
			model.a1.add(list.get(0).name);
		}
		list.removeAll(del);
		// һ���ը��
		for (int i = 0, len = list.size(); i < len; i++) {
			if (i + 3 < len
					&& TTCommon.getValue(list.get(i)) == TTCommon.getValue(list
							.get(i + 3))) {
				String s = list.get(i).name + ",";
				s += list.get(i + 1).name + ",";
				s += list.get(i + 2).name + ",";
				s += list.get(i + 3).name;
				model.a4.add(s);
				for (int j = i; j <= i + 3; j++)
					del.add(list.get(j));
				i = i + 3;
			}
		}
		list.removeAll(del);
	}

	// ��3��
	public static void getThree(List<TTCard> list, TTModel model) {
		List<TTCard> del = new Vector<TTCard>();// Ҫɾ����Cards
		// ����3����ͬ
		for (int i = 0, len = list.size(); i < len; i++) {
			if (i + 2 < len
					&& TTCommon.getValue(list.get(i)) == TTCommon.getValue(list
							.get(i + 2))) {
				String s = list.get(i).name + ",";
				s += list.get(i + 1).name + ",";
				s += list.get(i + 2).name;
				model.a3.add(s);
				for (int j = i; j <= i + 2; j++)
					del.add(list.get(j));
				i = i + 2;
			}
		}
		list.removeAll(del);
	}

	// �����
	public static void getTwo(List<TTCard> list, TTModel model) {
		List<TTCard> del = new Vector<TTCard>();// Ҫɾ����Cards
		// ����2����ͬ
		for (int i = 0, len = list.size(); i < len; i++) {
			if (i + 1 < len
					&& TTCommon.getValue(list.get(i)) == TTCommon.getValue(list
							.get(i + 1))) {
				String s = list.get(i).name + ",";
				s += list.get(i + 1).name;
				model.a2.add(s);
				for (int j = i; j <= i + 1; j++)
					del.add(list.get(j));
				i = i + 1;
			}
		}
		list.removeAll(del);
	}

	// ����
	public static void getSingle(List<TTCard> list, TTModel model) {
		List<TTCard> del = new Vector<TTCard>();// Ҫɾ����Cards
		// 1
		for (int i = 0, len = list.size(); i < len; i++) {
			model.a1.add(list.get(i).name);
			del.add(list.get(i));
		}
		list.removeAll(del);
	}

	// ����֮ǰ��������
	public static void hideCards(List<TTCard> list) {
		for (int i = 0, len = list.size(); i < len; i++) {
			list.get(i).setVisible(false);
		}
	}

	// ����Ƶ��Ƿ��ܳ�
	public static int checkCards(List<TTCard> c, List<TTCard>[] current,TTMain m) {
		// �ҳ���ǰ���������ĸ����Գ���,c�ǵ�ѡ����
		List<TTCard> currentlist;
		if(m.time[0].getText().equals("��Ҫ"))
		{
			currentlist=current[2];
		}
			
		else
		{
			currentlist=current[0];
		}
		TTCardType cType = TTCommon.jugdeType(c);
		TTCardType cType2=TTCommon.jugdeType(currentlist);
		// ���������ֱͬ�ӹ���
		if (cType != TTCardType.c4 && c.size() != currentlist.size())
			return 0;
		// �Ƚ��ҵĳ�������
		if (cType != TTCardType.c4&&TTCommon.jugdeType(c) != TTCommon.jugdeType(currentlist)) {

			return 0;
		}
		// �Ƚϳ������Ƿ�Ҫ��
		// ����ը��
		if (cType == TTCardType.c4) {
			if(c.size()==2)
				return 1;
			if(cType2!=TTCardType.c4)
			{
				return 1;
			}
		}

		// ����,����,3��,4ը��
		if (cType == TTCardType.c1 || cType == TTCardType.c2
				|| cType == TTCardType.c3 || cType == TTCardType.c4) {
			if (TTCommon.getValue(c.get(0)) <= TTCommon
					.getValue(currentlist.get(0))) {
				return 0;
			} else {
				return 1;
			}
		}
		// ˳��,���ӣ��ɻ���
		if (cType == TTCardType.c123 || cType == TTCardType.c1122
				|| cType == TTCardType.c111222) {
			if (TTCommon.getValue(c.get(0)) <= TTCommon
					.getValue(currentlist.get(0)))
				return 0;
			else
				return 1;
		}
		// ���ظ���������
		// 3��1,3��2 ,�ɻ�������˫,4��1,2,ֻ��Ƚϵ�һ�����У���һ�޶���
		if (cType == TTCardType.c31 || cType == TTCardType.c32
				|| cType == TTCardType.c411 || cType == TTCardType.c422
				|| cType == TTCardType.c11122234 || cType == TTCardType.c1112223344) {
			List<TTCard> a1 = TTCommon.getOrder2(c); // �ҳ�����
			List<TTCard> a2 = TTCommon.getOrder2(currentlist);// ��ǰ�����
			if (TTCommon.getValue(a1.get(0)) < TTCommon.getValue(a2.get(0)))
				return 0;
		}
		return 1;
	}

	// �����ظ���������
	public static List<TTCard> getOrder2(List<TTCard> list) {
		List<TTCard> list2 = new Vector<TTCard>(list);
		List<TTCard> list3 = new Vector<TTCard>();
		//List<Integer> list4 = new Vector<Integer>();
		int len = list2.size();
		int a[] = new int[20];
		for (int i = 0; i < 20; i++)
			a[i] = 0;
		for (int i = 0; i < len; i++) {
			a[TTCommon.getValue(list2.get(i))]++;
		}
		int max = 0;
		for (int i = 0; i < 20; i++) {
			max = 0;
			for (int j = 19; j >= 0; j--) {
				if (a[j] > a[max])
					max = j;
			}

			for (int k = 0; k < len; k++) {
				if (TTCommon.getValue(list2.get(k)) == max) {
					list3.add(list2.get(k));
				}
			}
			list2.remove(list3);
			a[max] = 0;
		}
		return list3;
	}
	//����ѭ��
	public static void showOrders(int i, List<TTCard> list, TTModel model) {
		switch (i) {
		case 1:
			TTCommon.getSingle(list, model);
			break;
		case 2:
			TTCommon.getTwo(list, model);
			TTCommon.getTwoTwo(list, model);
			break;
		case 3:
			TTCommon.getThree(list, model);
			TTCommon.getPlane(list, model);
			break;
		case 4:
			TTCommon.getBoomb(list, model);
			break;
		case 5:
			TTCommon.get123(list, model);
			break;
		}
	}
}

class Card_index {
	List a[] = new Vector[4];// ����
}