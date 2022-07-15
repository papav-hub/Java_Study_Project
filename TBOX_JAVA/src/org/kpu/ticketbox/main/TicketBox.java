package org.kpu.ticketbox.main;

import java.io.*;
import java.util.*;

import org.kpu.ticketbox.cinema.*;
import org.kpu.ticketbox.main.*;
import org.kpu.ticketbox.payment.Receipt;
import org.kpu.ticketbox.util.*;

public class TicketBox {
	
	public static final String ADMIN_PASSWORD = "admin";
	
	void managerMode() throws IOException {
		
		System.out.println("-------------");
		System.out.println("������ ���");
		System.out.println("-------------");
		
		double a;
		Screen s = famillyScreen;
		a = Statistics.sum(s.getReceiptMap());
		System.out.println("���� ��ȭ�� ���� �Ѿ� : " + a);
		s = animationScreen;
		a = Statistics.sum(s.getReceiptMap());
		System.out.println("�ִϸ��̼� ��ȭ�� ���� �Ѿ� : " + a);
		s = premiumScreen;
		a = Statistics.sum(s.getReceiptMap());
		System.out.println("�����̾� ��ȭ�� ���� �Ѿ� : " + a);
		
		
		BufferedWriter file = new BufferedWriter(new FileWriter("c:\\Temp\\tBoxReceipt.txt"));
		file.close();
		
		s = famillyScreen;
		BackupWriter.backupFile("c:\\Temp\\tBoxReceipt.txt", s.getReceiptMap());
		
		s = animationScreen;
		BackupWriter.backupFile("c:\\Temp\\tBoxReceipt.txt", s.getReceiptMap());
		
		s = premiumScreen;
		BackupWriter.backupFile("c:\\Temp\\tBoxReceipt.txt", s.getReceiptMap());
		
		FileReader fin = new FileReader("c:\\Temp\\tBoxReceipt.txt");
		int c;
		int number = 0;
		while((c=fin.read())!=-1) {
			if((char)c==',') {
				number ++;
			}
		}		
		System.out.println("��ȭ�� �� Ƽ�� �Ǹŷ� : " + number/5);
		System.out.println("c:\\Temp\\tBoxReceipt.txt ��� �����մϴ�.");
		System.out.println("���� ��ȭ�� ���� ��� �Ϸ�");
		System.out.println("�ִϸ��̼� ��ȭ�� ���� ��� �Ϸ�");
		System.out.println("�����̾� ��ȭ�� ���� ��� �Ϸ�");
		
		
	}

	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	
	Scanner scan = new Scanner(System.in);
	
	public void initScreen() {
		famillyScreen = new FamillyScreen("������ ���ݸ� ����", "�Ŵ� ���ݸ� ������ �����ϰ� �� �ҳ� ���� ��Ŷ", 8000, 10, 10);
		animationScreen = new AnimationScreen("���� ��޺� ŰŰ", "���� �߽��� ŰŰ�� �뵵�ÿ� ����", 10000, 10, 10);
		premiumScreen = new PremiumScreen("����", "ȭ���� ȥ�� ��ġ�� ���� ����� ��ũ ��Ʈ���� ������ ���� ����", 25000, 5, 5);
	}
	

	public Screen selectScreen() throws IOException {
		
		System.out.println("-------------");
		System.out.println("�󿵰� ���� ���θ޴�");
		System.out.println("-------------");
		System.out.println("1. ���� ��ȭ 1��");
		System.out.println("2. �ִϸ��̼� ��ȭ 2��");
		System.out.println("3. �����̾� ��ȭ 3�� (Ŀ��, ���� ����)");
		System.out.println("9. ������ �޴�");
		System.out.println("  ����(1~3, 9)�� ����");
		System.out.println();
		System.out.print("�󿵰� ���� : ");
		
		int n = scan.nextInt();
		if(n==1) {
			return famillyScreen;
		}else if(n==2) {
			return animationScreen;
		}else if(n==3) {
			return premiumScreen;
		}else if(n==9) {
			System.out.print("��ȣ �Է� : ");
			String passwd = scan.next();
			if(passwd.equals(ADMIN_PASSWORD)) {
				managerMode();
			}
			return null;
		}else {
			return null;
		}

	}

}
