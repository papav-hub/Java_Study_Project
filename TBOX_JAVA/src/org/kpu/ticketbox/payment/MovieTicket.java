package org.kpu.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTYMARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	
	private int nRow; // �¼� ��
	private int nCol; // �¼� ��
	private char cStatus; // �¼� ���� - EMPTY, RESERVED, PAY_COMPLETION
	
	private int nReservedid; // �߰� ��� 1
	
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
	
	public void setSeat(int row, int col) { // �¼���ȣ ����
		nRow = row;
		nCol = col;
	}
	
	public void setnReservedid(int id) { // ���� ��ȣ ����
		nReservedid = id;		
	}
	
	public int getnReservedid() { // �����ȣ �б�
		return nReservedid;
	}

}
