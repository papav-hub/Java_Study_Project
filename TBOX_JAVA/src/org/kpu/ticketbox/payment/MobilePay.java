package org.kpu.ticketbox.payment;

public class MobilePay implements Pay{
	
	public static final double MOBILE_COMMISION = 0.2;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {

		Receipt r = new Receipt();
		r.productName = product;
		r.payMethod = "MobilePay";
		r.subTotalAmount = amount;
		r.totalAmount = r.subTotalAmount + (r.subTotalAmount * MOBILE_COMMISION);
		r.client = name;
		r.payNumber = number;
		
		return r;
	}
}