package org.kpu.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTYMARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	
	private int nRow; // 좌석 행
	private int nCol; // 좌석 열
	private char cStatus; // 좌석 상태 - EMPTY, RESERVED, PAY_COMPLETION
	
	private int nReservedid; // 추가 기능 1
	
	public MovieTicket() {
		cStatus = SEAT_EMPTYMARK;
	}

	
	public int getnRow() {
		return nRow;
	}
	
	public int getnCol() {
		return nCol;
	}
	
	public char getcStatus() {
		return cStatus;
	}
	
	public void setcStatus(char cStatus) {
		this.cStatus = cStatus;
	}
	
	public void setSeat(int row, int col) { // 좌석번호 저장
		nRow = row;
		nCol = col;
	}
	
	public void setnReservedid(int id) { // 예약 번호 저장
		nReservedid = id;		
	}
	
	public int getnReservedid() { // 예약번호 읽기
		return nReservedid;
	}

}
