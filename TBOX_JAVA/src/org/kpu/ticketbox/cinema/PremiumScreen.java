package org.kpu.ticketbox.cinema;

public class PremiumScreen extends Screen{
	public PremiumScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	@Override
	public void showMovieInfo() {
		System.out.println("-------------");
		System.out.println(strMovieName + " 소개");
		System.out.println("-------------");
		System.out.println("영화관 : 가족영화 1관");
		System.out.println("줄거리 : " + strMovieStory);
		System.out.println("가격 : " + nTicketPrice);
	}

}
