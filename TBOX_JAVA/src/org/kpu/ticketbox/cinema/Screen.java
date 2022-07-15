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
	
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>(); // 추가기능 3
	
	private int nCurrentReservedid = 100; // 추가기능1 // 예약번호 100번부터 시작
	
	Screen(String name, String story, int price, int row, int col){ // 생성자
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
	
	public void printTicket() { // 추가기능 4 // 티켓 영수증 출력
		
		System.out.println(" [ 좌석 티켓 출력 ]");
		System.out.print("좌석 예약 번호 입력 : ");
		int check = scan.nextInt();
		
		Receipt r = receiptMap.get(check);
		r.toString();
	
	}
	
	public HashMap<Integer, Receipt> getReceiptMap() {
		return receiptMap;
	}

	public void payment() { // 결제하기
		System.out.println(" [ 좌석 예약 결제 ]");
		System.out.print("좌석 예약 번호 입력 : ");
		int check = scan.nextInt();
		
		
		
		MovieTicket a = checkReservedid(check);
		
		if(a!=null) {		
			System.out.println("-------------");
			System.out.println("  결제 방식 선택  ");
			System.out.println("-------------");
			System.out.println("1. 은행 이체");
			System.out.println("2. 카드 결제");
			System.out.println("3. 모바일 결제");
			System.out.print("결제 방식 입력(1~3) : ");
			int method = scan.nextInt();
			
			Pay pay = null;
			
			switch(method) {
				case Pay.BANK_TRANSFER_PAYMENT:
					pay = new BankTransfer();
					System.out.println(" [ 은행 이체 ]");
					break;
				case Pay.CREDIT_CARD_PAYMENT:
					pay = new CardPay();
					System.out.println(" [ 카드 결제 ]");
					break;
				case Pay.MOBILE_PHONE_PAYMENT:
					pay = new MobilePay();
					System.out.println(" [ 모바일 결제 ]");
					break;				
			}
		
			int rrow = a.getnRow();
			int ccol = a.getnCol();			
			
			if(a.getcStatus()==MovieTicket.SEAT_RESERVED_MARK) {
				
				System.out.print("이름 입력 : ");
				String name = scan.next();
				System.out.print("핸드폰 번호 입력(11자리수)");
				String number = scan.next();

				seatArray[rrow][ccol].setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
				Receipt r = pay.charge(strMovieName, (double)nTicketPrice, name, number);
				receiptMap.put(check,  r);
				System.out.println(r.getPayMethod() + "결제가 완료되었습니다. : " + r.getTotalAmount() + "원");
			
			}

			
		}else {
			System.out.println("존재하지 않는 예약번호입니다.");
		}
		
		
	}
	
	private MovieTicket checkReservedid(int id) { // 추가기능2 // 예약 번호로 티켓 체크하기
		for(int i=0 ; i<nRowMax ; i++) {
			for(int j=0 ; j<nColMax ; j++) {
				if(seatArray[i][j].getnReservedid()==id) {
					return seatArray[i][j];
				}
			}
		}
		return null;
	}
	
	public void changeTicket() { // 좌석 변경
		System.out.println(" [ 좌석 변경 ]");
		System.out.print("좌석 예약 번호 입력 : ");
		int check = scan.nextInt();
		
		MovieTicket a = checkReservedid(check);
		
		if(a!=null) {
		
			System.out.print("좌석 행 번호 입력 (1-" + nRowMax + ") : ");
			int rrow = scan.nextInt();
			rrow--;
			System.out.print("좌석 열 번호 입력 (1-" + nColMax + ") : ");
			int ccol = scan.nextInt();
			ccol--;
			
			if(seatArray[rrow][ccol].getcStatus()=='-') {
				seatArray[rrow][ccol].setnReservedid(check);
				seatArray[rrow][ccol].setcStatus(MovieTicket.SEAT_RESERVED_MARK);
				a.setnReservedid(0);
				a.setcStatus(MovieTicket.SEAT_EMPTYMARK);
				System.out.println("예약번호" + check +" 행[" + (rrow+1) + "] 열[" + (ccol+1) + "] 로 접수되었습니다.");
			}else {
				System.out.println("이미 예약되어진 좌석입니다.");
			}
			
		}else {
			System.out.println("존재하지 않는 예약번호입니다.");
		}
	}

	
	public void showScreenMenu() { // 상영관 메뉴 보여주기
		System.out.println("-------------");
		System.out.println("영화 메뉴 - " + strMovieName);
		System.out.println("-------------");
		System.out.println("1. 상영 영화 정보");
		System.out.println("2. 좌석 예약 현황");
		System.out.println("3. 좌석 예약 하기");
		System.out.println("4. 좌석 변경 하기");
		System.out.println("5. 좌석 결제 하기");
		System.out.println("6. 티켓 출력 하기");
		System.out.println("7. 메인 메뉴 이름");
	}
	
	public void showSeatMap() { // 상영관 좌석 예약 현황 보여주기
		System.out.println("\t[ 좌석 예약 현황 ]");
		
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
	
	public void reserveTicket() { // 추가기능1 // 좌석 예약
		System.out.println(" [ 좌석 예약 ]");
		System.out.print("좌석 행 번호 입력 (1-" + nRowMax + ") : ");
		int rrow = scan.nextInt();
		rrow--;
		System.out.print("좌석 열 번호 입력 (1-" + nColMax + ") : ");
		int ccol = scan.nextInt();
		ccol--;
		if(seatArray[rrow][ccol].getcStatus()=='-') {
			System.out.println("행[" + (rrow+1) + "] 열[" + (ccol+1) + "] " + nCurrentReservedid + " 예약 번호로 접수되었습니다.");
			seatArray[rrow][ccol].setnReservedid(nCurrentReservedid);
			seatArray[rrow][ccol].setcStatus(MovieTicket.SEAT_RESERVED_MARK);
			nCurrentReservedid++;
		}else {
			System.out.println("이미 예약되어진 좌석입니다.");
		}		
	}
}