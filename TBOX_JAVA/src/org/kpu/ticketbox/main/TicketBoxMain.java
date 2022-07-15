package org.kpu.ticketbox.main;

import java.io.IOException;
import java.util.Scanner;

import org.kpu.ticketbox.cinema.Screen;

public class TicketBoxMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		boolean bMainMenu = true; // �󿵰� ���� �޴� ���
		ticketBox.initScreen(); // 3���� ��ũ�� ��ü�� ����
		
		while(true) {
			if(bMainMenu) {
				screen = ticketBox.selectScreen();
				if(screen==null) {
					System.out.println("�ȳ���������!");
					scan.close();
					System.exit(0);
				}
				bMainMenu = false;
			}
			screen.showScreenMenu();
			System.out.print("�޴��� �����ϼ��� >>");
			try {
				int select = scan.nextInt();
				switch(select) {
				case 1: // ��ũ�� �� ��ȭ ���� ����
					screen.showMovieInfo();
					break;
				case 2:
					screen.showSeatMap();
					break;
				case 3:
					screen.reserveTicket();
					break;
				case 4:
					screen.changeTicket();
					break;
				case 5:
					screen.payment();
					break;
				case 6:
					screen.printTicket();
					break;
				case 7:
					bMainMenu = true;
					break;					
				}
			}catch(NullPointerException e) {
				
			}
		}

	}

}
