package mall;

public class Item {

	private String itemName;
	private int itemPrice;
	private int itemQuantity;
	
	public Item(String in, int ip, int iq) {
		this.itemName = in;
		this.itemPrice = ip;
		this.itemQuantity = iq;
	}

	public String getItemName() {
		return itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
}
