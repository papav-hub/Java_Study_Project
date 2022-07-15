package org.kpu.ticketbox.util;

import java.util.*;


import org.kpu.ticketbox.payment.Receipt;

public class Statistics {
	
	public static double sum(HashMap<Integer, Receipt> map) {
		
		double TOTAL = 0;
		
		Set <Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator();
		
		while(it.hasNext()) {
			int a = it.next();
			TOTAL += map.get(a).getTotalAmount();
		}
		
		
		return TOTAL;
		
	}

}
