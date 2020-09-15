package entity;

import java.io.Serializable;

@SuppressWarnings("serial")

public class MenuItem implements Serializable{
	private String itemName, itemDescription;
	private double itemPrice;
	private int itemID;
	
	public MenuItem() {
		
	}
	
	public MenuItem(String itemName, String itemDescription, double itemPrice, int itemID) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
}
