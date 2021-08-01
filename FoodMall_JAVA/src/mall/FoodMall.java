package mall;


import java.util.*;
import java.util.Scanner;


public class FoodMall implements Mall{
	
	private HashMap<String, Item> shoppingCart = new HashMap <String, Item>();

	
	public void displayItem() {
		System.out.println("-----");
		System.out.println("FOOD MALL");
		System.out.println("-----");
		System.out.println("1. �Ұ�� ���  2000");
		System.out.println("2. �ſ� �Ŷ��  3000");
		System.out.println("3. ���� �ܹ���  4000");
		System.out.println("4. �ع� ������  5000");
		System.out.println("5. ���� ������  6000");
	}
	public void buyItem() {
		Scanner scanner = new Scanner(System.in); // Scanner ��ü 
		int n = 0;
		while (n!=9) {
			System.out.print("�ֹ��ϼ���. ����(9) >>");
			n = scanner.nextInt();
			if(n==1) {
				System.out.print("������ >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("�Ұ�� ���", new Item("�Ұ�� ���", 2000, nn));
				System.out.println("�Ұ�� ��� �ֹ� �Ϸ�. �� ���� : " + nn);
			}else if(n==2) {
				System.out.print("������ >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("�ſ� �Ŷ��", new Item("�ſ� �Ŷ��", 3000, nn));
				System.out.println("�ſ� �Ŷ�� �ֹ� �Ϸ�. �� ���� : " + nn);
			}else if(n==3) {
				System.out.print("������ >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("���� �ܹ���", new Item("���� �ܹ���", 4000, nn));
				System.out.println("���� �ܹ��� �ֹ� �Ϸ�. �� ���� : " + nn);
			}else if(n==4) {
				System.out.print("������ >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("�ع� ������", new Item("�ع� ������", 5000, nn));
				System.out.println("�ع� ������ �ֹ� �Ϸ�. �� ���� : " + nn);
			}else if(n==5) {
				System.out.print("������ >> ");
				int nn = scanner.nextInt();
				shoppingCart.put("���� ������", new Item("���� ������", 6000, nn));
				System.out.println("���� ������ �ֹ� �Ϸ�. �� ���� : " + nn);
			}
		}
		if(n==9) {
			System.out.println("�ֹ� �����մϴ�.");
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
			
			System.out.println("[ " + name + ", " + itemprice + ", " + itemq + "�� ]");
			
		}
		
		System.out.println("�� ���� �ݾ��� " + total + "�Դϴ�.");
		System.out.println("�ְ� ���� ��ǰ�� " + maxx + "(" + max + ")�Դϴ�.");
	}


}
