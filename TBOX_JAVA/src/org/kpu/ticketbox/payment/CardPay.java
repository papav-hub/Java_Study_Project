package org.kpu.ticketbox.payment;

public class CardPay implements Pay{
	
	public static final double CARD_COMMISION = 0.15;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {


		Receipt r = new Receipt();
		r.productName = product;
		r.payMethod = "CardPay";
		r.subTotalAmount = amount;
		r.totalAmount = r.subTotalAmount + (r.subTotalAmount * CARD_COMMISION);
		r.client = name;
		r.payNumber = number;
		
		return r;
	}

}
