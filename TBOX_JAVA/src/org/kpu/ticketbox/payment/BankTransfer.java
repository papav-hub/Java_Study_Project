package org.kpu.ticketbox.payment;

public class BankTransfer implements Pay{
	
	public static final double BANK_TRANSFER_COMMISION = 0.1;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		
		
		Receipt r = new Receipt();
		r.productName = product;
		r.payMethod = "BankTransfer";
		r.subTotalAmount = amount;
		r.totalAmount = r.subTotalAmount + (r.subTotalAmount * BANK_TRANSFER_COMMISION);
		r.client = name;
		r.payNumber = number;
		
		return r;
	}

}
