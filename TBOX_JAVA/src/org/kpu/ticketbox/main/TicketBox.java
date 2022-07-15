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
		System.out.println("관리자 기능");
		System.out.println("-------------");
		
		double a;
		Screen s = famillyScreen;
		a = Statistics.sum(s.getReceiptMap());
		System.out.println("가족 영화관 결제 총액 : " + a);
		s = animationScreen;
		a = Statistics.sum(s.getReceiptMap());
		System.out.println("애니메이션 영화관 결제 총액 : " + a);
		s = premiumScreen;
		a = Statistics.sum(s.getReceiptMap());
		System.out.println("프리미엄 영화관 결제 총액 : " + a);
		
		
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
		System.out.println("영화관 총 티켓 판매량 : " + number/5);
		System.out.println("c:\\Temp\\tBoxReceipt.txt 백업 시작합니다.");
		System.out.println("가족 영화관 매출 백업 완료");
		System.out.println("애니메이션 영화관 매출 백업 완료");
		System.out.println("프리미엄 영화관 매출 백업 완료");
		
		
	}

	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	
	Scanner scan = new Scanner(System.in);
	
	public void initScreen() {
		famillyScreen = new FamillyScreen("찰리와 초콜릿 공장", "거대 초콜릿 공장을 견학하게 된 소년 찰리 버킷", 8000, 10, 10);
		animationScreen = new AnimationScreen("마녀 배달부 키키", "마녀 견습생 키키가 대도시에 정착", 10000, 10, 10);
		premiumScreen = new PremiumScreen("마션", "화성에 혼자 방치된 우주 비행사 마크 와트니의 생존을 위한 분투", 25000, 5, 5);
	}
	

	public Screen selectScreen() throws IOException {
		
		System.out.println("-------------");
		System.out.println("상영관 선택 메인메뉴");
		System.out.println("-------------");
		System.out.println("1. 가족 영화 1관");
		System.out.println("2. 애니메이션 영화 2관");
		System.out.println("3. 프리미엄 영화 3관 (커피, 케익 제공)");
		System.out.println("9. 관리자 메뉴");
		System.out.println("  선택(1~3, 9)외 종료");
		System.out.println();
		System.out.print("상영관 선택 : ");
		
		int n = scan.nextInt();
		if(n==1) {
			return famillyScreen;
		}else if(n==2) {
			return animationScreen;
		}else if(n==3) {
			return premiumScreen;
		}else if(n==9) {
			System.out.print("암호 입력 : ");
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
