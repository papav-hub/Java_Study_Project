package org.kpu.ticketbox.cinema;

import java.util.HashMap;
import java.util.Scanner;

import org.kpu.ticketbox.payment.*;
import org.kpu.ticketbox.*;

public abstract class Screen {
	
	Scanner scan = new Scanner(System.in);
	
	protected int nTicketPrice;
	protected int nRowMax;
	protected int nColMax;
	protected String strMovieName;
	protected String strMovieStory;
	protected MovieTicket[][] seatArray;
	public abstract void showMovieInfo();
	
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>(); // �߰���� 3
	
	private int nCurrentReservedid = 100; // �߰����1 // �����ȣ 100������ ����
	
	Screen(String name, String story, int price, int row, int col){ // ������
		strMovieName = name;
		strMovieStory = story;
		nTicketPrice = price;
		nRowMax = row;
		nColMax = col;	
		
		seatArray = new MovieTicket[nRowMax][nColMax];

		for(int i=0 ; i<nRowMax ; i++) {
			for(int j=0 ; j<nColMax ; j++) {
				seatArray[i][j] = new MovieTicket();
				seatArray[i][j].setSeat(i, j);
				seatArray[i][j].setcStatus(MovieTicket.SEAT_EMPTYMARK);
			}
		}
		
	}
	
	public void printTicket() { // �߰���� 4 // Ƽ�� ������ ���
		
		System.out.println(" [ �¼� Ƽ�� ��� ]");
		System.out.print("�¼� ���� ��ȣ �Է� : ");
		int check = scan.nextInt();
		
		Receipt r = receiptMap.get(check);
		r.toString();
	
	}
	
	public HashMap<Integer, Receipt> getReceiptMap() {
		return receiptMap;
	}

	public void payment() { // �����ϱ�
		System.out.println(" [ �¼� ���� ���� ]");
		System.out.print("�¼� ���� ��ȣ �Է� : ");
		int check = scan.nextInt();
		
		
		
		MovieTicket a = checkReservedid(check);
		
		if(a!=null) {		
			System.out.println("-------------");
			System.out.println("  ���� ��� ����  ");
			System.out.println("-------------");
			System.out.println("1. ���� ��ü");
			System.out.println("2. ī�� ����");
			System.out.println("3. ����� ����");
			System.out.print("���� ��� �Է�(1~3) : ");
			int method = scan.nextInt();
			
			Pay pay = null;
			
			switch(method) {
				case Pay.BANK_TRANSFER_PAYMENT:
					pay = new BankTransfer();
					System.out.println(" [ ���� ��ü ]");
					break;
				case Pay.CREDIT_CARD_PAYMENT:
					pay = new CardPay();
					System.out.println(" [ ī�� ���� ]");
					break;
				case Pay.MOBILE_PHONE_PAYMENT:
					pay = new MobilePay();
					System.out.println(" [ ����� ���� ]");
					break;				
			}
		
			int rrow = a.getnRow();
			int ccol = a.getnCol();			
			
			if(a.getcStatus()==MovieTicket.SEAT_RESERVED_MARK) {
				
				System.out.print("�̸� �Է� : ");
				String name = scan.next();
				System.out.print("�ڵ��� ��ȣ �Է�(11�ڸ���)");
				String number = scan.next();

				seatArray[rrow][ccol].setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
				Receipt r = pay.charge(strMovieName, (double)nTicketPrice, name, number);
				receiptMap.put(check,  r);
				System.out.println(r.getPayMethod() + "������ �Ϸ�Ǿ����ϴ�. : " + r.getTotalAmount() + "��");
			
			}

			
		}else {
			System.out.println("�������� �ʴ� �����ȣ�Դϴ�.");
		}
		
		
	}
	
	private MovieTicket checkReservedid(int id) { // �߰����2 // ���� ��ȣ�� Ƽ�� üũ�ϱ�
		for(int i=0 ; i<nRowMax ; i++) {
			for(int j=0 ; j<nColMax ; j++) {
				if(seatArray[i][j].getnReservedid()==id) {
					return seatArray[i][j];
				}
			}
		}
		return null;
	}
	
	public void changeTicket() { // �¼� ����
		System.out.println(" [ �¼� ���� ]");
		System.out.print("�¼� ���� ��ȣ �Է� : ");
		int check = scan.nextInt();
		
		MovieTicket a = checkReservedid(check);
		
		if(a!=null) {
		
			System.out.print("�¼� �� ��ȣ �Է� (1-" + nRowMax + ") : ");
			int rrow = scan.nextInt();
			rrow--;
			System.out.print("�¼� �� ��ȣ �Է� (1-" + nColMax + ") : ");
			int ccol = scan.nextInt();
			ccol--;
			
			if(seatArray[rrow][ccol].getcStatus()=='-') {
				seatArray[rrow][ccol].setnReservedid(check);
				seatArray[rrow][ccol].setcStatus(MovieTicket.SEAT_RESERVED_MARK);
				a.setnReservedid(0);
				a.setcStatus(MovieTicket.SEAT_EMPTYMARK);
				System.out.println("�����ȣ" + check +" ��[" + (rrow+1) + "] ��[" + (ccol+1) + "] �� �����Ǿ����ϴ�.");
			}else {
				System.out.println("�̹� ����Ǿ��� �¼��Դϴ�.");
			}
			
		}else {
			System.out.println("�������� �ʴ� �����ȣ�Դϴ�.");
		}
	}

	
	public void showScreenMenu() { // �󿵰� �޴� �����ֱ�
		System.out.println("-------------");
		System.out.println("��ȭ �޴� - " + strMovieName);
		System.out.println("-------------");
		System.out.println("1. �� ��ȭ ����");
		System.out.println("2. �¼� ���� ��Ȳ");
		System.out.println("3. �¼� ���� �ϱ�");
		System.out.println("4. �¼� ���� �ϱ�");
		System.out.println("5. �¼� ���� �ϱ�");
		System.out.println("6. Ƽ�� ��� �ϱ�");
		System.out.println("7. ���� �޴� �̸�");
	}
	
	public void showSeatMap() { // �󿵰� �¼� ���� ��Ȳ �����ֱ�
		System.out.println("\t[ �¼� ���� ��Ȳ ]");
		
		System.out.print("\t");
		for(int j=0 ; j<nColMax ; j++) {
			System.out.print("[" + (j+1) + "] ");
		}
		System.out.println();
		
		for(int i=0 ; i<nRowMax ; i++) {
			System.out.print("[" + (i+1) + "]" + "\t");
			for(int j=0 ; j<nColMax ; j++) {
				//System.out.println(i + " " + j);
				System.out.print("[" + seatArray[i][j].getcStatus() + "] ");
			}
			System.out.println();
		}
	}
	
	public void reserveTicket() { // �߰����1 // �¼� ����
		System.out.println(" [ �¼� ���� ]");
		System.out.print("�¼� �� ��ȣ �Է� (1-" + nRowMax + ") : ");
		int rrow = scan.nextInt();
		rrow--;
		System.out.print("�¼� �� ��ȣ �Է� (1-" + nColMax + ") : ");
		int ccol = scan.nextInt();
		ccol--;
		if(seatArray[rrow][ccol].getcStatus()=='-') {
			System.out.println("��[" + (rrow+1) + "] ��[" + (ccol+1) + "] " + nCurrentReservedid + " ���� ��ȣ�� �����Ǿ����ϴ�.");
			seatArray[rrow][ccol].setnReservedid(nCurrentReservedid);
			seatArray[rrow][ccol].setcStatus(MovieTicket.SEAT_RESERVED_MARK);
			nCurrentReservedid++;
		}else {
			System.out.println("�̹� ����Ǿ��� �¼��Դϴ�.");
		}		
	}
}