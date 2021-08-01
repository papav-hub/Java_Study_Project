package mall;


import java.util.*;
import java.util.Scanner;


public class FoodMall implements Mall{
	
	private HashMap<String, Item> shoppingCart = new HashMap <String, Item>();

	
	public void displayItem() {
		System.out.println("-----");
		System.out.println("FOOD MALL");
		System.out.println("-----");
		System.out.println("1. 소고기 김밥  2000");
		System.out.println("2. 매운 신라면  3000");
		System.out.println("3. 새우 햄버그  4000");
		System.out.println("4. 해물 볶음밥  5000");
		System.out.println("5. 영양 갈비탕  6000");
	}
	public void buyItem() {
		Scanner scanner = new Scanner(System.in); // Scanner 객체 
		int n = 0;
		while (n!=9) {
			System.out.print("주문하세요. 종료(9) >>");
			n = scanner.nextInt();
			if(n==1) {
				System.out.print("개수는 >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("소고기 김밥", new Item("소고기 김밥", 2000, nn));
				System.out.println("소고기 김밥 주문 완료. 총 갯수 : " + nn);
			}else if(n==2) {
				System.out.print("개수는 >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("매운 신라면", new Item("매운 신라면", 3000, nn));
				System.out.println("매운 신라면 주문 완료. 총 갯수 : " + nn);
			}else if(n==3) {
				System.out.print("개수는 >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("새우 햄버그", new Item("새우 햄버그", 4000, nn));
				System.out.println("새우 햄버그 주문 완료. 총 갯수 : " + nn);
			}else if(n==4) {
				System.out.print("개수는 >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("해물 볶음밥", new Item("해물 볶음밥", 5000, nn));
				System.out.println("해물 볶음밥 주문 완료. 총 갯수 : " + nn);
			}else if(n==5) {
				System.out.print("개수는 >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("영양 갈비탕", new Item("영양 갈비탕", 6000, nn));
				System.out.println("영양 갈비탕 주문 완료. 총 갯수 : " + nn);
			}
		}
		if(n==9) {
			System.out.println("주문 종료합니다.");
		}

		scanner.close();
		
	}
	public void showReceipt() {
		
		Set <String> keys = shoppingCart.keySet();
		Iterator<String> it = keys.iterator();
		
		System.out.println("-----");
		System.out.println("RECEIPT");
		System.out.println("-----");
		
		int total = 0;
		int max = 0;
		String maxx = null;
		
		while(it.hasNext()) {
			String name = it.next();
			int itemprice = shoppingCart.get(name).getItemPrice();
			int itemq = shoppingCart.get(name).getItemQuantity();
			total += itemprice * itemq;
			
			if(itemprice * itemq > max) {
				max = itemprice * itemq;
				maxx = name;
			}
			
			System.out.println("[ " + name + ", " + itemprice + ", " + itemq + "개 ]");
			
		}
		
		System.out.println("총 구매 금액은 " + total + "입니다.");
		System.out.println("최고 구매 상품은 " + maxx + "(" + max + ")입니다.");
	}


}
