package org.kpu.ticketbox.cinema;

import org.kpu.ticketbox.payment.MovieTicket;

public class FamillyScreen extends Screen{
	public FamillyScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	@Override
	public void showMovieInfo() {
		System.out.println("-------------");
		System.out.println(strMovieName + " �Ұ�");
		System.out.println("-------------");
		System.out.println("��ȭ�� : ������ȭ 1��");
		System.out.println("�ٰŸ� : " + strMovieStory);
		System.out.println("���� : " + nTicketPrice);
	}
}
