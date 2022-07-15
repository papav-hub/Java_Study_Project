package org.kpu.ticketbox.payment;

public class Receipt {
	
	String client;
	String productName;
	String payMethod;
	String payNumber;
	double subTotalAmount;
	double totalAmount;
	
	public String toBackupString() { // �߰���� 5 // ������(,)�� �̿��Ͽ� �� �ٿ� ���		
		return client + "," + productName + "," + payMethod + "," + payNumber + "," + subTotalAmount + "," + totalAmount;
	}
	
	public String getPayMethod() {
		return payMethod;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	
	public String toString() { // �߰���� 4 // Ƽ�� ���� ���� ���
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
