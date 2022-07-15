package org.kpu.ticketbox.payment;

public class Receipt {
	
	String client;
	String productName;
	String payMethod;
	String payNumber;
	double subTotalAmount;
	double totalAmount;
	
	public String toBackupString() { // 추가기능 5 // 구분자(,)를 이용하여 한 줄에 출력		
		return client + "," + productName + "," + payMethod + "," + payNumber + "," + subTotalAmount + "," + totalAmount;
	}
	
	public String getPayMethod() {
		return payMethod;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	
	public String toString() { // 추가기능 4 // 티켓 결제 내용 출력
		System.out.println("-------------");
		System.out.println("recipt");
		System.out.println("-------------");
		
		System.out.println("[ Client : " + client + "]");
		System.out.println("[ product : " + productName + "]");
		System.out.println("[ PayMethod : " + payMethod + "]");
		System.out.println("[ PayNumber : " + payNumber + "]");
		System.out.println("[ SubTotal : " + subTotalAmount + "]");
		System.out.println("[ Totla : " + totalAmount + "]");
		
		return null;
	}

	
	
}
